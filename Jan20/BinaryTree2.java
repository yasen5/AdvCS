package Jan20;

public class BinaryTree2<E extends Comparable<E>> {
	private Node<E> root;

	public BinaryTree2() {
		root = null;
	}

	public void add(E elem) {
		if (root == null) {
			root = new Node<E>(elem);
		}
		else {
			add(elem, root);
		}
	}
	public void add(E elem, Node<E> node) {
		int comparison = elem.compareTo(node.get());
		if (comparison > 0) {
			if (node.getRight() == null) {
				node.setRight(new Node<E>(elem));
			} else {
				add(elem, node.getRight());
			}
		}
		else if (comparison < 0) {
			if (node.getLeft() == null) {
				node.setLeft(new Node<E>(elem));
			} else {
				add(elem, node.getLeft());
			}
		}
	}
	public boolean contains(E elem) {
		return contains(elem, root);
	}
	public boolean contains(E elem, Node<E> node) {
		if (node == null) return false;
		int comparison = elem.compareTo(node.get());
		if (comparison == 0) return true;
		return contains(elem, (comparison > 0) ? node.getRight() : node.getLeft());
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
