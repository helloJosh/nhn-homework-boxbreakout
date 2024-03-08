package com.nhnacademy;

import java.awt.Rectangle;

public interface Bounded {
    public Rectangle getBounds();
    public void setBounds(Rectangle bounds);
    public boolean isOutOfBounds();
    public void move();
    public void bounce();
    public void bounce(Regional otherBall);
    
}
