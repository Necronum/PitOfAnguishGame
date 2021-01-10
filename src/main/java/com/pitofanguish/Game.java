package com.pitofanguish;

import com.pitofanguish.menu.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application {
    public static final Image BACKGROUND_1 = new Image("file:src/main/resources/background.jpg");
    public static final int BACKGROUND_WIDTH = 100;
    public static final int BACKGROUND_HEIGHT = 100;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene = new Scene(new MainMenu().menu(primaryStage));
        primaryStage.setTitle("Pit of Anguish");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

