import java.util.*;
import java.io.IOException;
public class GameSpace {
    public static void main(String[] args) {
        Scanner inLine = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        Scanner pause = new Scanner(System.in);
        ArrayList<String> menu = new ArrayList<String>();
        ArrayList<Game> games = new ArrayList<Game>();
        ArrayList<Player> players = new ArrayList<Player>();

        int durations[] = {30, 1, 2, 5, 8};
        int prices[] = {5, 10, 18, 40, 65};
        menu.add("1: Add new player;");
        menu.add("2: Display the revenu;");

        System.out.println("\t\t\t****** WELCOME: GameSpace app management ******");
        for (String option: menu){
            System.out.println(option);
        }

        System.out.print("Choose the operation that you want >> ");
        int num = in.nextInt();

        switch (num) {
            case(1):
                System.out.println(">> Add new player: ");
                players.add(addPlayer(inLine));
                System.out.println(players.get(0).getCodePlayer());
                pause.nextLine();
                break;
        }
    }

    public static Player addPlayer(Scanner inLine){
        System.out.print("\t\t First name: ");
        String firstName = inLine.nextLine();
        System.out.print("\t\t Last name: ");
        String lastName = inLine.nextLine();
        return new Player(firstName, lastName);
    }

    public static void addBooking(Scanner in){
        System.out.println("hey");
        //return new Booking();
    }

}
