package com.nhnacademy;

import java.awt.Rectangle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegionalBall implements Regional{
    static final Logger logger = LogManager.getLogger();
    static int count = 0;

    private int id = ++count;
    private Rectangle region;

    public RegionalBall(int x, int y, int radius){
        if(radius <= 0){
            throw new IllegalArgumentException("ball 반지름이 0 보다 커야합니다.");
            
        }
        if(x+(long)radius > Integer.MAX_VALUE 
            || x-(long)radius < Integer.MIN_VALUE
            || y+(long)radius > Integer.MAX_VALUE
            || y-(long)radius < Integer.MIN_VALUE){
            throw new IllegalArgumentException("ball이 정수 공간을 넘어갑니다.");
        }
        this.region = new Rectangle(x-radius, y-radius, 2*radius, 2*radius);
    }
    @Override
    public String toString(){
        return "Ball Id : "+id;
    }
    @Override
    public Rectangle getRegion() {
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

    public int getRadius(){
        return (int)region.getWidth()/2;
    }

    protected void setX(int x){
        region.setLocation(x-this.getRadius(), this.getY()- this.getRadius());
    }

    protected void setY(int y){
        region.setLocation(this.getX()-this.getRadius(), y-this.getRadius());
    }
    public int getId(){
        return this.id;
    }
    
}
