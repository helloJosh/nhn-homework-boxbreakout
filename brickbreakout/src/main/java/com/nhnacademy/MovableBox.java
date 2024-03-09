package com.nhnacademy;

import java.awt.Color;

public class MovableBox extends PaintableBox implements Movable{
    public static final int DEFAULT_DX = 0;
    public static final int DEFAULT_DY = 0;
    final Vector motion = new Vector();
    static int count = 0;
    private int id = count++;
    


    public MovableBox(int x, int y, int width, int height, Color color){
        super(x,y,width,height,color);
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
    @Override
    public int getId(){
        return id;
    }
}
