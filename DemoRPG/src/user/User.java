package user;

import gameEngine.GameHandler;
import gameEngine.GameParams;
import gameEntities.Character;
import gameInterfaces.GraphicsInterface;
import javafx.scene.input.KeyCode;
import vocations.Mage;

public class User implements GraphicsInterface {

    private GameHandler gameHandler;
    private Character character;

    private KeyCode keyCode;

    public User(GameHandler gameHandler){
        this.gameHandler = gameHandler;
        this.character = new Mage(gameHandler,100, 100, GameParams.PLAYER_SPEED);
        setListeners();
        log();
    }

    //tmp
    private void log() {
        System.err.println("From User**: ");
        System.err.println("\t 3. Entities data imported from XML files.");
        System.err.println("\t 4. Showing damage dealt on screen.");
        System.err.println("\t 5. NPCs - talking and shit.");
        System.err.println("\t 6. Equipment, loot.");
        System.err.println("\t 7. Trading.");
        System.err.println("\t 8. Resistances, balancing attributes calculations.");
        System.err.println("I'd do 5,6,7 at popups, equipment at right panel");
    }

    private void setListeners(){
        UserMouseListener mouseListener = new UserMouseListener(this.gameHandler, this.character);
        this.gameHandler.getCanvas().setOnKeyPressed(e -> this.keyCode = e.getCode());
        this.gameHandler.getCanvas().setOnKeyReleased(e -> this.keyCode = null);
        this.gameHandler.getCanvas().setOnMouseClicked(e -> mouseListener.onMouseClicked(e));
    }



    @Override
    public void update(){
        if(this.keyCode != null)
            character.replaceMovement(this.keyCode);
        this.character.update();
    }

    @Override
    public void draw() {
        this.character.draw();
    }

    public Character getCharacter() {
        return character;
    }
}
