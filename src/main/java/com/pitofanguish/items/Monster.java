package com.pitofanguish.items;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Monster implements Item {
    private static final Image MONSTER_1 = new Image("file:src/main/resources/monster1.png");
    private static final Image MONSTER_2 = new Image("file:src/main/resources/monster2.png");
    private static final Image MONSTER_3 = new Image("file:src/main/resources/monster3.png");
    private static final Image MONSTER_4 = new Image("file:src/main/resources/monster4.png");
    private static final Image MONSTER_5 = new Image("file:src/main/resources/monster5.png");
    private static final Map<Integer, Image> IMAGE_MAP = new HashMap<>();
    private final Random rnd = new Random();

    static{
        IMAGE_MAP.put(0, MONSTER_1);
        IMAGE_MAP.put(1, MONSTER_2);
        IMAGE_MAP.put(2, MONSTER_3);
        IMAGE_MAP.put(3, MONSTER_4);
        IMAGE_MAP.put(4, MONSTER_5);
    }

    public Image place(){
        int value = rnd.nextInt(5);
        return IMAGE_MAP.get(value);
    }
}
