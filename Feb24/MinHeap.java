package Feb24;

import java.util.ArrayList;

public class MinHeap<E extends Comparable<E>> {
    private ArrayList<E> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public MinHeap(int startingSize) {
        heap = new ArrayList<>(startingSize);
    }

    public void insert(E element) {
        heap.add(element);
        siftUp();
    }

    public E pop() {
        E temp = heap.get(0);
        heap.set(0, heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
        if (!heap.isEmpty()) {
            siftDown();
        }
        return temp;
    }

    public void siftDown() {
        E parent = heap.get(0);
        E leftChild;
        E rightChild;
        int i = 0;
        while (i*2+1 < heap.size()) {
            leftChild = heap.get(i*2+1);
            rightChild = (i*2+2 >= heap.size()) ? null : heap.get(i*2+2);
            if (rightChild != null && leftChild.compareTo(parent) > 0 && rightChild.compareTo(parent) > 0) {
                if (leftChild.compareTo(rightChild) > 0) {
                    heap.set(i, leftChild);
                    heap.set(i*2+1, parent);
                    i = i*2+1;
                }
                else {
                    heap.set(i, rightChild);
                    heap.set(i*2+2, parent);
                    i = i*2+2;
                }
            }
            else if (leftChild.compareTo(parent) > 0) {
                heap.set(i, leftChild);
                heap.set(i*2+1, parent);
                i = i*2+1;
            }
            else if (rightChild != null && rightChild.compareTo(parent) > 0) {
                heap.set(i, rightChild);
                heap.set(i*2+2, parent);
                i = i*2+2;
            }
            else {
                break;
            }
        }
    }

    public void siftUp() {
        int i = heap.size() - 1;
        while (i != 0) {
            E parent = heap.get(i/2);
            if (heap.get(i).compareTo(parent) < 0) {
                heap.set(i/2, heap.get(i));
                heap.set(i, parent);
            }
            i/=2;
        }
    }

    @Override
    public String toString() {
        String concat = "[";
        for (E val : heap) {
            concat += val + ", ";
        }
        concat += "]";
        return concat;
    }

    public void printTree() {
        int index = 0;
        int height = 0;
        int size = heap.size();
        while (index < heap.size()) {
            int endIndex = Math.min(size, index + (1 << height));
            for (int i = index; i < endIndex; i++) {
                System.out.print(heap.get(i) + " ");
            }
            System.out.println();
            index=endIndex;
            height++;
        }
    }

    public int size() {
        return heap.size();
    }
}
