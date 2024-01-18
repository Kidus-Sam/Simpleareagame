import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Location {
    // Declaration of the fields of the object
    private String name;
    private String description;
    private ArrayList<Item> locationList;
    private HashMap<String, Location> connections;

    // constructor
    public Location(String pName, String pDescription) {
        name = pName;
        description = pDescription;
        locationList = new ArrayList<Item>();
        connections = new HashMap<String, Location>(4);
    }

    // getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // mutators
    public void setName(String pName) {
        name = pName;
    }

    public void setDescription(String pDescription) {
        description = pDescription;
    }

    // ADD ITEM: adds an item to the arraylist
    public void addItem(Item pItem) {
        locationList.add(pItem);
    }

    /*
     * Helper method: so that i dont have to repeat the iterator over and over in
     * some classes
     */
    private Item iteratorHelper(String pname) {
        Iterator<Item> myIter = locationList.iterator();
        while (myIter.hasNext()) {
            Item myItem = myIter.next();
            int x = pname.compareToIgnoreCase(myItem.getName());
            /*
             * compareToIgnoreCase(String str) coompares the string without caring about if
             * they have a upper or lower case. return negative number or postive number or
             * 0 depending on if they are equal. if they are equal then it returns a 0.
             */
            if (x == 0) {
                return myItem;
            }
        }
        return null;
    }

    // HAS ITEM: checks if the arraylist has an item
    public boolean hasItem(String itemName) {
        Item myItem = iteratorHelper(itemName);
        if (myItem != null) {
            return true;
        }
        // compareToIgnoreCase(String str) coompares the string without caring about
        // upper or lower case.
        return false;
    }

    // getItem: compares name of Item and returns the item that matches it.
    public Item getItem(String itemName) {
        Item myitem = iteratorHelper(itemName);
        if (myitem != null) {
            return myitem;
        }
        return null;
    }

    public Item getItem(int index) {
        if (index < locationList.size() && !(index < 0)) {
            return locationList.get(index);
        }
        return null;
    }

    /*
     * numItems: see how many items are at the location ie checks how many items are
     * in the locationList
     */
    public int numItems() {
        return locationList.size();
    }

    // removeItem: takes a name, remove and return the matching item
    public Item removeItem(String pName) {
        Item myItem;
        Iterator<Item> myIter = locationList.iterator();
        while (myIter.hasNext()) {
            myItem = myIter.next();
            if (myItem.getName().compareToIgnoreCase(pName) == 0) {
                locationList.remove(myItem);
                return myItem;
            }
        }
        return null;
    }

    // connect method for connecting the different locations together
    public void connect(String direction, Location connected) {
        this.connections.put(direction, connected);
    }

    // canMove finds out of the character can move in a certain direction
    public boolean canMove(String direction) {
        if (this.connections.containsKey(direction)) {
            return true;
        }
        return false;
    }

    public Location getLocation(String direction) {
        if (this.canMove(direction)) {
            return this.connections.get(direction);
        }
        return null;
    }
}
