package harry;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class StudentGradeTrackerGUI extends JFrame {
    private JPanel contentPane;
    private JTextField nameField, marksField;
    private JTextArea summaryArea;
    private ArrayList<String> studentNames = new ArrayList<>();
    private ArrayList<Integer> studentMarks = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StudentGradeTrackerGUI frame = new StudentGradeTrackerGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public StudentGradeTrackerGUI() {
        setTitle("Student Grade Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245)); // Light tone background
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBackground(new Color(245, 245, 245));
        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Marks:"));
        marksField = new JTextField();
        inputPanel.add(marksField);

        JButton addButton = new JButton("Add Student");
        addButton.setBackground(new Color(70, 130, 180)); // Soft blue
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> addStudent());
        inputPanel.add(addButton);

        JButton reportButton = new JButton("Show Report");
        reportButton.setBackground(new Color(60, 179, 113)); // Soft green
        reportButton.setForeground(Color.WHITE);
        reportButton.setFocusPainted(false);
        reportButton.addActionListener(e -> showReport());
        inputPanel.add(reportButton);

        contentPane.add(inputPanel, BorderLayout.NORTH);

        summaryArea = new JTextArea();
        summaryArea.setEditable(false);
        summaryArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        summaryArea.setBackground(new Color(250, 250, 250));
        summaryArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        contentPane.add(new JScrollPane(summaryArea), BorderLayout.CENTER);
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String marksText = marksField.getText().trim();

        if (name.isEmpty() || marksText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill both fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int marks = Integer.parseInt(marksText);
            studentNames.add(name);
            studentMarks.add(marks);
            nameField.setText("");
            marksField.setText("");
            summaryArea.setText("Student added successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter valid number for marks.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showReport() {
        if (studentMarks.isEmpty()) {
            summaryArea.setText("No students added.");
            return;
        }

        StringBuilder report = new StringBuilder("Student Summary Report:\n\n");
        int total = 0, highest = studentMarks.get(0), lowest = studentMarks.get(0);

        for (int i = 0; i < studentNames.size(); i++) {
            int marks = studentMarks.get(i);
            report.append(studentNames.get(i)).append(" - ").append(marks).append(" marks\n");
            total += marks;
            if (marks > highest) highest = marks;
            if (marks < lowest) lowest = marks;
        }

        double average = (double) total / studentMarks.size();

        report.append("\nAverage marks: ").append(String.format("%.2f", average));
        report.append("\nHighest marks: ").append(highest);
        report.append("\nLowest marks: ").append(lowest);

        summaryArea.setText(report.toString());
    }
}

