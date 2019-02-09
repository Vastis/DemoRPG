package gameEngine;

import gameEntities.Character;
import gameEntities.EntityAttributes;
import gameInterfaces.GraphicsInterface;
import javafx.scene.canvas.Canvas;
import user.User;

public class GameHandler implements GraphicsInterface {

    private EntityAttributes[] monstersDefinitions;
    private Canvas canvas;
    private GameRenderer gameRenderer;
    private EntitiesManager entitiesManager;
    private WorldManager worldManager;
    private int rows, cols;
    private User user;

    public GameHandler(Canvas canvas, EntityAttributes[] monstersDefinitions){
        this.canvas = canvas;
        this.monstersDefinitions = monstersDefinitions;
        for(int i = 1; i < monstersDefinitions.length; i++)
            monstersDefinitions[i].setGameHandler(this);

        this.worldManager = new WorldManager(this);
        this.entitiesManager = new EntitiesManager(this);
        this.gameRenderer = new GameRenderer(this);

        this.canvas.widthProperty().addListener((observable, oldWidth, newWidth) -> this.rows = (int)(newWidth.doubleValue() / GameParams.TILE_SIZE));
        this.canvas.heightProperty().addListener((observable, oldHeight, newHeight) -> this.cols = (int)(newHeight.doubleValue() / GameParams.TILE_SIZE));
    }

    public void initialize(User user) {
        this.user = user;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }
    public EntitiesManager getEntitiesManager() {
        return this.entitiesManager;
    }
    public WorldManager getWorldManager() {
        return this.worldManager;
    }
    public int getTilesRows() {
        return this.rows;
    }
    public int getTilesCols() {
        return this.cols;
    }
    public int getWorldMaxRows() {
        return this.worldManager.getWorldRows();
    }
    public int getWorldMaxCols() {
        return this.worldManager.getWorldCols();
    }
    public Character getUserCharacter(){
        return this.user.getCharacter();
    }
    public GameRenderer getGameRenderer() {
        return gameRenderer;
    }
    public EntityAttributes[] getMonstersDefinitions() {
        return monstersDefinitions;
    }

    @Override
    public void update() {
        this.gameRenderer.update();
        this.entitiesManager.update();
        this.user.update();
        this.worldManager.update();
    }

    @Override
    public void draw() {
        this.canvas.getGraphicsContext2D().clearRect(0,0, this.canvas.getWidth(), this.canvas.getHeight());
        this.worldManager.draw();
        this.user.draw();
        this.entitiesManager.draw();

    }
}
