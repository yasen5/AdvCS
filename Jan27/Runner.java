package Jan27;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Runner extends JPanel implements ActionListener {

    private BinarySearchTree<Integer> tree;
    private JTextField input;
    private JButton addButton;
    private JButton removeButton;
    private JButton clearButton;
    private JButton randomButton;

    public Runner() {
        tree = new BinarySearchTree<>();

        tree.add(3);
        tree.add(2);
        tree.add(1);

        input = new JTextField(5);
        addButton = new JButton("Add");
        addButton.addActionListener(this);

        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);

        randomButton = new JButton("Rotate");
        randomButton.addActionListener(this);

        this.add(new JLabel("Enter Value:"));
        this.add(input);
        this.add(addButton);
        this.add(removeButton);
        this.add(clearButton);
        this.add(randomButton);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        tree.drawMe(g);
        g.drawString("" + tree.isBalanced(), 50, 50);
        g.drawString("Height: " + tree.getHeight(), 400, 400);
        g.drawString("Level: " + (tree.getHeight() + 1), 400, 400);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            int value = Integer.parseInt(input.getText());
            tree.add(value);
            input.setText("");
        } else if (e.getSource() == removeButton) {
            int value = Integer.parseInt(input.getText());
            tree.remove(value);
            input.setText("");
        } else if (e.getSource() == clearButton) {
            tree.clear();
        } else if (e.getSource() == randomButton) {
            tree.rotate();
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
