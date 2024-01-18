
/**
 * @author Kidus Samson
 * This is a Game program made to be a 2D scoller text based game.
 * Currently you can interact with the world around you by Picking up, and dropping items in locations, as well as moving between locations using Cardinal Directions(North, South, East, West)
 * 
 */
import java.util.Scanner;

public class Driver {
    private static Location currLocation;
    private static ContainerItem myInventory;

    public static void createWorld() {

        // Location declarations::hallway, bedroom, kitchen, bathroom
        Location hallway = new Location("Hallway", "Just a long hallway in the house");
        Location bedroom = new Location("Bedroom", "The place where you sleep");
        Location kitchen = new Location("kitchen", "A place where you cook");
        Location bathroom = new Location("Bathroom", "A place where you freshen yourself up and clean yourself");
        // Item declaration
        Item lamp = new Item("Lamp", "Light source", "Lights up the area around you");
        Item turkey = new Item("Turkey", "Food", "Very huge meal that fills you up");
        Item towel = new Item("Towel", "bathroom Item", "Can be used to dry your body");
        Item painting = new Item("Painting", "Decoration", "A painting drawn by you");
        kitchen.addItem(turkey);
        bedroom.addItem(lamp);
        bathroom.addItem(towel);
        hallway.addItem(painting);

        bathroom.connect("west", hallway);
        hallway.connect("east", bathroom);
        bedroom.connect("south", hallway);
        hallway.connect("north", bedroom);
        kitchen.connect("east", hallway);
        hallway.connect("west", kitchen);
        currLocation = hallway;
        myInventory = new ContainerItem("Backpack", "Container", "A leather backpack that contains: ");
        ContainerItem bag = new ContainerItem("Backpack", "Container", "A leather backpack that contains: ");
        hallway.addItem(bag);
        Item rope = new Item("Rope", "rope", "helps you get up");
        bag.addItem(rope);
        ContainerItem chest = new ContainerItem("Chest", "Container", "A wooden chest that contains: ");
        bedroom.addItem(chest);
    }

    public static String helpHelper() {
        StringBuilder myBuilder = new StringBuilder();
        myBuilder.append("Here is a list of commands you can use in this game and their descriptions\n");
        myBuilder.append(
                "+'examine': Use on an item to see its desciption, name and type. For example: 'examine towel'");
        myBuilder.append("\n");
        myBuilder.append(
                "+'look': Use it to see what location you are in as well as it desciption and the items in that location.");
        myBuilder.append("\n");
        myBuilder.append(
                "+ Use 'go' and a cardinal directon(north,south, east, west) to move your charcter in that direction. It should put you in a new location if you put in a valid direction.");
        myBuilder.append("\n");
        myBuilder.append(
                "+ Use 'take' on an item inside a location to add it into your inventory. For example 'take towel'. Alternatively if you wanted to take an Item from a Container then use the format 'take__X_from___Y__'. X being the item and Y being the container");
        myBuilder.append("\n");
        myBuilder.append(
                "+ Use 'drop' on an item in your inventory to drop the item into the location you are currently in.");
        myBuilder.append("\n");
        myBuilder.append(
                "Use 'put' to put your items inside a container. Usage format: 'put_X_in_Y_' where x is the item and Y is the container\n");
        myBuilder.append("+ Use 'inventory' to see what items are currently in your inventory");
        myBuilder.append("\n");
        myBuilder.append("+ Use 'quit' to to close out the game");
        myBuilder.append("\n");
        return myBuilder.toString();
    }

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        String input;
        createWorld();
        boolean x = true;
        while (x == true) {
            System.out.println("Enter a command: ");
            input = myScanner.nextLine();
            String[] words = input.split(" ");
            switch (words[0]) {
                case "quit": {
                    x = false;
                    break;
                }
                case "examine": {
                    if (words.length == 1) {
                        System.out.println("You must enter an item");
                    } else if (currLocation.getItem(words[1]) != null) {
                        Item DesiredItem = currLocation.getItem(words[1]);
                        System.out.println(DesiredItem);
                    } else if (currLocation.getItem(words[1]) == null) {
                        System.out.println(
                                "Your item '" + words[1] + "' is not in this location");
                    }
                    break;
                }

                case "look": {
                    System.out.println(currLocation.getName() + " - " + currLocation.getDescription() + " :");
                    for (int y = 0; y < currLocation.numItems(); y++) {
                        System.out.println("+ " + (currLocation.getItem(y).getName()));
                    }
                    break;
                }
                case "go": {
                    if (words.length == 1) {
                        System.out.println("You must enter a direction");
                    } else if ((words[1].compareToIgnoreCase("North") == 0)
                            || (words[1].compareToIgnoreCase("West") == 0)
                            || (words[1].compareToIgnoreCase("East") == 0)
                            || (words[1].compareToIgnoreCase("South") == 0)) {
                        words[1] = words[1].toLowerCase();
                        if (currLocation.canMove(words[1])) {
                            currLocation = currLocation.getLocation(words[1]);
                            System.out.println("You are now at :" + currLocation.getName());
                        } else {
                            System.out.println("There is nothing in that direction");
                        }
                    } else {
                        System.out.println("Please input a valid direction.");
                    }
                    break;
                }

                case "take": {
                    if (words.length == 1) {
                        System.out.println("Please specify what item you want to take");
                    } else if (words.length == 2) {
                        if (currLocation.hasItem(words[1])) {
                            words[1] = words[1].toLowerCase();
                            Item currItem = currLocation.removeItem(words[1]);
                            myInventory.addItem(currItem);
                            System.out.println("You have taken: " + currItem.getName());
                        } else {
                            System.out.println("That Item does not exist here");
                        }
                    } else if (words.length == 4) {
                        words[3] = words[3].toLowerCase();
                        if (currLocation.hasItem(words[3])
                                && (currLocation.getItem(words[3]) instanceof ContainerItem)
                                && (words[2].compareToIgnoreCase("from") == 0)) {
                            ContainerItem myItem = (ContainerItem) currLocation.getItem(words[3]);
                            if (myItem.hasItem(words[1])) {
                                Item pItem = myItem.removeItem(words[1]);
                                myInventory.addItem(pItem);
                                System.out.println("You have taken " + pItem.getName() + " from " + myItem.getName());
                            } else {
                                System.out.println("That Item does not exist in " + myItem.getName());
                            }
                        } else {
                            System.out.println("Cannot find that Container here");
                        }
                    } else {
                        System.out.println("Cannot find that item here");
                    }
                    break;
                }

                case "drop": {
                    if (words.length == 1) {
                        System.out.println("Please specify what item you want to drop");
                    } else if (myInventory.hasItem(words[1])) {
                        words[1] = words[1].toLowerCase();
                        Item myItem = myInventory.removeItem(words[1]);
                        currLocation.addItem(myItem);
                        System.out.println("You have dropped: " + myItem.getName());
                    } else if (!(myInventory.hasItem(words[1]))) {
                        System.out.println("You dont currently have that item in your inventory");
                    }
                    break;
                }
                case "put": {
                    if (words.length == 1) {
                        System.out.println("Please specify what item you want to put");
                    } else if (words.length == 4) {
                        words[3] = words[3].toLowerCase();
                        if (currLocation.hasItem(words[3])
                                && (currLocation.getItem(words[3]) instanceof ContainerItem)
                                && (words[2].compareToIgnoreCase("in") == 0)) {
                            if (myInventory.hasItem(words[1])) {
                                Item pItem = myInventory.removeItem(words[1]);
                                ContainerItem mycontainer = (ContainerItem) currLocation.getItem(words[3]);
                                mycontainer.addItem(pItem);
                                System.out.println("You have put " + pItem.getName() + " in " + mycontainer.getName()
                                        + " from your inventory");
                            } else {
                                System.out.println("That Item does not exist in your inventory");
                            }
                        } else {
                            System.out.println("That container does not exist in this area");
                        }
                    } else {
                        System.out.println("That container or Item does not exist in this area");
                    }
                    break;
                }
                case "inventory": {
                    System.out.println(myInventory.getItems());
                    break;
                }
                case "help": {
                    System.out.println(helpHelper());
                    break;
                }
                default:
                    System.out.println("I don't know how to do that");

            }
        }
        myScanner.close();
    }
}