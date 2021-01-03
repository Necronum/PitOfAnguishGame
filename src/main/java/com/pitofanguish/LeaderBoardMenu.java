package com.pitofanguish;

import com.pitofanguish.io.EventHandler;
import com.pitofanguish.io.Leader;
import com.pitofanguish.io.LeaderBoard;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import static com.pitofanguish.ContentGenerator.*;
import static com.pitofanguish.ContentGenerator.TILE_SIZE;

public class LeaderBoardMenu {
    private Stage mainStage;

    public Parent leaderMenu(Stage primaryStage, LeaderBoard leaderBoard){
        this.mainStage = primaryStage;

        BorderPane root = new BorderPane();
        root.setPrefSize(WIDTH *TILE_SIZE,(HEIGHT *TILE_SIZE)+40);
        BackgroundSize backgroundSize = new BackgroundSize(Game.BACKGROUND_WIDTH, Game.BACKGROUND_HEIGHT, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(Game.BACKGROUND_1, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane logo = new GridPane();
        logo.setAlignment(Pos.TOP_CENTER);
        ImageView logoImage = new ImageView(MainMenu.LOGO);
        Text text = new Text("Leaderboard");
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
        text.setFill(Color.BLANCHEDALMOND);
        logo.add(logoImage, 0, 0, 1, 1);
        logo.add(text, 0,1,1,1);

        GridPane leaders = new GridPane();
        ListView<String> list = new ListView<>();
        StringBuilder leaderString = new StringBuilder();
        leaders.setAlignment(Pos.TOP_CENTER);
        Leader leader;
        for (int i = 0; i< leaderBoard.leaderBoardLength(); i++){
            leader = leaderBoard.getLeaders().get(i);
            leaderString.append(i+1);
            leaderString.append(". ");
            leaderString.append(leader.getName());
            leaderString.append(": ");
            leaderString.append(leader.getScore());
            list.getItems().add(leaderString.toString());
            leaderString.delete(0, leaderString.length());
        }

        Button mainMenu = new Button("Main menu");
        mainMenu.setOnAction(e -> {
            Platform.runLater(() -> {
                mainStage.close();
                new Game().start(new Stage());
            });
        });

        EventHandler event = new EventHandler();
        primaryStage.setOnCloseRequest(event.confirmCloseEventHandler);

        mainMenu.setStyle("-fx-background-color: #100c08; -fx-text-fill: #fffafa");

        leaders.add(list, 0, 0, 1, 1);
        leaders.add(mainMenu, 0, 1, 1, 1);

        root.setBackground(background);
        root.setTop(logo);
        root.setCenter(leaders);
        return root;
    }
}
