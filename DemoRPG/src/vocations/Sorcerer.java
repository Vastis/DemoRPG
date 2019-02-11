package vocations;

import gameCore.GameHandler;
import gameEntities.Character;
import gameEntitiesAttributes.CharacterAttributes;
import vocationsMovement.SorcererMovement;

public class Sorcerer extends Character {
    public Sorcerer(GameHandler gameHandler, CharacterAttributes entityAttributes) {
        super(gameHandler, entityAttributes);
        this.entityMovement = new SorcererMovement(gameHandler, this);
    }
}
