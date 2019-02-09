package vocations;

import gameEngine.GameHandler;
import gameEntities.Character;
import gameEntities.EntityAttributes;
import gameEntities.HostileEntity;
import javafx.scene.paint.Color;

public class Warrior extends Character {

    public Warrior(GameHandler gameHandler, EntityAttributes attributes, int tileX, int tileY) {
        super(gameHandler, attributes, tileX, tileY);
    }

    @Override
    public boolean attack(){
        if(this.entitySelected != null
                && this.entitySelected instanceof HostileEntity
                && Math.abs(this.tileX - this.entitySelected.getTileX()) <= 1
                && Math.abs(this.tileY - this.entitySelected.getTileY()) <= 1) {
            ((HostileEntity)this.entitySelected).getAttributes().physicalDamageDealt(this);
            return true;
        }
        return false;
    }

    @Override
    protected void drawAttack() {
        this.gameHandler.getGameRenderer().fillOvalRelativeToPlayer(Color.RED,
                this.entitySelected.getPosX() - 3, this.entitySelected.getPosY() - 3, 6, 6);
    }
}
