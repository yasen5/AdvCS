package Aug21;

import java.util.ArrayList;

public class Warmup {
    public static void main(String[] args) {
        Item[] twoItems = { new Item("Lays chips x Gucci", 5000), new Item("Food in japan", 0) };
        for (Item item : twoItems) {
            System.out.println(item);
        }
        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(twoItems[0]);
        itemList.add(twoItems[1]);
        for (Item item : itemList) {
            System.out.println(item);
        }
        ArrayList<ArrayList<Item>> itemList2D = new ArrayList<>();
        itemList2D.add(new ArrayList<Item>());
        itemList2D.add(new ArrayList<Item>());
        itemList2D.get(0).add(twoItems[0]);
        itemList2D.get(1).add(twoItems[1]);
        for (ArrayList<Item> list : itemList2D) {
            for (Item item : list) {
                System.out.println(item);
            }
        }
    }
}

class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return name + " - $" + price;
    }
}
