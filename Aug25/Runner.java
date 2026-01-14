package Aug25;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        ArrayList<Item<Food>> foods = new ArrayList<>(List.of(new Item<Food>(new Food("Sushi"), 5), new Item<Food>(new Food("Takoyaki"), 5), new Item<Food>(new Food("Ramen"), 5), new Item<Food>(new Food("Udon"), 5), new Item<Food>(new Food("Tonkatsu"), 5)));
        ArrayList<Item<Toy>> toys = new ArrayList<>(List.of(new Item<Toy>(new Toy("Spiderman", 10), 10), new Item<Toy>(new Toy("Batman", 10), 10), new Item<Toy>(new Toy("Steve", 10), 10), new Item<Toy>(new Toy("Bob", 10), 10), new Item<Toy>(new Toy("Bob II", 10), 10)));

        while (true) {
            System.out.println("Food or Toys");
            String input = sc.next().toLowerCase();
            Boolean isFood = input.equals("food");
            ArrayList<? extends Item> list = isFood ? foods : toys;
            for (Item item : list) {
                System.out.println(item.getItem());
            }
            System.out.println("Would you like to\n1. Add an object\n2. Display list\n3. Return to previous menu\n4.");
            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.println("Add a name " + (isFood ? "" : " and a target age group"));
                }
            }
        }
    }
}

class Food {
    private String name;

    public Food(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

class Toy {
    private String name;
    private int ageGroup;

    public Toy(String name, int ageGroup) {
        this.name = name;
        this.ageGroup = ageGroup;
    }

    public String toString() {
        return name + ", for kids " + ageGroup;
    }
}

class Item<T> {
    private T o;
    private double price;

    public Item(T o, double price) {
        this.o = o;
        this.price = price;
    }

    public T getItem() {
        return o;
    }

    public double getPrice() {
        return price;
    }
}
