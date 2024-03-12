package com.nhnacademy;

import java.awt.Color;

public class MovableWorld extends World{
    
    public static final int DEFAULT_MAX_MOVE_COUNT = 0;
    private int moveCount;
    private int maxMoveCount = DEFAULT_MAX_MOVE_COUNT;
    private int dt;

    public void reset(){
        this.moveCount = 0;
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
                                    ((GameWorld)this).gameUserLoseHp();
                                    HitFunction f = (((Bounded)object))->{HitListener.bounceReduceHp(((Bounded)object));};
                                }
                                if(otherObject instanceof PaintableBrickBox){
                                    if(((PaintableBrickBox)otherObject).getHp() == 0){
                                        ((GameWorld)this).gameUsersGetPoint(((PaintableBrickBox)otherObject));
                                    }
                                    else
                                        ((PaintableBrickBox)otherObject).setHp(((PaintableBrickBox)otherObject).getHp()-1);
                                }
                                if(regionalList.size()<=6){
                                    ((GameWorld)this).gameExplainClear();
                                    ((GameWorld)this).gameUserWin();
                                    return;
                                }
                                if(((GameWorld)this).getCurrentUserHp() <= 0){
                                    ((GameWorld)this).gameExplainClear();
                                    ((GameWorld)this).gameUserLose();
                                    return;
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
            if(((GameWorld)this).getCurrentUserHp() <= 0 
            || regionalList.size()<=6){
                return;
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
    }
}
