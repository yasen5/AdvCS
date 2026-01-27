import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Runner extends JPanel implements ActionListener {

    private BinarySearchTree<Integer> tree;
    private JTextField input;
    private JButton addButton;

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

        this.add(new JLabel("Enter Value:"));
        this.add(input);
        this.add(addButton);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        tree.drawMe(g);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int value = Integer.parseInt(input.getText());
            tree.add(value);
            input.setText("");
            repaint();
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
        }
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
