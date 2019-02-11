package gameCore;

import gameEntities.Entity;
import gameEntities.Monster;
import gameEntitiesAttributes.MonsterAttributes;
import utilities.FileManager;

import java.io.IOException;

public class MonstersGenerator {

    private GameHandler gameHandler;
    private MonsterAttributes[] monstersDefinitions;

    public MonstersGenerator(GameHandler gameHandler){
        this.gameHandler = gameHandler;
        this.monstersDefinitions = this.gameHandler.getMonstersDefinitions();
    }

    public Entity[][] generate(){
        Entity[][] entities = null;
        try {
            int[][] monstersMatrix = FileManager.readFileAsMatrix(GameParams.MONSTERS_MATRIX_FILE_PATH);
            entities = new Entity[monstersMatrix.length][monstersMatrix[0].length];
            for(int row = 0; row < monstersMatrix.length; row++){
                for(int col = 0; col < monstersMatrix[row].length; col++){
                    if(monstersMatrix[row][col] != 0) {
                        MonsterAttributes monsterAttributes = monstersDefinitions[monstersMatrix[row][col]].clone();
                        monsterAttributes.setInitTileX(row);
                        monsterAttributes.setInitTileY(col);
                        entities[row][col] = new Monster(this.gameHandler, monsterAttributes);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("**from EntitiesManager: Couldn't load the monsters.");
        }
        return entities;
    }
}
