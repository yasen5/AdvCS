package Sep2;


public class Classwork1 {
    public static void main(String args[]) {
        MyArrayList<Task> tasks = new MyArrayList<>();
        for (int i = 0; i < 100; i++) {
            tasks.add(new Task(i));
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i));
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
        if (size == capacity) {
            capacity *= 2;
        }
        Object[] newList = new Object[capacity];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        newList[size] = element;
        list = newList;
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
}

class Task {
    private int num;

    public Task(int num) {
        this.num = num;
    }

    public String toString() {
        return "" + num;
    }
}
