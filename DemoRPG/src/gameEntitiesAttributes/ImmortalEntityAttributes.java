package gameEntitiesAttributes;

import gameCore.GameHandler;

public abstract class ImmortalEntityAttributes extends EntityAttributes {
    protected ImmortalEntityAttributes() {
        super();
    }
    protected ImmortalEntityAttributes(GameHandler gameHandler){
        this();
        setGameHandler(gameHandler);
    }
}
