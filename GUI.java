import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.*;

public class GUI {
    // Event for when the "Add Book" button is pressed
    public void addBookButtonEvent(Library lib) {
        JFrame addBookFrame = new JFrame();
        addBookFrame.setSize(400, 440);
        addBookFrame.setLayout(null);
        addBookFrame.setVisible(true);

        JButton OK = new JButton("Ok");
        JButton CANCEL = new JButton("Cancel");
        JLabel bookID = new JLabel("Book ID:  ");
        JLabel bookName = new JLabel("Book Name:  ");
        JLabel bookAuthor = new JLabel("Book Author:  ");
        JLabel bookGenre = new JLabel("Book Genre:  ");
        JLabel bookQuantity = new JLabel("Book Quantity:  ");
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField quantityField = new JTextField();

        bookID.setBounds(50, 50, 150, 50);
        bookName.setBounds(50, 100, 150, 50);
        bookAuthor.setBounds(50, 150, 150, 50);
        bookGenre.setBounds(50, 200, 150, 50);
        bookQuantity.setBounds(50, 250, 150, 50);
        idField.setBounds(200, 60, 150, 30);
        nameField.setBounds(200, 110, 150, 30);
        authorField.setBounds(200, 160, 150, 30);
        genreField.setBounds(200, 210, 150, 30);
        quantityField.setBounds(200, 260, 150, 30);
        OK.setBounds(90, 320, 50, 50);
        CANCEL.setBounds(200, 320, 90, 50);

        addBookFrame.add(bookID);
        addBookFrame.add(bookName);
        addBookFrame.add(bookAuthor);
        addBookFrame.add(bookGenre);
        addBookFrame.add(bookQuantity);
        addBookFrame.add(idField);
        addBookFrame.add(nameField);
        addBookFrame.add(authorField);
        addBookFrame.add(genreField);
        addBookFrame.add(quantityField);
        addBookFrame.add(OK);
        addBookFrame.add(CANCEL);

        // Action Listener for the OK and Cancel Buttons
        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idField.getText().equals("") || nameField.getText().equals("")  || authorField.getText().equals("") 
                    || genreField.getText().equals("")  || quantityField.getText().equals("") ){
                        JOptionPane.showMessageDialog(addBookFrame, "Please enter all details!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    try {
                        int id = Math.abs(Integer.parseInt(idField.getText()));
                        int q = Integer.parseInt(quantityField.getText());

                        if(lib.compareBooks(id) == 0){
                            lib.books.add(new Book(id, nameField.getText(), authorField.getText(), genreField.getText(), q));
                            lib.saveBookData();
                            addBookFrame.dispose();
                        }
                        else
                            JOptionPane.showMessageDialog(addBookFrame, "A book with the same ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    catch(NumberFormatException n){
                        JOptionPane.showMessageDialog(addBookFrame, "Please enter integers for ID / Quantity!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        CANCEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBookFrame.dispose();
                return;
            }
        });
    }

    // Event for when the "Add User" button is pressed
    public void addUserButtonEvent(Library lib) {
        JFrame addUserFrame = new JFrame();
        addUserFrame.setSize(400, 300);
        addUserFrame.setLayout(null);
        addUserFrame.setVisible(true);

        JButton OK = new JButton("Ok");
        JButton CANCEL = new JButton("Cancel");
        JLabel userID = new JLabel("User ID:  ");
        JLabel userName = new JLabel("User Name:  ");
        JLabel userContact = new JLabel("User Contact:  ");
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField contactField = new JTextField();

        userID.setBounds(50, 30, 150, 50);
        userName.setBounds(50, 80, 150, 50);
        userContact.setBounds(50, 130, 150, 50);
        idField.setBounds(200, 40, 150, 30);
        nameField.setBounds(200, 90, 150, 30);
        contactField.setBounds(200, 140, 150, 30);
        OK.setBounds(90, 200, 50, 50);
        CANCEL.setBounds(200, 200, 90, 50);

        addUserFrame.add(userID);
        addUserFrame.add(userName);
        addUserFrame.add(userContact);
        addUserFrame.add(idField);
        addUserFrame.add(nameField);
        addUserFrame.add(contactField);
        addUserFrame.add(OK);
        addUserFrame.add(CANCEL);

        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idField.getText().equals("") || nameField.getText().equals("")  || contactField.getText().equals("") ){
                        JOptionPane.showMessageDialog(addUserFrame, "Please enter all details!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    try {
                        int id = Math.abs(Integer.parseInt(idField.getText()));

                        if(lib.compareUsers(id) == 0){
                            lib.users.add(new User(id, nameField.getText(), contactField.getText()));
                            lib.saveUserData();
                            addUserFrame.dispose();
                        }
                        else
                        JOptionPane.showMessageDialog(addUserFrame, "A user with the same ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                        
                    }
                    catch(NumberFormatException n){
                        JOptionPane.showMessageDialog(addUserFrame, "Please enter integers for ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        CANCEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserFrame.dispose();
                return;
            }
        });
    }

    // Event for when the "Show All Books" button is pressed
    public void printBooksButtonEvent(Library lib) {
        JFrame printBooksFrame = new JFrame();
        printBooksFrame.setSize(400, 300);
        printBooksFrame.setLayout(null);
        printBooksFrame.setVisible(true);

        JButton OK = new JButton("Ok");
        JButton CANCEL = new JButton("Cancel");

        String[][] booksData;
        String[] booksColumn = {"ID", "Title", "Author", "Genre", "Quantity"};

        booksData = new String[lib.books.size()][5];

        for(int i = 0; i < lib.books.size(); i++){
            booksData[i][0] = Integer.toString(lib.books.get(i).ID);
            booksData[i][1] = lib.books.get(i).title;
            booksData[i][2] = lib.books.get(i).author;
            booksData[i][3] = lib.books.get(i).genre;
            booksData[i][4] = Integer.toString(lib.books.get(i).quantity);
        }

        JTable booksTable = new JTable(booksData, booksColumn);
        JScrollPane scrollPane = new JScrollPane();

        scrollPane.setBounds(50, 50, 300, 100);
        OK.setBounds(90, 200, 50, 50);
        CANCEL.setBounds(200, 200, 90, 50);

        scrollPane.setViewportView(booksTable);
        printBooksFrame.add(OK);
        printBooksFrame.add(CANCEL);
        printBooksFrame.add(scrollPane);

        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printBooksFrame.dispose();
                return;
            }
        });

        CANCEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printBooksFrame.dispose();
                return;
            }
        });
    }

    // Event for when the "Show All Users" button is pressed
    public void printUsersButtonEvent(Library lib) {
        JFrame printUsersFrame = new JFrame();
        printUsersFrame.setSize(470, 300);
        printUsersFrame.setLayout(null);
        printUsersFrame.setVisible(true);

        JButton OK = new JButton("Ok");
        JButton CANCEL = new JButton("Cancel");

        String[][] UsersData;
        String[] UsersColumn = {"ID", "Name", "Contact", "Checked Books"};

        UsersData = new String[lib.users.size()][4];

        for(int i = 0; i < lib.users.size(); i++){
            UsersData[i][0] = Integer.toString(lib.users.get(i).ID);
            UsersData[i][1] = lib.users.get(i).name;
            UsersData[i][2] = lib.users.get(i).contact;
            UsersData[i][3] = Integer.toString(lib.users.get(i).borrowed.size());
        }

        JTable UsersTable = new JTable(UsersData, UsersColumn);
        JScrollPane scrollPane = new JScrollPane();

        scrollPane.setBounds(50, 50, 360, 100);
        OK.setBounds(130, 200, 50, 50);
        CANCEL.setBounds(240, 200, 90, 50);

        scrollPane.setViewportView(UsersTable);
        printUsersFrame.add(OK);
        printUsersFrame.add(CANCEL);
        printUsersFrame.add(scrollPane);

        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printUsersFrame.dispose();
                return;
            }
        });

        CANCEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printUsersFrame.dispose();
                return;
            }
        });
    }

    // Event for when the "Search Book" button is pressed
    public void searchBookButtonEvent(Library lib) {
        JFrame searchBookFrame = new JFrame();
        searchBookFrame.setSize(460, 300);
        searchBookFrame.setLayout(null);
        searchBookFrame.setVisible(true);

        JButton SEARCH = new JButton("Search");
        JLabel name = new JLabel("Book / Author Name:  ");
        JTextField nameField = new JTextField();
        JScrollPane scrollPane = new JScrollPane();
        String[] infoColumn = {"ID", "Title", "Author", "Genre", "Quantity"};

        scrollPane.setBounds(50, 80, 350, 100);
        name.setBounds(50, 20, 150, 50);
        nameField.setBounds(200, 30, 200, 30);
        SEARCH.setBounds(180, 200, 80, 50);
        searchBookFrame.add(name);
        searchBookFrame.add(nameField);
        searchBookFrame.add(SEARCH);
        searchBookFrame.add(scrollPane);

        SEARCH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameField.getText().equals("")){
                    JOptionPane.showMessageDialog(searchBookFrame, "Please enter all details!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    int count = 0;
                    String[][] bookInfo = new String[lib.books.size()][5];

                    for(int i = 0; i < lib.books.size(); i++)
                        if(lib.books.get(i).author.equalsIgnoreCase(nameField.getText()) || lib.books.get(i).title.equalsIgnoreCase(nameField.getText())){
                            bookInfo[count][0] = Integer.toString(lib.books.get(i).ID);
                            bookInfo[count][1] = lib.books.get(i).title;
                            bookInfo[count][2] = lib.books.get(i).author;
                            bookInfo[count][3] = lib.books.get(i).genre;
                            bookInfo[count][4] = Integer.toString(lib.books.get(i).quantity);
                            count++;
                        }

                    JTable infoTable = new JTable(bookInfo, infoColumn);
                    scrollPane.setViewportView(infoTable);
                }
            }
        });
    }

    // Event for when the "Search User" button is pressed
    public void searchUserButtonEvent(Library lib) {
        JFrame searchUserFrame = new JFrame();
        searchUserFrame.setSize(460, 300);
        searchUserFrame.setLayout(null);
        searchUserFrame.setVisible(true);

        JButton SEARCH = new JButton("Search");
        JLabel name = new JLabel("User ID:  ");
        JTextField nameField = new JTextField();
        JScrollPane scrollPane = new JScrollPane();
        String[] infoColumn = {"ID", "Title", "Author", "Genre"};

        scrollPane.setBounds(50, 80, 350, 100);
        name.setBounds(50, 20, 150, 50);
        nameField.setBounds(200, 30, 200, 30);
        SEARCH.setBounds(180, 200, 80, 50);
        searchUserFrame.add(name);
        searchUserFrame.add(nameField);
        searchUserFrame.add(SEARCH);
        searchUserFrame.add(scrollPane);

        SEARCH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameField.getText().equals(""))
                    JOptionPane.showMessageDialog(searchUserFrame, "Please enter all details!", "Error", JOptionPane.ERROR_MESSAGE);
                else
                    try {
                        int id = Integer.parseInt(nameField.getText());
                        int count = 0;
                        String[][] userInfo = new String[1][4];

                        for(int i = 0; i < lib.users.size(); i++)
                            if(lib.users.get(i).ID == id){
                                userInfo = new String[lib.users.get(i).borrowed.size()][4];
                                for(int j = 0; j < lib.users.get(i).borrowed.size(); j++){
                                userInfo[count][0] = Integer.toString(lib.users.get(i).borrowed.get(j).ID);
                                userInfo[count][1] = lib.users.get(i).borrowed.get(j).title;
                                userInfo[count][2] = lib.users.get(i).borrowed.get(j).author;
                                userInfo[count][3] = lib.users.get(i).borrowed.get(j).genre;
                                count++;
                                }
                            }

                        JTable infoTable = new JTable(userInfo, infoColumn);
                        scrollPane.setViewportView(infoTable);
                    } 
                    catch (NumberFormatException n) {
                        JOptionPane.showMessageDialog(searchUserFrame, "Please enter integers for ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
            }
        });
    }

    // Event for when the "Check In" button is pressed
    public void checkInButtonEvent(Library lib) {
        JFrame checkInFrame = new JFrame();
        checkInFrame.setSize(470, 250);
        checkInFrame.setLayout(null);
        checkInFrame.setVisible(true);

        JButton OK = new JButton("Ok");
        JButton CANCEL = new JButton("Cancel");
        JLabel name = new JLabel("User ID:  ");
        JLabel title = new JLabel("Book Name:  ");
        JTextField nameField = new JTextField();
        JTextField bookField = new JTextField();

        OK.setBounds(130, 140, 50, 50);
        CANCEL.setBounds(240, 140, 90, 50);
        name.setBounds(50, 80, 150, 50);
        nameField.setBounds(200, 90, 200, 30);
        title.setBounds(50, 40, 150, 50);
        bookField.setBounds(200, 50, 200, 30);

        checkInFrame.add(OK);
        checkInFrame.add(CANCEL);
        checkInFrame.add(name);
        checkInFrame.add(nameField);
        checkInFrame.add(bookField);
        checkInFrame.add(title);

        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameField.getText().equals("") || bookField.getText().equals(""))
                    JOptionPane.showMessageDialog(checkInFrame, "Please enter all details!", "Error", JOptionPane.ERROR_MESSAGE);
                else{
                    try {
                        int id = lib.userExists(Integer.parseInt(nameField.getText()));
                        String title = bookField.getText();

                        if(lib.checkIn(id, title) <= 0)
                            JOptionPane.showMessageDialog(checkInFrame, "Check User ID or Book Name and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        else{
                            lib.saveBookData();
                            lib.saveUserData();
                            checkInFrame.dispose();
                        }
                    }
                    catch(NumberFormatException n){
                        JOptionPane.showMessageDialog(checkInFrame, "Please enter integers for ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                return;
            }
        });

        CANCEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkInFrame.dispose();
                return;
            }
        });
    }

    // Event for when the "Check Out" button is pressed
    public void checkOutButtonEvent(Library lib) {
        JFrame checkInFrame = new JFrame();
        checkInFrame.setSize(470, 250);
        checkInFrame.setLayout(null);
        checkInFrame.setVisible(true);

        JButton OK = new JButton("Ok");
        JButton CANCEL = new JButton("Cancel");
        JLabel name = new JLabel("User ID:  ");
        JLabel title = new JLabel("Book Name:  ");
        JTextField nameField = new JTextField();
        JTextField bookField = new JTextField();

        OK.setBounds(130, 140, 50, 50);
        CANCEL.setBounds(240, 140, 90, 50);
        name.setBounds(50, 80, 150, 50);
        nameField.setBounds(200, 90, 200, 30);
        title.setBounds(50, 40, 150, 50);
        bookField.setBounds(200, 50, 200, 30);

        checkInFrame.add(OK);
        checkInFrame.add(CANCEL);
        checkInFrame.add(name);
        checkInFrame.add(nameField);
        checkInFrame.add(bookField);
        checkInFrame.add(title);

        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameField.getText().equals("") || bookField.getText().equals(""))
                    JOptionPane.showMessageDialog(checkInFrame, "Please enter all details!", "Error", JOptionPane.ERROR_MESSAGE);
                else{
                    try {
                        int id = lib.userExists(Integer.parseInt(nameField.getText()));
                        String title = bookField.getText();

                        if(lib.checkOut(id, title) <= 0)
                            JOptionPane.showMessageDialog(checkInFrame, "Check User ID or Book Name and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        else{
                            lib.saveBookData();
                            lib.saveUserData();
                            checkInFrame.dispose();
                        }
                    }
                    catch(NumberFormatException n){
                        JOptionPane.showMessageDialog(checkInFrame, "Please enter integers for ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                return;
            }
        });

        CANCEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkInFrame.dispose();
                return;
            }
        });
    }
}
