package gameEngine;

import gameEntities.Entity;
import gameEntities.MonstersGenerator;
import gameInterfaces.GraphicsInterface;

public class EntitiesManager implements GraphicsInterface {

    private GameHandler gameHandler;
    private Entity[][] entities;
    private MonstersGenerator monstersGenerator;

    public EntitiesManager(GameHandler gameHandler){
        this.gameHandler = gameHandler;
        this.monstersGenerator = new MonstersGenerator(gameHandler);
        this.entities = this.monstersGenerator.generate();
    }

    public void moveEntity(int beginRow, int beginCol, int endRow, int endCol){
        this.entities[endRow][endCol] = this.entities[beginRow][beginCol];
        this.entities[beginRow][beginCol] = null;
    }

    @Override
    public void update() {
        for(int i = 0; i < this.gameHandler.getWorldMaxRows(); i++){
            for(int j = 0; j < this.gameHandler.getWorldMaxCols(); j++){
                if(this.entities[i][j] != null)
                    this.entities[i][j].update();
            }
        }
    }
    @Override
    public void draw() {
        int playerTileX = this.gameHandler.getUserCharacter().getTileX();
        int playerTileY = this.gameHandler.getUserCharacter().getTileY();
        int xOffset = this.gameHandler.getTilesRows()/2;
        int yOffset = this.gameHandler.getTilesCols()/2;

        int beginRow = playerTileX - xOffset;
        int endRow = playerTileX + xOffset;
        int beginCol = playerTileY - yOffset;
        int endCol = playerTileY + yOffset;

        for(int i = beginRow - 3; i < endRow + 3; i++){
            for(int j = beginCol - 3; j < endCol + 3; j++){
                if(this.entities[i][j] != null)
                    this.entities[i][j].draw();
            }
        }
    }
}
