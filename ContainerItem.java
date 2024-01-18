import java.util.ArrayList;
import java.util.Iterator;

public class ContainerItem extends Item {
    private ArrayList<Item> items;

    public ContainerItem(String pContainerName, String pContainerType, String pContainerDescription) {
        super(pContainerName, pContainerType, pContainerDescription);
        items = new ArrayList<Item>();
    }

    public void addItem(Item addedItem) {
        items.add(addedItem);
    }

    /*
     * This method should return true if the ContainerItem’s ArrayList contains an
     * item with the same name
     */
    public boolean hasItem(String addedItemName) {
        Iterator<Item> myIterator = items.iterator();
        while (myIterator.hasNext()) {
            Item myItem = myIterator.next();
            if (myItem.getName().compareToIgnoreCase(addedItemName) == 0) {
                return true;
            }
        }
        return false;
    }

    public String getItems() {
        StringBuilder myBuilder = new StringBuilder();
        Iterator<Item> myIterator = items.iterator();
        while (myIterator.hasNext()) {
            Item myItem = myIterator.next();
            myBuilder.append("\n");
            myBuilder.append("+ ");
            myBuilder.append(myItem.getName());
        }
        return myBuilder.toString();
    }

    public Item getItem(String Itemname) {
        Iterator<Item> myIter = items.iterator();
        while (myIter.hasNext()) {
            Item myItem = myIter.next();
            if (myItem.getName().compareToIgnoreCase(Itemname) == 0) {
                return myItem;
            }
        }
        return null;
    }

    /*
     * This method should remove the Item from the ContainerItem’s ArrayList and it
     * should also return the Item object back when it is finished
     */
    public Item removeItem(String itemRemove) {
        Iterator<Item> myIterator = items.iterator();
        while (myIterator.hasNext()) {
            Item myItem = myIterator.next();
            if (myItem.getName().compareToIgnoreCase(itemRemove) == 0) {
                items.remove(myItem);
                return myItem;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder myBuilder = new StringBuilder();
        myBuilder.append(super.getName());
        myBuilder.append(" [");
        myBuilder.append(super.getType());
        myBuilder.append(" ] : ");
        myBuilder.append(super.getDescription());
        myBuilder.append("\n");
        Iterator<Item> myIterator = items.iterator();
        while (myIterator.hasNext()) {
            Item myItem = myIterator.next();
            myBuilder.append("+ ");
            myBuilder.append(myItem.getName());
            myBuilder.append("\n");
        }
        return myBuilder.toString();
    }

}
