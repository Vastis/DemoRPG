package gameEntitiesMovement;

import gameCore.GameHandler;
import gameEntities.MortalEntity;
import gameEntitiesAttributes.EntityAttributes;
import gameEntitiesAttributes.MortalEntityAttributes;

public abstract class MortalEntityMovement extends EntityMovement {

    protected boolean dead;

    public MortalEntityMovement(GameHandler gameHandler, MortalEntity owner) {
        super(gameHandler, owner);
        this.dead = false;
    }

    @Override
    public void update(){
        super.update();
        if(((MortalEntityAttributes)this.owner.getAttributes()).getHealth() == 0)
            this.dead = true;
    }

    @Override
    public boolean isDead(){
        return this.dead;
    }
}
