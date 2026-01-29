package Jan27;

import java.awt.Graphics;
import java.awt.Color;
import Jan15.Node;

public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;

    public BinarySearchTree() {
        root = null;
    }

    public void add(E element) {
        if (root == null) {
            root = new Node<E>(element);
        }

        else {
            add(element, root);
        }
    }

    private void add(E element, Node<E> node) {
        int comparisonResult = element.compareTo(node.get());

        if (comparisonResult < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new Node<>(element));
            }

            else {
                add(element, node.getLeft());
            }
        }

        else if (comparisonResult > 0) {
            if (node.getRight() == null) {
                node.setRight(new Node<>(element));
            } else {
                add(element, node.getRight());
            }
        }
    }

    public String toString() {
        String string = inOrderString(root);
        return string;
    }

    private String inOrderString(Node<E> node) {
        if (node == null) {
            return "";
        }

        return inOrderString(node.getLeft()) + node.get() + " " + inOrderString(node.getRight());
    }

    public String toStringPreOrder() {
        String string = toStringPreOrder(root);
        return string;
    }

    private String toStringPreOrder(Node<E> node) {
        if (node == null) {
            return "";
        }

        return node.get() + " " + toStringPreOrder(node.getLeft()) + toStringPreOrder(node.getRight());
    }

    public boolean contains(E element) {
        return contains(element, root);
    }

    private boolean contains(E element, Node<E> node) {
        if (node == null) {
            return false;
        }

        int comparisonResult = element.compareTo(node.get());

        if (comparisonResult == 0) {
            return true;
        } else if (comparisonResult > 0) {
            return contains(element, node.getRight());
        }

        else {
            return contains(element, node.getLeft());
        }
    }

    public void remove(E element) {
        remove(element, root, null);
    }

    private void remove(E element, Node<E> current, Node<E> parent) {
        if (current == null) {
            return;
        }

        int comparisonResult = element.compareTo(current.get());

        if (comparisonResult < 0) {
            remove(element, current.getLeft(), current);
        }

        else if (comparisonResult > 0) {
            remove(element, current.getRight(), current);
        }

        else {

            if (current.getLeft() != null && current.getRight() != null) {

                Node<E> successor = current.getRight();
                Node<E> successorParent = current;

                while (successor.getLeft() != null) {
                    successorParent = successor;
                    successor = successor.getLeft();
                }

                current.set(successor.get());

                if (successorParent == current) {
                    successorParent.setRight(null);
                } else {
                    successorParent.setLeft(null);
                }
            }

            else {
                Node<E> child;

                if (current.getLeft() != null) {
                    child = current.getLeft();
                } else {
                    child = current.getRight();
                }

                if (parent == null) {
                    root = child;
                } else if (parent.getLeft() == current) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
            }
        }
    }

    public void clear() {
        clear(root, null);
        root = null;
    }

    private void clear(Node<E> current, Node<E> parent) {
        if (current == null) {
            return;
        }

        clear(current.getLeft(), current);
        clear(current.getRight(), current);

        if (parent != null) {
            if (parent.getLeft() == current) {
                parent.setLeft(null);
            } else if (parent.getRight() == current) {
                parent.setRight(null);
            }
        }
    }

    public void drawMe(Graphics g) {
        drawNode(g, root, 400, 60, 200);
    }

    private void drawNode(Graphics g, Node<E> node, int x, int y, int offset) {
        if (node == null) {
            return;
        }

        Color ovalColor = new Color(10, 140, 245);
        g.setColor(ovalColor);
        g.fillOval(x - 20, y - 20, 40, 40);

        g.setColor(Color.BLACK);
        g.drawOval(x - 20, y - 20, 40, 40);
        g.drawString(node.get().toString(), x - 8, y + 5);

        if (node.getLeft() != null) {
            g.drawLine(x, y, x - offset, y + 60);
            drawNode(g, node.getLeft(), x - offset, y + 60, offset / 2);
        }

        if (node.getRight() != null) {
            g.drawLine(x, y, x + offset, y + 60);
            drawNode(g, node.getRight(), x + offset, y + 60, offset / 2);
        }
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node<E> node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int getLevel() {
        return getHeight() + 1;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    public boolean isBalanced(Node<E> node) {
        if (node == null) {
            return true;
        }
        if (Math.abs(getHeight(node.getLeft()) - getHeight(node.getRight())) >= 2) {
            return false;
        }
        return isBalanced(node.getLeft()) && isBalanced(node.getRight());
    }

    public void rotateLeft(Node<E> parent, Node<E> swinging) {
        Node<E> pivot = swinging.getRight();
        Node<E> temp = pivot.getLeft();
        pivot.setLeft(swinging);
        swinging.setRight(temp);
        if (parent == null) {
            root = pivot;
        } else {
            parent.setRight(pivot);
        }
    }

    public void rotateRight(Node<E> parent, Node<E> swinging) {
        Node<E> pivot = swinging.getLeft();
        Node<E> temp = pivot.getRight();
        pivot.setRight(swinging);
        swinging.setLeft(temp);
        if (parent == null) {
            root = pivot;
        } else {
            parent.setLeft(pivot);
        }
    }

    public void rotate() {
        rotateRight(null, root);
    }
}
