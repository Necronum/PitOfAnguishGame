package com.pitofanguish.imagegenerator;

import com.pitofanguish.items.Food;
import com.pitofanguish.items.Gold;
import com.pitofanguish.items.Monster;
import com.pitofanguish.items.Weapon;
import com.pitofanguish.movement.CharacterType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static com.pitofanguish.imagegenerator.ContentGenerator.TILE_SIZE;

public class ImageGenerator extends StackPane {
    private static final Monster MONSTER = new Monster();
    private static final Gold GOLD = new Gold();
    private static final Weapon WEAPON = new Weapon();
    private static final Food FOOD = new Food();
    private static final Player PLAYER = new Player();
    private final CharacterType type;

    private double mouseX, mouseY;
    private double oldX, oldY;

    public CharacterType getType(){
        return this.type;
    }

    public double getOldX() {
        return this.oldX;
    }

    public double getOldY() {
        return this.oldY;
    }

    //generate image and check if selected character is Player model
    public ImageGenerator(CharacterType type, int x, int y){
        this.type = type;
        move(x, y);

        Rectangle image = placeImage(type, x, y);
        getChildren().addAll(image);

        if(type == CharacterType.PLAYER) {
            setOnMousePressed(e -> {
                mouseX = e.getSceneX();
                mouseY = e.getSceneY();
            });

            setOnMouseDragged(e -> relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY));
        }
    }

    public void move(int x, int y) {
        oldX = x*TILE_SIZE;
        oldY = y*TILE_SIZE;
        relocate(oldX, oldY);
    }

    //get back character to his old position
    public void abortMove() {
        relocate(oldX, oldY);
    }

    //place rectangle with image inside proper tile
    public Rectangle placeImage(CharacterType type, int x, int y){
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(ContentGenerator.TILE_SIZE);
        rectangle.setHeight(ContentGenerator.TILE_SIZE);
        rectangle.relocate(x * ContentGenerator.TILE_SIZE, y * ContentGenerator.TILE_SIZE);

        rectangle.setFill(new ImagePattern(
                type == CharacterType.PLAYER ? PLAYER.playerModel()
                        :type == CharacterType.FOOD ? FOOD.place()
                        :type == CharacterType.GOLD ? GOLD.place()
                        :type == CharacterType.MONSTER ? MONSTER.place()
                        :WEAPON.place()
        ));
        return rectangle;
    }
}
