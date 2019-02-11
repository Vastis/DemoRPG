package gameWorld;

import gameCore.GameHandler;
import gameCore.GameParams;
import utilities.FileManager;

import java.io.IOException;

public class WorldMap {

    private GameHandler gameHandler;
    private int rows, cols;
    private Tile[][] worldTiles;

    public WorldMap(GameHandler gameHandler){
        try {
            this.gameHandler = gameHandler;
            initWorldTiles();
        } catch (IOException e){
            System.err.println("**from WorldMap: Couldn't load the map.");
        }
    }

    private void initWorldTiles() throws IOException {
        int[][] map = FileManager.readFileAsMatrix(GameParams.WORLD_MAP_FILE_PATH);
        this.rows = map.length;
        this.cols = map[0].length;
        this.worldTiles = new Tile[this.rows][this.cols];
        for(int row = 0; row < map.length; row++){
            for(int col = 0; col < map[row].length; col++){
                worldTiles[row][col] = new Tile(this.gameHandler, map[row][col], row, col);
            }
        }
    }

    public Tile getTileAt(int row, int col){
        return this.worldTiles[row][col];
    }
    public int getRows() {
        return this.rows;
    }
    public int getCols() {
        return this.cols;
    }
}
