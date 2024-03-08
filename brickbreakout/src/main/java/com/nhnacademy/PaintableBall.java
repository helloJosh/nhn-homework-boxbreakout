package com.nhnacademy;
import java.awt.Color;
import java.awt.Graphics;

public class PaintableBall extends RegionalBall implements Paintable{
    public static final Color DEFAULT_COLOR = Color.black;
    Color color;

    public PaintableBall(int x, int y, int radius) {
        this(x,y,radius, DEFAULT_COLOR);
    }
    public PaintableBall(int x, int y, int radius, Color color){
        super(x,y,radius);
        
        if(color == null){
            throw new IllegalArgumentException();
        }
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }
    public void setColor(Color color){
        if(color == null){
            throw new IllegalArgumentException();
        }
        this.color = color;
    }
    public void paint(Graphics g){
        if(g == null){
            throw new IllegalArgumentException();
        }
        Color originalColor = g.getColor();
        g.setColor(getColor());
        g.fillOval(this.getX()-this.getRadius(),this.getY()-this.getRadius(), this.getRadius()*2, this.getRadius()*2);
        g.setColor(originalColor);
    }
}
