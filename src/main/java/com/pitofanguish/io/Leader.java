package com.pitofanguish.io;

public class Leader {
    private String name;
    private int score;

    public Leader(String name, int score){
        this.name = name;
        this.score = score;
    }

    public Leader() {
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
