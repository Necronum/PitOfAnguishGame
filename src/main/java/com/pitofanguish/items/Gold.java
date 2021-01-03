package com.pitofanguish.items;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Gold {
    private static final Image GOLD_1 = new Image("file:src/main/resources/gold1.png");
    private static final Image GOLD_2 = new Image("file:src/main/resources/gold2.png");
    private static final Image GOLD_3 = new Image("file:src/main/resources/gold3.png");
    private static final Image GOLD_4 = new Image("file:src/main/resources/gold4.png");
    private static final Map<Integer, Image> IMAGE_MAP = new HashMap<>();
    private final Random rnd = new Random();

    static{
        IMAGE_MAP.put(0, GOLD_1);
        IMAGE_MAP.put(1, GOLD_2);
        IMAGE_MAP.put(2, GOLD_3);
        IMAGE_MAP.put(3, GOLD_4);
    }

    public Image place(){
        int value = rnd.nextInt(4);
        return IMAGE_MAP.get(value);
    }
}
