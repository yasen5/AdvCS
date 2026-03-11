import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import util.MyDLList;
import util.Graph.WeightedLink;

public class Traveler implements Runnable {
    public MyDLList<Location> targetLocations = new MyDLList<>();
    public Location lastTarget;
    public MyDLList<WeightedLink<Location>> lastPath = null;
    public int x, y;
    public final int SPEED = Screen.IMAGE_WIDTH / 200;
    private int animState = 0;
    private BufferedImage[] animation;
    private final int WIDTH = 20, HEIGHT = 20;
    private final Screen screen;

    public Traveler(Screen screen) {
        this.x = 0;
        this.y = 0;
        this.screen = screen;
        targetLocations.add(Screen.locations[0]);
        lastTarget = targetLocations.get(0);
        try {
            animation = new BufferedImage[] {
                    ImageIO.read(new File("anim1.png")),
                    ImageIO.read(new File("anim2.png")),
                    ImageIO.read(new File("anim3.png")),
            };
            return;
        } catch (IOException e) {
            System.out.println("L: " + e);
            System.exit(0);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (targetLocations.size() == 0) {
                continue;
            }
            int xDiff = targetLocations.last().x - x;
            int yDiff = targetLocations.last().y - y;
            if (Math.abs(xDiff) < SPEED && Math.abs(yDiff) < SPEED) {
                targetLocations.remove(targetLocations.size() - 1);
            }
            double angle = Math.atan2(yDiff, xDiff);
            animState = (animState + 1) % animation.length;
            x += (int) (Math.cos(angle) * SPEED);
            y += (int) (Math.sin(angle) * SPEED);
            screen.repaint();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(animation[animState], x - WIDTH / 2, y - WIDTH / 2, WIDTH, HEIGHT, null);
    }

    public void setTargetLocations(MyDLList<Location> path) {
        path.readReversed(true);
        for (Location loc : path) {
            lastPath.add()
        }
        targetLocations = locs;
        lastTarget = targetLocations.get(0);
    }
}
