import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import util.MyDLList;
import util.Graph;

import java.io.File;
import java.io.IOException;

public class Screen extends JPanel implements MouseListener {
    public static final int IMAGE_WIDTH = 1000, IMAGE_HEIGHT = 1000, LOCATION_FONT_SIZE = 15;
    private final Color seeThroughWhite = new Color(255, 255, 255, 200);

    static BufferedImage parkMap;
    static {
        try {
            parkMap = ImageIO.read(new File("ghibli_map2.png"));
        } catch (IOException e) {
            System.out.println("Failed to load park map");
        }
    }
    private JTextField input;
    public static final Location[] locations = {
            new Location("KNS", "Koen Nishi Station", 0.036, 0.343),
            new Location("KNE", "Koen Nishi Station Entrance", 0.098, 0.298),
            new Location("NOE", "North Entrance", 0.526, 0.113),
            new Location("RKG", "Rotund Kazegaoka", 0.557, 0.167),
            new Location("MMM", "も", 0.831, 0.204),
            new Location("MOO", "魔", 0.799, 0.380),
            new Location("WEE", "West Entrance", 0.397, 0.599),
            new Location("TOG", "Toromon Gate", 0.643, 0.452),
            new Location("TOT", "Totoro", 0.804, 0.645),
            new Location("MPL", "Music Plaza", 0.613, 0.270),
            new Location("IDK", "大", 0.614, 0.403),
            new Location("BBD", "Baseball Diamond", 0.536, 0.879),
            new Location("FLC", "Flying Castle", 0.556, 0.266),
            new Location("KIA", "Kids' Area", 0.263, 0.359),
            new Location("MOT", "Mononoke Tent", 0.879, 0.894),
            new Location("FOR", "Forest Restaurant", 0.840, 0.845),
            new Location("DUP", "Duck Pond", 0.579, 0.584),
            new Location("FEW", "Duck Pond", 0.162, 0.337),
            new Location("SOC", "Soccer Field", 0.662, 0.103),
            new Location("FTR", "Food Trucks", 0.419, 0.233),
    };
    private Graph<Location> locationMap = new Graph<>();

    public static int charIdHash(String id) {
        return 26 * 26 * (id.charAt(0) - 'A') + 26 * (id.charAt(1) - 'A')
                + (id.charAt(2) - 'A');
    }

    public Traveler traveler = new Traveler(this, locationMap);

    public Screen() {
        setFocusable(true);
        addMouseListener(this);
        for (Location loc : locations) {
            locationMap.add(loc);
        }
        locationMap.makeEdge(charIdHash("KNS"), charIdHash("KNE"));
        locationMap.makeEdge(charIdHash("KNE"), charIdHash("FEW"));
        locationMap.makeEdge(charIdHash("KNE"), charIdHash("WEE")); // 1
        locationMap.makeEdge(charIdHash("FEW"), charIdHash("FTR"));
        locationMap.makeEdge(charIdHash("FTR"), charIdHash("RKG"));
        locationMap.makeEdge(charIdHash("FEW"), charIdHash("KIA")); // 2
        locationMap.makeEdge(charIdHash("RKG"), charIdHash("NOE"));
        locationMap.makeEdge(charIdHash("RKG"), charIdHash("SOC"));
        locationMap.makeEdge(charIdHash("RKG"), charIdHash("MPL")); // 3
        locationMap.makeEdge(charIdHash("SOC"), charIdHash("MMM"));
        locationMap.makeEdge(charIdHash("MOO"), charIdHash("MMM"));
        locationMap.makeEdge(charIdHash("MPL"), charIdHash("MMM")); // 4
        locationMap.makeEdge(charIdHash("IDK"), charIdHash("MOO"));
        locationMap.makeEdge(charIdHash("IDK"), charIdHash("TOG"));
        locationMap.makeEdge(charIdHash("IDK"), charIdHash("DUP")); // 5
        locationMap.makeEdge(charIdHash("TOT"), charIdHash("MOO")); // 6
        locationMap.makeEdge(charIdHash("TOT"), charIdHash("FOR")); // 7
        locationMap.makeEdge(charIdHash("FOR"), charIdHash("BBD"));
        locationMap.makeEdge(charIdHash("FOR"), charIdHash("MOT")); // 8
        input = new JTextField(3);
        input.addActionListener((e) -> {
            Location targetLocation = locationMap.getFullEntry(charIdHash(input.getText()));
            if (targetLocation != null) {
                MyDLList<Location> shortestPath = locationMap.shortestPath(traveler.lastTarget,
                        targetLocation);
                traveler.setTargetLocations(shortestPath);
            }
        });
        this.add(input);
        new Thread(traveler).start();
    }

    public Dimension getPreferredSize() {
        return new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(parkMap, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT,
                null);
        g.setFont(new Font("SansSerif", Font.BOLD, LOCATION_FONT_SIZE));
        for (Location location : locations) {
            String str = location.toString();
            int x = location.x - LOCATION_FONT_SIZE * str.length() / 3;
            int y = location.y;
            g.setColor(seeThroughWhite);
            g.fillRect(x, y - LOCATION_FONT_SIZE,
                    LOCATION_FONT_SIZE * str.length() * 3 / 5, LOCATION_FONT_SIZE * 4 / 3);
            g.setColor(Color.BLACK);
            g.drawString(str, x, y);
        }
        locationMap.drawGraph(g);
        traveler.draw(g);
        Location prevLocation = null;
        g.setColor(Color.GREEN);
        for (Location location : traveler.targetLocations) {
            if (prevLocation != null) {
                g.drawLine(location.x(), location.y(), prevLocation.x(), prevLocation.y());
            }
            prevLocation = location;
        }
        g.setColor(Color.BLACK);
        if (traveler.directions != null) {
            g.drawString(traveler.directions, 0, 0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse location: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}