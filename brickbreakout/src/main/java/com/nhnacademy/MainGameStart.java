package com.nhnacademy;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainGameStart {
    static final int BALL_COUNT = 1;
    static final int FRAME_HEIGHT = 600;
    static final int FRAME_WIDTH = 600;
    static final int MIN_RADIUS = 10;
    static final int MAX_RADIUS = 50;
    static final int MOVE_COUNT = 1000;
    static final int MAX_D = 30;
    static final int MIN_D = 10;
    static final int WORLD_DTIME = 10;
    static final int WALL_THICKNESS = 50;
    static final Color[] COLOR_TABLE = {Color.BLACK, Color.BLUE, Color.DARK_GRAY, Color.MAGENTA};
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MovableWorld panel = new MovableWorld();
        panel.setBounds(-WALL_THICKNESS, -WALL_THICKNESS,
                FRAME_WIDTH + WALL_THICKNESS*2,
                FRAME_HEIGHT+WALL_THICKNESS*2);
        panel.add(new PaintableBox(-WALL_THICKNESS/2, FRAME_HEIGHT, FRAME_WIDTH*2 + WALL_THICKNESS ,WALL_THICKNESS));
        
        
        frame.add(panel);
        // 프레임을 만들어서 bound코드를 제거한다.
        
        
        Random random = new Random();
        for(int i=0; i< 10; i++){
            panel.add(new PaintableBox(i*60+50, 10, 50,50,Color.GREEN));
        }
        

        for( int i = 0; i < BALL_COUNT;i ++){
            try{
                BoundedBall ball = new BoundedBall(random.nextInt(FRAME_WIDTH), random.nextInt(FRAME_HEIGHT),
                    MIN_RADIUS+random.nextInt(MAX_RADIUS-MIN_RADIUS+1),
                    COLOR_TABLE[random.nextInt(COLOR_TABLE.length)]);

                int dx = MIN_D - random.nextInt(MAX_D-MIN_D+1);
                int dy = MIN_D - random.nextInt(MAX_D-MIN_D+1);

                ball.setDX(dx);
                ball.setDY(dy);

                panel.add(ball);
            } catch(IllegalArgumentException e) {
                i-=1;
                System.err.println(e);
            }
        }
        
        frame.add(panel);
        frame.setVisible(true);

        panel.setDT(WORLD_DTIME);
        panel.setMaxMoveCount(MOVE_COUNT);
        panel.run();
        
    }
}
