package gameEntitiesMovement;

import gameCore.GameHandler;
import gameEntities.Character;

public abstract class CharacterMovement extends HostileEntityMovement {

    public CharacterMovement(GameHandler gameHandler, Character owner){
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

        super.draw();
    }


}
