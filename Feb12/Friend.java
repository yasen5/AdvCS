package Feb12;

public class Friend {

    private String name;
    private String college;
    private int id;

    public Friend(String name, String college, int id) {
        this.name = name;
        this.college = college;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCollege() {
        return college;
    }

    public int getId() {
        return id;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Friend))
            return false;
        Friend other = (Friend) obj;
        return this.id == other.id || this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return id + ":" + name + ":" + college;
    }
}