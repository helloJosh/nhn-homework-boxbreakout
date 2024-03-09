package com.nhnacademy;

import java.awt.Color;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {
    public static final int BALL_COUNT = 1;
    public static final int USER_SCREEN_HEIGHT = 950;
    public static final int USER_SCREEN_WIDTH = 850;
    public static final int FRAME_HEIGHT = 700;
    public static final int FRAME_WIDTH = 700;
    public static final int FRAME_DELTA = 10;
    public static final int MIN_RADIUS = 20;
    public static final int MAX_RADIUS = 25;
    public static final int MOVE_COUNT = 0;
    public static final int MAX_D = 20;
    public static final int MIN_D = 15;
    public static final int WORLD_DTIME = 13;
    public static final int WALL_THICKNESS = 50;
    protected static final Color[] COLOR_TABLE = {Color.BLUE, Color.YELLOW, Color.RED, Color.BLACK};
    public static final int USER_BOX_WIDTH = 100;
    public static final int USER_BOX_HEIGHT = 20;
    public static final int USER_BOX_INIT_X = 390;
    public static final int USER_BOX_INIT_Y = 710;
    public static final int USER_BOX_MAX_X = 700;
    public static final int USER_BOX_MIN_X  = 80;
    public static final int USER_BOX_MAX_Y  = 730;
    public static final int USER_BOX_MIN_Y  = 400;
    public static final int BRICK_BOX_SIZE = 85;
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(USER_SCREEN_WIDTH,USER_SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GameWorld world = new GameWorld();
        frame.add(world);
        frame.setVisible(true);

        world.setBounds(WALL_THICKNESS*2+FRAME_DELTA, WALL_THICKNESS*2+FRAME_DELTA,
                FRAME_WIDTH + WALL_THICKNESS*3,
                FRAME_HEIGHT+WALL_THICKNESS*3);
        world.add(new PaintableBox(WALL_THICKNESS / 2 + FRAME_DELTA, 
                FRAME_HEIGHT / 2+WALL_THICKNESS+FRAME_DELTA,
                WALL_THICKNESS, FRAME_HEIGHT + 2 * WALL_THICKNESS));
        world.add(new PaintableBox(FRAME_WIDTH / 2+WALL_THICKNESS+FRAME_DELTA,
                WALL_THICKNESS / 2+FRAME_DELTA,
                FRAME_WIDTH + 2 * WALL_THICKNESS, WALL_THICKNESS));
        world.add(new PaintableBox(FRAME_WIDTH + 3*WALL_THICKNESS / 2+FRAME_DELTA,
                FRAME_HEIGHT / 2+WALL_THICKNESS+FRAME_DELTA,
                WALL_THICKNESS, FRAME_HEIGHT + 2 * WALL_THICKNESS));
        PaintableBox bottomBound = new PaintableBox(FRAME_WIDTH / 2+WALL_THICKNESS+FRAME_DELTA,
                FRAME_HEIGHT + 3*WALL_THICKNESS / 2+FRAME_DELTA,
                FRAME_WIDTH + 2 * WALL_THICKNESS, WALL_THICKNESS);
        world.add(bottomBound);
        world.gameInit();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        world.gameInitClear();

        Random random = new Random();
        for(int j = 0; j< 2;j++){
            for(int i=0; i< 8; i++){
                world.add(new PaintableBrickBox(i*(BRICK_BOX_SIZE+3)+WALL_THICKNESS*2+FRAME_DELTA,
                                                WALL_THICKNESS*2+FRAME_DELTA+j*(BRICK_BOX_SIZE+3),
                                                BRICK_BOX_SIZE,BRICK_BOX_SIZE,COLOR_TABLE[random.nextInt(COLOR_TABLE.length)]));
            }
        }
        BoundedBall ball = new BoundedBall(FRAME_WIDTH*2/3, FRAME_HEIGHT*2/3,
                    MIN_RADIUS+random.nextInt(MAX_RADIUS-MIN_RADIUS+1),
                    Color.DARK_GRAY);
        int dx = MIN_D - random.nextInt(MAX_D-MIN_D+1);
        int dy = MIN_D - random.nextInt(MAX_D-MIN_D+1);

        ball.setDX(dx);
        ball.setDY(dy);

        world.add(ball);

        world.setMaxMoveCount(MOVE_COUNT);
        world.setDT(WORLD_DTIME);
        world.gameInfo();
        world.run();
    }
}
