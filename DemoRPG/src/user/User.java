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
