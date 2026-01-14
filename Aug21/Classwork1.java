package Aug21;

public class Classwork1 {
    public static void main(String[] args) {
        Student yasen = new Student("Yasen Giuli");

        System.out.println(yasen.saying());

        Profile yasenProfile = yasen;

        System.out.println(yasenProfile.saying());
    }
}

class Profile {
    private String name;
    
    public Profile(String name) {
        this.name = name;
    }

    public String saying() {
        return "My name is " + name;
    }
}

class Student extends Profile {
    public Student(String name) {
        super(name);
    }

    @Override
    public String saying() {
        return super.saying() + " I am a student";
    }
}
