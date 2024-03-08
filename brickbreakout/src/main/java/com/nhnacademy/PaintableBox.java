package com.nhnacademy;

import java.awt.Color;
import java.awt.Graphics;


public class PaintableBox extends RegionalBox implements Paintable{
    public static final Color DEFAULT_COLOR = Color.black;
    Color color;

    public PaintableBox(int x, int y, int width, int height) {
        this(x,y,width, height, DEFAULT_COLOR);
    }
    public PaintableBox(int x, int y, int width, int height, Color color){
        super(x,y,width, height);
        
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
        g.fillRect((int)this.getRegion().getX(),(int)this.getRegion().getY(), (int)this.getRegion().getWidth() , (int)this.getRegion().getHeight());
        g.setColor(originalColor);
    }
    
}
