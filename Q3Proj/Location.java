import util.LocationInterface;

public class Location implements LocationInterface {
    public final String abbreviation, name;
    public final int x, y;

    public Location(String abbreviation, String name, int x, int y) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Location(String abbreviation, String name, double x, double y) {
        this(abbreviation, name, (int) (x * Screen.IMAGE_WIDTH), (int) (y * Screen.IMAGE_HEIGHT));
    }

    @Override
    public int hashCode() {
        return Screen.charIdHash(abbreviation);
    }

    @Override
    public String toString() {
        return name + "(" + abbreviation + ")";
    }

    @Override
    public double GetDistance(LocationInterface other) {
        return Math.hypot(this.x - other.x(), this.y - other.y());
    }

    @Override
    public int x() {
        return this.x;
    }

    @Override
    public int y() {
        return this.y;
    }
}