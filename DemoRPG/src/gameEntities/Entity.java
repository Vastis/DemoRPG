package gameEntities;

import gameEntitiesAttributes.EntityAttributes;
import gameEntitiesMovement.EntityMovement;
import gameCore.GameHandler;
import gameInterfaces.GraphicsInterface;

public abstract class Entity implements GraphicsInterface {

    protected GameHandler gameHandler;
    protected EntityMovement entityMovement;
    protected EntityAttributes entityAttributes;

    public Entity(GameHandler gameHandler, EntityAttributes entityAttributes){
        this.gameHandler = gameHandler;
        this.entityAttributes = entityAttributes;
        //this.entityMovement should be assigned in non-abstract classes
    }

    @Override
    public void update(){
        this.entityMovement.update();
    }

    @Override
    public void draw(){
        this.entityMovement.draw();
    }

    public EntityAttributes getAttributes() {
        return entityAttributes;
    }
    public EntityMovement getMovement() {
        return entityMovement;
    }
}
