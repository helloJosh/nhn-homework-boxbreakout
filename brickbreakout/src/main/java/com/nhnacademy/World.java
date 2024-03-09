package com.nhnacademy;


import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class World extends JPanel implements MouseMotionListener{
    public List<Regional> regionalList = new ArrayList<>();
    public List<PaintableString> stringList = new ArrayList<>();
    static final Logger logger = LogManager.getLogger();
    PaintableBox userBox;
    
    public World(){
        super();
        userBox = new PaintableBox(Main.USER_BOX_INIT_X, Main.USER_BOX_INIT_Y, Main.USER_BOX_WIDTH, Main.USER_BOX_HEIGHT);
        add(userBox);
        addMouseMotionListener(this);
    }

    public void add(Regional object){
        if(object == null)
            throw new IllegalArgumentException("ball 객체가 비었습니다.");

        if(object instanceof Bounded)
            ((Bounded)object).setBounds(getBounds());
        regionalList.add(object);
    }
    public void remove(Regional object){
        regionalList.remove(object);
    }
    public void remove(int index){
        regionalList.remove(index);
    }
    public int getCount(){
        return regionalList.size();
    }

    public Regional get(int index){
        return regionalList.get(index);
    }

    public void addStringList(PaintableString object){
        if(object == null)
            throw new IllegalArgumentException("ball 객체가 비었습니다.");
        stringList.add(object);
    }
    public void removeStringList(PaintableString object){
        stringList.remove(object);
    }
    public void removeStringList(int index){
        stringList.remove(index);
    }
    public Regional getStringList(int index){
        return stringList.get(index);
    }
    public int getStringListCount(){
        return stringList.size();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(Regional objectArgument : regionalList){
            if(objectArgument instanceof Paintable){
                Paintable paintableObject = (Paintable)objectArgument;
                paintableObject.paint(g);
            }
        }
        for(PaintableString stringObject : stringList){
            stringObject.paint(g);
        }
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        if(Main.USER_BOX_MIN_Y<= arg0.getY() && arg0.getY() <= Main.USER_BOX_MAX_Y){
            userBox.setY(arg0.getY());
        } else if(Main.USER_BOX_MIN_Y > arg0.getY()){
            userBox.setY(Main.USER_BOX_MIN_Y);
        } else if(arg0.getY() > Main.USER_BOX_MAX_Y){
            userBox.setY(Main.USER_BOX_MAX_Y);
        }

        if(Main.USER_BOX_MIN_X <= arg0.getX() && arg0.getX() <= Main.USER_BOX_MAX_X){
            userBox.setX(arg0.getX());
        } else if(Main.USER_BOX_MIN_X  > arg0.getX()){
            userBox.setX(Main.USER_BOX_MIN_X);
        } else if(arg0.getX() > Main.USER_BOX_MAX_X){
            userBox.setX(Main.USER_BOX_MAX_X);
        }
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
    }
}
