package npcControls;

import gameCore.GameHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class NPCDialogPane extends AnchorPane {

    public NPCDialogPane(GameHandler gameHandler){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("npcDialogPane.fxml"));
            loader.setController(new NPCDialogPaneController(gameHandler));
            loader.setRoot(this);
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
