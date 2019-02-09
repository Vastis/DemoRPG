package gameEngine;

public class Game implements Runnable {

    private GameHandler gameHandler;

    private Thread thread;
    private boolean running;

    public Game(GameHandler gameHandler){
        this.running = false;
        this.gameHandler = gameHandler;
    }

    @Override
    public void run(){
        int fps = GameParams.TICKS_PER_SECOND;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        while(this.running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            if(delta >= 1) {
                update();
                draw();
                delta--;
            }
        }
        stop();
    }

    private void update(){
        this.gameHandler.update();
    }
    private void draw(){
        this.gameHandler.draw();
    }

    public void start(){
        if(this.running) return;
        this.running = true;
        this.thread = new Thread(this);
        this.thread.setDaemon(true);
        this.thread.start();
    }
    public void stop(){
        if(!this.running) return;
        this.running = false;
        try {
            this.thread.join();
        } catch (InterruptedException ex) {}
    }
}
