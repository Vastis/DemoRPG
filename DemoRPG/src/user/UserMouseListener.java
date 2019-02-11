package user;

import gameCore.GameHandler;
import gameCore.GameParams;
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
        if(!this.character.getMovement().isMoving()) {
            int tileX = (int) ((e.getX() + this.gameHandler.getGameRenderer().getRelativePosX()) / GameParams.TILE_SIZE);
            int tileY = (int) ((e.getY() + this.gameHandler.getGameRenderer().getRelativePosY()) / GameParams.TILE_SIZE);

            int deltaX = this.character.getMovement().getTileX() - tileX;
            int deltaY = this.character.getMovement().getTileY() - tileY;
            int absDeltaX = Math.abs(deltaX);
            int absDeltaY = Math.abs(deltaY);

            this.character.getMovement().enqueueMovementPath(deltaX, deltaY, absDeltaX, absDeltaY);
        }
    }
    private void selectEntity(MouseEvent e){
        int tileX = (int) ((e.getX() + this.gameHandler.getGameRenderer().getRelativePosX()) / GameParams.TILE_SIZE);
        int tileY = (int) ((e.getY() + this.gameHandler.getGameRenderer().getRelativePosY()) / GameParams.TILE_SIZE);

        if(this.gameHandler.getWorldManager().getTileAt(tileX, tileY).isOccupied()){
            Entity newSelectedEntity = this.gameHandler.getWorldManager().getTileAt(tileX, tileY).getEntityOccupying();
            if(!newSelectedEntity.getMovement().isDead()) {
                if (this.character.getMovement().getEntitySelected() != null) {
                    if (this.character.getMovement().getEntitySelected().equals(newSelectedEntity)) {
                        this.character.getMovement().getEntitySelected().getMovement().setSelected(false);
                        this.character.getMovement().setEntitySelected(null);
                        return;
                    }
                }
                this.character.getMovement().setEntitySelected(newSelectedEntity);
            } else
                System.out.println("You clicked at a dead body...");
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
