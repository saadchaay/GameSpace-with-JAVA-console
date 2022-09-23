import java.time.LocalTime;
import java.util.*;
import java.io.IOException;
public class GameSpace {
    public static void main(String[] args) {
        Scanner inLine = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        Scanner pause = new Scanner(System.in);
        String[] menu = {"1: Add new Session for a player;", "2: Posts configure;", "3: Dashboard;"};
        ArrayList<Game> games = new ArrayList<Game>();
        ArrayList<Player> players = new ArrayList<Player>();
        ArrayList<Post> posts = postManagement();

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
                /*System.out.println("\t>> Add new Session: ");
                Player ply = addPlayer(players);
                players.add(ply);
                System.out.println(players.get(0).getCodePlayer());
                System.out.println();
                pause.nextLine();*/
                posts = postAvailable(posts);
                break;
            case(2):
                int i=0;
                for (Post post: posts){
                    System.out.println("Post n "+ (i+1) +":");
                    System.out.print("\tGame Console: "+ post.getDevice()[0].getName());
                    System.out.print(" | Display: "+ post.getDevice()[1].getName());
                    System.out.print("\n\tGames available: ");
                    for (Game game: post.getGames()){
                        System.out.print(game.getName()+", ");
                    }
                    System.out.println();
                    i++;
                }
                break;

        }
    }

    // This function allows to add new player with generate a random unique ID for each Player
    public static Player addPlayer(ArrayList<Player> players){
        Scanner in = new Scanner(System.in);
        int i=0, codePlayer;
        System.out.print("\t\t First name: ");
        String firstName = in.nextLine();
        System.out.print("\t\t Last name: ");
        String lastName = in.nextLine();
        do {
            codePlayer = (int)Math.floor(Math.random() * 99999);
            if(i>0){
                if(players.get(i).getCodePlayer() == codePlayer){
                    i++;
                }else break;
            }
        }while (i < players.size());
        return new Player(codePlayer, firstName, lastName);
    }

    public static void addSession(ArrayList<Session> sessions, ArrayList<Player> players, ArrayList<Post> posts){
        Scanner in = new Scanner(System.in);
        System.out.println("\t ADD NEW SESSION:");
        System.out.println("\t\t Choose the post: ");
        int numPost = in.nextInt();
        System.out.println("\t\t Choose the game: ");
        
        System.out.println("\t\t Choose the post: ");

        sessions.add(new Session(addPlayer(players), postAvailable(posts, sessions).get(numPost), game, ))
        return sessions;
    }

    public static ArrayList<Post> postAvailable(ArrayList<Post> posts, ArrayList<Session> sessions){
        int i=0;
        ArrayList<Post> postsAvailable ;
        LocalTime time = LocalTime.now();
        System.out.println(time);
        /*for (Post post: posts){
            if(sessions.get(i).getStartTime())
        }*/
        return posts;
    }

    public static ArrayList<Post> postManagement(){
        ArrayList<Post> posts = new ArrayList<>();
        ArrayList<Game> gamesObj = new ArrayList<>();
        String games[] = {"FIFA", "PES", "GTA5"};
        String devs[][] =  {
                {"Xbox", "Xbox", "Xbox", "Xbox", "Playstation5", "Playstation5", "Playstation5", "Nintendo Switch", "Nintendo Switch"},
                {"Dell", "Dell", "Dell", "Asus", "Asus", "Asus", "Samsung", "Samsung", "HP"}
            };
        for (int k=0; k< games.length; k++) {
            gamesObj.add(new Game(games[k]));
        }
        for (int i=0; i<9; i++) {
            Device devices[] = {new Device(devs[0][i]), new Device(devs[1][i])};
            posts.add(new Post(devices,gamesObj));
        }
        return posts;
    }

}
