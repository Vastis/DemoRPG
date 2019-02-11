package gameCore;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameRenderer {

    private GameHandler gameHandler;
    private double relativePosX, relativePosY;

    public GameRenderer(GameHandler gameHandler){
        this.gameHandler = gameHandler;
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
        return relativePosX;
    }
    public double getRelativePosY() {
        return relativePosY;
    }

    public void fillRectRelativeToPlayer(Color color, double globalPosX, double globalPosY, double width, double height){
        GraphicsContext gc = this.gameHandler.getCanvas().getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(globalPosX - this.relativePosX, globalPosY - this.relativePosY, width, height);
    }
    public void strokeRectRelativeToPlayer(Color color, double globalPosX, double globalPosY, double width, double height){
        GraphicsContext gc = this.gameHandler.getCanvas().getGraphicsContext2D();
        gc.setStroke(color);
        gc.strokeRect(globalPosX - this.relativePosX, globalPosY - this.relativePosY, width, height);
    }
    public void fillOvalRelativeToPlayer(Color color, double globalPosX, double globalPosY, double width, double height){
        GraphicsContext gc = this.gameHandler.getCanvas().getGraphicsContext2D();
        gc.setFill(color);
        gc.fillOval(globalPosX - this.relativePosX, globalPosY - this.relativePosY, width, height);
    }
    public void strokeLineRelativeToPlayer(Color color, double globalPosX1, double globalPosY1, double globalPosX2, double globalPosY2){
        GraphicsContext gc = this.gameHandler.getCanvas().getGraphicsContext2D();
        gc.setStroke(color);
        gc.setLineWidth(3);
        gc.strokeLine(globalPosX1 - this.relativePosX, globalPosY1 - this.relativePosY, globalPosX2 - this.relativePosX, globalPosY2 - this.relativePosY);
    }
    public void drawTextRelativeToPlayer(Color color, String text, double globalPosX, double globalPosY){
        GraphicsContext gc = this.gameHandler.getCanvas().getGraphicsContext2D();
        gc.setFill(color);
        gc.setFont(Font.font(15));
        gc.fillText(text, globalPosX - this.relativePosX, globalPosY - this.relativePosY);
    }
}
