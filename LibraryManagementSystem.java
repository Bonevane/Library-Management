import javax.swing.*;
import java.awt.event.*;

public class LibraryManagementSystem{
    public static void main(String[] args) {
        
        // GUI Elements
        GUI events = new GUI();
        JFrame frame = new JFrame("Library Management System");
        JButton addBookButton = new JButton("Add Book");
        JButton addUserButton = new JButton("Add User");
        JButton printBooksButton = new JButton("Show All Books");
        JButton printUsersButton = new JButton("Show All Users");
        JButton checkOutButton = new JButton("Check Out");
        JButton checkInButton = new JButton("Check In");
        JButton searchBookButton = new JButton("Search Book");
        JButton searchUserButton = new JButton("Search User");

        addBookButton.setBounds(50, 50, 200, 50);
        addUserButton.setBounds(350, 50, 200, 50);
        printBooksButton.setBounds(50, 150, 200, 50);
        printUsersButton.setBounds(350, 150, 200, 50);
        checkInButton.setBounds(50, 250, 200, 50);
        checkOutButton.setBounds(350, 250, 200, 50);
        searchBookButton.setBounds(50, 350, 200, 50);
        searchUserButton.setBounds(350, 350, 200, 50);

        frame.add(addBookButton);
        frame.add(addUserButton);
        frame.add(printBooksButton);
        frame.add(printUsersButton);
        frame.add(checkInButton);
        frame.add(checkOutButton);
        frame.add(searchBookButton);
        frame.add(searchUserButton);
        frame.setSize(620, 500);
        frame.setLayout(null);
        frame.setVisible(true);


        // Making a new Library object and loading in the data from the .txt file
        Library lib = new Library();
        lib.loadBookData();
        lib.loadUserData();


        // Action Listeners for each button on the Home Screen
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.addBookButtonEvent(lib);
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.addUserButtonEvent(lib);
            }
        });

        printBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.printBooksButtonEvent(lib);
            }
        });

        printUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.printUsersButtonEvent(lib);
            }
        });

        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.searchBookButtonEvent(lib);
            }
        });

        searchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.searchUserButtonEvent(lib);
            }
        });

        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.checkInButtonEvent(lib);
            }
        });

        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.checkOutButtonEvent(lib);
            }
        });
    }
}


