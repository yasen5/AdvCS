package BinaryTreeLab;

public class Item implements Comparable<Item> {
    public String name;
    public double price;
    public int quantity;

    public Item(String name, double price) {
        this(name, price, 1);
    }

    public Item(String name,double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public int compareTo(Item other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object other) {
        Item itemOther = (Item) other;
        return compareTo(itemOther) == 0;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String fullListing() {
        return this.name + " : $" + this.price;
    }

    public String fullListingWithQuantity() {
        return this.name + " : $" + this.price + " x " + this.quantity;
    }
}