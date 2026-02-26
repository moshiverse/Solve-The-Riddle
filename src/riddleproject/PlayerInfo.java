/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package riddleproject;

/**
 *
 * @author Jamisola
 */
public class PlayerInfo implements HintCoin{
    private String name;
    private int hintCoin;
    private int level;
    
    public PlayerInfo(){
        this.name=name;
        this.hintCoin=hintCoin;
        this.level=level;
    }
    
    public PlayerInfo(String name, int hintCoin, int level){
        this.name=name;
        this.hintCoin=hintCoin;
        this.level=level;
    }


    @Override
    public void setHintCoin(int hintCoin){
        this.hintCoin+=hintCoin;
    }
    
     public int getHintCoin(){
        return hintCoin;
    }

    // Getter and setter for 'name'
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for 'level'
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if(level>=3){
            this.level = 3;
        }
        this.level = level;
    }
    
    /*
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points+=points;
    }
*/
}
