package window;

import gameCore.Game;
import gameCore.GameHandler;
import gameEntitiesAttributes.CharacterAttributes;
import gameEntitiesAttributes.EntityAttributes;
import gameEntitiesAttributes.MonsterAttributes;
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
        MonsterAttributes[] monstersDefinitions = XMLManager.loadMonsters();
        CharacterAttributes userDefinition = XMLManager.loadUser();
        GameHandler gameHandler = new GameHandler(this.mainCanvas, monstersDefinitions);
        User user = new User(gameHandler, userDefinition);
        gameHandler.initialize(user);

        new Game(gameHandler).start();
    }
}
