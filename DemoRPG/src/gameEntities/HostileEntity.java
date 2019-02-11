package gameEntities;

import gameCore.GameHandler;
import gameEntitiesAttributes.HostileEntityAttributes;

public abstract class HostileEntity extends MortalEntity {

    public HostileEntity(GameHandler gameHandler, HostileEntityAttributes entityAttributes) {
        super(gameHandler, entityAttributes);
    }
}
