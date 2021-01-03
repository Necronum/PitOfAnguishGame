package com.pitofanguish.imagegenerator;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;


public class Player extends Rectangle {
    private final Image character = new Image("file:src/main/resources/character.png");

    public Image playerModel(){
        return character;
    }
}
