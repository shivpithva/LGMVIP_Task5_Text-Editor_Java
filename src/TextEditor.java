
//Created by Shivam Pithva 
//Inter as Java Developer Intern at LetGrowMore
// Task 5 : Create a Text-Editor

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, helpMenu;
    private JMenuItem openItem, saveItem, closeItem, printItem, cutItem, copyItem, pasteItem, aboutItem;
    private JButton saveAndSubmitButton;

    public TextEditor() {
        // Set up the JFrame
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create JTextArea
        textArea = new JTextArea();
        textArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        textArea.setBackground(Color.white);

        // Create JMenuBar
        menuBar = new JMenuBar();

        // Create file menu and menu items
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        closeItem = new JMenuItem("Close");
        printItem = new JMenuItem("Print");
        // Add action listeners to file menu items
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        closeItem.addActionListener(this);
        printItem.addActionListener(this);
        // Add menu items to file menu
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(closeItem);
        fileMenu.add(printItem);
        // Designing file menu
        fileMenu.setFont(new Font("Arial", Font.PLAIN, 14));
        fileMenu.setForeground(Color.white);

        // Create edit menu and menu items
        editMenu = new JMenu("Edit");
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        // Add action listeners to edit menu items
        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        // Add menu items to edit menu
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        // Designing edit menu
        editMenu.setFont(new Font("Arial", Font.PLAIN, 14));
        editMenu.setForeground(Color.white);

        // Create help menu and menu items
        helpMenu = new JMenu("Help");
        aboutItem = new JMenuItem("About");
        // Add action listener to about menu item
        aboutItem.addActionListener(this);
        // Add about menu item to help menu
        helpMenu.add(aboutItem);
        // Designing help menu
        helpMenu.setFont(new Font("Arial", Font.PLAIN, 14));
        helpMenu.setForeground(Color.white);

        // Create Save and Submit button
        saveAndSubmitButton = new JButton("Save and Submit");
        saveAndSubmitButton.addActionListener(this);
        // Designing Save and Submit button
        saveAndSubmitButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveAndSubmitButton.setBackground(new Color(119, 164, 76));
        saveAndSubmitButton.setOpaque(true);
        saveAndSubmitButton.setFocusable(false);

        // Add menus to menubar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        menuBar.setBackground(new Color(84, 84, 84));
        menuBar.setOpaque(true);
        // Set the menubar
        setJMenuBar(menuBar);

        // Adding text area and button to the JFrame
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(saveAndSubmitButton, BorderLayout.SOUTH);

        // Display the JFrame
        setVisible(true);
    }

    // about section and documentation message dialogbox
    private void displayAboutDialog() {
        JOptionPane.showMessageDialog(this,
                "Shivam Pithva (LGMVIPADWL0008489)\nLetsGrowMore Virtual Intership as 'Java Developer Intern'\n\nA simple text editor application built with Java Swing. \nWhich has File Menu for operations like open, save, close, print and \nEdit Menu which has freatures like cut, copy and past. \nIt also has Save and Submit button.",
                "About", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openItem) {
            openFile();
        } else if (e.getSource() == saveItem) {
            saveFile();
        } else if (e.getSource() == closeItem) {
            closeFile();
        } else if (e.getSource() == printItem) {
            printFile();
        } else if (e.getSource() == cutItem) {
            textArea.cut();
        } else if (e.getSource() == copyItem) {
            textArea.copy();
        } else if (e.getSource() == pasteItem) {
            textArea.paste();
        } else if (e.getSource() == aboutItem) {
            displayAboutDialog();
        } else if (e.getSource() == saveAndSubmitButton) {
            saveFile();
            closeFile();
        }
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                reader.close();
                textArea.setText(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(textArea.getText());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeFile() {
        textArea.setText("");
    }

    private void printFile() {
        try {
            textArea.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TextEditor();
            }
        });
    }
}
