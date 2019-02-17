package gameCore;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameRenderer {

    private GameHandler gameHandler;
    private GraphicsContext gc;
    private double relativePosX, relativePosY;

    public GameRenderer(GameHandler gameHandler){
        this.gameHandler = gameHandler;
        this.gc = this.gameHandler.getCanvas().getGraphicsContext2D();
    }

    public void update(){
        double playerPosX = this.gameHandler.getUserCharacter().getMovement().getPosX();
        double playerPosY = this.gameHandler.getUserCharacter().getMovement().getPosY();
        double xOffset = (GameParams.TILE_SIZE * this.gameHandler.getTilesRows())/2;
        double yOffset = (GameParams.TILE_SIZE * this.gameHandler.getTilesCols())/2;

        this.relativePosX = playerPosX - xOffset;
        this.relativePosY = playerPosY - yOffset;
    }

    public double getRelativePosX() {
        return this.relativePosX;
    }
    public double getRelativePosY() {
        return this.relativePosY;
    }

    public void fillRectRelativeToPlayer(Color color, double globalPosX, double globalPosY, double width, double height){
        this.gc.setFill(color);
        this.gc.fillRect(globalPosX - this.relativePosX, globalPosY - this.relativePosY, width, height);
    }
    public void strokeRectRelativeToPlayer(Color color, double globalPosX, double globalPosY, double width, double height){
        this.gc.setStroke(color);
        this.gc.strokeRect(globalPosX - this.relativePosX, globalPosY - this.relativePosY, width, height);
    }
    public void fillOvalRelativeToPlayer(Color color, double globalPosX, double globalPosY, double width, double height){
        this.gc.setFill(color);
        this.gc.fillOval(globalPosX - this.relativePosX, globalPosY - this.relativePosY, width, height);
    }
    public void strokeLineRelativeToPlayer(Color color, double globalPosX1, double globalPosY1, double globalPosX2, double globalPosY2){
        this.gc.setStroke(color);
        this.gc.setLineWidth(3);
        this.gc.strokeLine(globalPosX1 - this.relativePosX, globalPosY1 - this.relativePosY, globalPosX2 - this.relativePosX, globalPosY2 - this.relativePosY);
    }
    public void drawTextRelativeToPlayer(Color color, String text, double globalPosX, double globalPosY){
        this.gc.setFill(color);
        this.gc.setFont(Font.font(15));
        this.gc.fillText(text, globalPosX - this.relativePosX, globalPosY - this.relativePosY);
    }

    public double getPosRelativeToPlayerX(double globalPosX){
        return globalPosX - this.relativePosX;
    }
    public double getPosRelativeToPlayerY(double globalPosY){
        return globalPosY - this.relativePosY;
    }
}
