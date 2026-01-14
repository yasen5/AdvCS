package Aug18;

import java.util.Scanner;

public class Classwork1 {
    public static void main(String[] args) {
        Profile[] profiles = {
            new Profile("Frank", 40),
            new Profile("Grace", 32),
            new Profile("Alice", 30),
            new Profile("Bob", 25),
            new Profile("David", 28),
            new Profile("Eve", 22),
            new Profile("Hannah", 29),
            new Profile("Ian", 31),
            new Profile("Jack", 27),
            new Profile("Charlie", 35)
        };
        for (Profile profile : profiles) {
            System.out.println(profile);
        }

        // Classwork2: Sorting

        Scanner sc = new Scanner(System.in);

        System.out.println("Do you want the output to be sorted by name");
        String input = sc.next();
        if (input.toLowerCase().equals("yes") || input.toLowerCase().equals("y")) {
            for (int i = 0; i < profiles.length; i++) {
                for (int j = i; j < profiles.length; j++) {
                    if (profiles[i].getName().compareTo(profiles[j].getName()) > 0) {
                        Profile temp = profiles[j];
                        profiles[j] = profiles[i];
                        profiles[i] = temp;
                    }
                }
            }

            for (Profile profile : profiles) {
                System.out.println(profile);
            }
        }
        sc.close();
    }
}

class Profile {
    private String name;
    private int age;

    public Profile(String name, int age)  {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return "Name: " + name + " Age: " + age;
    }
}
