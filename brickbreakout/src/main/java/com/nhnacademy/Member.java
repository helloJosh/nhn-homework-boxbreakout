package com.nhnacademy;

public class Member implements Comparable{
    private int id;
    private String name;
    private int totalScore;
    private boolean status;
    
    public Member(int id, String name, int totalScore) {
        this.id = id;
        this.name = name;
        this.totalScore = totalScore;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getTotalScore() {
        return totalScore;
    }
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return name + " 총 점수=" + totalScore;
    }
    @Override
    public int compareTo(Object arg0) {
        if(arg0 == null)
            throw new NullPointerException();
        if(this.isStatus() && ((Member)arg0).isStatus()){
            if(this.getTotalScore()>((Member)arg0).getTotalScore())
                return -1;
            else if(this.getTotalScore()>((Member)arg0).getTotalScore())
                return 0;
            else
                return 1;
        }else if(this.isStatus() || ((Member)arg0).isStatus()){
            if(this.isStatus())
                return -1;
            else
                return 1;
        } else{
            if(this.getTotalScore()>((Member)arg0).getTotalScore())
                return -1;
            else if(this.getTotalScore()>((Member)arg0).getTotalScore())
                return 0;
            else
                return 1;
        }
    }
    
}
