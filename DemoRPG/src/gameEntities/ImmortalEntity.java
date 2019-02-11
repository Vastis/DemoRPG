package gameEntities;

import gameCore.GameHandler;
import gameEntitiesAttributes.ImmortalEntityAttributes;

public abstract class ImmortalEntity extends Entity {
    public ImmortalEntity(GameHandler gameHandler, ImmortalEntityAttributes entityAttributes) {
        super(gameHandler, entityAttributes);
    }
}
