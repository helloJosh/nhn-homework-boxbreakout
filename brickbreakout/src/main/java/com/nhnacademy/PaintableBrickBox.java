package com.nhnacademy;

import java.awt.Color;

public class PaintableBrickBox extends PaintableBox{
    private int hp ;
    private int score;
    
    public PaintableBrickBox(int x, int y, int width, int height) {
        this(x,y,width, height, DEFAULT_COLOR);
    }
    public PaintableBrickBox(int x, int y, int width, int height, Color color){
        super(x,y,width, height);
        
        if(color == null){
            throw new IllegalArgumentException();
        }
        this.color = color;
        if(color == Color.BLUE){
            this.hp = 0;
            this.score = 2;
        }
        else if(color == Color.YELLOW){
            this.hp = 1;
            this.score = 3;
        }
        else if(color == Color.RED){
            this.hp = 1;
            this.score = 4;
        }
        else if(color == Color.BLACK){
            this.hp = 1;
            this.score = 5;
        }
    }

    public int getHp(){
        return this.hp;
    }
    public void setHp(int hp){
        this.hp = hp;
    }
    public int getScore(){
        return this.score;
    }
    
}
