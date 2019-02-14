package equipment;

import gameCore.GameParams;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Item {

    private ItemAttributes attributes;

    public Item(ItemAttributes attributes){
        this.attributes = attributes;
    }

    public void draw(GraphicsContext gc){
        gc.setFill(Color.SANDYBROWN);
        gc.fillOval(0, 0, GameParams.ITEM_SLOT_SIZE, GameParams.ITEM_SLOT_SIZE);
    }

    public boolean equals(Item item){
        if(this.getAttributes().getType().equals(item.getAttributes().getType()))
            return true;
        else
            return false;
    }

    public ItemAttributes getAttributes() {
        return this.attributes;
    }

    public Image getGraphics(){
        return this.attributes.getGraphics();
    }
}
