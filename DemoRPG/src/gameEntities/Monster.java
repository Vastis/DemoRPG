package gameEntities;

import gameCore.GameHandler;
import gameEntitiesAttributes.MonsterAttributes;
import gameEntitiesMovement.MonsterMovement;

public class Monster extends HostileEntity {

    public Monster(GameHandler gameHandler, MonsterAttributes entityAttributes) {
        super(gameHandler, entityAttributes);
        this.entityMovement = new MonsterMovement(gameHandler, this);
    }
}
