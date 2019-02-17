package gameEntities;

import gameCore.GameHandler;
import gameEntitiesAttributes.CharacterAttributes;
import gameEntitiesMovement.ArcherMovement;

public class Archer extends Character {
    public Archer(GameHandler gameHandler, CharacterAttributes entityAttributes) {
        super(gameHandler, entityAttributes);
        this.entityMovement = new ArcherMovement(gameHandler, this);
    }
}
