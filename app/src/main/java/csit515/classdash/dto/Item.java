package csit515.classdash.dto;

/**
 * Created by Kufel on 4/20/2017.
 */

public class Item {

    int id;
    String value;

    public Item() {
        super();
    }

    public Item(int id, String value) {
        this.id = id;
        this.value = value;
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
}
