package gameEntitiesAttributes;

import gameCore.GameHandler;

public class MonsterAttributes extends HostileEntityAttributes {

    public MonsterAttributes(){
        super();
    }
    public MonsterAttributes(GameHandler gameHandler){
        this();
        setGameHandler(gameHandler);
    }

    public MonsterAttributes clone(){
        MonsterAttributes monsterAttributes = new MonsterAttributes();
        cloneTo(monsterAttributes);
        return monsterAttributes;
    }
}