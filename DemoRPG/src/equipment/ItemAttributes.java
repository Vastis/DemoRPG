package equipment;

import javafx.scene.image.Image;

public class ItemAttributes {

    private boolean isStackable;
    private String type;
    private int quantity;
    private Image graphics;

    public ItemAttributes() {
        this.isStackable = true;
        this.type = "meat";
        this.quantity = 1;
        //this.graphics = new Image();
    }
    public ItemAttributes(int containerSize) {
        this.isStackable = false;
        this.type = "backpack";
        this.quantity = containerSize;
    }

    public boolean isStackable() {
        return isStackable;
    }
    public void setStackable(boolean isStackable) {
        this.isStackable = isStackable;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
    public Image getGraphics() {
        return graphics;
    }
    public void setGraphics(Image graphics) {
        this.graphics = graphics;
    }
    public void setGraphics(String graphicsPath) {
        this.graphics = new Image(graphicsPath);
    }
}
