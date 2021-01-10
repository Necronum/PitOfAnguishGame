package com.pitofanguish.menu;

import com.pitofanguish.Game;
import com.pitofanguish.imagegenerator.ContentGenerator;
import com.pitofanguish.io.EventHandler;
import com.pitofanguish.io.LeaderBoard;
import com.pitofanguish.io.PoASave;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import static com.pitofanguish.imagegenerator.ContentGenerator.TILE_SIZE;
import static com.pitofanguish.imagegenerator.ContentGenerator.WIDTH;
import static com.pitofanguish.imagegenerator.ContentGenerator.HEIGHT;


public class MainMenu {
    public static final Image LOGO = new Image("file:src/main/resources/logo.png");
    public static final int TEXT_FIELD_SIZE = 220;
    public static final int BUTTON_SIZE = 100;
    public static String nickname;
    private final PoASave loader = new PoASave();

    public MainMenu() throws IOException {
    }

    //starting menu with buttons and logo
    public Parent menu(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPrefSize(WIDTH *TILE_SIZE,(HEIGHT *TILE_SIZE)+40);
        BackgroundSize backgroundSize = new BackgroundSize(Game.BACKGROUND_WIDTH, Game.BACKGROUND_HEIGHT, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(Game.BACKGROUND_1, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane logo = new GridPane();
        logo.setAlignment(Pos.TOP_CENTER);
        ImageView logoImage = new ImageView(LOGO);
        logo.add(logoImage, 0, 0, 1, 1);

        GridPane options = new GridPane();
        options.setAlignment(Pos.CENTER);
        EventHandler event = new EventHandler();
        primaryStage.setOnCloseRequest(event.confirmCloseEventHandler);

        Text setNickname = new Text("Press \"Enter\" to set nickname");
        setNickname.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        setNickname.setFill(Color.BISQUE);
        setNickname.setTextAlignment(TextAlignment.JUSTIFY);
        TextField nicknameField = new TextField();
        nicknameField.setAlignment(Pos.CENTER);
        nicknameField.setMaxWidth(TEXT_FIELD_SIZE);
        nicknameField.setOnAction(e-> nickname = nicknameField.getText());

        Button startGame = new Button("Start Game");
        startGame.setMaxWidth(BUTTON_SIZE);
        startGame.setOnAction(e -> {
            if (nickname == null){
                event.emptyNicknameEventHandler();
            } else {
                newGame(primaryStage);
            }
        });

        Button leaderBoardShow = new Button("Leaderboard");
        leaderBoardShow.setMaxWidth(BUTTON_SIZE);
        leaderBoardShow.setOnAction(e -> loadLeaderboard(primaryStage));

        Button endButton = new Button("Exit Game");
        endButton.setMaxWidth(BUTTON_SIZE);
        endButton.setOnAction(e -> primaryStage.fireEvent(
                new WindowEvent(
                        primaryStage,
                        WindowEvent.WINDOW_CLOSE_REQUEST
                    )
                )
        );

        startGame.setStyle("-fx-background-color: #100c08; -fx-text-fill: #fffafa");
        leaderBoardShow.setStyle("-fx-background-color: #100c08; -fx-text-fill: #fffafa");
        endButton.setStyle("-fx-background-color: #100c08; -fx-text-fill: #fffafa");
        nicknameField.setStyle("-fx-background-color: #100c08; -fx-text-fill: #fffafa");

        options.add(setNickname, 0, 0,2,1);
        options.add(nicknameField, 0, 1,2,1);
        options.add(startGame, 0, 2,1,1);
        options.add(leaderBoardShow, 0, 3,1,1);
        options.add(endButton, 1, 3,1,1);

        root.setBackground(background);
        root.setTop(logo);
        root.setCenter(options);
        return root;
    }

    private void newGame (Stage primaryStage){
        ContentGenerator game = new ContentGenerator();
        primaryStage.getScene().setRoot(game.createContent(primaryStage));
    }

    private void loadLeaderboard (Stage primaryStage){
        LeaderBoard leaderBoard = loader.loadLeaderBoard();
        LeaderBoardMenu leaderBoardMenu = new LeaderBoardMenu();
        primaryStage.getScene().setRoot(leaderBoardMenu.leaderMenu(primaryStage, leaderBoard));
    }
}
