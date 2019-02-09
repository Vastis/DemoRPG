package vocations;

import gameEngine.GameHandler;
import gameEngine.GameParams;
import gameEntities.Character;
import gameEntities.HostileEntity;
import javafx.scene.paint.Color;

public class Mage extends Character {

    private double beginX, beginY, endX, endY;

    public Mage(GameHandler gameHandler, int tileX, int tileY, double speed) {
        super(gameHandler, tileX, tileY, speed);
    }

    @Override
    public boolean attack() {
        if(this.entitySelected != null
                && this.entitySelected instanceof HostileEntity
                && Math.abs(this.tileX - this.entitySelected.getTileX()) <= this.attributes.getLineOfSight()
                && Math.abs(this.tileY - this.entitySelected.getTileY()) <= this.attributes.getLineOfSight()) {
            this.beginX = this.tileX;
            this.beginY = this.tileY;
            this.endX = this.entitySelected.getTileX();
            this.endY = this.entitySelected.getTileY();
            ((HostileEntity)this.entitySelected).getAttributes().magicDamageDealt(this);
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


        double missileBeginX = this.beginX + (1 + currentDrawTicks) * missileModuleX;
        double missileBeginY = this.beginY + (1 + currentDrawTicks) * missileModuleY;

        this.gameHandler.getGameRenderer().fillOvalRelativeToPlayer(Color.BLUE,
                missileBeginX * GameParams.TILE_SIZE + GameParams.TILE_SIZE/2 - 3, missileBeginY * GameParams.TILE_SIZE + GameParams.TILE_SIZE/2 - 3, 6, 6);
    }
}
