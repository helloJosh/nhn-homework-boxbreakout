package com.nhnacademy;

import java.awt.Color;

public class MovableWorld extends World{
    
    public static final int DEFAULT_MAX_MOVE_COUNT = 0;
    private int moveCount;
    private int maxMoveCount = DEFAULT_MAX_MOVE_COUNT;
    private int dt;
    private int comboCount=0;
    private int totalScore =0;
    private int MaxSore = 35;
    private int userHp = 20;


    public int getMaxSore() {
        return MaxSore;
    }

    public void reset(){
        this.moveCount = 0;
    }

    public void gameInfo(){
        this.addStringList(new PaintableString(Main.FRAME_WIDTH/2, 350, "Your Score : "+this.getTotalScore(), Color.GRAY));
        this.addStringList(new PaintableString(Main.FRAME_WIDTH/2, 400, "Your HP : "+this.getUserHp(), Color.GRAY));
    }
    public void move(){
        if(this.getMaxMoveCount() == 0 || this.getMoveCount() < this.getMaxMoveCount()){
            for(int i=0; i<getCount();i++){
                Regional object = regionalList.get(i);
                if(object instanceof Movable){
                    ((Movable)object).move();

                    if(object instanceof Bounded){

                    for(int j=0;j<getCount();j++){
                            Regional otherObject = get(j);
                            if(object != otherObject && object.isCollision(otherObject)){
                                ((Bounded)object).bounce(otherObject);
                                if(otherObject instanceof PaintableBox && otherObject == regionalList.get(4)){
                                    this.setUserHp(this.getUserHp()-1);
                                    stringList.get(1).setStatement("Your HP : "+this.getUserHp());
                                    logger.trace("user hp {}", this.getUserHp());
                                }
                                if(otherObject instanceof PaintableBrickBox){
                                    if(((PaintableBrickBox)otherObject).getHp() == 0){   
                                        regionalList.remove(otherObject);
                                        this.setTotalScore(this.getTotalScore()+ ((PaintableBrickBox)otherObject).getScore());
                                        stringList.get(0).setStatement("Your Score : "+this.getTotalScore());
                                    }
                                    else
                                        ((PaintableBrickBox)otherObject).setHp(((PaintableBrickBox)otherObject).getHp()-1);
                                }
                                if(regionalList.size()<=6){
                                    regionalList.clear();
                                    stringList.clear();
                                    this.addStringList(new PaintableString(Main.FRAME_WIDTH/2, 350, "You Win", Color.BLACK));
                                    this.addStringList(new PaintableString(Main.FRAME_WIDTH/2, 400, "Your Score : "+this.getTotalScore(), Color.BLACK));
                                }
                                if(this.getUserHp() <= 0){
                                    stringList.clear();
                                    this.addStringList(new PaintableString(Main.FRAME_WIDTH/2, 350, "You Lose", Color.BLACK));
                                    regionalList.clear();
                                }
                            }
                        }
                    }
                }
            }
            moveCount++;
            repaint();
        }
    }
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            move();
            try{
                Thread.sleep(this.getDT());
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
    public int getMoveCount(){
        return this.moveCount;
    }
    public int getMaxMoveCount(){
        return this.maxMoveCount;
    }
    public void setMaxMoveCount(int count){
        if(count < 0)
            throw new IllegalArgumentException();
        this.maxMoveCount = count;
    }
    public void setDT(int dt){
        if(dt<0){
            throw new IllegalArgumentException();
        }
        this.dt = dt;
    }
    public int getDT(){
        return this.dt;
    }public int getComboCount() {
        return comboCount;
    }
    public void setComboCount(int comboCount) {
        this.comboCount = comboCount;
    }
    public int getTotalScore() {
        return totalScore;
    }
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getUserHp() {
        return userHp;
    }

    public void setUserHp(int userHp) {
        this.userHp = userHp;
    }
    

}
