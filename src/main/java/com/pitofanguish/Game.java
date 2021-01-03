package com.pitofanguish;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Game extends Application {
    public final static Image BACKGROUND_1 = new Image("file:src/main/resources/background.jpg");
    public final static int BACKGROUND_WIDTH = 100;
    public final static int BACKGROUND_HEIGHT = 100;

    @Override
    public void start(Stage primaryStage){
        Scene scene = new Scene(new MainMenu().menu(primaryStage));
        primaryStage.setTitle("Pit of Anguish");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

