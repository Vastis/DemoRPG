package gameGraphics;
/**
 * Unused for now...
 */
public abstract class GraphicsLoader<T> {

    private T graphics;

    public GraphicsLoader(){
        this.graphics = loadGraphics();
    }

    protected abstract T loadGraphics();

    public T getGraphics(){
        return this.graphics;
    }
}
