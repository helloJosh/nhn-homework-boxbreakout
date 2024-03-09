package com.nhnacademy;

import java.awt.Rectangle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegionalBox implements Regional{
    static final Logger logger = LogManager.getLogger();
    static int count = 0;

    private int id = ++count;
    private Rectangle region;

    public RegionalBox(int x, int y, int width, int height){
        if(height <= 0 || width <= 0){
            throw new IllegalArgumentException("box의 크기는 0 보다 커야합니다.");
        }
        if(x+(long)width/2 > Integer.MAX_VALUE 
            || x-(long)width/2 < Integer.MIN_VALUE
            || y+(long)height/2 > Integer.MAX_VALUE
            || y-(long)height/2 < Integer.MIN_VALUE){
            throw new IllegalArgumentException("box가 정수 공간을 넘어갑니다.");
        }
        this.region = new Rectangle(x-width/2, y-height/2, width, height);
    }
    @Override
    public String toString(){
        return "Box Id : "+id;
    }

    @Override
    public Rectangle getRegion(){
        return this.region;
    }
    @Override
    public int getX(){
        return (int)this.getRegion().getCenterX();
    }
    @Override
    public int getY(){
        return (int)this.getRegion().getCenterY();
    }
    @Override
    public boolean isCollision(Regional other){
        return this.getRegion().intersects(other.getRegion());
    }

    public int getWidth(){
        return (int)region.getWidth();
    }

    public int getHeight(){
        return (int)region.getHeight();
    }

    protected void setX(int x) {
        region.setLocation(x-getWidth()/2, this.getY()-getHeight()/2);
    }

    protected void setY(int y) {
        region.setLocation(this.getX()-getWidth()/2, y-getHeight()/2);
    }
    public int getId(){
        return this.id;
    }
    
}
