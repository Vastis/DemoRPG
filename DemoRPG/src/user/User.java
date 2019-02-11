package user;

import gameCore.GameHandler;
import gameEntities.Character;
import gameEntitiesAttributes.CharacterAttributes;
import gameInterfaces.GraphicsInterface;
import javafx.scene.input.KeyCode;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class User implements GraphicsInterface {

    private GameHandler gameHandler;
    private Character character;

    private KeyCode keyCode;

    public User(GameHandler gameHandler, CharacterAttributes userDefinition){
        this.gameHandler = gameHandler;
        this.character = initCharacter(userDefinition);
        setListeners();
    }

    private Character initCharacter(CharacterAttributes userDefinition) {
        try {
            userDefinition.setGameHandler(this.gameHandler);
            Class<?> characterClass = Class.forName(userDefinition.getType());
            Constructor<?> characterConstructor = characterClass
                    .getConstructor(GameHandler.class, CharacterAttributes.class);
            return (Character)characterConstructor.newInstance(this.gameHandler, userDefinition);
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
            this.character.getMovement().replaceMovement(this.keyCode);
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
