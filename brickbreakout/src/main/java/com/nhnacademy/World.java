package com.nhnacademy;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class World extends JPanel implements MouseListener{
    public List<Regional> list = new ArrayList<>();
    static final Logger logger = LogManager.getLogger();
    public World(){
        super();
        addMouseListener(this);
    }

    public void add(Regional object){
        if(object == null)
            throw new IllegalArgumentException("ball 객체가 비었습니다.");

        // if((object.getX() - object.getRegion().getWidth()/2 <0)
        //     || object.getX() + object.getRegion().getWidth()/2 > getWidth()
        //     || object.getRegion().getMinY() < 0
        //     || object.getRegion().getMaxX()> getHeight())
        //     throw new IllegalArgumentException();

        for(Regional objectArgument : list){
            if(object.isCollision(objectArgument))
                throw new IllegalArgumentException();
        }

        if(object instanceof Bounded)
            ((Bounded)object).setBounds(getBounds());
        list.add(object);
    }

    public void remove(Regional object){
        list.remove(object);
    }

    public void remove(int index){
        list.remove(index);
    }
    public int getCount(){
        return list.size();
    }

    public Regional get(int index){
        return list.get(index);
    }

    @Override
    public void paint(Graphics g){
        logger.debug("new paint[width : {}, heigth : {}]", getWidth(), getHeight());
        super.paint(g);
        for(Regional objectArgument : list){
            if(objectArgument instanceof Paintable){
                Paintable paintableObject = (Paintable)objectArgument;
                paintableObject.paint(g);
            }
        }
        g.drawRect(0,0,600,600);
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        //add(new PaintableBox(arg0.getX(), arg0.getY(), 50, 50));
        Random random = new Random();

        MovableBall ball = new MovableBall(arg0.getX(), arg0.getY(), 40, Color.GRAY);
        ball.setDX(-10+random.nextInt(20));
        ball.setDY(-10+random.nextInt(20));
        
        add(ball);
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    
}
