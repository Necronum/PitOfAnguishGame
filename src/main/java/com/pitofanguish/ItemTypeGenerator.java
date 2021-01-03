package com.pitofanguish;


import java.util.Random;

public class ItemTypeGenerator {
    private final Random rnd = new Random();

    //generates board with characters for first round and next round when there is no need to generate player model
    public CharacterType randomizeType(int x, int y){
        int value = rnd.nextInt(100);
        CharacterType type;
        if (x ==1 && y ==1){
            type = CharacterType.PLAYER;
        } else if (value>0 && value<20){
            type = CharacterType.GOLD;
        } else if (value>20 && value<35){
            type = CharacterType.FOOD;
        } else if (value>35 && value<60){
            type = CharacterType.WEAPON;
        } else {
            type = CharacterType.MONSTER;
        }
        return type;
    }

    //generates board with characters for next round with harder events after round 20
    public CharacterType randomizeTypeRound(int roundNumber, int x, int y){
        int value = rnd.nextInt(100);
        CharacterType type;
        if (roundNumber < 20){
            if (value>0 && value<25){
                type = CharacterType.GOLD;
            } else if (value>25 && value<40){
                type = CharacterType.FOOD;
            } else if (value>40 && value<60){
                type = CharacterType.WEAPON;
            } else {
                type = CharacterType.MONSTER;
            }
        } else {
            if (value>0 && value<20){
                type = CharacterType.GOLD;
            } else if (value>20 && value<35){
                type = CharacterType.FOOD;
            } else if (value>35 && value<55){
                type = CharacterType.WEAPON;
            } else {
                type = CharacterType.MONSTER;
            }
        }
        return type;
    }
}
