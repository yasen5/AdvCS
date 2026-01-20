package Jan20;

public class Node<E extends Comparable<E>> {
    private E data;
    private Node<E> leftChild = null, rightChild = null;

    public Node(E data) {
        this.data = data;
    }

    public E get() {
        return data;
    }

    public Node<E> getLeft() {
        return leftChild;
    }

    public Node<E> getRight() {
        return rightChild;
    }

    public void setLeft(Node<E> left) {
        this.leftChild = left;
    }

    public void setRight(Node<E> right) {
        this.rightChild = right;
    }
}
