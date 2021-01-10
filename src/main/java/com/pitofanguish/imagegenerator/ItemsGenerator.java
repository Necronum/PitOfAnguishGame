package com.pitofanguish.imagegenerator;

import com.pitofanguish.items.Food;
import com.pitofanguish.items.Gold;
import com.pitofanguish.items.Monster;
import com.pitofanguish.items.Weapon;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class ItemsGenerator extends Rectangle{
    private static final Monster MONSTER = new Monster();
    private static final Gold GOLD = new Gold();
    private static final  Weapon WEAPON = new Weapon();
    private static final Food FOOD = new Food();
    private static final Player PLAYER = new Player();
    private final Random rnd = new Random();

    public ItemsGenerator(int x, int y){
        setWidth(ContentGenerator.TILE_SIZE);
        setHeight(ContentGenerator.TILE_SIZE);

        relocate(x * ContentGenerator.TILE_SIZE, y * ContentGenerator.TILE_SIZE);

        if (x == 1 && y == 1) {
            setFill(new ImagePattern(PLAYER.playerModel()));
        } else {
            setFill(new ImagePattern(place()));
        }
    }

    public Image place() {
        int value = rnd.nextInt(100);
        Image item;
        if (value>0 && value<20){
            item = GOLD.place();
        } else if (value>20 && value<35){
            item = FOOD.place();
        } else if (value>35 && value<60){
            item = WEAPON.place();
        } else {
            item = MONSTER.place();
        }
        return item;
    }
}
