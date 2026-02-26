/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package riddleproject;
import javax.swing.*;

import java.awt.Color;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartGame extends javax.swing.JFrame implements GameTimer {
    private Random rand = new Random();
    private PlayerInfo player;
    private int questionCount = 8;
    private Timer timer;
    private int secondsPassed;
    private int level;
    private List<Riddle> level1Riddles;
    private List<Riddle> level2Riddles;
    private List<Riddle> level3Riddles;
    private List<Riddle> currentLevelRiddles;
    private List<Integer> shownIndexes = new ArrayList<>();
    private int currentRiddleIndex;
    private int correctAnswer;
    private int hintCoinAdded;
    private int hintUsed;

    public StartGame(PlayerInfo player) {
        this.player = player;
        initComponents();
        setTimer(90);
        gameOverPanel.setVisible(false);
        gamePausePanel.setVisible(false);
        levelPassedPanel.setVisible(false);
        answerFeedback.setText("");
        txtHintCoin.setText(String.valueOf(this.player.getHintCoin()));
        level = player.getLevel();
        initializeRiddles();
        loadRiddlesForLevel(player.getLevel());
        txtHint.setVisible(false);
        if(this.player.getHintCoin()==0){
            btnHint.setEnabled(false);
        }
    }


    private void initializeRiddles() {
        
        level1Riddles = new ArrayList<>();
        level1Riddles.add(new Riddle("What gets taller when it's young and shorter when it's old?", "candle", "Light"));
        level1Riddles.add(new Riddle("Always in you, sometimes on you; if I surround you, I can kill you. What am I?", "water", "Drinkable"));
        level1Riddles.add(new Riddle("David’s parents have three children- Snap, Crackle and…?", "david", "Identity"));
        level1Riddles.add(new Riddle("What follows you everywhere, mimicking your movements, but you can't touch it?", "shadow", "Mimic"));
        level1Riddles.add(new Riddle("I have keys, but no locks and space, and no rooms. You can enter, but you can’t go outside. What am I?", "keyboard", "board with keys"));
        level1Riddles.add(new Riddle("I turn once, what is out will not get in. I turn again, what is in will not get out. What am I?", "key", "Use to unlock doors"));
        level1Riddles.add(new Riddle("What's black and white and read all over?", "newspaper", "news in  paper"));
        level1Riddles.add(new Riddle("What comes once in a minute, twice in a moment, but never in a thousand years?", "m", "Letter"));

        level2Riddles = new ArrayList<>();
        level2Riddles.add(new Riddle("What tells you what to do every night and still gets scolded?", "alarm clock", "Loud and wakes you up"));
        level2Riddles.add(new Riddle("What walks on four legs in the morning, two in the afternoon, and three at night?", "human", "you man"));
        level2Riddles.add(new Riddle("Simon’s dad had four sons: March, April, and May. What is the name of the fourth son?", "simon", "Sequence"));
        level2Riddles.add(new Riddle("What letter is seen only in the middle of March and April?", "r", " Alphabet of the middle word"));
        level2Riddles.add(new Riddle("What belongs to you but is used by everyone else?", "name", "Identity"));
        level2Riddles.add(new Riddle("What's white when it's dirty and black when it's clean?", "blackboard", " Education"));
        level2Riddles.add(new Riddle("What can a prisoner say to save himself from punishment?", "nothing", "Consequence"));
        level2Riddles.add(new Riddle("The more you take, the more you leave behind. What am I?", "footsteps", "walk"));

        level3Riddles = new ArrayList<>();
        level3Riddles.add(new Riddle("What's wanted by the rich, known by the wise, needed by the poor, and shown by the kind?", "love", "Emotion"));
        level3Riddles.add(new Riddle("What do you call bears with no ears?", "b", "Letters"));
        level3Riddles.add(new Riddle("What has cities but no houses, forests but no trees, and rivers but no water?", "map", "Navigation"));
        level3Riddles.add(new Riddle("What's full of keys but can't open any doors?", "piano", "Musical Instrument"));
        level3Riddles.add(new Riddle("Who has married many women but has never married?", "priest", "Performs sacred rituals"));
        level3Riddles.add(new Riddle("What has a head and a tail but no legs?", "coin", "Currency"));
        level3Riddles.add(new Riddle("What can't be kept until it's given?", "promise", "Commitment"));
        level3Riddles.add(new Riddle("What has no life but can still die?", "battery", "Power"));
    }


    private void loadRiddlesForLevel(int level) {
        switch (level) {
            case 1:
                currentLevelRiddles = level1Riddles;
                break;
            case 2:
                currentLevelRiddles = level2Riddles;
                break;
            case 3:
                currentLevelRiddles = level3Riddles;
                break;
            default:
                currentLevelRiddles = new ArrayList<>(); 
                break;
        }
        questionCount--;
        currentRiddleIndex = rand.nextInt(questionCount);
        displayCurrentRiddle();
    }

    private void displayCurrentRiddle() {
        int numRiddles = currentLevelRiddles.size();
        if (shownIndexes.size() == numRiddles) {
            return;
    }

    int randomIndex;
    do {
        randomIndex = rand.nextInt(numRiddles);
    } while (shownIndexes.contains(randomIndex));

    currentRiddleIndex = randomIndex;
    Riddle riddle = currentLevelRiddles.get(currentRiddleIndex);
    txtRiddleQuestion.setText("<html><center>"+ riddle.getQuestion() + "</center></html>");
    }
    

    @Override
    public void startTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                secondsPassed--;
                if(secondsPassed==(90-3)){
                        answerFeedback.setText("");
                    }
                
                if (secondsPassed < 0) {
                    stopTimer();
                } else {
                    int minutes = secondsPassed / 60;
                    int seconds = secondsPassed % 60;
                    String time = String.format("%02d:%02d", minutes, seconds);
                    if(time.equals("00:00")){
                        stopTimer();
                        gameOverPanel.setVisible(true);
                    }
                    updateTimerLabel(time);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000); // Update every second
    }

    @Override
    public void stopTimer(){
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void resetTimer(){
        secondsPassed = 0;
        updateTimerLabel("00:00");
    }

    @Override
    public void setTimer(int seconds) {    
        this.secondsPassed = seconds;
        int minutes = secondsPassed / 60;
        int remainingSeconds = secondsPassed % 60;
        String time = String.format("%02d:%02d", minutes, remainingSeconds);
        updateTimerLabel(time);
        startTimer();
    }

    private void updateTimerLabel(String time) {
        txtTimer.setText(time);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        gameOverPanel = new javax.swing.JPanel();
        btnToMain = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        levelPassedPanel = new javax.swing.JPanel();
        btnBackToMain1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        gamePausePanel = new javax.swing.JPanel();
        btnBackToGame = new javax.swing.JButton();
        btnBackToMain = new javax.swing.JButton();
        lblGamePaused = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnHint = new javax.swing.JButton();
        txtHint = new javax.swing.JLabel();
        btnPause = new javax.swing.JButton();
        answerFeedback = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTimer = new javax.swing.JLabel();
        lblHintCoin = new javax.swing.JLabel();
        txtHintCoin = new javax.swing.JLabel();
        txtRiddleQuestion = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        imgBoard = new javax.swing.JLabel();
        btnEnter = new javax.swing.JButton();
        txtAnswer = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GuessTheRiddle - Game");

        jPanel1.setMaximumSize(new java.awt.Dimension(750, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        gameOverPanel.setBackground(new java.awt.Color(153, 0, 0));
        gameOverPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnToMain.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnToMain.setText("Back to Main");
        btnToMain.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), new java.awt.Color(0, 255, 0)));
        btnToMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToMainActionPerformed(evt);
            }
        });
        gameOverPanel.add(btnToMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 120, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/gameOver.jpg"))); // NOI18N
        gameOverPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 490, 290));

        jPanel1.add(gameOverPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 490, 310));

        levelPassedPanel.setBackground(new java.awt.Color(153, 255, 153));
        levelPassedPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBackToMain1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBackToMain1.setText("Back To Main");
        btnBackToMain1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(255, 255, 255), new java.awt.Color(51, 255, 51)));
        btnBackToMain1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToMain1ActionPerformed(evt);
            }
        });
        levelPassedPanel.add(btnBackToMain1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 150, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("YOU HAVE PASSED THE LEVEL !");
        levelPassedPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 382, 63));

        jLabel7.setFont(new java.awt.Font("Stencil", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 204, 0));
        jLabel7.setText("CONGATULATIONS!");
        levelPassedPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 350, 50));

        jLabel8.setBackground(new java.awt.Color(102, 102, 102));
        jLabel8.setOpaque(true);
        levelPassedPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 450, 280));

        jPanel1.add(levelPassedPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 450, 300));

        gamePausePanel.setBackground(new java.awt.Color(255, 255, 153));
        gamePausePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBackToGame.setBackground(new java.awt.Color(204, 204, 204));
        btnBackToGame.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBackToGame.setText("Resume");
        btnBackToGame.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(255, 255, 51)));
        btnBackToGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToGameActionPerformed(evt);
            }
        });
        gamePausePanel.add(btnBackToGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 100, 40));

        btnBackToMain.setBackground(new java.awt.Color(204, 204, 204));
        btnBackToMain.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBackToMain.setText("Quit Game");
        btnBackToMain.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 102, 102), new java.awt.Color(0, 0, 0)));
        btnBackToMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToMainActionPerformed(evt);
            }
        });
        gamePausePanel.add(btnBackToMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 100, 40));

        lblGamePaused.setFont(new java.awt.Font("Stencil", 1, 36)); // NOI18N
        lblGamePaused.setForeground(new java.awt.Color(255, 255, 204));
        lblGamePaused.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGamePaused.setText("GAME PAUSED");
        gamePausePanel.add(lblGamePaused, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 280, 86));

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setOpaque(true);
        gamePausePanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 490, 290));

        jPanel1.add(gamePausePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 490, 310));

        btnHint.setBackground(new java.awt.Color(255, 255, 204));
        btnHint.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHint.setText("Hint");
        btnHint.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnHint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHintActionPerformed(evt);
            }
        });
        jPanel1.add(btnHint, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 80, 40));

        txtHint.setBackground(new java.awt.Color(255, 255, 204));
        txtHint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHint.setText("Hint");
        txtHint.setOpaque(true);
        jPanel1.add(txtHint, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 280, 40));

        btnPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/pause.png"))); // NOI18N
        btnPause.setContentAreaFilled(false);
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });
        jPanel1.add(btnPause, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 50, 50));

        answerFeedback.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        answerFeedback.setForeground(new java.awt.Color(255, 102, 102));
        answerFeedback.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        answerFeedback.setText("feedback");
        jPanel1.add(answerFeedback, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 300, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/timer.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, -1));

        txtTimer.setBackground(new java.awt.Color(51, 51, 51));
        txtTimer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTimer.setForeground(new java.awt.Color(255, 255, 255));
        txtTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTimer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        txtTimer.setOpaque(true);
        jPanel1.add(txtTimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 100, 30));

        lblHintCoin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/hintCoin.png"))); // NOI18N
        lblHintCoin.setText("jLabel2");
        jPanel1.add(lblHintCoin, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 50, -1));

        txtHintCoin.setBackground(new java.awt.Color(51, 51, 51));
        txtHintCoin.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        txtHintCoin.setForeground(new java.awt.Color(255, 255, 255));
        txtHintCoin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHintCoin.setText("0");
        txtHintCoin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        txtHintCoin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        txtHintCoin.setOpaque(true);
        jPanel1.add(txtHintCoin, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 70, 30));

        txtRiddleQuestion.setFont(new java.awt.Font("Kristen ITC", 1, 18)); // NOI18N
        txtRiddleQuestion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRiddleQuestion.setText("<html><center>You have completed this level<br>You are already a Grand Riddle Solver!</center></html>");
        txtRiddleQuestion.setToolTipText("");
        txtRiddleQuestion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(txtRiddleQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 300, 180));

        jPanel2.setOpaque(false);

        imgBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/answerCon (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 480, 230));

        btnEnter.setBackground(new java.awt.Color(204, 255, 204));
        btnEnter.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEnter.setText("ENTER");
        btnEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterActionPerformed(evt);
            }
        });
        jPanel1.add(btnEnter, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, 230, 50));

        txtAnswer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtAnswer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnswerActionPerformed(evt);
            }
        });
        jPanel1.add(txtAnswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 210, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/startgamebh (1).png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, -1));

        jLabel4.setText("jLabel4");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnswerActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtAnswerActionPerformed

    private void btnEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterActionPerformed
        // TODO add your handling code here:
        String userAnswer = txtAnswer.getText().trim();
        Riddle currentRiddle = currentLevelRiddles.get(currentRiddleIndex);

        if (userAnswer.equalsIgnoreCase(currentRiddle.getAnswer())) {
            correctAnswer++;
            
            if (secondsPassed >= 70) {
                this.player.setHintCoin(1);
                hintCoinAdded++;
            }

            if (correctAnswer == 8) {
               
                stopTimer();
                level++;
                player.setLevel(level);
                levelPassedPanel.setVisible(true);
                return;
            }

            
            shownIndexes.add(currentRiddleIndex);
            answerFeedback.setForeground(Color.GREEN);
            answerFeedback.setText("Nice!");
            stopTimer();
            setTimer(90); 
            txtHint.setVisible(false);
            
            displayCurrentRiddle();
        } else {
            answerFeedback.setForeground(Color.RED);
            answerFeedback.setText("Wrong!");
        }
        
        if(this.player.getHintCoin()>0){
            btnHint.setEnabled(true);
        }
        hintUsed=0;
        txtHintCoin.setText(String.valueOf(player.getHintCoin()));
        txtAnswer.setText("");
    }//GEN-LAST:event_btnEnterActionPerformed

    private void btnHintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHintActionPerformed
        // TODO add your handling code here:  
        if(hintUsed<1){
            if (currentRiddleIndex >= 0 && currentRiddleIndex < currentLevelRiddles.size()) {
                Riddle currentRiddle = currentLevelRiddles.get(currentRiddleIndex);
                this.player.setHintCoin(-1);
                txtHint.setVisible(true);
                txtHint.setText("Hint: " + currentRiddle.getHint());
                txtHintCoin.setText(String.valueOf(player.getHintCoin()));
                this.hintCoinAdded--;
                this.hintUsed++;
            }
        }
    }//GEN-LAST:event_btnHintActionPerformed

    private void btnToMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToMainActionPerformed
        // TODO add your handling code here:
        RiddleGameMain rg = null;
        try {
            rg = new RiddleGameMain(this.player);
        } catch (SQLException ex) {
            Logger.getLogger(StartGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        rg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnToMainActionPerformed

    private void btnBackToMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToMainActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(rootPane,"If you give up, your progress will not be saved", "WARNING MESSAGE", JOptionPane.YES_NO_OPTION);
        if(option==0){
            this.player.setHintCoin(-(hintCoinAdded));
            RiddleGameMain rg = null;
            try {
                rg = new RiddleGameMain(this.player);
            } catch (SQLException ex) {
                Logger.getLogger(StartGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            rg.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnBackToMainActionPerformed

    private void btnBackToGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToGameActionPerformed
        // TODO add your handling code here:
        gamePausePanel.setVisible(false);
        setTimer(secondsPassed);
    }//GEN-LAST:event_btnBackToGameActionPerformed

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        // TODO add your handling code here:
        stopTimer();
        gamePausePanel.setVisible(true);
        
    }//GEN-LAST:event_btnPauseActionPerformed

    private void btnBackToMain1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToMain1ActionPerformed
        // TODO add your handling code here:
        this.player.setLevel(this.level);
        RiddleGameMain rg = null;
        try {
            rg = new RiddleGameMain(player);
        } catch (SQLException ex) {
            Logger.getLogger(StartGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        rg.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btnBackToMain1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PlayerInfo player = new PlayerInfo();
                new StartGame(player).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel answerFeedback;
    private javax.swing.JButton btnBackToGame;
    private javax.swing.JButton btnBackToMain;
    private javax.swing.JButton btnBackToMain1;
    private javax.swing.JButton btnEnter;
    private javax.swing.JButton btnHint;
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnToMain;
    private javax.swing.JPanel gameOverPanel;
    private javax.swing.JPanel gamePausePanel;
    private javax.swing.JLabel imgBoard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblGamePaused;
    private javax.swing.JLabel lblHintCoin;
    private javax.swing.JPanel levelPassedPanel;
    private javax.swing.JTextField txtAnswer;
    private javax.swing.JLabel txtHint;
    private javax.swing.JLabel txtHintCoin;
    private javax.swing.JLabel txtRiddleQuestion;
    private javax.swing.JLabel txtTimer;
    // End of variables declaration//GEN-END:variables
}
