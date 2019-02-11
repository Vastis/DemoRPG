package gameEntities;

import gameCore.GameHandler;
import gameEntitiesAttributes.MortalEntityAttributes;

public abstract class MortalEntity extends Entity {
    public MortalEntity(GameHandler gameHandler, MortalEntityAttributes entityAttributes) {
        super(gameHandler, entityAttributes);
    }
}
