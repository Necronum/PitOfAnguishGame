package com.pitofanguish.items;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Food implements Item {
    private static final Image FOOD_1 = new Image("file:src/main/resources/food1.png");
    private static final Image FOOD_2 = new Image("file:src/main/resources/food2.png");
    private static final Image FOOD_3 = new Image("file:src/main/resources/food3.png");
    private static final Image FOOD_4 = new Image("file:src/main/resources/food4.png");
    private static final Map<Integer, Image> IMAGE_MAP = new HashMap<>();
    private final Random rnd = new Random();

    static{
        IMAGE_MAP.put(0, FOOD_1);
        IMAGE_MAP.put(1, FOOD_2);
        IMAGE_MAP.put(2, FOOD_3);
        IMAGE_MAP.put(3, FOOD_4);
    }

    public Image place(){
        int value = rnd.nextInt(4);
        return IMAGE_MAP.get(value);
    }
}
