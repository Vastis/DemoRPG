package gameEntitiesMovement;

import gameCore.GameHandler;
import gameEntities.Entity;

public class NPCMovement extends EntityMovement {
    public NPCMovement(GameHandler gameHandler, Entity owner) {
        super(gameHandler, owner);
    }

    @Override
    public void draw() {
        this.gameHandler.getGameRenderer().fillOvalRelativeToPlayer(
                this.owner.getAttributes().getGraphics(),
                this.posX - 5,
                this.posY - 5,
                10,
                10);
    }

    public void openDialog(){

    }
}
