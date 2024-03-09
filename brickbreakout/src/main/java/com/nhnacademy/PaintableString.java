package com.nhnacademy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PaintableString implements Paintable,Regional{
    public static final Color DEFAULT_COLOR = Color.black;
    private int x;
    private int y;
    private String statement;
    Color color;
    public PaintableString(int x, int y, String statement, Color color){
        this.x = x;
        this.y = y;
        this.statement = statement;
        this.color = color;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public String getStatement() {
        return statement;
    }
    public void setStatement(String statement) {
        this.statement = statement;
    }
    @Override
    public void setColor(Color color) {
        if(color == null){
            throw new IllegalArgumentException();
        }
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void paint(Graphics g) {
        if(g == null){
            throw new IllegalArgumentException();
        }
        Color originalColor = g.getColor();
        g.setColor(getColor());
        g.drawString(this.statement, this.x, this.y);
        g.setColor(originalColor);
    }
    @Override
    public Rectangle getRegion() {
        return new Rectangle(this.x, this.y, 1, 1);
    }
    @Override
    public boolean isCollision(Regional other) {
        return false;
    }
    
}
