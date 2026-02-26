/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package riddleproject;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class RiddleGameMain extends javax.swing.JFrame{
    private int currentStoryIndex = 0;
    private int count = 0;
    private String[][][] stories = {
        {{"Finally, after overcoming many trials, the adventurer stands before the Riddle "
            + "Master, a wise and enigmatic figure in mystery. "
            + "The Riddle Master presents a series of mind-bending riddles, "
            + "each more perplexing than the last."}, {"Story 1 - Part 2"}}, 
        // First set of stories
        {{"With determination and quick thinking, the adventurer solves the riddles, "
            + "earning the respect of the Riddle Master and the title of the Grand "
            + "Riddle Solver. As the new champion, they are bestowed with ancient knowledge "
            + "and become a legend in their own right, inspiring others to test their minds "
            + "and unravel the mysteries of Enigmaria.\""}, {"Story 2 - Part 2"}}  
        // Second set of stories
    };
    private final String[] nextLabels = {"Continue...", "Continue..."};
    private PlayerInfo player;
    private DecimalFormat df = new DecimalFormat("#,##0");
    Connection con; 
    Statement st; 

    public RiddleGameMain(PlayerInfo player) throws SQLException {
        initComponents();     
        this.player = player;
        profilePanel.setVisible(false);
        endPanel.setVisible(false);
        if(player.getLevel()<=3){
            txtLevel.setText("Level " + String.valueOf(player.getLevel()));
        }else{
            txtLevel.setText("Grand Riddle Solver");
            txtLevel.setForeground(Color.YELLOW);
        }
        txtCoin.setText(df.format(player.getHintCoin()));
        txtPlayerNm.setText(player.getName());
        if (player.getLevel()==4){
                endPanel.setVisible(true);
            }
        
        if(player.getLevel()==1){
            btnWorld1.setEnabled(true);
            btnWorld2.setEnabled(false);
            btnWorld3.setEnabled(false);
        }else if(player.getLevel()==2){
            btnWorld1.setEnabled(true);
            btnWorld2.setEnabled(true);
            btnWorld3.setEnabled(false);
        }else if (player.getLevel()>=3){
            btnWorld1.setEnabled(true);
            btnWorld2.setEnabled(true);
            btnWorld3.setEnabled(true);
        }
        updateUserData(player.getName());
        
        if(player.getLevel()==4){
        setStory(); // Initialize with the first story
        imgRiddler.setVisible(true);
        imgMC.setVisible(false);
        lblNext.setText(nextLabels[0]); // Set the text of the "Next" button
        lblNext.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Update the index to navigate to the next story
            currentStoryIndex++;
            count++;
            
            if (currentStoryIndex >= stories.length) {
                    endPanel.setVisible(false);
                    return;
                }

            // Check if the current story index exceeds the length of stories
            if (currentStoryIndex >= stories.length) {
                // Reset the story index and navigate to the first story
                currentStoryIndex = 0;
            }

            setStory(); // Update the story text
            updateNextLabel(); // Update the label of the "Next" button
            if(count == 1){
                imgRiddler.setVisible(false);
                imgMC.setVisible(true);
            }
            if(count == 2){
                storyPanel.setVisible(false);
                imgMC.setVisible(false);
            }
        }
        });
        }
    }

    public void setStory(){
        // Construct the story text based on the current story index
        StringBuilder storyBuilder = new StringBuilder("<html>");
        for (String part : stories[currentStoryIndex][0]) {
            storyBuilder.append(part).append("<br>");
        }
        storyBuilder.append("</html>");

        // Set the story text to lblText
        lblText.setText(storyBuilder.toString());
    }

    public void updateNextLabel() {
        // Set the text of lblNext to the corresponding label from nextLabels array
        if (currentStoryIndex < nextLabels.length) {
            lblNext.setText(nextLabels[currentStoryIndex]);
        }
    }

    
    private void updateUserData(String name) throws SQLException{
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/riddlegamedata", "root", "root");
            String sql = "UPDATE playerdata SET hintcoin='" + this.player.getHintCoin() + "', level = '" + this.player.getLevel() + "' WHERE name='" + name + "'";        
            st = con.createStatement();
            st.execute(sql);
        }catch(SQLException ex){
            
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profilePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNameProfile = new javax.swing.JLabel();
        txtLevelProfile = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        endPanel = new javax.swing.JPanel();
        storyPanel = new javax.swing.JPanel();
        lblText = new javax.swing.JLabel();
        lblNext = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        lblBorderText = new javax.swing.JLabel();
        imgRiddler = new javax.swing.JLabel();
        imgMC = new javax.swing.JLabel();
        imgBG = new javax.swing.JLabel();
        btnProfile = new javax.swing.JButton();
        lblHintCoin = new javax.swing.JLabel();
        txtCoin = new javax.swing.JLabel();
        txtPlayerNm = new javax.swing.JLabel();
        btnViewStoryLine = new javax.swing.JButton();
        txtLevel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnWorld1 = new javax.swing.JButton();
        btnWorld2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnWorld3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        btnReturn = new javax.swing.JButton();
        img_bg = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Guess The Riddle - Main Dashboard");
        setMinimumSize(new java.awt.Dimension(750, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profilePanel.setBackground(new java.awt.Color(153, 255, 153));
        profilePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/profile.png"))); // NOI18N
        profilePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 61, -1, -1));

        txtNameProfile.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtNameProfile.setForeground(new java.awt.Color(255, 255, 255));
        txtNameProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNameProfile.setText("PlayeName");
        profilePanel.add(txtNameProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 122, 306, -1));

        txtLevelProfile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLevelProfile.setForeground(new java.awt.Color(255, 255, 255));
        txtLevelProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtLevelProfile.setText("level");
        profilePanel.add(txtLevelProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 160, 157, -1));

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/delete.png"))); // NOI18N
        btnDelete.setContentAreaFilled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        profilePanel.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 235, 45, 40));

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/back.png"))); // NOI18N
        btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        profilePanel.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 61, 44));

        jLabel13.setBackground(new java.awt.Color(102, 102, 102));
        jLabel13.setOpaque(true);
        profilePanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 340, 290));

        getContentPane().add(profilePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 90, 340, 310));

        endPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        storyPanel.setMaximumSize(new java.awt.Dimension(750, 500));
        storyPanel.setMinimumSize(new java.awt.Dimension(750, 500));
        storyPanel.setOpaque(false);
        storyPanel.setPreferredSize(new java.awt.Dimension(750, 500));
        storyPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblText.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        lblText.setForeground(new java.awt.Color(255, 255, 255));
        lblText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        storyPanel.add(lblText, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 480, 100));

        lblNext.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNext.setForeground(new java.awt.Color(153, 153, 153));
        lblNext.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblNext.setToolTipText("");
        storyPanel.add(lblNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 450, 120, 20));

        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Riddler_imgs/txtBackground.jpg"))); // NOI18N
        btnNext.setAlignmentY(0.0F);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        storyPanel.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 440, 130, 30));

        lblBorderText.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Riddler_imgs/txtBackground.jpg"))); // NOI18N
        lblBorderText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 4, true));
        storyPanel.add(lblBorderText, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 520, 140));

        endPanel.add(storyPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 500));

        imgRiddler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Riddler_imgs/SampleRiddler.png"))); // NOI18N
        endPanel.add(imgRiddler, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 260, 250));

        imgMC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Riddler_imgs/file.png"))); // NOI18N
        endPanel.add(imgMC, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 260, 250));

        imgBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Riddler_imgs/MysticLand_BG.jpg"))); // NOI18N
        imgBG.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgBG.setMaximumSize(new java.awt.Dimension(750, 500));
        imgBG.setMinimumSize(new java.awt.Dimension(750, 500));
        endPanel.add(imgBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 500));

        getContentPane().add(endPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 500));

        btnProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/profile.png"))); // NOI18N
        btnProfile.setContentAreaFilled(false);
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });
        getContentPane().add(btnProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, 60));

        lblHintCoin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/hintCoin.png"))); // NOI18N
        lblHintCoin.setText("jLabel2");
        getContentPane().add(lblHintCoin, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 50, -1));

        txtCoin.setBackground(new java.awt.Color(51, 51, 51));
        txtCoin.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        txtCoin.setForeground(new java.awt.Color(255, 255, 255));
        txtCoin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCoin.setText("0");
        txtCoin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        txtCoin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        txtCoin.setOpaque(true);
        getContentPane().add(txtCoin, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 70, 30));

        txtPlayerNm.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        txtPlayerNm.setForeground(new java.awt.Color(255, 255, 255));
        txtPlayerNm.setText("Player Name");
        getContentPane().add(txtPlayerNm, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 280, -1));

        btnViewStoryLine.setBackground(new java.awt.Color(51, 51, 51));
        btnViewStoryLine.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnViewStoryLine.setForeground(new java.awt.Color(255, 255, 255));
        btnViewStoryLine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/play.png"))); // NOI18N
        btnViewStoryLine.setText("VIEW STORYLINE");
        btnViewStoryLine.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, new java.awt.Color(0, 204, 0)));
        btnViewStoryLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewStoryLineActionPerformed(evt);
            }
        });
        getContentPane().add(btnViewStoryLine, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 430, 160, 40));

        txtLevel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtLevel.setForeground(new java.awt.Color(204, 204, 204));
        txtLevel.setText("Level");
        getContentPane().add(txtLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 270, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/lvl1 (1).png"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 60, 60));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/lvl1 (1).png"))); // NOI18N
        jLabel4.setText("jLabel3");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 60, 60));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/lvl1 (1).png"))); // NOI18N
        jLabel5.setText("jLabel3");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 60, 60));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/lvl1 (1).png"))); // NOI18N
        jLabel6.setText("jLabel3");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 60, 60));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/lvl1 (1).png"))); // NOI18N
        jLabel8.setText("jLabel3");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 60, 60));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/lvl2 (1).png"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 60, 60));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/lvl2 (1).png"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 60, 60));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/lvl2 (1).png"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 60, 60));

        btnWorld1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/imagelevel.gif"))); // NOI18N
        btnWorld1.setContentAreaFilled(false);
        btnWorld1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWorld1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnWorld1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 180, 190));

        btnWorld2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/imagelevel.gif"))); // NOI18N
        btnWorld2.setContentAreaFilled(false);
        btnWorld2.setEnabled(false);
        btnWorld2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWorld2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnWorld2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, -1));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/lvl2 (1).png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 60, 60));

        btnWorld3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/imagelevel.gif"))); // NOI18N
        btnWorld3.setContentAreaFilled(false);
        btnWorld3.setEnabled(false);
        btnWorld3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWorld3ActionPerformed(evt);
            }
        });
        getContentPane().add(btnWorld3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, -1, -1));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/lvl2 (1).png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 60, 60));

        btnReturn.setBackground(new java.awt.Color(0, 0, 0));
        btnReturn.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        btnReturn.setForeground(new java.awt.Color(255, 255, 255));
        btnReturn.setText("RETURN");
        btnReturn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });
        getContentPane().add(btnReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 120, 30));

        img_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_game/mainbg (2).jpg"))); // NOI18N
        getContentPane().add(img_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, -1));

        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:  
        PlayerPoint point = new PlayerPoint();
        point.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnReturnActionPerformed

    private void btnWorld2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWorld2ActionPerformed
        // TODO add your handling code here:
        StartGame start = new StartGame(player);
        start.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnWorld2ActionPerformed

    private void btnWorld1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWorld1ActionPerformed
        // TODO add your handling code here:
        StartGame start = new StartGame(player);
        start.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnWorld1ActionPerformed

    private void btnWorld3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWorld3ActionPerformed
        // TODO add your handling code here:
        StartGame start = new StartGame(player);
        start.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnWorld3ActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        // TODO add your handling code here:
        if(player.getLevel()<=3){
            txtLevelProfile.setText("Level " + String.valueOf(player.getLevel()));
        }else{
            txtLevelProfile.setText("Level MAX");
        }
        txtNameProfile.setText(player.getName());
        profilePanel.setVisible(true);
    }//GEN-LAST:event_btnProfileActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(rootPane,"Do you want to delete your account?", "WARNING MESSAGE", JOptionPane.YES_NO_OPTION);
        if(option==0){
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/riddlegamedata", "root", "root");
                String sql = "DELETE FROM playerdata WHERE name = '" + player.getName() + "'";
                st = con.createStatement();
                st.execute(sql);
                
                PlayerPoint pp = new PlayerPoint();
                pp.setVisible(true);
                this.dispose();   
                
            } catch (SQLException ex) {
               java.util.logging.Logger.getLogger(PlayerPoint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        profilePanel.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnViewStoryLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewStoryLineActionPerformed
        // TODO add your handling code here:
        RiddlerStart rs = new RiddlerStart();
        rs.setVisible(true);
    }//GEN-LAST:event_btnViewStoryLineActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextActionPerformed

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
            java.util.logging.Logger.getLogger(RiddleGameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RiddleGameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RiddleGameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RiddleGameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PlayerInfo player = new PlayerInfo();
                try {
                    new RiddleGameMain(player).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(RiddleGameMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnViewStoryLine;
    private javax.swing.JButton btnWorld1;
    private javax.swing.JButton btnWorld2;
    private javax.swing.JButton btnWorld3;
    private javax.swing.JPanel endPanel;
    private javax.swing.JLabel imgBG;
    private javax.swing.JLabel imgMC;
    private javax.swing.JLabel imgRiddler;
    private javax.swing.JLabel img_bg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblBorderText;
    private javax.swing.JLabel lblHintCoin;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblText;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JPanel storyPanel;
    private javax.swing.JLabel txtCoin;
    private javax.swing.JLabel txtLevel;
    private javax.swing.JLabel txtLevelProfile;
    private javax.swing.JLabel txtNameProfile;
    private javax.swing.JLabel txtPlayerNm;
    // End of variables declaration//GEN-END:variables
}
