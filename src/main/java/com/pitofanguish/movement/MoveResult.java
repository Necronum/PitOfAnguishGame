package com.pitofanguish.movement;

import com.pitofanguish.imagegenerator.ImageGenerator;

//class to pass MoveType and image for next move
public class MoveResult {
    private final MoveType type;

    public MoveType getType(){
        return type;
    }

    private final ImageGenerator image;

    public ImageGenerator getImage(){
        return image;
    }

    public MoveResult(MoveType type){
        this.type = type;
        this.image = null;
    }

    public MoveResult(MoveType type, ImageGenerator image){
        this.type = type;
        this.image = image;
    }
}
