package user;

import gameEngine.GameHandler;
import gameEngine.GameParams;
import gameEntities.Character;
import gameEntities.Entity;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class UserMouseListener {

    private GameHandler gameHandler;
    private Character character;

    public UserMouseListener(GameHandler gameHandler, Character character){
        this.gameHandler = gameHandler;
        this.character = character;
    }

    private void enqueueMovement(MouseEvent e) {
        if(!this.character.isMoving()) {
            int tileX = (int) ((e.getX() + this.gameHandler.getGameRenderer().getRelativePosX()) / GameParams.TILE_SIZE);
            int tileY = (int) ((e.getY() + this.gameHandler.getGameRenderer().getRelativePosY()) / GameParams.TILE_SIZE);

            int deltaX = this.character.getTileX() - tileX;
            int deltaY = this.character.getTileY() - tileY;
            int absDeltaX = Math.abs(deltaX);
            int absDeltaY = Math.abs(deltaY);

            this.character.enqueueMovementPath(deltaX, deltaY, absDeltaX, absDeltaY);
        }
    }
    private void selectEntity(MouseEvent e){
        int tileX = (int) ((e.getX() + this.gameHandler.getGameRenderer().getRelativePosX()) / GameParams.TILE_SIZE);
        int tileY = (int) ((e.getY() + this.gameHandler.getGameRenderer().getRelativePosY()) / GameParams.TILE_SIZE);

        if(this.gameHandler.getWorldManager().getTileAt(tileX, tileY).isOccupied()){
            if(this.character.getEntitySelected() != null) {
                this.character.getEntitySelected().setSelected(false);
                Entity newSelectedEntity = this.gameHandler.getWorldManager().getTileAt(tileX, tileY).getEntityOccupying();
                if(this.character.getEntitySelected().equals(newSelectedEntity))
                    this.character.setEntitySelected(null);
                else {
                    this.character.setEntitySelected(newSelectedEntity);
                    this.character.getEntitySelected().setSelected(true);
                }
            } else {
                this.character.setEntitySelected(this.gameHandler.getWorldManager().getTileAt(tileX, tileY).getEntityOccupying());
                this.character.getEntitySelected().setSelected(true);
            }
        }
    }
    public void onMouseClicked(MouseEvent e) {
        if(e.getButton() == MouseButton.PRIMARY)
            enqueueMovement(e);
        else if(e.getButton() == MouseButton.SECONDARY){
            selectEntity(e);
        }
    }
}
