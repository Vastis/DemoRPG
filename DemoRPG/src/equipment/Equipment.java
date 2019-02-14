package equipment;

import java.util.ArrayList;

public class Equipment extends Container {

    public Equipment(ItemAttributes attributes) {
        super(attributes);
    }
    public Equipment(ItemAttributes attributes, ArrayList<Item> items) {
        super(attributes, items);
    }

    public ItemSlot getItemSlotAt(int index){
        try {
            return this.itemSlots.get(index);
        } catch (IndexOutOfBoundsException e){
            return null;
        }
    }
}
