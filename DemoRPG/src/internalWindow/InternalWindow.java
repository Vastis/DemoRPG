package internalWindow;

import gameCore.GameHandler;
import gameCore.GameRenderer;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InternalWindow extends AnchorPane {

    private InternalWindowController controller;

    public InternalWindow(GameHandler gameHandler, double posX, double posY){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("internalWindow.fxml"));
            this.getStylesheets().add("/internalWindow/internalWindowStyle.css");
            this.controller = new InternalWindowController(gameHandler);

            double relativePosX = gameHandler.getGameRenderer().getPosRelativeToPlayerX(posX) - 200;
            double relativePosY = gameHandler.getGameRenderer().getPosRelativeToPlayerY(posY) - 200;
            this.setLayoutX(relativePosX);
            this.setLayoutY(relativePosY);

            loader.setController(this.controller);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e){
            System.err.println("Couldn't load the content...");
        }
    }

    public InternalWindowController getController() {
        return controller;
    }
}
