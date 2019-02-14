package npcControls;

import gameCore.GameHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class NPCDialogWindow extends Stage {

    public NPCDialogWindow(GameHandler gameHandler){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("npcDialogWindow.fxml"));
            loader.setController(new NPCDialogWindowController(gameHandler));
            Parent root = loader.load();
            this.initStyle(StageStyle.UNDECORATED);
            this.setResizable(false);
            Scene scene = new Scene(root);
            this.setScene(scene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
