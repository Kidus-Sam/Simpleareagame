public class Item {
    // Declaration of fields
    private String name;
    private String type;
    private String description;

    // Contructors
    public Item(String pName, String pType, String pDescription) {
        name = pName;
        type = pType;
        description = pDescription;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    // setters
    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ToString: to make it come out correctly
    // Using StringBuilder to make the code more efficient
    @Override
    public String toString() {
        StringBuilder myBuilder = new StringBuilder();
        myBuilder.append(name);
        myBuilder.append(" [ ");
        myBuilder.append(type);
        myBuilder.append(" ] : ");
        myBuilder.append(description);
        String myString = myBuilder.toString();
        return myString;
    }
}
