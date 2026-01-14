package Sep2;

import java.util.Scanner;

public class Classwork2 {
    public static void main(String args[]) {
        MyArrayList<Task> tasks = new MyArrayList<>();
        for (int i = 0; i < 100; i++) {
            int randInt = (int) (Math.random() * 30 + 1);
            tasks.add(new Task("task" + randInt, randInt));
        }
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. View task list\n2. Add task\n3. Remove task\n4. Add task at index\n5. Remove task by index\n6. Replace task by index\n7. Remove at random\n8. Quit");
            switch (sc.nextInt()) {
                case 1 -> {
                    for (int i = 1; i < tasks.size(); i++) {
                        int index = i;
                        for (int j = i; j >= 0; j--) {
                            if (tasks.get(index).getRank() < tasks.get(j).getRank()) {
                                Task temp = tasks.get(index);
                                tasks.set(index, tasks.get(j));
                                tasks.set(j, temp);
                                index = j;
                            }
                        }
                    }
                    System.out.println(tasks);
                }
                case 2 -> {
                    System.out.println("Enter the name and rank of task");
                    tasks.add(new Task(sc.next(), sc.nextInt()));
                }
                case 3 -> {
                    System.out.println("enter name of task");
                    tasks.remove(new Task(sc.next(), -1));
                }
                case 4 -> {
                    System.out.println("Enter index, then name and rank of task");
                    tasks.add(sc.nextInt(), new Task(sc.next(), sc.nextInt()));
                }
                case 5 -> {
                    System.out.println("Enter index");
                    tasks.remove(sc.nextInt());
                }
                case 6 -> {
                    System.out.println("Enter index, then name and rank of task");
                    tasks.set(sc.nextInt(), new Task(sc.next(), sc.nextInt()));
                }
                case 7 -> {

                }
                case 8 -> {
                    return;
                }
                default -> {

                }
            }
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
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) continue;
            joined += list[i] + ((i == list.length - 1) ? "" : "\n");
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

class Task {
    private int rank;
    private String name;

    public Task(String name, int rank) {
        this.rank = rank;
        this.name = name;
    }

    public String toString() {
        return "#" + rank + " : " + name;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        Task casted = (Task) other;
        return casted.getName().equals(this.name);
    }
}
