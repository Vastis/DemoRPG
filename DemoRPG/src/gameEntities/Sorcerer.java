package gameEntities;

import gameCore.GameHandler;
import gameEntitiesAttributes.CharacterAttributes;
import gameEntitiesMovement.SorcererMovement;

public class Sorcerer extends Character {
    public Sorcerer(GameHandler gameHandler, CharacterAttributes entityAttributes) {
        super(gameHandler, entityAttributes);
        this.entityMovement = new SorcererMovement(gameHandler, this);
    }
}
