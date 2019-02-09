package user;

import gameEngine.GameHandler;
import gameEngine.GameParams;
import gameEntities.Character;
import gameEntities.EntityAttributes;
import gameInterfaces.GraphicsInterface;
import javafx.scene.input.KeyCode;
import vocations.Sorcerer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class User implements GraphicsInterface {

    private GameHandler gameHandler;
    private Character character;

    private KeyCode keyCode;

    public User(GameHandler gameHandler, EntityAttributes userDefinition){
        this.gameHandler = gameHandler;
        this.character = initCharacter(userDefinition);
        setListeners();
        log();
    }

    private Character initCharacter(EntityAttributes userDefinition) {
        try {
            Class<?> characterClass = Class.forName(userDefinition.getType());
            Constructor<?> characterConstructor = characterClass
                    .getConstructor(GameHandler.class, EntityAttributes.class, Integer.TYPE, Integer.TYPE);
            return (Character)characterConstructor.newInstance(this.gameHandler, userDefinition,
                    userDefinition.getInitTileX(), userDefinition.getInitTileY());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
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
