package com.nhnacademy;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static final int BALL_COUNT = 1;
    public static final int USER_SCREEN_HEIGHT = 800;
    public static final int USER_SCREEN_WIDTH = 900;
    public static final int FRAME_HEIGHT = 500;
    public static final int FRAME_WIDTH = 500;
    public static final int FRAME_DELTA = 10;
    public static final int MIN_RADIUS = 20;
    public static final int MAX_RADIUS = 25;
    public static final int MOVE_COUNT = 0;
    public static final int MAX_D = 6;
    public static final int MIN_D = 5;
    public static final int WORLD_DTIME = 10;
    public static final int WALL_THICKNESS = 50;
    protected static final Color[] COLOR_TABLE = {Color.BLUE, Color.YELLOW, Color.RED, Color.BLACK};
    public static final int USER_BOX_WIDTH = 120;
    public static final int USER_BOX_HEIGHT = 30;
    public static final int USER_BOX_INIT_X = 400;
    public static final int USER_BOX_INIT_Y = 510;
    public static final int USER_BOX_MAX_X = 500;
    public static final int USER_BOX_MIN_X  = 80;
    public static final int USER_BOX_MAX_Y  = 560;
    public static final int USER_BOX_MIN_Y  = 350;
    public static final int BRICK_BOX_SIZE = 55;
    public static int playerNumber = 0;
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger();
        Random random = new Random();
        JFrame frame = new JFrame();

        frame.setSize(USER_SCREEN_WIDTH,USER_SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GameWorld world = new GameWorld();
        frame.add(world);

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
        world.gameExplain();

        JPanel buttonPanel = new JPanel();
        JButton player1Button = new JButton("Player 1");
        JButton player2Button = new JButton("Player 2");
        JButton player3Button = new JButton("Player 3");

        player1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerNumber = 1;
                buttonPanel.remove(player1Button);
                buttonPanel.remove(player2Button);
                buttonPanel.remove(player3Button);
                frame.repaint();
            }
        });

        player2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerNumber = 2;
                buttonPanel.remove(player1Button);
                buttonPanel.remove(player2Button);
                buttonPanel.remove(player3Button);
                frame.repaint();
            }
        });

        player3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerNumber = 3;
                buttonPanel.remove(player1Button);
                buttonPanel.remove(player2Button);
                buttonPanel.remove(player3Button);
                frame.repaint();
            }
        });
        
        buttonPanel.add(player1Button);
        buttonPanel.add(player2Button);
        buttonPanel.add(player3Button);

        frame.add(buttonPanel);
        frame.setVisible(true);

        frame.repaint();
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        world.gameExplainClear();

        logger.info("playerNumber = {}",playerNumber);
        if(playerNumber ==0){
            throw new IllegalArgumentException("버튼을 안눌렀습니다. 다시시작해주십시오");
        }

        Member[] members = new Member[playerNumber];
        for(int i = 0; i < members.length ;i++){
            members[i]= new Member(i,"player"+(i+1),0);
        }
        for(int n = 0; n < members.length ;n++){
            for(int j = 0; j<2;j++){
                for(int i=0; i< 8; i++){
                    world.add(new PaintableBrickBox(i*(BRICK_BOX_SIZE+3)+WALL_THICKNESS*2,
                                                    WALL_THICKNESS*2+j*(BRICK_BOX_SIZE+3),
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
            world.gameUsersScoreAndHpStatus();
            world.run();
            

            members[n].setTotalScore(world.getCurrentUserTotalScore());

            world.gameUserInit();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            logger.info(members[n]);
            world.gameExplainClear();
        }
        world.gameEndClear();
        world.userRanking(members);
    }
}
