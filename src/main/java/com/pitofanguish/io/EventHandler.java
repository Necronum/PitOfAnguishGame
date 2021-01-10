package com.pitofanguish.io;

import com.pitofanguish.Game;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

public class EventHandler {
    public final javafx.event.EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "YOU REALLY DARE TO EXIT PIT OF ANGUISH!?"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(ButtonType.OK);
        exitButton.setText("Yes, please let me out");
        closeConfirmation.setTitle("I am disappointed");
        closeConfirmation.setHeaderText(null);
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())){
            event.consume();
        }
    };

    public final void emptyNicknameEventHandler(){
        Alert emptyNickname = new Alert(Alert.AlertType.WARNING);
        emptyNickname.setTitle("Your nickname is empty!");
        emptyNickname.setHeaderText(null);
        emptyNickname.setContentText("Please enter Your nickname and press \"ENTER\" before starting game.");
        emptyNickname.showAndWait();
    }

    public final void gameOver(Stage mainStage){
        Alert gameEnder = new Alert(
                Alert.AlertType.INFORMATION,
                "GAME OVER"
        );
        gameEnder.setHeaderText("GAME OVER");
        ButtonType buttonOne = new ButtonType("Main menu");
        ButtonType buttonTwo = new ButtonType("Exit");
        gameEnder.getButtonTypes().setAll(buttonOne,buttonTwo);
        Optional<ButtonType> click = gameEnder.showAndWait();
        if(click.get() == buttonOne){
            Platform.runLater(() -> {
                mainStage.close();
                try {
                    new Game().start(new Stage());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        } else if (click.get() == buttonTwo){
            Platform.exit();
        }
    }
}
