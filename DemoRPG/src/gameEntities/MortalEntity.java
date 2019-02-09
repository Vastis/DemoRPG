package gameEntities;

import gameEngine.GameHandler;

public abstract class MortalEntity extends Entity {

    protected EntityAttributes attributes;
    protected boolean dead;

    public MortalEntity(GameHandler gameHandler, EntityAttributes attributes, int tileX, int tileY) {
        super(gameHandler, tileX, tileY, attributes.getSpeed());
        this.attributes = attributes;
        this.attributes.setEntity(this);
        this.dead = false;
    }

    @Override
    public void update(){
        super.update();
        if(this.attributes.getHealth() == 0)
            this.dead = true;
    }

    @Override
    public boolean isDead(){
        return this.dead;
    }
    public EntityAttributes getAttributes() {
        return attributes;
    }
}
