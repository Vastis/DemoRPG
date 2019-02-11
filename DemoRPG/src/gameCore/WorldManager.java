package gameCore;

import gameInterfaces.GraphicsInterface;
import gameWorld.Tile;
import gameWorld.WorldMap;

public class WorldManager implements GraphicsInterface {

    private GameHandler gameHandler;
    private WorldMap worldMap;

    public WorldManager(GameHandler gameHandler){
        this.gameHandler = gameHandler;
        this.worldMap = new WorldMap(gameHandler);
    }

    @Override
    public void update() {}

    @Override
    public void draw() {
        int playerTileX = this.gameHandler.getUserCharacter().getMovement().getTileX();
        int playerTileY = this.gameHandler.getUserCharacter().getMovement().getTileY();
        int xOffset = this.gameHandler.getTilesRows()/2;
        int yOffset = this.gameHandler.getTilesCols()/2;

        int beginRow = playerTileX - xOffset;
        int endRow = playerTileX + xOffset;
        int beginCol = playerTileY - yOffset;
        int endCol = playerTileY + yOffset;

        for(int i = beginRow - 2; i < endRow + 2; i++){
            for(int j = beginCol - 2; j < endCol + 2; j++){
                this.worldMap.getTileAt(i,j).draw();
            }
        }
    }

    public Tile getTileAt(int i, int j){
        return this.worldMap.getTileAt(i, j);
    }
    public int getWorldRows(){
        return this.worldMap.getRows();
    }
    public int getWorldCols(){
        return this.worldMap.getCols();
    }
}
