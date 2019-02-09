package gameGraphics;

import javafx.scene.paint.Color;

public class TileGraphics {

    Color color;

    public TileGraphics(int tileId){
        this.color = loadGraphics(tileId);
    }

    private Color loadGraphics(int tileId) {
        switch (tileId) {
            case 0:
                return Color.GREEN;
            case 1:
                return Color.DARKGREY;
            case 2:
                return Color.AQUA;
            case 3:
                return Color.SANDYBROWN;
            default:
                return Color.BLACK;
        }
    }

    public Color getGraphics(){
        return this.color;
    }
}
