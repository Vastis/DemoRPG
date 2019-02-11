package npcControls;

import gameCore.GameHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class NPCDialogWindowController {

    private GameHandler gameHandler;
    @FXML
    private TextArea dialogTextArea;

    public NPCDialogWindowController(GameHandler gameHandler){
        this.gameHandler = gameHandler;
    }

    @FXML
    private void onTalkButtonClicked(){
        dialogTextArea.setText("I've got nothing to say for now...");
    }
    @FXML
    private void onTradeButtonClicked(){
        dialogTextArea.setText("I've got nothing for sale right now...");
    }
    @FXML
    private void onLeaveButtonClicked(){
        this.gameHandler.getUserCharacter().getMovement().setEntitySelected(null);
        this.gameHandler.setNpcSpokenTo(null);
        ((NPCDialogWindow)dialogTextArea.getParent().getScene().getWindow()).close();
    }
}
