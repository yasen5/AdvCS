package Feb5;

public class Runner {
    private static record Point(int x, int y) {
    }

    private static final Point[] dataset = new Point[] {
            new Point(1, 5),
            new Point(2, 3),
            new Point(3, 2)
    };

    public static void main(String[] args) {
        double xMean = 0;
        double yMean = 0;
        for (Point point : dataset) {
            xMean += point.x;
            yMean += point.y;
        }
        xMean /= dataset.length;
        yMean /= dataset.length;
        double numerator = 0;
        double denominator = 0;
        for (Point point : dataset) {
            numerator += (point.x - xMean) * (point.y - yMean);
            denominator += Math.pow(point.x - xMean, 2);
        }
        double slope = numerator / denominator;
        System.out.println("slope: " + slope);
        double intercept = yMean - slope * xMean;
        System.out.println("intercept: " + intercept);
    }
}
