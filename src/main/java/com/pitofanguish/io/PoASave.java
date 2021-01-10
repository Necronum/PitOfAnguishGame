package com.pitofanguish.io;

import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PoASave {
    private static final String LEADERBOARD_PATH = "build/resources/main/leaderboard.json";

    public PoASave() throws IOException {
    }

    public void saveLeaderBoard(LeaderBoard leaderBoard) throws IOException {
        try{
            Gson gson = new Gson();
            LeaderBoard sortedLeaderBoard = new LeaderBoard(leaderBoard.getLeaders());
            Writer writer = Files.newBufferedWriter(Paths.get(LEADERBOARD_PATH));
            gson.toJson(sortedLeaderBoard, writer);
            writer.flush();
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public LeaderBoard loadLeaderBoard(){
        LeaderBoard leaderBoard = new LeaderBoard();
        try{
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(LEADERBOARD_PATH));
            leaderBoard = gson.fromJson(reader, LeaderBoard.class);
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return leaderBoard;
    }
}
