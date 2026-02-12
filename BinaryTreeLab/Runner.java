package BinaryTreeLab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Runner extends JPanel implements ActionListener {
    private BinarySearchTree<Item> tree;
    private MyDLList<Item> cart;
    private JTextField input;
    private JButton addButton;
    private JButton removeButton;
    private JButton shoppingAddButton;
    private JButton shoppingRemoveButton;
    private JButton clearButton;
    private JButton searchButton;
    private int passes;
    private Item searchListing;

    public Runner() {
        tree = new BinarySearchTree<>();
        cart = new MyDLList<>();

        tree.add(new Item("Shoes", 5));
        tree.add(new Item("Milk", 7));
        tree.add(new Item("Monitor", 15));
        tree.add(new Item("Bread", 2));

        input = new JTextField(5);
        addButton = new JButton("Add to tree");
        addButton.addActionListener(this);

        removeButton = new JButton("Remove from tree");
        removeButton.addActionListener(this);

        shoppingAddButton = new JButton("Add to cart");
        shoppingAddButton.addActionListener(this);

        shoppingRemoveButton = new JButton("Remove from cart");
        shoppingRemoveButton.addActionListener(this);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);

        searchButton = new JButton("search");
        searchButton.addActionListener(this);

        this.add(new JLabel("Enter Value:"));
        this.add(input);
        this.add(addButton);
        this.add(removeButton);
        this.add(shoppingAddButton);
        this.add(shoppingRemoveButton);
        this.add(clearButton);
        this.add(searchButton);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 1000);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        tree.drawMe(g);
        g.drawString("Available items: ", 0, 450);
        int x = 150;
        for (Item item : tree.inOrder()) {
            g.drawString(item.fullListing(), x, 450);
            x += 100;
        }
        x = 0;
        double price = 0;
        for (Item item : cart) {
            g.drawString(item.fullListingWithQuantity(), x, 500);
            price += item.price * item.quantity;
            x += 125;
        }
        g.drawString("Total: " + price, 0, 550);
        g.drawString("Size: " + tree.size, 500, 500);
        g.drawString("Height: " + tree.getHeight(), 550, 500);
        g.drawString("Levels: " + (tree.getHeight() + 1), 630, 500);
        if (searchListing != null) {
            g.drawString("Passes: " + passes, 50, 50);
            g.drawString("Search: " + searchListing.fullListing(), 50, 75);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String text = input.getText();
            int price = Integer.parseInt(text.substring(text.indexOf(" ") + 1));
            tree.add(new Item(text.substring(0, text.indexOf(" ")), price));
            input.setText("");
        } else if (e.getSource() == removeButton) {
            String value = input.getText();
            tree.remove(new Item(value, 0.0));
            input.setText("");
        } else if (e.getSource() == clearButton) {
            tree.clear();
        } else if (e.getSource() == searchButton) {
            Item search = new Item(input.getText(), 0.0);
            passes = tree.passes(search);
            this.searchListing = tree.get(search);
        } else if (e.getSource() == shoppingAddButton) {
            String text = input.getText();
            int quantity = Integer.parseInt(text.substring(text.indexOf(" ") + 1));
            double price = tree.get(new Item(text.substring(0, text.indexOf(" ")), 0)).price;
            Item newItem = new Item(text.substring(0, text.indexOf(" ")), price, quantity);
            input.setText("");
            Item searchResult = cart.get(newItem);
            if (quantity == 0) {
                cart.remove(newItem);
            }
            if (searchResult == null) {
                cart.add(newItem);
            } else {
                searchResult.quantity += 1;
            }
        } else if (e.getSource() == shoppingRemoveButton) {
            String text = input.getText();
            cart.remove(new Item(text, 0));
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
