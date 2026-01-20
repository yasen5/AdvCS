package Jan20;

import java.util.Random;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        Random rand = new Random();

        // Add 100 random numbers from 1 to 99
        for (int i = 0; i < 100; i++) {
            int value = rand.nextInt(99) + 1; // range 1–99
            tree.add(value);
        }

        // Print the tree
        System.out.println("Tree contents:");
        System.out.println(tree.toString());

        // Ask the user for a number
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to search for: ");
        int target = scanner.nextInt();

        // Call contains and print the result
        boolean result = tree.contains(target);
        System.out.println("Tree contains " + target + ": " + result);

        scanner.close();
    }
}
