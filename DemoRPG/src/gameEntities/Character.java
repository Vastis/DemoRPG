package gameEntities;

import gameCore.GameHandler;
import gameEntitiesAttributes.CharacterAttributes;

public abstract class Character extends HostileEntity {

    public Character(GameHandler gameHandler, CharacterAttributes entityAttributes) {
        super(gameHandler, entityAttributes);
    }


}
