package vocations;

import gameCore.GameHandler;
import gameEntities.Character;
import gameEntitiesAttributes.CharacterAttributes;
import vocationsMovement.ArcherMovement;

public class Archer extends Character {
    public Archer(GameHandler gameHandler, CharacterAttributes entityAttributes) {
        super(gameHandler, entityAttributes);
        this.entityMovement = new ArcherMovement(gameHandler, this);
    }
}
