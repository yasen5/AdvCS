import java.util.Scanner;

class Animal {
    private String type, name;
    private int age;

    public Animal(String type, String name, int age) {
        this.type = type;
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "I am a " + type + " named " + name + " and I am " + age + " years old";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class Aug12Classwork {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Animal animal = null;
        System.out.println("Please choose one of the following options: \nCreate animal.\n" +
                "Print animal.\n" +
                "Change name.\n" +
                "Change age.\n" +
                "Quit \n");
        while (true) {
            String nextLine = sc.nextLine();
            if (nextLine.equals("\n") || nextLine.equals("")) {
                continue;
            }
            switch (nextLine) {
                case "Create animal":
                    System.out.println("Please enter a type, a name and an age");
                    animal = new Animal(sc.next(), sc.next(), sc.nextInt());
                    break;
                case "Print animal":
                    if (animal == null) {
                        System.out.println("No animals available");
                    }
                    System.out.println(animal);
                    break;
                case "Change name":
                    System.out.println("Enter a name");
                    if (animal == null) {
                        System.out.println("No animals available");
                        break;
                    }
                    animal.setName(sc.next());
                    break;
                case "Change age":
                    System.out.println("Enter an age");
                    if (animal == null) {
                        System.out.println("No animals available");
                        break;
                    }
                    animal.setAge(sc.nextInt());
                    break;
                case "Quit":
                    sc.close();
                    return;
                default:
                    System.out.println("That is not an option or it was entered wrong");
            }
            System.out.println();
        }
    }
}