package Jan13;

public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;

    public BinarySearchTree() {
        root = null;
    }

    public void add(E elem) {
        if (root == null) {
            root = new Node<E>(elem);
        } else {
            add(elem, root);
        }
    }

    private void add(E elem, Node<E> node) {
        if (node.get().compareTo(elem) > 0) {
            if (node.getLeft() == null) {
                node.setLeft(new Node<E>(elem));
            } else {
                add(elem, node.getLeft());
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new Node<E>(elem));
            } else {
                add(elem, node.getRight());
            }
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

    public boolean contains(E elem) {
        return root == null ? false : contains(elem, root);
    }

    private boolean contains(E elem, Node<E> node) {
        int comparisonResult = node.get().compareTo(elem);
        if (comparisonResult == 0) {
            return true;
        } else if (comparisonResult < 0) {
            return node.getRight() != null && contains(elem, node.getRight());
        } else {
            return node.getLeft() != null && contains(elem, node.getLeft());
        }
    }

    public void remove(E elem) {
        remove(elem, root, null);
    }

    private void remove(E elem, Node<E> currNode, Node<E> parent) {
        if (currNode == null) {
            return;
        }
        int comparisonResult = currNode.get().compareTo(elem);
        if (comparisonResult == 0) {
            boolean leftExists = currNode.getLeft() != null;
            boolean rightExists = currNode.getRight() != null;
            Node<E> replacementNode = null;
            if (leftExists && !rightExists) {
                replacementNode = currNode.getLeft();
            } else if (!leftExists && rightExists) {
                replacementNode = currNode.getRight();
            } else if (leftExists && rightExists) {
                Node<E> lowestRightParent = currNode;
                Node<E> lowestRight = currNode.getRight();
                while (lowestRight.getLeft() != null) {
                    lowestRightParent = lowestRight;
                    lowestRight = lowestRight.getLeft();
                }
                if (lowestRightParent == currNode) {
                    lowestRightParent.setRight(null);
                } else {
                    lowestRightParent.setLeft(null);
                }
                lowestRight.setLeft(currNode.getLeft());
                lowestRight.setRight(currNode.getRight());
                replacementNode = lowestRight;
            }
            if (parent == null) {
                root = replacementNode;
            } else {
                if (currNode.get().compareTo(parent.get()) < 0) {
                    parent.setLeft(replacementNode);
                } else {
                    parent.setRight(replacementNode);
                }
            }
        } else if (comparisonResult < 0) {
            remove(elem, currNode.getRight(), currNode);
        } else if (comparisonResult > 0) {
            remove(elem, currNode.getLeft(), currNode);
        }
    }

    public void clear() {
        root = null;
    }
}
