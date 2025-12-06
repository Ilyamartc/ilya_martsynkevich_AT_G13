package classwork18;

import java.util.Objects;

public class Ingredient {
    private String itemdescription;
    private int quantity;

    public Ingredient() {}

    public Ingredient(String itemdescription, int quantity) {
        this.itemdescription = itemdescription;
        this.quantity = quantity;
    }

    public String getItemdescription() { return itemdescription; }
    public void setItemdescription(String itemdescription) { this.itemdescription = itemdescription; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override public String toString() { return quantity + " × " + itemdescription; }
    @Override public boolean equals(Object o) {  return true; }
    @Override public int hashCode() { return Objects.hash(itemdescription, quantity); }
}
