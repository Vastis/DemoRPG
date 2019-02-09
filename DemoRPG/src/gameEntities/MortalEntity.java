package gameEntities;

import gameEngine.GameHandler;

public abstract class MortalEntity extends Entity {

    protected boolean dead;
    protected EntityAttributes attributes;

    public MortalEntity(GameHandler gameHandler, int tileX, int tileY, double speed) {
        super(gameHandler, tileX, tileY, speed);
        this.attributes = new EntityAttributes(this, gameHandler);
        this.dead = false;
    }

    public EntityAttributes getAttributes() {
        return attributes;
    }

    @Override
    public void update(){
        super.update();
        if(this.attributes.getHealth() == 0)
            this.dead = true;
    }

    @Override
    public void draw(){

    }

    @Override
    public boolean isDead(){
        return this.dead;
    }
}
