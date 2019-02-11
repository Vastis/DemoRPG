package vocations;

import gameCore.GameHandler;
import gameEntities.Character;
import gameEntitiesAttributes.CharacterAttributes;
import vocationsMovement.WarriorMovement;

public class Warrior extends Character {
    public Warrior(GameHandler gameHandler, CharacterAttributes entityAttributes) {
        super(gameHandler, entityAttributes);
        this.entityMovement = new WarriorMovement(gameHandler, this);
    }
}
