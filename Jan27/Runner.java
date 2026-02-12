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
    private final int[][] scenarios = new int[][] {
            new int[] { 3, 2, 1 },
            new int[] { 1, 2, 3 },
            new int[] { 1, 3, 2 },
            new int[] { 4, 3, 2, 1, 5 },
            new int[] { 2, 1, 6, 5, 8, 9 },
            new int[] { 2, 1, 8, 6, 5, 9 },
            new int[] { 2, 1, 8, 5, 6, 9 },
            new int[] { 6, 2, 1, 5, 8, 9, 10 },
            new int[] { 50, 20, 10, 5, 25, 80, 90, 100, 26, 17, 89, 34 }
    };

    public Runner() {
        tree = new BinarySearchTree<>();

        input = new JTextField(5);
        loadScenarioButton = new JButton("Load Scenario");
        balanceButton = new JButton("Rotate Scenario");
        loadScenarioButton.addActionListener(this);
        balanceButton.addActionListener(this);

        this.add(new JLabel("Enter Value:"));
        this.add(input);
        this.add(loadScenarioButton);
        this.add(balanceButton);
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
