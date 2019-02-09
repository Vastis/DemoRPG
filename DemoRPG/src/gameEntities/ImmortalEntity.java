package gameEntities;

import gameEngine.GameHandler;

public abstract class ImmortalEntity extends Entity {
    public ImmortalEntity(GameHandler gameHandler, int tileX, int tileY, double speed) {
        super(gameHandler, tileX, tileY, speed);
    }
}
