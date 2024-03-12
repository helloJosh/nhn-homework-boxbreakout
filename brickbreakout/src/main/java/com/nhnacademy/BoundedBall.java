package com.nhnacademy;

import java.awt.Color;
import java.awt.Rectangle;

public class BoundedBall extends MovableBall implements Bounded, HitListener{
    Rectangle bounds;
    private HitListener hitListener;

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
        double angle = Math.atan2(this.getRegion().getCenterY()-other.getRegion().getCenterY() ,this.getRegion().getCenterX()- other.getRegion().getCenterX());

        if ((this.getRegion().getHeight() != intersection.getHeight())
                && (other.getRegion().getHeight() != intersection.getHeight())) {
            if(Math.toDegrees(angle)<30){
                if( 3<Math.abs(this.getDY()) && Math.abs(this.getDY()) < 10)
                    this.setDY(-this.getDY()-1);
                else
                    this.setDY(-this.getDY());
            } else if(Math.toDegrees(angle)<60){
                this.setDY(-this.getDY());
            } else{
                if( 3<Math.abs(this.getDY()) && Math.abs(this.getDY()) < 10)
                    this.setDY(-this.getDY()+1);
                else
                    this.setDY(-this.getDY());
            }
        }

        if ((this.getRegion().getWidth() != intersection.getWidth())
                && (other.getRegion().getWidth() != intersection.getWidth())) {
            if(Math.toDegrees(angle)<30){
                if( 3<Math.abs(this.getDX()) && Math.abs(this.getDX()) < 10)
                    this.setDX(-this.getDX()+1);
                else
                    this.setDX(-this.getDX());
            } else if(Math.toDegrees(angle)<60){
                this.setDX(-this.getDX());
            } else{
                if( 3<Math.abs(this.getDX()) && Math.abs(this.getDX()) < 10)
                    this.setDX(-this.getDX()-1);
                else
                    this.setDX(-this.getDX());
            }
        }
    }
    
    @Override
    public void bounceReduceHp() {
        
    }
    @Override
    public void bounceRemoveObejct() {
        
    }
}
