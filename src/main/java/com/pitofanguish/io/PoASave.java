package com.pitofanguish.io;

import com.google.gson.*;
import com.pitofanguish.imagegenerator.TileGenerator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PoASave {
    private static final String LEADERBOARD_PATH = "build/resources/main/leaderboard.json";
    private static final String SAVE_PATH = "build/resources/main/save.json";

    public PoASave() throws IOException {
    }

    //Niestety nie udało się w czasie dokończyć modelu zapisu i odczytu planszy :C
//    public void saveGame(TileGenerator[][]board) throws IOException{
//        try{
//            Gson gson = new Gson();
//            Writer writer = Files.newBufferedWriter(Paths.get(SAVE_PATH));
//            gson.toJson(board, writer);
//            writer.flush();
//            writer.close();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    public TileGenerator[][] loadGame(){
//        TileGenerator[][] board = null;
//        try{
//            Gson gson = new Gson();
//            Reader reader = Files.newBufferedReader(Paths.get(SAVE_PATH));
//            board = gson.fromJson(reader, TileGenerator[][].class);
//            reader.close();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//        return board;
//    }

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
