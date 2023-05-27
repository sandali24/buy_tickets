import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Theatre {
//Add 3 arrays (row1,row2,row3)
    static int[] row1 = new int[12];
    static int[] row2 = new int[16];
    static int[] row3 = new int[20];
    static Scanner input = new Scanner(System.in);
    static ArrayList <Ticket> tickets = new ArrayList<>();
    static int rowNo;
    static int seat;
    static double price;
    static double totPrice;
    static Ticket ticket;

    public static void main(String[] args) {
        //set all elements of an array to zero
        for (int i = 0; i < row1.length; i++) {
            row1[i] = 0;
        }
        for (int i = 0; i < row2.length; i++) {
            row2[i] = 0;
        }
        for (int i = 0; i < row3.length; i++) {
            row3[i] = 0;
        }menu();
    }

    public static void menu() {
        System.out.println("Welcome to the New Theatre!");
        System.out.println("-----------------------------------------------");
        System.out.println("     ----- Please select an option -----       ");
        System.out.println("1 Buy a ticket                                 ");
        System.out.println("2 Print seating area                           ");
        System.out.println("3 Cancel ticket                                ");
        System.out.println("4 List available seats                         ");
        System.out.println("5 Save to file                                 ");
        System.out.println("6 Load from file                               ");
        System.out.println("7 Print ticket information and total price     ");
        System.out.println("8 Sort tickets by price                        ");
        System.out.println("0 Quit                                         ");
        System.out.println("-----------------------------------------------");
        enter_menu();
    }
    public static void enter_menu() {
        try {
            //validations for (Enter option)
            System.out.print("Enter option:");
            int option = input.nextInt();
            //ask input from user
            switch (option) {
                case 1:
                    buy_ticket();
                    break;
                case 2:
                    print_seating_area();
                    break;
                case 3:
                    cancel_ticket();
                    break;
                case 4:
                    show_available();
                    break;
                case 5:
                    save();
                    break;
                case 6:
                    load();
                    break;
                case 7:
                    show_tickets_info();
                    break;
                case 8:
                    sort_tickets();
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid input type!\n");
            input.next();
            menu();
        }
    }
    public static void ask_info(){
        //Ask information from user
        Scanner info = new Scanner(System.in);
        System.out.print("Enter your name:");
        String name = info.nextLine();
        System.out.print("Enter your surname:");
        String surname = info.nextLine();
        System.out.print("Enter you email:");
        String email = info.nextLine();
        while (true){
            //validate email
            if (email.contains("@gmail.com") || email.contains("@yahoo.com") || email.contains("@hotmail.com")){
                break;
            } else {
                System.out.println("Invalid email,Try again!");
                System.out.print("Enter your email:");
                email = info.nextLine();
            }
        }
        //create an object call person
        Person person = new Person(name,  surname, email);
        Scanner getTicketType = new Scanner(System.in);
        System.out.println("Ticket Prices:\n  Full ticket = $100 \n  Half ticket = $50");
        System.out.print("Enter 'F' to buy Full ticket or 'H' to buy Half ticket: ");
        char ticketType = getTicketType.next().toUpperCase().charAt(0);
        if (ticketType == 'F') {
            price = 100.00;
        } else if (ticketType == 'H') {
            price = 50.00;
        }
        while (true){
            //validate ticketType
            if (ticketType == 'F' || ticketType == 'H'){
                break;
            } else {
                System.out.println("Invalid input,Try again!");
                System.out.print("Enter 'F' to buy Full ticket or 'H' to buy Half ticket: ");
                ticketType = getTicketType.next().charAt(0);
            }
        }
        totPrice += price;
        ticket = new Ticket(rowNo, seat, price, person);
        tickets.add(ticket);
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println(tickets.get(i));
        }
    }
    public static void buy_ticket() {
        ask_info();
        //asks the user to input a row number and a seat number
        System.out.print("Input a row number:");
         int rowN = input.nextInt();
        if (rowN <= 0 || rowN > 3) {
            System.out.println("Invalid row number,Try again!");
            buy_ticket();
        } else {
            System.out.print("Input a seat number:");
           int seatN = input.nextInt();

            switch (rowN) {
                case 1:
                    if (seatN > 0 && seatN < 13) {
                        //Check that the row and seat are correct and that the seat is available.
                        if (row1[seatN - 1] == 0) {
                            System.out.println("Seat number " + seatN + " is available.");
                            row1[seatN - 1] = 1;
                        }
                        enter_menu();
                    } else {
                        System.out.println("Invalid seat number,Try again!");
                        buy_ticket();
                    }
                    break;

                    case 2:
                    if (seatN > 0 && seatN < 17) {
                        if (row2[seatN - 1] == 0) {
                            System.out.println("Seat number " + seatN + " is available.");
                            row2[seatN - 1] = 1;
                        }
                        enter_menu();
                    } else {
                        System.out.println("Invalid seat number,Try again!");
                        buy_ticket();
                    }
                    break;

                case 3:
                    if (seatN > 0 && seatN < 21) {
                        if (row3[seatN - 1] == 0) {
                            System.out.println("Seat number " + seatN + " is available");
                            row3[seatN - 1] = 1;
                        }
                        enter_menu();
                    } else {
                        System.out.println("Invalid seat number,Try again!");
                        buy_ticket();
                    }
                    break;
            }
        }
        enter_menu();
    }

    public static void print_seating_area() {
        //this method is for shows the seats that have been sold, and the seats that are still available
        System.out.println("    ***********");
        System.out.println("    *  STAGE  * ");
        System.out.println("    ***********");
        System.out.print("    ");
        for (int i = 0; i < row1.length; i++) {
            //Display available seats with the character ‘O’ and the sold seats with ‘X’.
            if (row1[i]==0) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < row2.length; i++) {
            if (row2[i]==0) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
        }
        System.out.println();
        for (int i = 0; i < row3.length; i++) {
            if (row3[i]==0) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
        }
        System.out.println();
        enter_menu();
    }

    public static void cancel_ticket() {
        //this method is for makes a seat available again
        System.out.print("Input a row number:");
        int rowN = input.nextInt();
        if (rowN <= 0 || rowN > 3) {
            System.out.print("Invalid row number,Try again!");
            buy_ticket();
        } else {
            System.out.print("Input a seat number:");
            int seatN = input.nextInt();

            switch (rowN) {
                case 1:
                    if (seatN > 0 && seatN < 13) {
                        if (row1[seatN - 1] == 1) {
                            System.out.println("Seat number"+seatN + " is successfully cancelled.");
                            row1[seatN - 1] = 0;
                        }
                        enter_menu();
                    } else {
                        System.out.println("Invalid seat number");
                        buy_ticket();
                    }
                    break;
                case 2:
                    if (seatN > 0 && seatN < 17) {
                        if (row2[seatN - 1] == 1) {
                            System.out.println("Seat number"+seatN + " is successfully cancelled.");
                            row2[seatN - 1] = 0;
                        }
                        enter_menu();
                    } else {
                        System.out.println("Invalid seat number");
                        buy_ticket();
                    }
                    break;
                case 3:
                    if (seatN > 0 && seatN < 21) {
                        if (row3[seatN - 1] == 1) {
                            System.out.println("Seat number"+seatN + " is successfully cancelled.");
                            row3[seatN - 1] = 0;
                        }
                        enter_menu();
                    } else {
                        System.out.println("Invalid seat number");
                        buy_ticket();
                    }
                    break;
            }
        }
        tickets.remove(ticket);
        System.out.println(tickets);
        enter_menu();
    }

    public static void show_available(){
        //this method is for displays the seats that are still available.
        System.out.print("Seats available in row 1:");
        for(int i=0; i<row1.length; i++){
            if(row1[i]==0){
                System.out.print(i+1+",");
            }
        }
        System.out.println();
        System.out.print("Seats available in row 2:");
        for (int i=0; i<row2.length; i++) {
            if(row2[i]==0){
                System.out.print(i+1+",");
            }
        }
        System.out.println();
        System.out.print("Seats available in row 3:");
        for (int i=0; i<row3.length; i++) {
            if(row3[i]==0){
                System.out.print(i+1+",");
            }
        }
        System.out.println();
        enter_menu();
    }

    public static void save() {
        //this method is for saves the 3 arrays with the row’s information in a file
        try {
            FileWriter myWriter = new FileWriter("saveInfo.txt");
            for (int i = 0; i < row1.length; i++) {
                if (row1[i] == 0) {
                    myWriter.write("O ");
                } else if (row1[i] == 1) {
                    myWriter.write("X ");
                }
            }
            myWriter.write("\n");

            for (int i = 0; i < row2.length; i++) {
                if (row2[i] == 0) {
                    myWriter.write("O ");
                } else if (row2[i] == 1) {
                    myWriter.write("X ");
                }
            }
            myWriter.write("\n");

            for (int i = 0; i < row3.length; i++) {
                if (row3[i] == 0) {
                    myWriter.write("O ");
                } else if (row3[i] == 1) {
                    myWriter.write("X ");
                }
            }
            myWriter.close();
        }
        catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println();
        enter_menu();
    }
    public static void load(){
        try {
            File file = new File("saveInfo.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                System.out.println(myReader.nextLine());
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        enter_menu();
    }
    private static void show_tickets_info() {
        for (Ticket ticket : tickets) {
            ticket.print();
        }
        enter_menu();
    }
    private static void sort_tickets(){
        for (int i = 0; i< tickets.size(); i++){
            if (ticket.getPrice() == 20){
                Ticket t  = tickets.get(i);
                t.print();
            }
        }
        for (int i =0; i< tickets.size(); i++){
            if (ticket.getPrice() == 10){
                Ticket t = tickets.get(i);
                t.print();
            }
        }
        enter_menu();
    }
}










