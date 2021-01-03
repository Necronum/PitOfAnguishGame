package com.pitofanguish.io;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LeaderBoard {
    private List<Leader> leaders = new ArrayList<>();

    public LeaderBoard(List<Leader> leaders) {
        this.leaders = leaders;
    }

    public LeaderBoard() {
    }

    public int leaderBoardLength(){
        return leaders.size();
    }

    public List<Leader> getLeaders() {
        return leaders.stream().sorted(Comparator.comparingInt(Leader::getScore).reversed()).collect(Collectors.toList());
    }

    public void setLeaders(List<Leader> leaders) {
        this.leaders = leaders;
    }
}
