package com.nhnacademy;
import java.awt.Color;
import java.util.Arrays;
public class GameWorld extends MovableWorld {
    public static final int INIT_USER_HP = 100000;
    public static final int INIT_TOTALSCORE = 0;
    private int currentUserHp = INIT_USER_HP;
    private int currentUserTotalScore = INIT_TOTALSCORE;

    public void gameExplain(){
        this.addStringList(new PaintableString(Main.FRAME_DELTA*8, Main.FRAME_DELTA*10, "위 버튼으로 게임을 플레이하는 인원을 선택해주세요", Color.BLACK));
        this.addStringList(new PaintableString(Main.FRAME_DELTA*8, Main.FRAME_DELTA*13, "게임 설명", Color.BLACK));
        this.addStringList(new PaintableString(Main.FRAME_DELTA*8, Main.FRAME_DELTA*16, "파랑색 박스는 한번 맞추면 없어지고 점수는 2점입니다.", Color.BLACK));
        this.addStringList(new PaintableString(Main.FRAME_DELTA*8, Main.FRAME_DELTA*19, "노랑색 박스는 두번 맞추면 없어지고 점수는 3점입니다.", Color.BLACK));
        this.addStringList(new PaintableString(Main.FRAME_DELTA*8, Main.FRAME_DELTA*22, "빨강생 박스는 두번 맞추면 없어지고 점수는 4점입니다.", Color.BLACK));
        this.addStringList(new PaintableString(Main.FRAME_DELTA*8, Main.FRAME_DELTA*25, "검정색 박스는 두번 맞추면 없어지고 점수는 5점입니다.", Color.BLACK));
        this.addStringList(new PaintableString(Main.FRAME_DELTA*8, Main.FRAME_DELTA*28, "플레이어의 HP는 가장 하단의 박스에 떨어지게되면 1 낮아지고 0이되면 게임을 패배합니다.", Color.BLACK));
        repaint();
    }
    public void gameExplainClear(){
        stringList.clear();
        repaint();
    }
    public void gameUserInit(){
        for(int i= regionalList.size()-1; i>=5;i--){
            regionalList.remove(i);
        }
        this.setCurrentUserHp(INIT_USER_HP);
        this.setCurrentUserTotalScore(INIT_TOTALSCORE);
        repaint();
    }
    public void gameUsersGetPoint(PaintableBrickBox otherObject){
        this.setCurrentUserTotalScore(this.getCurrentUserTotalScore()+otherObject.getScore());
        stringList.get(0).setStatement("Your Score : "+this.getCurrentUserTotalScore());   
        regionalList.remove(otherObject);

    }
    public void gameUserLoseHp(){
        this.setCurrentUserHp(this.getCurrentUserHp()-1);
        stringList.get(1).setStatement("Your HP : "+this.getCurrentUserHp());
        logger.trace("user hp {}", this.getCurrentUserHp());
    }
    public void gameUsersScoreAndHpStatus(){
        this.addStringList(new PaintableString(Main.FRAME_WIDTH/2, 300, "Your Score : "+this.getCurrentUserTotalScore(), Color.GRAY));
        this.addStringList(new PaintableString(Main.FRAME_WIDTH/2, 350, "Your HP : "+this.getCurrentUserHp(), Color.GRAY));
        repaint();
    }
    public void gameUserWin(){
        stringList.clear();
        this.addStringList(new PaintableString(Main.FRAME_WIDTH/2, 300, "You Win", Color.BLACK));
        this.addStringList(new PaintableString(Main.FRAME_WIDTH/2, 400, "Your Score : "+this.getCurrentUserTotalScore(), Color.BLACK));
    }
    public void gameUserLose(){
        stringList.clear();
        this.addStringList(new PaintableString(Main.FRAME_WIDTH/2, 350, "You Lose", Color.BLACK));
    }
    public void gameEndClear(){
        regionalList.clear();
        stringList.clear();
        repaint();
    }

    public void userRanking(Member[] members){
        Arrays.sort(members);
        for(int i=0; i<members.length; i++){
            this.addStringList(new PaintableString(Main.FRAME_WIDTH/2-100, i*50+200, i+1+"등 : "+members[i], Color.BLACK));
        }
        repaint();
    }
    public int getCurrentUserHp() {
        return currentUserHp;
    }
    public void setCurrentUserHp(int currentUserHp) {
        this.currentUserHp = currentUserHp;
    }
    public int getCurrentUserTotalScore() {
        return currentUserTotalScore;
    }
    public void setCurrentUserTotalScore(int currentUserTotalScore) {
        this.currentUserTotalScore = currentUserTotalScore;
    }
    
    
}
