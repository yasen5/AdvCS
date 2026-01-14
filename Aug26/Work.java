package Aug26;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Work {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        ArrayList<Company> companies = new ArrayList<>(List.of(new Company("Microsoft", 110.47), new Company("Google", 1172.10), new Company("Apple", 219.89)));
        ArrayList<Pair<Employee, Company>> pairs = new ArrayList<>(List.of(
            new Pair<Employee, Company>(new Employee("John"), companies.get(0)),
            new Pair<Employee, Company>(new Employee("Jose"), companies.get(1)),
            new Pair<Employee, Company>(new Employee("Jennifer"), companies.get(1)),
            new Pair<Employee, Company>(new Employee("Heather"), companies.get(2)),
            new Pair<Employee, Company>(new Employee("Maria"), companies.get(2))
        ));

        while (true) {
            System.out.println("\nWhich option?\n1. Show all employees and where they work\n2. Change the stock price of a company\n3. Add a new company\n4. Switch an employee's company\n5. Quit\n");
            switch (sc.nextInt()) {
                case 1 -> {
                    for (Pair<Employee, Company> employee : pairs) {
                        System.out.println(employee.getFirst() + " : " + employee.getSecond());
                    }
                }
                case 2 -> {
                    System.out.println("Which company would you like to modify the stock price of?");
                    String compName = sc.next();
                    for (Company company : companies) {
                        if (company.getName().equals(compName)) {
                            System.out.println("What do you want to set the price to?");
                            company.updateStockPrice(sc.nextDouble());
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Enter the name and stock price of the company");
                    companies.add(new Company(sc.next(), sc.nextDouble()));
                }
                case 4 -> {
                    System.out.println("Enter the name of the employee");
                    String name = sc.next();
                    for (Pair<Employee, Company> employee : pairs) {
                        if (employee.getFirst().toString().equals(name)) {
                            System.out.println("Which company should they switch to");
                            String compName = sc.next();
                            for (Company company : companies) {
                                if (company.getName().equals(compName)) {
                                    employee.setSecond(company);
                                }
                            }
                        }
                    }
                }
                case 5 -> {
                    return;
                }
            }
        }
    }
}

class Pair<T, V> {
    private T obj1;
    private V obj2;

    public Pair(T obj1, V obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public T getFirst() {
        return obj1;
    }

    public V getSecond() {
        return obj2;
    }

    public void setSecond(V newObj2) {
        this.obj2 = newObj2;
    }
}

class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

class Company {
    private String name;
    private double stockPrice;

    public Company(String name, double stockPrice) {
        this.name = name;
        this.stockPrice = stockPrice;
    }

    public void updateStockPrice(double newStockPrice) {
        this.stockPrice = newStockPrice;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + " - $" + stockPrice;
    }
}


