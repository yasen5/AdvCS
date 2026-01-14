package Sep9;

public class Runner {
    public static void main(String[] args) {
        MyArrayList<Integer> arr = new MyArrayList<>();
        for (int i = 0; i < 3000; i++) {
            arr.add(i);
        }
        System.out.println(arr);
    }
}

class MyArrayList<E> {
    private Object[] list;
    private int size = 0, capacity = 10;

    public MyArrayList() {
        this.list = new Object[capacity];
    }

    public boolean add(E element) {
        if (size == capacity) {
            this.capacity *= 2;
            Object[] newList = new Object[capacity];
            for (int i = 0; i < list.length; i++) {
                newList[i] = list[i];
            }
            this.list = newList;
        }
        list[size] = element;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) list[index];
    }

    public int size() {
        return size;
    }

    public String toString() {
        String joined = "";
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) continue;
            if (i != 0) {
                joined += ", ";
            }
            joined += list[i];
        }
        return joined;
    }
}
