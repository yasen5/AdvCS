package Jan15;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BSTVisualizer extends JPanel implements ActionListener {

    private BinarySearchTree<Integer> tree;
    private JTextField inputField;
    private JButton addButton;

    public BSTVisualizer() {
        tree = new BinarySearchTree<>();

        // Initial tree from image
        tree.add(50);
        tree.add(20);
        tree.add(80);
        tree.add(70);
        tree.add(90);

        inputField = new JTextField(5);
        addButton = new JButton("Add");
        addButton.addActionListener(this);

        this.add(new JLabel("Value:"));
        this.add(inputField);
        this.add(addButton);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        tree.drawMe(g);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int value = Integer.parseInt(inputField.getText());
            tree.add(value);
            inputField.setText("");
            repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid integer.");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Binary Search Tree Visualizer");
        BSTVisualizer panel = new BSTVisualizer();

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}