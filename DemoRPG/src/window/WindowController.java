package window;

import equipment.Equipment;
import equipment.EquipmentDisplay;
import equipment.Item;
import equipment.ItemAttributes;
import gameCore.Game;
import gameCore.GameHandler;
import gameCore.GameParams;
import gameEntitiesAttributes.CharacterAttributes;
import gameEntitiesAttributes.MonsterAttributes;
import internalWindow.InternalWindow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import npcControls.NPCDialogPane;
import user.User;
import utilities.XMLManager;

public class WindowController {

    private GameHandler gameHandler;
    private InternalWindow dialogWindow;
    private boolean dialogShown;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Canvas mainCanvas;

    @FXML
    private void initialize(){
        this.mainCanvas.setFocusTraversable(true);
        MonsterAttributes[] monstersDefinitions = XMLManager.loadMonsters();
        CharacterAttributes userDefinition = XMLManager.loadUser();
        this.gameHandler = new GameHandler(this, monstersDefinitions);
        User user = new User(this.gameHandler, userDefinition);
        this.gameHandler.initialize(user);

        this.dialogShown = false;
        Timeline fiveSecondsWonder = new Timeline(
                new KeyFrame(Duration.millis(1000 / GameParams.TICKS_PER_SECOND),
                        event -> checkNpc()));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();

        new Game(gameHandler).start();
    }

    private void checkNpc() {
        //gonna change that later...
        if (this.gameHandler.getNpcSpokenTo() != null) {
            if(!this.dialogShown) {
                this.dialogWindow = new InternalWindow(this.gameHandler,
                        this.gameHandler.getNpcSpokenTo().getMovement().getPosX(),
                        this.gameHandler.getNpcSpokenTo().getMovement().getPosY());
                this.rootPane.getChildren().add(this.dialogWindow);
                this.dialogShown = true;
                this.gameHandler.setConversationState(0);
            } else
                checkConversationState();
        } else {
            dialogShown = false;
            if(this.dialogWindow != null) {
                this.rootPane.getChildren().remove(this.dialogWindow);
                this.dialogWindow = null;
            }
        }
    }

    private void checkConversationState() {
        switch(this.gameHandler.getConversationState()){
            case 0:
                //idle
                break;
            case 1:
                //not yet
                break;
            case 2:
                Equipment equipment = new Equipment(
                        new ItemAttributes(16)
                );
                equipment.add(new Item(new ItemAttributes()));
                equipment.add(new Item(new ItemAttributes()));
                equipment.add(new Item(new ItemAttributes()));
                equipment.add(new Item(new ItemAttributes()));

                EquipmentDisplay equipmentDisplay = new EquipmentDisplay(equipment);
                this.dialogWindow.getController().setContent(equipmentDisplay);
                this.gameHandler.setConversationState(0);
                break;
            default:
                System.out.println("Weird, shouldn't have gotten here...");
        }
    }

    public Canvas getCanvas() {
        return this.mainCanvas;
    }
}
