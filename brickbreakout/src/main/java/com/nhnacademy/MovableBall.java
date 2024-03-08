package com.nhnacademy;

import java.awt.Color;

public class MovableBall extends PaintableBall implements Movable{
    final Vector motion = new Vector();

    public MovableBall(int x, int y, int radius, Color color){
        super(x,y,radius,color);
    }
    public int getDX(){
        return motion.getDX();
    }
    public int getDY(){
        return motion.getDY();
    }
    public void setDX(int dx){
        this.motion.setDX(dx);
    }
    public void setDY(int dy){
        this.motion.setDY(dy);
    }
    public void move(){
        moveTo(this.getX()+this.getDX(),this.getY()+this.getDY());
    }
    public void moveTo(int x, int y){
        this.setX(x);
        this.setY(y);
    }
}
