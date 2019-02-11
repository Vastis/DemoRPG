package gameEntitiesMovement;

import gameCore.GameHandler;
import gameCore.GameParams;
import gameEntities.Entity;
import gameEntities.NPC;
import gameEntitiesAttributes.CharacterAttributes;
import javafx.scene.paint.Color;

public class NPCMovement extends EntityMovement {

    private boolean dialogOpen = false;

    public NPCMovement(GameHandler gameHandler, Entity owner) {
        super(gameHandler, owner);
    }

    @Override
    public void update(){
        super.update();
        if(this.selected
                && Math.abs(this.tileX - this.gameHandler.getUserCharacter().getMovement().getTileX()) <= ((CharacterAttributes)this.gameHandler.getUserCharacter().getAttributes()).getLineOfSight()
                && Math.abs(this.tileY - this.gameHandler.getUserCharacter().getMovement().getTileY()) <= ((CharacterAttributes)this.gameHandler.getUserCharacter().getAttributes()).getLineOfSight())
            this.gameHandler.setNpcSpokenTo((NPC)this.owner);
        else if(this.gameHandler.getUserCharacter().getMovement().getEntitySelected() != null
                && this.gameHandler.getUserCharacter().getMovement().getEntitySelected().equals(this.owner)){
            this.selected = false;
            this.gameHandler.setNpcSpokenTo(null);
            this.gameHandler.getUserCharacter().getMovement().setEntitySelected(null);
        }
    }
    @Override
    public void draw() {
        this.gameHandler.getGameRenderer().fillOvalRelativeToPlayer(
                this.owner.getAttributes().getGraphics(),
                this.posX - 5,
                this.posY - 5,
                10,
                10);

        if(this.selected){
            this.gameHandler.getGameRenderer().strokeRectRelativeToPlayer(Color.GREEN,
                    this.posX - GameParams.TILE_SIZE/2,
                    this.posY - GameParams.TILE_SIZE/2,
                    GameParams.TILE_SIZE,
                    GameParams.TILE_SIZE);
        }
    }
}
