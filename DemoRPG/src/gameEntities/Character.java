package gameEntities;

import gameEngine.GameHandler;
import javafx.scene.paint.Color;

public abstract class Character extends HostileEntity {

    public Character(GameHandler gameHandler, EntityAttributes attributes, int tileX, int tileY){
        super(gameHandler, attributes, tileX, tileY);
    }

    @Override
    public void draw() {
        this.gameHandler.getGameRenderer().fillOvalRelativeToPlayer(
                this.attributes.getGraphics(),
                this.posX - 5,
                this.posY - 5,
                10,
                10);

        super.draw();
    }


}
