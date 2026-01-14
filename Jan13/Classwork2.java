package Jan13;

public class Classwork2 {
    public static void main(String[] args) {
        // ---------- PART 1 ----------
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] values = { 90, 80, 100, 70, 85, 98, 120 };
        for (int v : values)
            bst.add(v);

        System.out.println("Part 1 In-order = " + bst);
        System.out.println("Part 1 Pre-order = " + bst.toStringPreOrder());
        System.out.println("Contains 85: " + bst.contains(85));
        System.out.println("Contains 86: " + bst.contains(86));

        // ---------- PART 2 ----------
        bst.clear();
        for (int v : values)
            bst.add(v);

        bst.remove(70);
        bst.remove(120);

        System.out.println("\nPart 2 In-order = " + bst);
        System.out.println("Part 2 Pre-order = " + bst.toStringPreOrder());

        // ---------- PART 3 ----------
        bst.clear();
        for (int v : values)
            bst.add(v);

        bst.remove(100);

        System.out.println("\nPart 3 In-order = " + bst);
        System.out.println("Part 3 Pre-order = " + bst.toStringPreOrder());

        // ---------- PART 4 ----------
        bst.clear();
        int[] part4 = { 90, 80, 100, 98, 91, 99 };
        for (int v : part4)
            bst.add(v);

        bst.remove(100);

        System.out.println("\nPart 4 In-order = " + bst);
        System.out.println("Part 4 Pre-order = " + bst.toStringPreOrder());

        // ---------- PART 5 ----------
        bst.clear();
        int[] part5 = { 90, 100, 98, 110 };
        for (int v : part5)
            bst.add(v);

        bst.remove(90);

        System.out.println("\nPart 5 In-order = " + bst);
        System.out.println("Part 5 Pre-order = " + bst.toStringPreOrder());

        // ---------- PART 6 ----------
        bst.clear();
        int[] part6 = { 90, 80, 100, 98, 110, 91, 99 };
        for (int v : part6)
            bst.add(v);

        bst.remove(90);

        System.out.println("\nPart 6 In-order = " + bst);
        System.out.println("Part 6 Pre-order = " + bst.toStringPreOrder());

        // ---------- PART 7 ----------
        bst.clear();
        bst.add(90);
        bst.remove(90);

        System.out.println("\nPart 7 In-order = " + bst);
        System.out.println("Part 7 Pre-order = " + bst.toStringPreOrder());
    }
}
