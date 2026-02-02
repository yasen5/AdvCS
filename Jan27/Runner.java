package Jan27;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Runner extends JPanel implements ActionListener {

    private BinarySearchTree<Integer> tree;
    private JTextField input;
    private JButton loadScenarioButton;
    private JButton balanceButton;
    private final int[][] scenarios = new int[] {
            new int[] { 3, 2, 1 },
            new int[] { 1, 2, 3 },
            new int[] { 1, 3, 2 },
            new int[] { 4, 3, 2, 1, 5 },
            new int[] { 2, 1, 6, 5, 8, 9 },
            new int[] { 2, 1, 8, 6, 5, 9 },
            new int[] { 2, 1, 8, 5, 6, 9 },
            new int[] { 6, 2, 1, 5, 8, 9, 10 }
    };

    public Runner() {
        tree = new BinarySearchTree<>();

        input = new JTextField(5);
        loadScenarioButton = new JButton("Load Scenario");
        balanceButton = new JButton("Rotate Scenario");
        loadButton.addActionListener(this);
        rotateButton.addActionListener(this);

        this.add(new JLabel("Enter Value:"));
        this.add(input);
        this.add(addButton);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        tree.drawMe(g);
        g.drawString("" + tree.isBalanced(), 50, 50);
        g.drawString("Height: " + tree.getHeight(), 500, 400);
        g.drawString("Level: " + (tree.getHeight() + 1), 400, 400);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadScenarioButton) {
            int scenario = Integer.parseInt(input.getText());
            tree.clear();
            for (int val : scenarios[scenario - 1]) {
                tree.add(val);
            }
        } else if (e.getSource() == balanceButton) {
            int scenario = Integer.parseInt(input.getText());
            tree.balance();
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Binary Search Tree Visualizer");

        Runner panel = new Runner();

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
