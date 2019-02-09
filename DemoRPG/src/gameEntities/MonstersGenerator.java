package gameEntities;

import gameEngine.GameHandler;
import gameEngine.GameParams;
import utilities.FileManager;

import java.io.IOException;

public class MonstersGenerator {

    private GameHandler gameHandler;

    public MonstersGenerator(GameHandler gameHandler){
        this.gameHandler = gameHandler;
    }

    public Entity[][] generate(){
        Entity[][] entities = null;
        try {
            int[][] monstersMatrix = FileManager.readFileAsMatrix(GameParams.MONSTERS_MATRIX_FILE_PATH);
            entities = new Entity[monstersMatrix.length][monstersMatrix[0].length];
            for(int row = 0; row < monstersMatrix.length; row++){
                for(int col = 0; col < monstersMatrix[row].length; col++){
                    if(monstersMatrix[row][col] != 0)
                        entities[row][col] = new Monster(this.gameHandler, row, col, 1.0);
                }
            }
        } catch (IOException e) {
            System.err.println("**from EntitiesManager: Couldn't load the monsters.");
        }
        return entities;
    }
}
