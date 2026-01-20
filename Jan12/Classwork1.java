package Jan12;

public class Classwork1 {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) {
        Node<Integer> head = new Node(1);
        head.setLeft(new Node<Integer>(2));
        head.setRight(new Node<Integer>(3));
        head.getLeft().setLeft(new Node<Integer>(4));
        head.getLeft().setRight(new Node<Integer>(5));
        head.getRight().setLeft(new Node<Integer>(6));
        head.getRight().setRight(new Node<Integer>(7));
        printInOrder(head);
        System.out.println();
        printPreOrder(head);
        System.out.println();
        printPostOrder(head);
        System.out.println();
        printReverseOrder(head);
        System.out.println();
    }

    @SuppressWarnings("rawtypes")
    public static void printInOrder(Node node) {
        if (node.getLeft() != null) {
            printInOrder(node.getLeft());
        }
        System.out.print(node.get() + " ");
        if (node.getRight() != null) {
            printInOrder(node.getRight());
        }
    }

    @SuppressWarnings("rawtypes")
    public static void printPreOrder(Node node) {
        System.out.print(node.get() + " ");
        if (node.getLeft() != null) {
            printPreOrder(node.getLeft());
        }
        if (node.getRight() != null) {
            printPreOrder(node.getRight());
        }
    }

    @SuppressWarnings("rawtypes")
    public static void printPostOrder(Node node) {
        if (node.getLeft() != null) {
            printPostOrder(node.getLeft());
        }
        if (node.getRight() != null) {
            printPostOrder(node.getRight());
        }
        System.out.print(node.get() + " ");
    }

    @SuppressWarnings("rawtypes")
    public static void printReverseOrder(Node node) {
        if (node.getRight() != null) {
            printReverseOrder(node.getRight());
        }
        System.out.print(node.get() + " ");
        if (node.getLeft() != null) {
            printReverseOrder(node.getLeft());
        }
    }
}
