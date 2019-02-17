package gameEntitiesMovement;

import gameCore.GameHandler;
import gameEntities.HostileEntity;
import gameEntitiesAttributes.HostileEntityAttributes;
import javafx.scene.paint.Color;
import gameEntities.Warrior;

public class WarriorMovement extends CharacterMovement {

    public WarriorMovement(GameHandler gameHandler, Warrior owner) {
        super(gameHandler, owner);
    }

    @Override
    public boolean attack(){
        if(this.entitySelected != null
                && this.entitySelected instanceof HostileEntity
                && Math.abs(this.tileX - this.entitySelected.getMovement().getTileX()) <= 1
                && Math.abs(this.tileY - this.entitySelected.getMovement().getTileY()) <= 1) {
            ((HostileEntityAttributes)this.entitySelected.getAttributes()).physicalDamageDealt((Warrior)this.owner);
            return true;
        }
        return false;
    }

    @Override
    protected void drawAttack() {
        this.gameHandler.getGameRenderer().fillOvalRelativeToPlayer(Color.RED,
                this.entitySelected.getMovement().getPosX() - 3, this.entitySelected.getMovement().getPosY() - 3, 6, 6);
    }
}
