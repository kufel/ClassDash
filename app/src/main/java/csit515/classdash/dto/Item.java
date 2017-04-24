package csit515.classdash.dto;

/**
 * Created by Kufel on 4/20/2017.
 */

public class Item {

    int id;
    String value;
    String description;

    public Item() {
        super();
    }

    public Item(int id, String value, String description) {
        this.id = id;
        this.value = value;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
