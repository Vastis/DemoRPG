package gameWorld;

import gameCore.GameHandler;
import gameCore.GameParams;
import gameEntities.Entity;
import gameInterfaces.GraphicsInterface;

public class Tile implements GraphicsInterface {

    private GameHandler gameHandler;
    private int tileId;
    private double tileX, tileY;
    private Entity entityOccupying;
    private boolean canOccupy;

    private TileGraphics graphics;

    public Tile(GameHandler gameHandler, int tileId, double tileX, double tileY){
        this.gameHandler = gameHandler;
        this.tileId = tileId;
        this.tileX = tileX;
        this.tileY = tileY;
        this.graphics = new TileGraphics(tileId);
        this.entityOccupying = null;
        if(tileId == 3)
            this.canOccupy = false;
        else
            this.canOccupy = true;
    }

    @Override
    public void update() {/*static*/}
    @Override
    public void draw() {
        this.gameHandler.getGameRenderer().fillRectRelativeToPlayer(graphics.getGraphics(),
                1+ tileX * GameParams.TILE_SIZE,
                1 + tileY * GameParams.TILE_SIZE,
                GameParams.TILE_SIZE - 2,
                GameParams.TILE_SIZE - 2);
    }

    public double getTileX() {
        return tileX;
    }
    public double getTileY() {
        return tileY;
    }
    public int getTileId() {
        return tileId;
    }
    public boolean isOccupied() {
        return !(entityOccupying == null);
    }
    public void setEntityOccupying(Entity entityOccupying) {
        this.entityOccupying = entityOccupying;
    }
    public Entity getEntityOccupying() {
        return entityOccupying;
    }
    public boolean canBeOccupied() {
        return this.canOccupy;
    }
    public void setCanOccupy(boolean canOccupy) {
        this.canOccupy = this.canOccupy && canOccupy;
    }
}
