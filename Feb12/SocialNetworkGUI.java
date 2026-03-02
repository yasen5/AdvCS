package Feb12;

import javax.swing.*;
import java.awt.*;

public class SocialNetworkGUI extends JFrame {

    private Graph<Friend> graph;
    private Friend currentUser;
    private JTextArea outputArea;

    public SocialNetworkGUI() {

        setTitle("Social Network");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3, 10, 10));

        JButton viewAllButton = new JButton("View All Users");
        JButton createUserButton = new JButton("Create User");
        JButton loginButton = new JButton("Login");
        JButton viewFriendsButton = new JButton("View My Friends");
        JButton changeCollegeButton = new JButton("Change College");
        JButton searchButton = new JButton("Search Friend");
        JButton connectButton = new JButton("Connect to Friend");

        buttonPanel.add(viewAllButton);
        buttonPanel.add(createUserButton);
        buttonPanel.add(loginButton);
        buttonPanel.add(viewFriendsButton);
        buttonPanel.add(changeCollegeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(connectButton);

        add(buttonPanel, BorderLayout.SOUTH);

        createUserButton.addActionListener(e -> {

            JTextField nameField = new JTextField();
            JTextField collegeField = new JTextField();
            JTextField idField = new JTextField();

            JPanel panel = new JPanel(new GridLayout(3, 2));
            panel.add(new JLabel("Name:"));
            panel.add(nameField);
            panel.add(new JLabel("College:"));
            panel.add(collegeField);
            panel.add(new JLabel("4-digit ID:"));
            panel.add(idField);

            int result = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Create New User",
                    JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {

                String name = nameField.getText();
                String college = collegeField.getText();
                String idText = idField.getText();
                int id = Integer.parseInt(idText);
                if (graph.getMatching(new Friend("", "", id)) == null) {
                    Friend newFriend = new Friend(name, college, id);

                    outputArea.append("\n\n" + "User Created:");
                    outputArea.append("\n\n" + newFriend.toString());

                    graph.add(newFriend);
                }

            }
        });

        loginButton.addActionListener(e -> {

            String idText = JOptionPane.showInputDialog("Enter your 4-digit ID to login:");

            int id = Integer.parseInt(idText);

            Friend maybeUser = graph.getMatching(new Friend("", "", id));
            if (maybeUser != null) {
                currentUser = maybeUser;
            }

            outputArea.append("\n\n" + "Logged in as: " + currentUser.toString());
        });

        viewAllButton.addActionListener(e -> {
            outputArea.append("\n\n");
            outputArea.append(graph.toString());
        });

        viewFriendsButton.addActionListener(e -> {
            outputArea.append("\n\n" + "Friends of ID " + currentUser.getId() + ":");
            for (Friend connection : graph.getConnections(currentUser).toDLList()) {
                outputArea.append("\n" + connection.toString());
            }
        });

        changeCollegeButton.addActionListener(e -> {
            String newCollege = JOptionPane.showInputDialog(
                    "Enter new college:");
            currentUser.setCollege(newCollege);

            outputArea.append("\n\n" + "College changed locally to: "
                    + newCollege);
        });

        searchButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(
                    "Enter name to search:");
            Friend friend = graph.BFS(currentUser, new Friend(name, "", 'X'));
            outputArea.append("\n\n" + ((friend == null) ? "Nonexistent" : friend.toString()));
        });

        connectButton.addActionListener(e -> {
            String idText = JOptionPane.showInputDialog(
                    "Enter ID to connect:");
            int targetId = Integer.parseInt(idText);
            graph.makeEdge(currentUser, graph.getMatching(new Friend("", "", targetId)));
        });

        this.graph = new Graph<>();
        Friend jen = new Friend("Jen", "Harvard", 1111);
        Friend jose = new Friend("Jose", "Harvard", 4321);
        Friend john = new Friend("John", "Harvard", 1234);
        Friend jane = new Friend("Jane", "Harvard", 2222);
        Friend henry = new Friend("Henry", "Yale", 7777);
        Friend harry = new Friend("Harry", "Yale", 8888);
        Friend hailey = new Friend("Hailey", "Yale", 5678);
        Friend heather = new Friend("Heather", "Yale", 8765);
        graph.add(jen);
        graph.add(jose);
        graph.add(john);
        graph.add(jane);
        graph.add(henry);
        graph.add(harry);
        graph.add(hailey);
        graph.add(heather);
        graph.makeEdge(john, jose);
        graph.makeEdge(john, jane);
        graph.makeEdge(jose, jane);
        graph.makeEdge(harry, henry);
        graph.makeEdge(harry, heather);
        graph.makeEdge(heather, hailey);
        graph.makeEdge(hailey, henry);
        graph.makeEdge(jose, jen);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SocialNetworkGUI().setVisible(true);
        });
    }
}