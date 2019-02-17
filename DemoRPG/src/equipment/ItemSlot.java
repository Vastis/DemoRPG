package equipment;

import gameCore.GameParams;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;


public class ItemSlot extends Button {

    private Item item;

    public ItemSlot(){
        this(null);
    }
    public ItemSlot(Item item){
        this.item = item;
        setOnMouseClicked(e -> onButtonClicked(e));
        setSize();
        showItem();
    }

    private void setSize() {
        setMinWidth(GameParams.ITEM_SLOT_SIZE);
        setMinHeight(GameParams.ITEM_SLOT_SIZE);
        setPrefWidth(GameParams.ITEM_SLOT_SIZE);
        setPrefHeight(GameParams.ITEM_SLOT_SIZE);
        setMaxWidth(GameParams.ITEM_SLOT_SIZE);
        setMaxHeight(GameParams.ITEM_SLOT_SIZE);
    }

    public void showItem(){
        if(this.item != null) {
            /*this.setGraphic(
                    new ImageView(
                            this.item.getGraphics()));*/
            this.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Paint.valueOf("red"),
                                    new CornerRadii(0),
                                    new Insets(2))));
        } else
            this.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Paint.valueOf("black"),
                                    new CornerRadii(0),
                                    new Insets(2))));
    }

    private void onButtonClicked(MouseEvent e) {
        if(this.item != null) {
            if (e.getButton() == MouseButton.PRIMARY)
                System.out.println("left");
            if (e.getButton() == MouseButton.SECONDARY)
                System.out.println("right");
        }
    }

    public void updateText(){
        int quantity = this.item.getAttributes().getQuantity();
        if(quantity > 1)
            this.setText("" + quantity);
        else
            this.setText("");
    }

    public boolean contains(Item item){
        return this.item.equals(item);
    }

    public Item getItem() {
        return item;
    }
}
