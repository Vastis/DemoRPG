package gameEntities;

import gameEngine.GameHandler;
import gameEngine.GameParams;
import utilities.FileManager;

import java.io.IOException;
import java.util.ArrayList;

public class MonstersGenerator {

    private GameHandler gameHandler;
    private EntityAttributes[] monstersDefinitions;

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
                    if(monstersMatrix[row][col] != 0)
                        entities[row][col] =
                                new Monster(this.gameHandler, monstersDefinitions[monstersMatrix[row][col]].clone(), row, col);
                }
            }
        } catch (IOException e) {
            System.err.println("**from EntitiesManager: Couldn't load the monsters.");
        }
        return entities;
    }
}
