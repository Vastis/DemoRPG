package gameEntities;

import gameEngine.GameHandler;
import javafx.scene.paint.Color;

public abstract class Character extends HostileEntity {

    public Character(GameHandler gameHandler, int tileX, int tileY, double speed){
        super(gameHandler, tileX, tileY, speed);
    }

    @Override
    public void draw() {
        this.gameHandler.getGameRenderer().fillOvalRelativeToPlayer(Color.BLACK,
                this.posX - 5,
                this.posY - 5,
                10,
                10);

        super.draw();
    }


}
