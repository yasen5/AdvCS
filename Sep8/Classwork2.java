package Sep8;

import java.util.Scanner;

public class Classwork2 {
    private static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyArrayList<Integer> arr = new MyArrayList<>();
        for (int i = 0; i < 2000; i++) {
            arr.add((int)(Math.random() * 10000));
        }
        for (int i = arr.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j+1));
                    arr.set(j+1, temp);
                }
            }
        }
        System.out.println(arr);
        System.out.println("\n\nEnter a number to search for");
        System.out.println("That number was at index " + binarySearch(arr, sc.nextInt(), 0, arr.size()));
    }

    public static int binarySearch(MyArrayList<Integer> arr, int searchTerm, int minIndex, int maxIndex) {
        count++;
        System.out.println("Count: " + count);
        int testIndex = (minIndex + maxIndex) / 2;
        int valueAtIndex = arr.get(testIndex);
        if (maxIndex < minIndex) {
            return -1;
        }
        if (valueAtIndex == searchTerm) {
            return testIndex;
        }
        else if (valueAtIndex > searchTerm) {
            return binarySearch(arr, searchTerm, minIndex, testIndex);
        }
        else {
            return binarySearch(arr, searchTerm, testIndex + 1, maxIndex);
        }
    }
}

class MyArrayList<E> {
    private Object[] list;
    private int size = 0;
    private int capacity = 6;

    public MyArrayList() {
        list = new Object[capacity];
    }

    public boolean add(E element) {
        checkCapacity();
        list[size] = element;
        size++;
        return true;
    }

    private void checkCapacity() {
        if (size == capacity) {
            capacity *= 2;
            Object[] newList = new Object[capacity];
            for (int i = 0; i < list.length; i++) {
                newList[i] = list[i];
            }
            list = newList;
        }
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) list[index];
    }

    public int size() {
        return size;
    }

    public E remove(int index) {
        E removed = get(index);
        for (int i = index; i < list.length - 1; i++) {
            list[index] = list[index + 1];
        }
        size--;
        list[size] = null;
        return removed;
    }

    @SuppressWarnings("unchecked")
    public E remove(Object element) {
        for (int i = 0; i < list.length; i++) {
            E cast = (E) element;
            if (cast.equals(list[i])) {
                return remove(i);
            }
        }
        return null;
    }

    public void set(int index, E newElement) {
        list[index] = newElement;
    }

    public boolean contains(Object val) {
        for (Object obj : list) {
            if (obj.equals(val)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String joined = "";
        // for (int i = 0; i < list.length; i++) {
        //     if (list[i] == null) continue;
        //     joined += list[i] + ((i == list.length - 1) ? "" : "\n");
        // }
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null)
                joined += list[i] + ", ";
        }
        return joined;
    }

    public void add(int index, Object element) {
        checkCapacity();
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = element;
        size++;
    }
}
