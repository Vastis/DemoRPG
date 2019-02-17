package gameEntitiesMovement;

import gameCore.GameHandler;
import gameCore.GameParams;
import gameEntities.HostileEntity;
import gameEntitiesAttributes.HostileEntityAttributes;
import javafx.scene.paint.Color;
import gameEntities.Archer;

public class ArcherMovement extends CharacterMovement {

    private double beginX, beginY, endX, endY;

    public ArcherMovement(GameHandler gameHandler, Archer owner) {
        super(gameHandler, owner);
    }

    @Override
    public boolean attack() {
        if(this.entitySelected != null
                && this.entitySelected instanceof HostileEntity
                && Math.abs(this.tileX - this.entitySelected.getMovement().getTileX()) <= ((HostileEntityAttributes)this.owner.getAttributes()).getLineOfSight()
                && Math.abs(this.tileY - this.entitySelected.getMovement().getTileY()) <= ((HostileEntityAttributes)this.owner.getAttributes()).getLineOfSight()) {
            this.beginX = this.tileX;
            this.beginY = this.tileY;
            this.endX = this.entitySelected.getMovement().getTileX();
            this.endY = this.entitySelected.getMovement().getTileY();
            ((HostileEntityAttributes)this.entitySelected.getAttributes()).distanceDamageDealt((Archer)this.owner);
            return true;
        }
        return false;
    }

    @Override
    protected void drawAttack() {
        double moduleX = this.endX - this.beginX;
        double moduleY = this.endY - this.beginY;
        double missileModuleX = moduleX / ticksPerAttackDraw;
        double missileModuleY = moduleY / ticksPerAttackDraw;


        double missileBeginX = this.beginX + currentDrawTicks * missileModuleX;
        double missileBeginY = this.beginY + currentDrawTicks * missileModuleY;
        double missileEndX = this.beginX + (1 + currentDrawTicks) * missileModuleX;
        double missileEndY = this.beginY + (1 + currentDrawTicks) * missileModuleY;

        this.gameHandler.getGameRenderer().strokeLineRelativeToPlayer(Color.BROWN,
                missileBeginX * GameParams.TILE_SIZE + GameParams.TILE_SIZE/2, missileBeginY * GameParams.TILE_SIZE + GameParams.TILE_SIZE/2,
                missileEndX * GameParams.TILE_SIZE + GameParams.TILE_SIZE/2, missileEndY * GameParams.TILE_SIZE + GameParams.TILE_SIZE/2);
    }
}
