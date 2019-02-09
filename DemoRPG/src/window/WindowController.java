package window;

import gameEngine.Game;
import gameEngine.GameHandler;
import gameEntities.EntityAttributes;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import user.User;
import utilities.XMLManager;

public class WindowController {

    @FXML
    private Canvas mainCanvas;

    @FXML
    private void initialize(){
        this.mainCanvas.setFocusTraversable(true);
        EntityAttributes[] monstersDefinitions = XMLManager.loadMonsters();
        EntityAttributes userDefinition = XMLManager.loadUser();
        GameHandler gameHandler = new GameHandler(this.mainCanvas, monstersDefinitions);
        User user = new User(gameHandler, userDefinition);
        gameHandler.initialize(user);

        new Game(gameHandler).start();
    }
}
