package gameEntitiesMovement;

import gameCore.GameHandler;
import gameCore.GameParams;
import gameEntities.HostileEntity;
import gameEntities.Monster;
import gameEntitiesAttributes.HostileEntityAttributes;
import gameEntitiesAttributes.MonsterAttributes;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.Random;

public class MonsterMovement extends HostileEntityMovement {

    private int ticksToNextGeneration;
    private Random random;

    public MonsterMovement(GameHandler gameHandler, Monster owner) {
        super(gameHandler, owner);
        this.random = new Random();
        this.ticksToNextGeneration = GameParams.TICKS_PER_SECOND * 2;
    }

    @Override
    public void update() {
        super.update();
        searchForUser();

        if(this.ticksToNextGeneration > 0)
            this.ticksToNextGeneration--;
        else {
            this.ticksToNextGeneration = GameParams.TICKS_PER_SECOND * 2;
            if(this.entitySelected == null){
                switch (this.random.nextInt(5)) {
                    case 0:
                        replaceMovement(KeyCode.UP);
                        break;
                    case 1:
                        replaceMovement(KeyCode.DOWN);
                        break;
                    case 2:
                        replaceMovement(KeyCode.RIGHT);
                        break;
                    case 3:
                        replaceMovement(KeyCode.LEFT);
                        break;
                }
            }
        }
    }
    private void searchForUser() {
        int deltaX = this.tileX - this.gameHandler.getUserCharacter().getMovement().getTileX();
        int deltaY = this.tileY - this.gameHandler.getUserCharacter().getMovement().getTileY();
        int absDeltaX = Math.abs(deltaX);
        int absDeltaY = Math.abs(deltaY);
        if(absDeltaX <= ((MonsterAttributes)this.owner.getAttributes()).getLineOfSight()
                && absDeltaY <= ((MonsterAttributes)this.owner.getAttributes()).getLineOfSight()){
            this.entitySelected = this.gameHandler.getUserCharacter();
            replaceMovementPath(deltaX, deltaY, absDeltaX, absDeltaY);
        } else
            this.entitySelected = null;
    }

    @Override
    public boolean attack() {
        if(this.entitySelected != null
                && Math.abs(this.tileX - this.entitySelected.getMovement().getTileX()) <= 1
                && Math.abs(this.tileY - this.entitySelected.getMovement().getTileY()) <= 1) {
            ((HostileEntityAttributes)this.entitySelected.getAttributes()).physicalDamageDealt((Monster)this.owner);
            return true;
        }
        return false;
    }

    @Override
    protected void drawAttack() {
        this.gameHandler.getGameRenderer().fillOvalRelativeToPlayer(Color.RED,
                this.entitySelected.getMovement().getPosX() - 3, this.entitySelected.getMovement().getPosY() - 3, 6, 6);
    }

    @Override
    public void draw() {
        super.draw();
        if(this.selected){
            this.gameHandler.getGameRenderer().strokeRectRelativeToPlayer(Color.RED,
                    this.posX - GameParams.TILE_SIZE/2,
                    this.posY - GameParams.TILE_SIZE/2,
                    GameParams.TILE_SIZE,
                    GameParams.TILE_SIZE);
        }
        Color color;
        if(!this.dead)
            color = this.owner.getAttributes().getGraphics();
        else
            color = Color.BLACK;
        this.gameHandler.getGameRenderer().fillOvalRelativeToPlayer(color,
                this.posX - 5,
                this.posY - 5,
                10,
                10);
    }
}
