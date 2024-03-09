package com.nhnacademy;
import java.awt.Color;
public class GameWorld extends MovableWorld {
    public void gameInit(){
        this.addStringList(new PaintableString(Main.FRAME_DELTA*8, Main.FRAME_DELTA*10, "게임 설명", Color.BLACK));
        this.addStringList(new PaintableString(Main.FRAME_DELTA*8, Main.FRAME_DELTA*13, "파랑색 박스은 한번 맞추면 없어지고 점수는 2점입니다.", Color.BLACK));
        this.addStringList(new PaintableString(Main.FRAME_DELTA*8, Main.FRAME_DELTA*16, "노랑색 박스은 두번 맞추면 없어지고 점수는 3점입니다.", Color.BLACK));
        this.addStringList(new PaintableString(Main.FRAME_DELTA*8, Main.FRAME_DELTA*19, "빨강생 박스은 두번 맞추면 없어지고 점수는 4점입니다.", Color.BLACK));
        this.addStringList(new PaintableString(Main.FRAME_DELTA*8, Main.FRAME_DELTA*22, "검정색 박스는 두번 맞추면 없어지고 점수는 5점입니다.", Color.BLACK));
        this.addStringList(new PaintableString(Main.FRAME_DELTA*8, Main.FRAME_DELTA*25, "플레이어의 HP는 가장 하단의 박스에 떨어지게되면 1낮아지고 0이되면 게임을 패배합니다.", Color.BLACK));
    }
    public void gameInitClear(){
        stringList.clear();
    }
    public void gameInfo(){
        this.addStringList(new PaintableString(Main.FRAME_WIDTH/2, 350, "Your Score : "+this.getTotalScore(), Color.GRAY));
        this.addStringList(new PaintableString(Main.FRAME_WIDTH/2, 400, "Your HP : "+this.getUserHp(), Color.GRAY));
    }
    
}
