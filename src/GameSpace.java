import java.util.*;
import java.io.IOException;
public class GameSpace {
    public static void main(String[] args) {
        Scanner inLine = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        Scanner pause = new Scanner(System.in);
        String[] menu = {"1: Add new player;", "2: Display the revenus;"};
        ArrayList<Game> games = new ArrayList<Game>();
        ArrayList<Player> players = new ArrayList<Player>();

        int durations[] = {30, 1, 2, 5, 8};
        int prices[] = {5, 10, 18, 40, 65};

        System.out.println("\t\t\t****** WELCOME: GameSpace app management ******");
        for (String option: menu){
            System.out.println(option);
        }

        System.out.print("Choose the operation that you want >> ");
        int num = in.nextInt();

        switch (num) {
            case(1):
                int i=0;
                System.out.println(">> Add new player: ");
                Player ply = addPlayer(inLine);
                for (int j=0; j<players.size(); j++){
                    if(ply.getCodePlayer() != players.get(j).getCodePlayer()){
                        players.add(ply);
                        break;
                    }else{
                        players.add(addPlayer(inLine));
                    }
                }
                /*do {
                    players.add(ply);
                    i++;
                }while(i<players.size() && ply.getCodePlayer() == players.get(i).getCodePlayer());*/

                players.add(ply);
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
        int codePlayer = (int)Math.floor(Math.random() * 99999);
        return new Player(codePlayer, firstName, lastName);
    }

    public static void addBooking(Scanner in){
        System.out.println("hey");
        //return new Booking();
    }

    public static ArrayList<Post> postManagement(){
        System.out.println("****** POST MANAGEMENT ******");
        ArrayList<Post> posts = new ArrayList<>();
        return posts;
    }

}
