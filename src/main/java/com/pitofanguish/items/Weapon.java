package com.pitofanguish.items;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Weapon {
    private static final Image WEAPON_1 = new Image("file:src/main/resources/weapon1.png");
    private static final Image WEAPON_2 = new Image("file:src/main/resources/weapon2.png");
    private static final Image WEAPON_3 = new Image("file:src/main/resources/weapon3.png");
    private static final Image WEAPON_4 = new Image("file:src/main/resources/weapon4.png");
    private static final Map<Integer, Image> IMAGE_MAP = new HashMap<>();
    private final Random rnd = new Random();

    static{
        IMAGE_MAP.put(0, WEAPON_1);
        IMAGE_MAP.put(1, WEAPON_2);
        IMAGE_MAP.put(2, WEAPON_3);
        IMAGE_MAP.put(3, WEAPON_4);
    }

    public Image place(){
        int value = rnd.nextInt(4);
        return IMAGE_MAP.get(value);
    }
}
