package com.pitofanguish.imagegenerator;

import com.pitofanguish.ContentGenerator;
import com.pitofanguish.ImageGenerator;
import com.pitofanguish.items.Item;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TileGenerator extends Rectangle implements Item{
    private static final Image TILE_1 = new Image("file:src/main/resources/tile1.png");
    private static final Image TILE_2 = new Image("file:src/main/resources/tile2.png");
    private static final Image TILE_3 = new Image("file:src/main/resources/tile3.png");
    private static final Image TILE_4 = new Image("file:src/main/resources/tile4.png");
    private static final Map<Integer, Image> IMAGE_MAP = new HashMap<>();
    private final Random rnd = new Random();
    private ImageGenerator image;

    static{
        IMAGE_MAP.put(0, TILE_1);
        IMAGE_MAP.put(1, TILE_2);
        IMAGE_MAP.put(2, TILE_3);
        IMAGE_MAP.put(3, TILE_4);
    }

    public ImageGenerator getImage(){
        return this.image;
    }

    public void setImage(ImageGenerator image){
        this.image = image;
    }

    public TileGenerator(int x, int y) {
        setWidth(ContentGenerator.TILE_SIZE);
        setHeight(ContentGenerator.TILE_SIZE);

        relocate(x * ContentGenerator.TILE_SIZE, y * ContentGenerator.TILE_SIZE);

        setFill(new ImagePattern(place()));
    }

    public TileGenerator() {
    }

    public Image place(){
        int value = rnd.nextInt(4);
        return IMAGE_MAP.get(value);
    }
}
