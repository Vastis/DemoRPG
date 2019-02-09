package window;

import gameEngine.Game;
import gameEngine.GameHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import user.User;

public class WindowController {

    @FXML
    private Canvas mainCanvas;

    @FXML
    private void initialize(){
        this.mainCanvas.setFocusTraversable(true);
        GameHandler gameHandler = new GameHandler(this.mainCanvas);
        User user = new User(gameHandler);
        gameHandler.initialize(user);

        new Game(gameHandler).start();
    }
}
