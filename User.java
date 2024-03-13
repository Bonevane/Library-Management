import java.util.ArrayList;

public class User {
    public int ID;
    public String name;
    public String contact;
    public ArrayList<Book> borrowed = new ArrayList<Book>();

    // Constructor for the User Class
    public User(int id, String n, String c){
        this.ID = id;
        this.name = n;
        this.contact = c;
    }
}
