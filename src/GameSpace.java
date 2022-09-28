import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.*;


public class GameSpace {
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String RESET = "\033[0m";  // Text Reset
//    public static int total
    static ArrayList<Player> players = new ArrayList<>();
    static ArrayList<Session> sessions = new ArrayList<>();
    static ArrayList<Post> posts = postManagement();
    public static void main(String[] args) {
        int durations[] = {30, 1, 2, 5, 8};
        int prices[] = {5, 10, 18, 40, 65};
        System.out.println("\n\t\t\t****** WELCOME: GameSpace app management ******");
        appManage(sessions);
    }

    // This function allows to add new player with generate a random unique ID for each Player
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
            posts.add(new Post(devices, gamesObj));
        }
        return posts;
    }

    public static void appManage(ArrayList<Session> sessions) {
        Scanner in = new Scanner(System.in);
        ArrayList<Session> tmpSession = new ArrayList<>();
        String[] menu = {"1: Add new Session for a player;", "2: Posts configure;", "3: checkTime;", "4: Sessions;"};
        System.out.println("\nAPPLICATION MENU: \n");
        for (String option: menu){
            System.out.println("\t"+ option);
        }

        System.out.print("\nChoose the operation that you want >> ");
        int num = in.nextInt();

        switch (num) {
            case(1):
                addSession();
                appManage(sessions);
                break;
            case(2):
                if(changeStatus(posts.get(1))){
                    System.out.println("********* FIN *********");
                    appManage(sessions);
                }
                 else
                    appManage(sessions);
                break;
            case(3):
                appManage(sessions);
                break;
            case(4):
                for(Session session: sessions){
                    System.out.print("\n" + session.toString());
                }
                appManage(sessions);
                break;
        }
//        return tmpSession;
    }

    public static void addPlayer(){
        Scanner in = new Scanner(System.in);
        int i=0, codePlayer;
        System.out.println("\n****  Player Details:  ****\n");
        System.out.print("\tFirst name: ");
        String firstName = in.next();
        System.out.print("\tLast name: ");
        String lastName = in.next();
        codePlayer = (int)Math.floor(Math.random() * 99999);
        players.add(new Player(codePlayer, firstName, lastName));
    }

    public static void addSession() {
        Scanner in = new Scanner(System.in);
        Scanner inScan = new Scanner(System.in);
        int duration=0;
        LocalTime t = LocalTime.of(9,00,00);
        double durPrices[][] = {{0.5, 1, 2, 5, 9}, {5, 10, 18, 40, 65}};

        System.out.println("\n***** ADD NEW SESSION *****");
//        LocalTime schedule = getValidTime(LocalTime.now());
        LocalTime schedule = getValidTime(LocalTime.of(10,11,00));
        System.out.print("\n\nPrices: \n");
        do {
            switch (getPlan()){
                case(1):
                    duration = 30;
                    break;
                case(2):
                    duration = 60;
                    break;
                case(3):
                    duration = 120;
                    break;
                case(4):
                    duration = 300;
                    break;
                case(5):
                    duration = 540;
                    break;
            }
            System.out.println(!checkAvSchedules(schedule, duration)?(RED+"\nFailed, Your plan is not valid, Choose another one! "+RESET):"");
        }while(!checkAvSchedules(schedule, duration));
//        for (Post post: posts){
//            if (changeStatus(post)) ;
//            else break;
//        }
        int postNum = getPost();
        System.out.print("\nChoose the game that you wanna play: ");
        int numGame = in.nextInt();
        Post newPost = posts.get(postNum-1);
        Game newGame = posts.get(postNum-1).getGames().get(numGame-1);

        // choose the post, if post is available continue for create session, else enter the list d'attend
        if(!posts.get(postNum-1).isStatus()){
            System.out.println("\nThis post in not available, Do u wanna wait your turn ? ");
            System.out.print("\n\tClick (Yes or No) to continue >>  ");
            String answer = inScan.nextLine();
            if(answer.equals("yes") || answer.equals("Yes"))
                createWaitingList(postNum, newGame, duration);
        }else{
            // add session
            addPlayer();
            Player newPlayer = players.get(players.size()-1);
            sessions.add(new Session(newPlayer, newPost, newGame, schedule, schedule.plusMinutes(duration)));
            newPost.setStatus(!newPost.isStatus());
            System.out.println(GREEN+"\n\tCreate new Session successfully.\n"+RESET);
        }
    }

    public static LocalTime getValidTime(LocalTime sch) {
        ArrayList<LocalTime> schedules = new ArrayList<>();
        LocalTime schedule = LocalTime.of(9,00,00);
        for (int i=0; i<18; i++){
            schedules.add(schedule) ;
            if(i == 5)
                schedule = schedule.plusMinutes(120);
            schedule = schedule.plusMinutes(30);
        }
        for (int j=0; j<schedules.size(); j++){
            if(sch.compareTo(schedules.get(j)) == 0 )
                return schedules.get(j);
            else if(sch.isBefore(schedules.get(j+1)) && sch.isAfter(schedules.get(j))){
                return schedules.get(j+1);
            }
        }
        return sch;
    }

    public static boolean checkAvSchedules(LocalTime s, int d){
        LocalTime e = s.plusMinutes(d);
        if( e.isAfter(LocalTime.of(20,00,00)) ||
                (e.isAfter(LocalTime.of(12,00,00))
                        && e.isBefore(LocalTime.of(14,00,00))) )
            return false;
        else return true;
    }

    public static int getPlan(){
        Scanner in = new Scanner(System.in);
        String durPrices[][] = {{"30 Min", "One hour", "Two hours", "Five hours", "All the day"}, {"5", "10", "18", "40", "65"}};
        for (int i=0; i<durPrices[0].length; i++){
            System.out.print("\n\t" + (i+1) +": \t" + durPrices[0][i] +", Price: "+ durPrices[1][i] +"DH;");
        }
        System.out.print("\n\nWhat's Your plan: ");
        int plan = in.nextInt();
        return plan;
    }

    public static int getPost(){
        Scanner in = new Scanner(System.in);
        System.out.println("\n\t\t****  Post:  ****");
        for (Post post: posts) {
            System.out.println(post.toString());
        }
        System.out.print("\nChoose the post: ");
        int numPost = in.nextInt();
        return numPost;
    }

    public static void createWaitingList(int numPost, Game g, int d) {
        LocalTime start = getValidTime(LocalTime.now());
        for (int i=0; i<sessions.size(); i++){
            if(sessions.get(i).getPost().getNumber() == posts.get(numPost-1).getNumber()){
                start = sessions.get(i).getEndTime();
            }
        }
        LocalTime end = start.plusMinutes(d);
        if(checkAvSchedules(start, d)){
            addPlayer();
            Player p = players.get(players.size()-1);
            Session newOne = new Session(p,posts.get(numPost-1), g, start, end);
            sessions.add(newOne);
            System.out.println(GREEN+"\n\t ^_^ Create new Session in the waiting list. \n"+RESET);
        }else
            System.out.println(RED+"\n -_- Your schedule is not available for today !!"+RESET);
    }

    public static boolean changeStatus(Post p){
        LocalTime timeNow = LocalTime.now();
        if(sessions.size() > 0){
            Session lastSession = sessions.get(0).getLastSessionByPost(sessions,p);
            if(lastSession == null){
                System.out.println("There's no session in this Post");
                if(!p.isStatus()){
                    posts.get(p.getNumber()).setStatus(true);
                }
            }else if(timeNow.isAfter(lastSession.getEndTime())){
                posts.get(p.getNumber()).setStatus(true);
            }
            return true;
        } else {
            return false;
        }
    }

    /*public static double calculatePrice(){
        double total = 0;


    }*/

}
