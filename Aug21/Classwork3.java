package Aug21;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Classwork3 {
    public static void main(String[] args) {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        ArrayList<Animal> animals = new ArrayList(List.of(new Cat("Tac"), new Dog("God"), new Bird("Drib")));
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("What would you like to do? \n1. Print animals \n2. Learn more about an animal \n3. Remove an animal \n4. Quit");
            switch (sc.nextInt()) {
                case 1 -> {
                    for (Animal animal : animals) {
                        System.out.println(animal.getName());
                    }
                }
                case 2 -> {
                    System.out.println("Enter a name to learn more about");
                    String lowerInput = sc.next().toLowerCase();
                    for (Animal animal : animals) {
                        if (animal.getName().toLowerCase().equals(lowerInput)) {
                            animal.printInfo();
                            break;
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Please enter an animal to remove");
                    String lowerInput = sc.next().toLowerCase();
                    for (Animal animal : animals) {
                        if (animal.getName().toLowerCase().equals(lowerInput)) {
                            animals.remove(animal);
                            break;
                        }
                    }
                }
                case 4 -> { return; }
            }
        }
    }
}

abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public abstract String speak();

    public abstract String getColor();

    public String getName() {
        return name;
    }

    public void printInfo() {
        System.out.println("A " + getColor() + " " + getName() + " says " + speak());
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return "Meow";
    }

    @Override
    public String getColor() {
        return "Orange";
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return "Ruff";
    }

    @Override
    public String getColor() {
        return "Brown";
    }
}

class Bird extends Animal {
    public Bird(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return "Cheep";
    }

    @Override
    public String getColor() {
        return "Yellow";
    }
}