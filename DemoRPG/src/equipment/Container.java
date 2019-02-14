package equipment;

import java.util.ArrayList;

public class Container extends Item {

    protected ArrayList<ItemSlot> itemSlots;

    public Container(ItemAttributes attributes) {
        super(attributes);
        this.itemSlots = new ArrayList<>();
    }
    public Container(ItemAttributes attributes, ArrayList<Item> items) {
        this(attributes);
        for(Item item : items)
            this.itemSlots.add(new ItemSlot(item));
    }

    public void add(Item item){
        ItemSlot itemSlotContainingItem = null;
        if(item.getAttributes().isStackable()
                && (itemSlotContainingItem = this.getSlotContainingItem(item)) != null) {
            stackItem(item, itemSlotContainingItem);
        }
        else
            this.itemSlots.add(new ItemSlot(item));
    }
    public ItemSlot getSlotContainingItem(Item item){
        for(ItemSlot itemSlot : this.itemSlots)
            if(itemSlot.contains(item))
                return itemSlot;
        return null;
    }
    private void stackItem(Item item, ItemSlot itemSlot) {
        int itemQ = item.getAttributes().getQuantity();
        itemSlot.getItem().getAttributes().addQuantity(itemQ);
        itemSlot.updateText();
    }


}
