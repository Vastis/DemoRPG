package gameEntitiesMovement;

import gameCore.GameHandler;
import gameEntities.ImmortalEntity;

public abstract class ImmortalEntityMovement extends EntityMovement {
    public ImmortalEntityMovement(GameHandler gameHandler, ImmortalEntity owner) {
        super(gameHandler, owner);
    }
}
