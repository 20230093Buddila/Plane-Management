import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class  Ticket {
    private int row;
    private int seat;
    private int price;
    private Person person;

    //constructors
    public Ticket(int row, int seat,int price, Person person) {
        this.row = row;
        this.seat = seat + 1;
        this.price = price;
        this.person = person;
        ticketInfo();
    }

    //setters
    public void setRow(int row) {
        this.row = row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    //getters
    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public int getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }

    //method for printing ticket info
    public void ticketInfo() {
        System.out.println("Ticket Information:");
        String letter = rowletter();
        System.out.println("Row: " + letter);
        System.out.println("Seat: " + seat);
        System.out.println("Price: £" + price);
        System.out.println();
        System.out.println("Person Information:");
        person.personInfo();
    }

    //Method to obtain a number for the row letter
    public String rowletter(){
        String letter = null;
        if (row == 0) {
            letter = ("A");
        } else if (row == 1) {
            letter = ("B");
        } else if (row == 2) {
            letter = ("C");
        } else if (row == 3) {
            letter = ("D");
        }
        return letter;
    }

    //Method to save the ticket details and person details to text file
    public void save(){
        String letter = rowletter();
        String place = (letter+seat+".txt");
        File file = new File(place);
        boolean file_created = false;
        try {
            file_created = file.createNewFile();
            if (file_created) {
                FileWriter writer = new FileWriter(place);
                writer.write("-------Ticket Details-------\n");
                writer.write("Row: "+letter+"\n");
                writer.write("Seat: "+seat+"\n");
                writer.write("Price: "+"€"+price+"\n");
                writer.write("");
                writer.write("-----Person details-----\n");
                writer.write("Name: " + getPerson().getName()+"\n");
                writer.write("Surname: "+ getPerson().getSurname()+"\n");
                writer.write("Email: "+getPerson().getEmail()+"\n");
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("ERROR: File could not be created.");
        }

    }
}