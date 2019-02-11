package gameEntities;

import gameCore.GameHandler;
import gameEntitiesAttributes.NPCAttributes;
import gameEntitiesMovement.NPCMovement;

public class NPC extends ImmortalEntity {
    public NPC(GameHandler gameHandler, NPCAttributes entityAttributes){
        super(gameHandler, entityAttributes);
        this.entityMovement = new NPCMovement(gameHandler, this);
    }
}
