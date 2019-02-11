package gameEntitiesAttributes;

import gameCore.GameHandler;
import gameEntities.HostileEntity;
import gameEntities.MortalEntity;
import javafx.scene.paint.Color;
import utilities.PseudoRandomGenerator;

public abstract class EntityAttributes {

    protected GameHandler gameHandler;
    protected PseudoRandomGenerator generator;

    protected String name, type;
    protected double speed;
    protected Color graphics;
    protected int initTileX, initTileY;

    protected EntityAttributes(){
        this.generator = new PseudoRandomGenerator();
    }
    protected EntityAttributes(GameHandler gameHandler){
        this();
        setGameHandler(gameHandler);
    }

    public void setGameHandler(GameHandler gameHandler){
        this.gameHandler = gameHandler;
    }

    public void cloneTo(EntityAttributes entityAttributes){
        entityAttributes.setGameHandler(this.gameHandler);
        entityAttributes.setInitTileX(this.initTileX);
        entityAttributes.setInitTileY(this.initTileY);
        entityAttributes.setName(this.name);
        entityAttributes.setType(this.type);
        entityAttributes.setGraphics(this.graphics);
    }

    //tmp
    protected Color decodeGraphics(String graphicsString){
        switch (graphicsString) {
            case "PINK":
                return Color.PINK;
            case "GREY":
                return Color.GREY;
            case "BROWN":
                return Color.BROWN;
            case "BLUE":
                return Color.BLUE;
            case "DARKGREEN":
                return Color.DARKGREEN;
            case "BLACK":
                return Color.BLACK;
        }
        return Color.YELLOW;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Color getGraphics() {
        return graphics;
    }
    public void setGraphics(Color graphics) {
        this.graphics = graphics;
    }
    public void setGraphics(String graphicsString) {
        this.graphics = decodeGraphics(graphicsString);
    }
    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getInitTileX() {
        return initTileX;
    }
    public void setInitTileX(int initTileX) {
        this.initTileX = initTileX;
    }
    public int getInitTileY() {
        return initTileY;
    }
    public void setInitTileY(int initTileY) {
        this.initTileY = initTileY;
    }

    public void addExperience(int experienceFromKilling){}
}
