package Jan20;

public class BinaryTree<E extends Comparable<E>> {
    private Node<E> root = null;

    public void add(E data) {
        if (root == null) {
            root = new Node<E>(data);
        } else {
            add(data, root);
        }
    }

    public void add(E data, Node<E> parent) {
        int comparison = data.compareTo(parent.get());
        if (comparison > 0) {
            if (parent.getRight() == null) {
                parent.setRight(new Node<E>(data));
            } else {
                add(data, parent.getRight());
            }
        } else if (comparison < 0) {
            if (parent.getLeft() == null) {
                parent.setLeft(new Node<E>(data));
            } else {
                add(data, parent.getLeft());
            }
        }
    }

    public boolean contains(E data) {
        return root == null ? false : contains(data, root);
    }

    public boolean contains(E data, Node<E> node) {
        if (node == null) {
            return false;
        }
        int comparison = data.compareTo(node.get());
        if (comparison > 0) {
            return contains(data, node.getRight());
        } else if (comparison < 0) {
            return contains(data, node.getLeft());
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return root == null ? "" : inOrder(root);
    }

    private static String inOrder(@SuppressWarnings("rawtypes") Node node) {
        String str = "";
        if (node.getLeft() != null) {
            str += inOrder(node.getLeft()) + " ";
        }
        str += node.get() + " ";
        if (node.getRight() != null) {
            str += inOrder(node.getRight()) + " ";
        }
        return str;
    }

    public String toStringPreOrder() {
        return root == null ? "" : toStringPreOrder(root);
    }

    public static String toStringPreOrder(@SuppressWarnings("rawtypes") Node node) {
        String str = "";
        str += node.get() + " ";
        if (node.getLeft() != null) {
            str += toStringPreOrder(node.getLeft()) + " ";
        }
        if (node.getRight() != null) {
            str += toStringPreOrder(node.getRight()) + " ";
        }
        return str;
    }
}
