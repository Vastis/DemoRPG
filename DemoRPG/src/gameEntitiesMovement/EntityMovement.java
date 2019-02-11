package gameEntitiesMovement;

import gameCore.GameHandler;
import gameCore.GameParams;
import gameEntities.Entity;
import gameInterfaces.GraphicsInterface;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public abstract class EntityMovement implements GraphicsInterface {

    protected GameHandler gameHandler;
    protected Entity owner;

    protected Entity entitySelected;
    protected int tileX, tileY, xOffset, yOffset;
    protected double posX, posY;

    protected double currentSpeedX, currentSpeedY;
    protected double stepTicksLeft;
    protected boolean moving, selected;

    protected ArrayList<KeyCode> walkPath;

    public EntityMovement(GameHandler gameHandler, Entity owner){
        this.gameHandler = gameHandler;
        this.owner = owner;
        this.tileX = owner.getAttributes().getInitTileX();
        this.tileY = owner.getAttributes().getInitTileY();
        this.posX = (tileX + 0.5) * GameParams.TILE_SIZE;
        this.posY = (tileY + 0.5) * GameParams.TILE_SIZE;
        this.currentSpeedX = this.currentSpeedY = 0.0;
        this.moving = false;
        this.walkPath = new ArrayList<>();
        this.selected = false;
        this.gameHandler.getWorldManager().getTileAt(this.tileX, this.tileY).setEntityOccupying(this.owner);
    }

    public void enqueueMovement(KeyCode code){
        if(!this.moving) {
            this.walkPath.add(code);
        }
    }
    public void enqueueMovementPath(int deltaX, int deltaY, int absDeltaX, int absDeltaY){
        int movesToBeEnqueued = absDeltaX + absDeltaY;
        for(int i = 0; i < movesToBeEnqueued; i++) {
            if (absDeltaY > absDeltaX) {
                //move vertical
                if (deltaY < 0)
                    enqueueMovement(KeyCode.DOWN);
                else
                    enqueueMovement(KeyCode.UP);
                absDeltaY--;
            } else {
                //move horizontal
                if (deltaX < 0)
                    enqueueMovement(KeyCode.RIGHT);
                else
                    enqueueMovement(KeyCode.LEFT);
                absDeltaX--;
            }
        }
    }
    public void replaceMovement(KeyCode code){
        if(this.walkPath.size() > 0)
            this.walkPath.removeAll(this.walkPath);
        enqueueMovement(code);
    }
    public void replaceMovementPath(int deltaX, int deltaY, int absDeltaX, int absDeltaY){
        if(this.walkPath.size() > 0)
            this.walkPath.removeAll(this.walkPath);
        enqueueMovementPath(deltaX, deltaY, absDeltaX, absDeltaY);
    }
    private void move(KeyCode code){
        boolean allowedToMove = false;
        if(isWithinWorldBounds(code)) {
            switch (code) {
                case UP:
                    allowedToMove = tryToMove(0, -1);
                    break;
                case DOWN:
                    allowedToMove = tryToMove(0, 1);
                    break;
                case LEFT:
                    allowedToMove = tryToMove(-1, 0);
                    break;
                case RIGHT:
                    allowedToMove = tryToMove(1, 0);
            }
            if(allowedToMove) {
                this.stepTicksLeft = GameParams.TILE_SIZE / this.owner.getAttributes().getSpeed();
                this.moving = true;
            }
        }
    }
    private boolean isWithinWorldBounds(KeyCode code){
        int maxTileX = this.gameHandler.getWorldMaxRows();
        int maxTileY = this.gameHandler.getWorldMaxCols();

        switch (code) {
            case UP:
                if (this.tileY > 0)
                    return true;
                break;
            case DOWN:
                if (this.tileY < maxTileY - 1)
                    return true;
                break;
            case LEFT:
                if (this.tileX > 0)
                    return true;
                break;
            case RIGHT:
                if (this.tileX < maxTileX - 1)
                    return true;
                break;
        }
        return false;
    }
    private boolean tryToMove(int xOffset, int yOffset){
        if (!this.gameHandler.getWorldManager().getTileAt(this.tileX + xOffset, this.tileY + yOffset).isOccupied()
                && this.gameHandler.getWorldManager().getTileAt(this.tileX + xOffset, this.tileY + yOffset).canBeOccupied()) {
            this.gameHandler.getWorldManager().getTileAt(this.tileX, this.tileY).setEntityOccupying(null);
            this.gameHandler.getWorldManager().getTileAt(this.tileX + xOffset, this.tileY + yOffset).setEntityOccupying(this.owner);
            this.currentSpeedX = xOffset * this.owner.getAttributes().getSpeed();
            this.tileX += xOffset;
            this.currentSpeedY = yOffset * this.owner.getAttributes().getSpeed();
            this.tileY += yOffset;
            this.xOffset = xOffset;
            this.yOffset = yOffset;
            return true;
        } else
            return false;
    }

    @Override
    public void update() {
        if(this.moving){
            if(this.stepTicksLeft < 1.0){
                this.posX = (this.tileX + 0.5) * GameParams.TILE_SIZE;
                this.posY = (this.tileY + 0.5) * GameParams.TILE_SIZE;
                this.moving = false;
                this.currentSpeedX = 0.0;
                this.currentSpeedY = 0.0;
                this.gameHandler.getEntitiesManager().moveEntity(this.tileX - this.xOffset, this.tileY - this.yOffset, this.tileX, this.tileY);
            } else {
                this.posX += this.currentSpeedX;
                this.posY += this.currentSpeedY;
            }
            this.stepTicksLeft--;
        } else {
            if (this.walkPath.size() > 0)
                move(this.walkPath.remove(0));
        }
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public double getPosX() {
        return posX;
    }
    public double getPosY() {
        return posY;
    }
    public int getTileX() {
        return tileX;
    }
    public int getTileY() {
        return tileY;
    }
    public boolean isMoving() {
        return moving;
    }
    public Entity getEntitySelected() {
        return entitySelected;
    }
    public void setEntitySelected(Entity entitySelected) {
        if(this.entitySelected != null)
            this.entitySelected.getMovement().setSelected(false);
        if(entitySelected != null)
            entitySelected.getMovement().setSelected(true);
        this.entitySelected = entitySelected;
    }
    public boolean isDead(){
        return false;
    }
}
