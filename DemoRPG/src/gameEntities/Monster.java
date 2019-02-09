package gameEntities;

import gameEngine.GameHandler;
import gameEngine.GameParams;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.Random;

public class Monster extends HostileEntity {

    private int ticksToNextGeneration;
    private Random random;

    public Monster(GameHandler gameHandler, EntityAttributes attributes, int tileX, int tileY) {
        super(gameHandler, attributes, tileX, tileY);
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
        int deltaX = this.tileX - this.gameHandler.getUserCharacter().getTileX();
        int deltaY = this.tileY - this.gameHandler.getUserCharacter().getTileY();
        int absDeltaX = Math.abs(deltaX);
        int absDeltaY = Math.abs(deltaY);
        if(absDeltaX <= this.attributes.getLineOfSight()
                && absDeltaY <= this.attributes.getLineOfSight()){
            this.entitySelected = this.gameHandler.getUserCharacter();
            replaceMovementPath(deltaX, deltaY, absDeltaX, absDeltaY);
        } else
            this.entitySelected = null;
    }

    @Override
    public boolean attack() {
        if(this.entitySelected != null
                && Math.abs(this.tileX - this.entitySelected.getTileX()) <= 1
                && Math.abs(this.tileY - this.entitySelected.getTileY()) <= 1) {
            ((HostileEntity)this.entitySelected).getAttributes().physicalDamageDealt(this);
            return true;
        }
        return false;
    }

    @Override
    protected void drawAttack() {
        this.gameHandler.getGameRenderer().fillOvalRelativeToPlayer(Color.RED,
                this.entitySelected.getPosX() - 3, this.entitySelected.getPosY() - 3, 6, 6);
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
            color = this.attributes.getGraphics();
        else
            color = Color.BLACK;
        this.gameHandler.getGameRenderer().fillOvalRelativeToPlayer(color,
                this.posX - 5,
                this.posY - 5,
                10,
                10);
    }
}
