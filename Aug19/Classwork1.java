package Aug19;

import java.util.ArrayList;
import java.util.Scanner;

public class Classwork1 {
    public static void main(String[] args) {
        ArrayList<Task> todo = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Would you like to:\n1. Add a task\n2. Remove a task\n3. Quit");
            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.println("Please enter the name and rank of the task");
                    String name = sc.next();
                    int rank = sc.nextInt();
                    if (todo.size() == 0) {
                        todo.add(new Task(name, rank));
                    }
                    else {
                        for (int i = 0; i < todo.size(); i++) {
                            if (todo.get(i).getRank() >= rank) {
                                todo.add(i, new Task(name, rank)); 
                                break;
                            }
                        }
                        todo.add(new Task(name, rank));
                    }
                }
                case 2 -> {
                    System.out.println("Please enter the name task to remove");
                    for (Task task : todo) {
                        if (task.getTask().equals(sc.next())) {
                            todo.remove(task);
                            break;
                        }
                    }
                }
                case 3 -> {
                    sc.close();
                    return;
                }
            }
            for (Task task : todo) {
                System.out.println(task);
            }
        }
    }
}

class Task {
    String task;
    int rank;

    public Task(String task, int rank) {
        this.task = task;
        this.rank = rank;
    }

    public String getTask() {
        return task;
    }

    public int getRank() {
        return rank;
    }

    public String toString() {
        return rank + ". " + task;
    }
}