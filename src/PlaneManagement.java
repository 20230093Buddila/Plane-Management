import java.util.InputMismatchException;
import java.util.Scanner;
public class PlaneManagement {
    //public static Scanner input = new Scanner(System.in);
    //Initiating the object array to store Ticket information
        public static Ticket[][] ticketsSold = new Ticket[4][];
        static {
            ticketsSold[0] = new Ticket[14];
            ticketsSold[1] = new Ticket[12];
            ticketsSold[2] = new Ticket[12];
            ticketsSold[3] = new Ticket[14];
        }
//Start of main method
    public static void main(String[] args) {

        System.out.println("Welcome to Plane Management application");
        //Initiating the integer array
        int[][] jaggedArray = new int[4][];
        jaggedArray[0] = new int[14];
        jaggedArray[1] = new int[12];
        jaggedArray[2] = new int[12];
        jaggedArray[3] = new int[14];

        //Start of the menu page
        do {
            System.out.println("***************************************************");
            System.out.println("*                      MENU                       *");
            System.out.println("***************************************************");
            System.out.println("     1) Buy a seat");
            System.out.println("     2) Cancel a seat");
            System.out.println("     3) Find first available seat");
            System.out.println("     4) Show seating plan");
            System.out.println("     5) Print ticket information and total sales");
            System.out.println("     6) Search ticket");
            System.out.println("     0) Quit");
            System.out.println("***************************************************");

            int choice;
            //Entering validity method to check for the correct type of validation
            choice = validity();
            switch (choice) {
                case 1:
                    //Entering to check for proper row and column
                    int row_num = rowcheck();
                    int column = coloumncheck();
                    buy_seat(jaggedArray, row_num, column);
                    break;
                case 2:
                    row_num = rowcheck();
                    column = coloumncheck();
                    cancel_seat(jaggedArray, row_num, column);
                    break;
                case 3:
                    find_first_available(jaggedArray);
                    break;
                case 4:
                    show_seating_plan(jaggedArray);
                    break;
                case 5:
                    print_tickets_info();
                    break;
                case 6:
                    row_num = rowcheck();
                    column = coloumncheck();
                    search_ticket(jaggedArray, row_num, column);
                    break;
                case 0:
                    System.exit(0);
            }
        } while (true);

    }

    //Start of buy seat method option 1 on the menu
    public static void buy_seat(int[][] jaggedArray, int row_num, int column) {
        if ((row_num >= 0 && row_num < jaggedArray.length) && (column >= 0 && column < jaggedArray[row_num].length)) {
            if (jaggedArray[row_num][column] == 0) {
                jaggedArray[row_num][column] = 1;
                System.out.println("Seat booked successfully");
                String name = validatationPersoninfo("Name");
                String surname = validatationPersoninfo("Surname");
                String email = validatationPersoninfo("Email");

                int price = price1(column);

                //crating a new object in person class
                Person person = new Person(name, surname, email);
                System.out.println("\n");

                //creating a new object in ticket class
                Ticket ticket = new Ticket(row_num, column,price,person);
                ticketsSold[row_num][column] = ticket;
                ticket.save();

            } else {
                System.out.println("Seat already booked");
            }
        } else {
            System.out.println("Invalid row or column");
        }
    }
    //Start of cancel method option 2 in the menu
    public static void cancel_seat(int[][] jaggedArray,int row_num,int column) {
        if ((row_num >= 0 && row_num < jaggedArray.length) && (column >= 0 && column < jaggedArray[row_num].length)) {
            if (jaggedArray[row_num][column] == 1) {
                jaggedArray[row_num][column] = 0;
                System.out.println("Seat cancelled successfully");
                String letter = null;
                if (row_num == 0) {
                    letter = ("A");
                } else if (row_num == 1) {
                    letter = ("B");
                } else if (row_num == 2) {
                    letter = ("C");
                } else if (row_num == 3) {
                    letter = ("D");
                }
                //Accessing into Ticket class
                for (Ticket[] ticket:ticketsSold) {
                    for (Ticket element: ticket) {
                        ticketsSold[row_num][column] = null;
                    }
                }
            } else {
                System.out.println("Seat is not booked");
            }
        } else {
            System.out.println("Invalid row or column");
        }
    }

    //Finding the first available seat option 3 on the menu
    public static void find_first_available(int[][] jaggedArray) {
        for (int i = 0; i < jaggedArray.length; i++) {
            for (int j = 0; j < jaggedArray[i].length; j++) {
                if (jaggedArray[i][j] == 0) {
                    String letter = null;
                    if (i == 0){
                        letter = ("A");
                    } else if (i == 1) {
                        letter = ("B");
                    } else if (i == 2) {
                        letter = ("C");
                    } else if (i == 3) {
                        letter = ("D");
                    }
                    System.out.println("Seat at "+ letter+j+1+" is available");
                    return;
                }
            }
        }
        System.out.println("No available seats");
    }

    //Displaying the entire seating plan option 4 in the menu
    public static void show_seating_plan(int[][] jaggedArray){
        for (int[] row : jaggedArray) {
            for (int col : row) {
                if (col == 1) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println();
            System.out.println();
        }
    }

    //printing ticket information option 5 on the menu
    public static void print_tickets_info(){
        int totalSales = 0;
        for(Ticket[] ticket: ticketsSold) {
            for (Ticket element : ticket) {
                if (element != null) {
                    element.ticketInfo();
                    System.out.println();
                    totalSales += element.getPrice();
                }
            }
        }
        System.out.println("Total Sales is "+"£"+totalSales);
    }

    //Searching for ticeket option 6 on the menu
    public static void search_ticket(int[][]jaggedArray, int row_num,int column){
        if (jaggedArray[row_num][column] == 0){
            System.out.println("Seat is available");
        } else if (jaggedArray[row_num][column] == 1){
            for(Ticket[] ticket: ticketsSold) {
                for (Ticket element : ticket) {
                    if (element != null&& ticket != null && element.getSeat()-1==column && element.getRow()==row_num) {
                        System.out.println();
                        element.ticketInfo();
                        System.out.println();
                    }
                }
            }
        }
    }

    //Used for validating the option menu number
    public static int validity(){
        Scanner input = new Scanner(System.in);
        int choice;
        while (true) {
            try {
                System.out.print("Please select an option: ");
                choice = input.nextInt();
                if((choice>=0)&&(choice<=6)){
                    break;
                }else{
                    System.out.println("Invalid input, please select from the available options");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter proper data type");
                input.nextLine();
            }
        }
        return choice;
    }

    //Used to validate the row letter and assigning a number to it
    public static int rowcheck() {
        Scanner input = new Scanner(System.in);
        String row;
        while (true) {
            try {
                System.out.print("Enter seat row (A/B/C/D): ");
                row = input.nextLine();
                if (!row.isEmpty()&&row.length()==1){
                    int row_num = -1;
                    if (row.equalsIgnoreCase("A")) {
                        row_num = 0;
                        return row_num;
                    } else if (row.equalsIgnoreCase("B")) {
                        row_num = 1;
                        return row_num;
                    } else if (row.equalsIgnoreCase("C")) {
                        row_num = 2;
                        return row_num;
                    } else if (row.equalsIgnoreCase("D")) {
                        row_num = 3;
                        return row_num;
                    } else {
                        System.out.println("Invalid Input");
                    }

                }else {
                    System.out.println("Invalid Input");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please Enter a letter");
                input.next();
            }
        }
    }

    //Used to validate the column
    public static int coloumncheck(){
        Scanner input = new Scanner(System.in);
        int column;
        while (true){
            try {
                System.out.print("Enter seat coloumn(1-14) ");
                column = input.nextInt() - 1;
                if (column >= 0 && column < 14){
                    break;
                } else {
                    System.out.println("Seat column is out of range");
                }

            }catch (InputMismatchException e){
                System.out.println("Please Enter a number");
                input.nextLine();
            }
        }
        return column;
    }

    //Assigning a price based on the seat
    public static int price1(int column) {
        int price = 0;
        if (column < 5) {
            price = 200;
        } else if (column < 9) {
            price = 150;
        } else if (column < 14) {
            price = 180;
        }
        return price;
    }

    //Checking for proper name, surname and email this is used in the buy method
    public static String validatationPersoninfo(String fieldName) {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print(fieldName + ": ");
                String value = input.nextLine();
                if (!value.isEmpty()) {
                    return value;
                }
                System.out.println(fieldName + " cannot be left empty");
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
    }
}


