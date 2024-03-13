import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Library {
    public ArrayList<Book> books;
    public ArrayList<User> users;

    // Constructor for the Library Class
    public Library(){
        books = new ArrayList<Book>();
        users = new ArrayList<User>();
    }


    // Compare Book IDs and return "1" if the book already exists
    public int compareBooks(int id){
        for(int i = 0; i < this.books.size(); i++)
            if(this.books.get(i).ID == id)
                return 1;
        return 0;
    }


    // Check availability and return -1 if the book is not available
    public int isAvailable(String title)
    {
        for (int i = 0; i < books.size(); i++)
            if (title.equalsIgnoreCase(books.get(i).title))
                return i;
        return -1;
    }

    // Check if the user has a book or not. Returns -1 if they do not.
    public int userHasBook(int id, String title){
        if(id >= 0){
            for(int i = 0; i < this.users.get(id).borrowed.size(); i++)
                if(this.users.get(id).borrowed.get(i).title.equalsIgnoreCase(title))
                    return i;
            return -1;
        }
        else
            return -1;
    }


    // Checkout Function to check a book out to a user if the book is available
    public int checkOut(int id, String title)
    {
        if(this.isAvailable(title) >= 0 && this.books.get(this.isAvailable(title)).quantity > 0 && id >= 0){
            this.books.get(this.isAvailable(title)).quantity--;
            this.users.get(id).borrowed.add(this.books.get(this.isAvailable(title)));
            return 1;
        }
        else
            return -1;
    }


    // Checkin Function to check a book in from a user if the user has said book in a 'borrowed' state
    public int checkIn(int id, String title)
    {
        if(this.userHasBook(id, title) >= 0){
            this.books.get(this.isAvailable(title)).quantity++;
            this.users.get(id).borrowed.remove(this.userHasBook(id, title));
            return 1;
        }
        else
            return -1;
    }


    // Compare two user id's and return 1 if said ID is already used
    public int compareUsers(int id) {
        for (int i = 0; i < this.users.size(); i++) 
            if (id == this.users.get(i).ID) 
                return 1;
        return 0;
    }


    // Check for the existance of a user, returns -1 if they do not exist
    public int userExists(int id)
    {
        for (int i = 0; i < users.size(); i++)
            if (users.get(i).ID == id)
                return i;
        return -1;
    }


    
    // Save Functions to save the users and books data to a "Users.txt" & "Books.txt" file sequentially (Separating each entry with a series of ----)
    public void saveUserData(){
        try {
            File savedUsers = new File("Users.txt");
            savedUsers.createNewFile();

            FileWriter writer = new FileWriter("Users.txt");

            for (int i = 0; i < this.users.size(); i++){
                writer.write(Integer.toString(this.users.get(i).ID) + "\n");
                writer.write(this.users.get(i).name + "\n");
                writer.write(this.users.get(i).contact + "\n");

                for (int j = 0; j < this.users.get(i).borrowed.size(); j++){
                    writer.write(Integer.toString(this.users.get(i).borrowed.get(j).ID) + "\n");
                    writer.write(this.users.get(i).borrowed.get(j).title + "\n");
                    writer.write(this.users.get(i).borrowed.get(j).author + "\n");
                    writer.write(this.users.get(i).borrowed.get(j).genre + "\n");
                    writer.write(Integer.toString(this.users.get(i).borrowed.get(j).quantity) + "\n");
                }
                writer.write("----\n");
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("Error when Creating / Loading file.");
            return;
        }
    }

    public void saveBookData() {
        try {
            File savedBooks = new File("Books.txt");
            savedBooks.createNewFile();

            FileWriter writer = new FileWriter("Books.txt");

            for (int i = 0; i < this.books.size(); i++){
                writer.write(Integer.toString(this.books.get(i).ID) + "\n");
                writer.write(this.books.get(i).title + "\n");
                writer.write(this.books.get(i).author + "\n");
                writer.write(this.books.get(i).genre + "\n");
                writer.write(Integer.toString(this.books.get(i).quantity) + "\n");
                writer.write("----\n");
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("Error when Creating / Loading file.");
            return;
        }
    }

    // Load Functions to load the users and books data from the "Users.txt" & "Books.txt" files sequentially
    public void loadUserData(){
        int id, bookID, quantity;
        String name, contact, title, author, genre;

        try{
            File savedUsers = new File("Users.txt");
            Scanner reader = new Scanner(savedUsers);

            while (reader.hasNextLine()) {

                id = Integer.valueOf(reader.nextLine());
                name = reader.nextLine();
                contact = reader.nextLine();
                User temp = new User(id, name, contact);

                while(reader.hasNext("----") == false){
                    bookID = Integer.valueOf(reader.nextLine());
                    title = reader.nextLine();
                    author = reader.nextLine();
                    genre = reader.nextLine();
                    quantity = Integer.valueOf(reader.nextLine());
                    temp.borrowed.add(new Book(bookID, title, author, genre, quantity));
                }
                reader.nextLine();
                this.users.add(temp);
            }

            reader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Error when Creating / Loading file.");
            return;
        }
    }

    public void loadBookData(){
        int bookID, quantity;
        String title, author, genre;

        try{
            File savedBooks = new File("Books.txt");
            Scanner reader = new Scanner(savedBooks);

            while (reader.hasNextLine()) {
                bookID = Integer.valueOf(reader.nextLine()); 
                title = reader.nextLine();
                author = reader.nextLine();
                genre = reader.nextLine();
                quantity = Integer.valueOf(reader.nextLine());
                reader.nextLine();
                this.books.add(new Book(bookID, title, author, genre, quantity));
            }

            reader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Error when Creating / Loading file.");
            return;
        }
    }
}
