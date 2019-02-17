package gameEntitiesMovement;

import gameCore.GameHandler;
import gameCore.GameParams;
import gameEntities.HostileEntity;

public abstract class HostileEntityMovement extends MortalEntityMovement {

    protected static final int ticksPerAttackDraw = GameParams.TICKS_PER_SECOND / 5;
    protected int currentDrawTicks;
    private int delay;
    protected boolean attacking;


    public HostileEntityMovement(GameHandler gameHandler, HostileEntity owner) {
        super(gameHandler, owner);
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
            if(this.gameHandler.getUserCharacter().getMovement().getEntitySelected() != null
            && this.gameHandler.getUserCharacter().getMovement().getEntitySelected().equals(this))
                this.gameHandler.getUserCharacter().getMovement().setEntitySelected(null);
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
