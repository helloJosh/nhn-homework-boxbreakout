package com.nhnacademy;

import java.awt.Color;
import java.awt.Rectangle;

public class BoundedBall extends MovableBall implements Bounded{
    Rectangle bounds;

    public BoundedBall(int x, int y, int radius, Color color){
        super(x,y,radius, color);
        bounds = new Rectangle(0,0,600,600);
    }
    public Rectangle getBounds(){
        return this.bounds;
    }
    public void setBounds(Rectangle bounds){
        if(bounds == null)
            throw new IllegalArgumentException();
        this.bounds = bounds;
    }
    @Override
    public void bounce(Regional other) {
        Rectangle intersection = getRegion().intersection(other.getRegion());

        if ((getRegion().getHeight() != intersection.getHeight())
                && (other.getRegion().getHeight() != intersection.getHeight())) {
            setDY(-getDY());
        }

        if ((getRegion().getWidth() != intersection.getWidth())
                && (other.getRegion().getWidth() != intersection.getWidth())) {
            setDX(-getDX());
        }
    }
    
}
