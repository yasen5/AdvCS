package Jan15;

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
    private JButton containsButton;
    private JButton randomButton;
    private boolean containsResult = false;

    public Runner() {
        tree = new BinarySearchTree<>();

        tree.add(42);
        tree.add(28);
        tree.add(62);

        tree.add(21);
        tree.add(31);
        tree.add(59);
        tree.add(71);

        tree.add(17);
        tree.add(23);
        tree.add(30);
        tree.add(35);
        tree.add(57);
        tree.add(60);
        tree.add(67);
        tree.add(82);

        input = new JTextField(5);
        addButton = new JButton("Add");
        addButton.addActionListener(this);

        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);

        containsButton = new JButton("Contains");
        containsButton.addActionListener(this);

        randomButton = new JButton("Add Random");
        randomButton.addActionListener(this);

        this.add(new JLabel("Enter Value:"));
        this.add(input);
        this.add(addButton);
        this.add(removeButton);
        this.add(clearButton);
        this.add(containsButton);
        this.add(randomButton);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        tree.drawMe(g);
        if (containsResult) {
            g.drawString("Contained!", 50, 50);
        }
        g.drawString("Height: " + tree.getHeight(), 400, 400);
        g.drawString("Level: " + (tree.getHeight() + 1), 450, 450);
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
        } else if (e.getSource() == containsButton) {
            containsResult = tree.contains(Integer.parseInt(input.getText()));
        }
        else if (e.getSource() == randomButton) {
            tree.clear();
            for (int i = 0; i < 15; i++) {
                int value = (int)(Math.random() * 99) + 1;
                tree.add(value);
            }
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
