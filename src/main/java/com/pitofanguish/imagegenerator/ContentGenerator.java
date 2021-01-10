package com.pitofanguish.imagegenerator;

import com.pitofanguish.*;
import com.pitofanguish.io.EventHandler;
import com.pitofanguish.io.Leader;
import com.pitofanguish.io.LeaderBoard;
import com.pitofanguish.io.PoASave;
import com.pitofanguish.menu.MainMenu;
import com.pitofanguish.movement.CharacterType;
import com.pitofanguish.movement.MoveResult;
import com.pitofanguish.movement.MoveType;
import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContentGenerator {
    public static final  int TILE_SIZE = 210;
    public static final int WIDTH = 3;
    public static final int HEIGHT = 3;
    public static final boolean[][] CORNER = new boolean[WIDTH][HEIGHT];
    static{
        CORNER[0][0] = true;
        CORNER[0][1] = false;
        CORNER[0][2] = true;
        CORNER[1][0] = false;
        CORNER[1][1] = false;
        CORNER[1][2] = false;
        CORNER[2][0] = true;
        CORNER[2][1] = false;
        CORNER[2][2] = true;
    }

    private final LongProperty health = new SimpleLongProperty(5);
    private final LongProperty attackPower = new SimpleLongProperty(2);
    private final LongProperty gold = new SimpleLongProperty(0);
    public Text tHP = new Text();
    public Text tAP = new Text();
    public Text tGold = new Text();
    private final TileGenerator[][] board = new TileGenerator[WIDTH][HEIGHT];
    private final Group tileGroup = new Group();
    private final Group charactersGroup = new Group();
    private final ItemTypeGenerator typeGenerator = new ItemTypeGenerator();
    private Stage mainStage;
    private PoASave loader = null;
    private int roundNumber = 0;
    private final EventHandler event = new EventHandler();

    //main method for game
    public Parent createContent(Stage primaryStage) {
        this.mainStage = primaryStage;
        //Main border
        BorderPane root = new BorderPane();
        root.setPrefSize(WIDTH * TILE_SIZE, (HEIGHT * TILE_SIZE)+40);
        root.getChildren().addAll(tileGroup, charactersGroup);

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                TileGenerator tile = new TileGenerator(i, j);
                board[i][j] = tile;
                tileGroup.getChildren().add(tile);

                ImageGenerator image = makeImage(typeGenerator.randomizeType(i, j), i, j);
                tile.setImage(image);
                charactersGroup.getChildren().add(image);
            }
        }

        // Menu border
        BorderPane menu = new BorderPane();
        menu.setPadding(new Insets(10, 10, 10, 10));
        BackgroundSize backgroundSize = new BackgroundSize(Game.BACKGROUND_WIDTH, Game.BACKGROUND_HEIGHT, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(Game.BACKGROUND_1, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane options = new GridPane();
        options.setAlignment(Pos.BOTTOM_CENTER);
        options.setHgap(10);

        Button endButton = new Button("Exit Game");
        endButton.setOnAction(e -> primaryStage.fireEvent(
                new WindowEvent(
                        primaryStage,
                        WindowEvent.WINDOW_CLOSE_REQUEST
                )
            )
        );

        EventHandler event = new EventHandler();
        primaryStage.setOnCloseRequest(event.confirmCloseEventHandler);

        tHP.textProperty().bind(Bindings.createStringBinding(() -> ("HP: " + health.get()), health));
        tHP.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        tHP.setFill(Color.RED);

        tAP.textProperty().bind(Bindings.createStringBinding(() -> ("AP: " + attackPower.get()), attackPower));
        tAP.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        tAP.setFill(Color.BLUE);

        tGold.textProperty().bind(Bindings.createStringBinding(() -> ("Gold: " + gold.get()), gold));
        tGold.setFont(Font.font("Verdana", FontWeight.THIN, 20));
        tGold.setFill(Color.YELLOW);

        endButton.setStyle("-fx-background-color: #100c08; -fx-text-fill: #fffafa");

        options.add(tHP, 0, 0, 1, 1);
        options.add(tAP, 1, 0, 1, 1);
        options.add(endButton, 2, 0, 1, 1);
        options.add(tGold, 3, 0, 1, 1);

        menu.setBackground(background);
        menu.setCenter(options);
        root.setBottom(menu);
        return root;
    }

    private int toBoard(double pixel){
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

    //method that will determine the move type for makeImage method
    private MoveResult tryMove(ImageGenerator image, int newX, int newY){
        int x0 = toBoard(image.getOldX());
        int y0 = toBoard(image.getOldY());
        MoveResult move = new MoveResult(MoveType.NONE);
        if ((newX != x0 && newY != y0) || (Math.abs(newX - x0) ==2 || Math.abs(newY - y0) ==2) || (newX == x0 && newY == y0)){
            move = new MoveResult(MoveType.NONE);
        } else if (board[newX][newY].getImage().getType()== CharacterType.FOOD){
            move = new MoveResult(MoveType.COLLECT_FOOD, board[newX][newY].getImage());
        } else if (board[newX][newY].getImage().getType()==CharacterType.WEAPON){
            move = new MoveResult(MoveType.COLLECT_WEAPON, board[newX][newY].getImage());
        } else if (board[newX][newY].getImage().getType()==CharacterType.MONSTER){
            move = new MoveResult(MoveType.ATTACK, board[newX][newY].getImage());
        } else if (board[newX][newY].getImage().getType()==CharacterType.GOLD){
            move = new MoveResult(MoveType.COLLECT_GOLD, board[newX][newY].getImage());
        }
        return move;
    }

    //method for createContent to operate movement on board
    private ImageGenerator makeImage(CharacterType type, int x, int y){
        ImageGenerator image = new ImageGenerator(type, x, y);

        if(type == CharacterType.PLAYER){
            image.setOnMouseReleased(e -> {
                int newX = toBoard(image.getLayoutX());
                int newY = toBoard(image.getLayoutY());

                MoveResult result;

                int x0 = toBoard(image.getOldX());
                int y0 = toBoard(image.getOldY());

                if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                    result = new MoveResult(MoveType.NONE);
                } else {
                    result = tryMove(image, newX, newY);
                }

                switch (result.getType()) {
                    case NONE:
                        image.abortMove();
                        break;
                    case COLLECT_FOOD:
                        collectFood(image, result, newX, newY, x0, y0);
                        break;
                    case COLLECT_GOLD:
                        collectGold(image, result, newX, newY, x0, y0);
                        break;
                    case COLLECT_WEAPON:
                        collectWeapon(image,result, newX, newY, x0, y0);
                        break;
                    case ATTACK:
                        attack(image, result, newX, newY, x0, y0);
                        break;
                }
            });
            }
        return image;
    }

    //method to create another content after move
    private void NewContentGenerator(int newX, int newY, int oldX, int oldY){
        ImageGenerator lastImage;
        ImageGenerator image;
        int x;
        int y;
        //when new position on X is higher than old X
        if (newX > oldX){
            if(oldX == 0){
                x = oldX;
            } else {
                x = oldX-1;
            }
            y = oldY;

            if(!(CORNER[oldX][oldY] || (oldX == 0 && oldY == 1))){
                image = board[x][y].getImage();
                image.move(oldX, y);
                board[x][y].setImage(null);
                board[oldX][y].setImage(image);
            }

            lastImage = makeImage(typeGenerator.randomizeTypeRound(roundNumber), x, y);
            board[x][y].setImage(lastImage);
            charactersGroup.getChildren().add(lastImage);
        } else if (newX < oldX){   //when new position on X is lower than old X
            if(oldX == WIDTH-1){
                x = oldX;
            } else {
                x = oldX+1;
            }
            y = oldY;

            if(!(CORNER[oldX][oldY] || (oldX == 2 && oldY ==1))){
                image = board[x][y].getImage();
                image.move(oldX, y);
                board[x][y].setImage(null);
                board[oldX][y].setImage(image);
            }

            lastImage = makeImage(typeGenerator.randomizeTypeRound(roundNumber), x, y);
            board[x][y].setImage(lastImage);
            charactersGroup.getChildren().add(lastImage);
        } else if (newY > oldY){  //when new position on Y is higher than old Y
            if(oldY == 0){
                y = oldY;
            } else {
                y = oldY-1;
            }
            x = oldX;

            if(!(CORNER[oldX][oldY] || (oldX==1 && oldY==0))){
                image = board[x][y].getImage();
                image.move(x, oldY);
                board[x][y].setImage(null);
                board[x][oldY].setImage(image);
            }

            lastImage = makeImage(typeGenerator.randomizeTypeRound(roundNumber), x, y);
            board[x][y].setImage(lastImage);
            charactersGroup.getChildren().add(lastImage);
        } else if(newY < oldY){   //when new position on Y is lower than old Y
            if(oldY == HEIGHT-1){
                y = oldY;
            } else {
                y = oldY+1;
            }
            x = oldX;

            if(!(CORNER[oldX][oldY] || (oldX==1 && oldY ==2))){
                image = board[x][y].getImage();
                image.move(x, oldY);
                board[x][y].setImage(null);
                board[x][oldY].setImage(image);
            }

            lastImage = makeImage(typeGenerator.randomizeTypeRound(roundNumber), x, y);
            board[x][y].setImage(lastImage);
            charactersGroup.getChildren().add(lastImage);
        }
    }

    public boolean checkIfDead(){
        boolean dead = false;
        if (health.intValue() == 1 && attackPower.intValue() == 0){
            dead = true;
        }
        return dead;
    }

    private void collectFood(ImageGenerator image, MoveResult result, int newX, int newY, int x0, int y0){
        imageMove(image, result, newX, newY, x0, y0);
        health.set(health.get() + 1);
        NewContentGenerator(newX, newY, x0, y0);
    }

    private void collectGold(ImageGenerator image, MoveResult result, int newX, int newY, int x0, int y0){
        imageMove(image, result, newX, newY, x0, y0);
        gold.set(gold.get() + 1);
        NewContentGenerator(newX, newY, x0, y0);
    }

    private void collectWeapon(ImageGenerator image, MoveResult result, int newX, int newY, int x0, int y0){
        imageMove(image, result, newX, newY, x0, y0);
        attackPower.set(attackPower.get() + 1);
        NewContentGenerator(newX, newY, x0, y0);
    }

    private void attack(ImageGenerator image, MoveResult result, int newX, int newY, int x0, int y0){
        imageMove(image, result, newX, newY, x0, y0);

        if(checkIfDead()){
            health.set(health.get() - 1);
            Leader leader = new Leader(MainMenu.nickname, gold.intValue());
            try {
                loader = new PoASave();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            List<Leader> leaderList = new ArrayList<>();
            leaderList.add(leader);
            LeaderBoard leaderBoard = new LeaderBoard();
            LeaderBoard oldLeaderBoard = loader.loadLeaderBoard();
            leaderList.addAll(oldLeaderBoard.getLeaders());
            leaderBoard.setLeaders(leaderList);
            try {
                assert loader != null;
                loader.saveLeaderBoard(leaderBoard);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            event.gameOver(mainStage);
        } else {
            if(attackPower.get() > 0){
                attackPower.set(attackPower.get() - 1);
            } else {
                health.set(health.get() - 1);
            }
            gold.set(gold.get() + 1);
        }
        NewContentGenerator(newX, newY, x0, y0);
    }

    private void imageMove(ImageGenerator image, MoveResult result, int newX, int newY, int x0, int y0) {
        ImageGenerator otherImage = result.getImage();
        board[toBoard(otherImage.getOldX())][toBoard(otherImage.getOldY())].setImage(null);
        charactersGroup.getChildren().remove(otherImage);
        roundNumber++;

        image.move(newX, newY);
        board[x0][y0].setImage(null);
        board[newX][newY].setImage(image);
    }
}
