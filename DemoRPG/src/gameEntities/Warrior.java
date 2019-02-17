package gameEntities;

import gameCore.GameHandler;
import gameEntitiesAttributes.CharacterAttributes;
import gameEntitiesMovement.WarriorMovement;

public class Warrior extends Character {
    public Warrior(GameHandler gameHandler, CharacterAttributes entityAttributes) {
        super(gameHandler, entityAttributes);
        this.entityMovement = new WarriorMovement(gameHandler, this);
    }
}
