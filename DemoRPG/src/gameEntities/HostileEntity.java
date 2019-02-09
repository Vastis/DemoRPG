package gameEntities;

import gameEngine.GameHandler;
import gameEngine.GameParams;

public abstract class HostileEntity extends MortalEntity {

    protected static final int ticksPerAttackDraw = GameParams.TICKS_PER_SECOND / 5;
    protected int currentDrawTicks;
    private int delay;
    protected boolean attacking;


    public HostileEntity(GameHandler gameHandler, EntityAttributes attributes, int tileX, int tileY) {
        super(gameHandler, attributes, tileX, tileY);
        this.delay = 0;
        this.attacking = false;
        this.currentDrawTicks = 0;
    }

    @Override
    public void update(){
        if(!this.dead || this.moving){
            super.update();
            this.delay--;
            if(this.delay <= 0) {
                if (this.attacking = attack()) {
                    this.delay = 2 * GameParams.TICKS_PER_SECOND;
                }
            }
        } else {
            if(this.gameHandler.getUserCharacter().getEntitySelected() != null
            && this.gameHandler.getUserCharacter().getEntitySelected().equals(this))
                this.gameHandler.getUserCharacter().setEntitySelected(null);
                this.selected = false;
        }
    }

    @Override
    public void draw(){
        if(this.attacking || this.currentDrawTicks != 0) {
            drawAttack();
            this.currentDrawTicks++;
        }
        if(this.currentDrawTicks == ticksPerAttackDraw) {
            this.attacking = false;
            this.currentDrawTicks = 0;
        }
    }

    public abstract boolean attack();
    protected abstract void drawAttack();
}
