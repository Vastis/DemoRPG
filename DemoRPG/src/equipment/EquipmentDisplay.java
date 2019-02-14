package equipment;

import gameCore.GameParams;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class EquipmentDisplay extends GridPane {

    private Equipment equipment;
    private int rows, cols;

    public EquipmentDisplay(Equipment equipment){
        this.equipment = equipment;
        initGrid();
        insertItems();
    }

    private void initGrid(){
        this.cols = GameParams.MAX_COLS_IN_EQUIPMENT_DISPLAY;
        for(int i = 0; i < this.cols; i++)
            this.getColumnConstraints().add(new ColumnConstraints(GameParams.ITEM_SLOT_SIZE));

        this.rows = 1 + (this.equipment.getAttributes().getQuantity() - 1) / GameParams.MAX_COLS_IN_EQUIPMENT_DISPLAY;
        for(int i = 0; i < this.rows; i++)
            this.getRowConstraints().add(new RowConstraints(GameParams.ITEM_SLOT_SIZE));
    }

    private void insertItems(){
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.cols; j++){
                ItemSlot slot = null;
                if((slot = this.equipment.getItemSlotAt(i * this.cols + j)) != null)
                    this.add(slot, j, i);
                else
                    this.add(new ItemSlot(), j, i);
            }
        }
    }
}
