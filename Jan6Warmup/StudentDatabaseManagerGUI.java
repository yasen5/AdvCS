import javax.swing.*;
import java.awt.*;

public class StudentDatabaseManagerGUI extends JFrame {

    // Display area for TreeSet contents
    private final JTextArea displayArea = new JTextArea();

    // Input fields
    private final JTextField nameField = new JTextField(10);
    private final JTextField ageField = new JTextField(5);
    private final JTextField idField  = new JTextField(8);

    public StudentDatabaseManagerGUI() {
        setTitle("Student Database Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== Display Panel =====
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // ===== Input Panel =====
        JPanel inputPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        inputPanel.add(new JLabel("Name"));
        inputPanel.add(new JLabel("Age"));
        inputPanel.add(new JLabel("ID"));
        inputPanel.add(nameField);
        inputPanel.add(ageField);
        inputPanel.add(idField);
        add(inputPanel, BorderLayout.NORTH);

        // ===== Button Panel =====
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        JButton viewButton   = new JButton("View TreeSet");
        JButton addButton    = new JButton("Add Profile");
        JButton removeButton = new JButton("Remove Profile");
        JButton clearButton  = new JButton("Clear Display");

        buttonPanel.add(viewButton);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // ===== Event Hooks (NO LOGIC) =====
        viewButton.addActionListener(e -> {
            // TODO: Display TreeSet contents (sorted by name, age, id)
        });

        addButton.addActionListener(e -> {
            // TODO: Add profile using nameField, ageField, idField
        });

        removeButton.addActionListener(e -> {
            // TODO: Remove profile by name, age, and id
        });

        clearButton.addActionListener(e -> displayArea.setText(""));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentDatabaseManagerGUI().setVisible(true);
        });
    }
}
