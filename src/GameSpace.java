import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.*;
import java.io.IOException;
public class GameSpace {
    static ArrayList<Player> players = new ArrayList<>();
    static ArrayList<Session> sessions = new ArrayList<>();
    static ArrayList<Post> posts = postManagement();
    public static void main(String[] args) {

        int durations[] = {30, 1, 2, 5, 8};
        int prices[] = {5, 10, 18, 40, 65};
        System.out.println("\n\t\t\t****** WELCOME: GameSpace app management ******");
//        Player p = new Player(43424,"saad", "chaay");
//        LocalTime t = LocalTime.of(9,00,00);
        appManage(sessions);
    }

    // This function allows to add new player with generate a random unique ID for each Player
    public static void addPlayer(){
        Scanner in = new Scanner(System.in);
        int i=0, codePlayer;
        System.out.println("\n****  Player Details:  ****");
        System.out.print("\t\t First name: ");
        String firstName = in.next();
        System.out.print("\t\t Last name: ");
        String lastName = in.next();
        codePlayer = (int)Math.floor(Math.random() * 99999);
        players.add(new Player(codePlayer, firstName, lastName));
    }

    /*public static void addSession(ArrayList<Post> posts){
        Scanner in = new Scanner(System.in);
        Scanner inScan = new Scanner(System.in);
        int i=0, duration;
        LocalTime t = LocalTime.of(9,00,00);
        double durPrices[][] = {{0.5, 1, 2, 5, 9}, {5, 10, 18, 40, 65}};
        System.out.println("***** ADD NEW SESSION *****");
        System.out.print("\n\t1: [30min, Price: 5DH]  \n\t2: [1h, Price: 10DH] \n\t3: [2h, Price: 18DH]  \n\t4: [5h, Price: 40]  \n\t5: [all Day, Price: 65DH]\n Enter the number of duration: ");
        System.out.println("\n\nWhat the time you wanna play at(ex: 09:00): ");
        String sch = inScan.next();

        System.out.println();
        System.out.println("\t POST AVAILABLE: ");
        for (Post post: postAvailable(posts, sessions)){
            System.out.println("\nPost n "+ (i+1) +":");
            System.out.print("\tConsole: "+ post.getDevice()[0].getName());
            System.out.print(" | Screen: "+ post.getDevice()[1].getName());
            System.out.print("\n\tGames : ");
            for (int j=0; j<post.getGames().size(); j++){
                System.out.print((j+1) +": "+ post.getGames().get(j).getName() +"\t\t");
            }
            System.out.println();
            i++;
        }
        System.out.print("\nChoose the post: ");
        int numPost = in.nextInt();
        System.out.print("\nChoose the game that you wanna play: ");
        int numGame = in.nextInt();
        do {
            duration = in.nextInt();
            if(duration < 1 && duration > 5){
                System.out.print("\nError, Please enter a valid number, between 1 and 5:  ");
            }
        }while(duration < 1 && duration > 5);
        System.out.println("\n****  Player Details:  ****");
        addPlayer();
        System.out.println("\n\t****  Available Schedules:  ****");
        int k=0;
        for (LocalTime sch: checkSchedule(posts.get(numPost-1))){
            System.out.print(" | "+ sch);
            if(k == (checkSchedule(posts.get(numPost-1)).size()/2)-1)
                System.out.println();
            k++;
        }
        System.out.println("\n\nWhat the time you wanna play at: ");
//      sessions.add(new Session(players.get(players.size()-1),posts.get(numPost-1),posts.get(numPost-1).getGames().get(numGame-1),LocalTime.now(),LocalTime.now().plusMinutes(duration)));
        sessions.add(new Session(players.get(players.size()-1),posts.get(numPost-1),posts.get(numPost-1).getGames().get(numGame-1),LocalTime.parse(sch.concat(":00")),LocalTime.parse(sch.concat(":00")).plusMinutes(duration)));
//        System.out.println(sessions.get(0).toString());
    }*/

    public static void addSession() {
        Scanner in = new Scanner(System.in);
        Scanner inScan = new Scanner(System.in);
        int duration=0;
        LocalTime t = LocalTime.of(9,00,00);
        double durPrices[][] = {{0.5, 1, 2, 5, 9}, {5, 10, 18, 40, 65}};

        System.out.println("\n***** ADD NEW SESSION *****");
        LocalTime schedule = getValidTime(LocalTime.of(13,46,00));

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
            System.out.println(!checkAvSchedules(schedule, duration)?"\nFailed, Your plan is not valid, Choose another one! ":"");
        }while(!checkAvSchedules(schedule, duration));
        int postNum = getPost();
        System.out.print("\nChoose the game that you wanna play: ");
        int numGame = in.nextInt();
        Post newPost = posts.get(postNum-1);
        Game newGame = posts.get(postNum-1).getGames().get(numGame-1);

        // choose the post, if post is available continue for create session, else enter the list d'attend
        if(!posts.get(postNum-1).isStatus()){
            System.out.println("NOT AVAILABLE");
            createWaitingList(postNum, newGame, duration);
        }else{
            // add session
//            System.out.print("\nChoose the game that you wanna play: ");
//            int numGame = in.nextInt();
            addPlayer();
            Player newPlayer = players.get(players.size()-1);
//            Post newPost = posts.get(postNum-1);
//            Game newGame = posts.get(postNum-1).getGames().get(numGame-1);
            sessions.add(new Session(newPlayer, newPost, newGame, schedule, schedule.plusMinutes(duration)));
            newPost.setStatus(!newPost.isStatus());
        }
        /*System.out.print("\nChoose the game that you wanna play: ");
        int numGame = in.nextInt();
        System.out.println("\n****  Player Details:  ****");
        addPlayer();
        Player newPlayer = players.get(players.size()-1);
        Post newPost = postAvl.get(numPost-1);
        Game newGame = postAvl.get(numPost-1).getGames().get(numGame-1);
        sessions.add(new Session(newPlayer, newPost, newGame, schedule, schedule.plusMinutes(duration)));
        newPost.setStatus(false);*/
        System.out.println("\n\t\t^_^     Create new Session successfully      ^_^\n");
    }
    public static ArrayList<Post> postAvailable(LocalTime schedule, int d){
        ArrayList<Post> postsAvailable = posts;
        ArrayList<Game> games = new ArrayList<>();
//        games.add(new Game("rsdfefs"));
//        Device devices[] = {new Device("edrgtdfc"), new Device("dxfcgardxz")};
//        Post p = new Post(devices, games);
//        postsAvailable.add(p);
        LocalTime endSch = schedule.plusMinutes(d);
        for (int i=0; i<sessions.size(); i++) {
            if( (schedule.isAfter(LocalTime.of(9, 00, 00)) && endSch.isBefore(LocalTime.of(12, 00, 00)))
            || (schedule.isAfter(LocalTime.of(14, 00, 00)) && endSch.isBefore(LocalTime.of(20, 00, 00)))) {
                if ( (schedule.isBefore(sessions.get(i).getStartTime()) && endSch.isBefore(sessions.get(i).getStartTime()))
                        || (schedule.isAfter(sessions.get(i).getEndTime()) && endSch.isAfter(sessions.get(i).getEndTime()))
                        || (schedule.isAfter(sessions.get(i).getEndTime()) && (endSch.isBefore(LocalTime.of(12, 00, 00)) || endSch.isBefore(LocalTime.of(20, 00, 00))))
                ) {
                    postsAvailable.remove(sessions.get(i).getPost());
                }

            }
            /*if( (schedule.compareTo(sessions.get(i).getStartTime()) >= 0 && schedule.plusMinutes(d).compareTo(sessions.get(i).getEndTime()) < 0)
                || (endSch.isAfter(sessions.get(i).getEndTime()) && endSch.isBefore(sessions.get(i).getEndTime()))
                || (schedule.compareTo(sessions.get(i).getStartTime()) == 0 ) )
                postsAvailable.remove(sessions.get(i).getPost());*/
        }
        return postsAvailable;
    }

    public static ArrayList<LocalTime> checkSchedule(Post post) {
        ArrayList<LocalTime> schedules = new ArrayList<>();
        LocalTime schedule = LocalTime.of(9,00,00);
        for (int i=0; i<18; i++){
            schedules.add(schedule) ;
            if(i == 5)
                schedule = schedule.plusMinutes(120);
            schedule = schedule.plusMinutes(30);
        }
        ArrayList<LocalTime> freeTimes = new ArrayList<>();
        for (int i=0; i<sessions.size(); i++) {
            if (sessions.get(i).getPost().getNumber() == post.getNumber()) {
                for(int j=0; j<schedules.size(); j++){
                    if(schedules.get(j).compareTo(sessions.get(i).getStartTime()) < 0 || schedules.get(j).compareTo(sessions.get(i).getEndTime()) >= 0)
                        freeTimes.add(schedules.get(j));
                }
            }
//            return freeTimes;
        }
        return (freeTimes.size() > 0) ? freeTimes : schedules;
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
                int i=0;
                /*for (Post post: posts){
                    System.out.println("Post n "+ (i+1) +":");
                    System.out.print("\tGame Console: "+ post.getDevice()[0].getName());
                    System.out.print(" | Screen: "+ post.getDevice()[1].getName());
                    System.out.print("\n\nGames available: ");
                    for (Game game: post.getGames()){
                        System.out.print(game.getName()+", ");
                    }
                    System.out.println();
                    i++;
                }*/
                System.out.println("********* FIN *********");
                appManage(sessions);
                break;
            case(3):
//                ArrayList<LocalTime> schs = checkSchedule(posts.get(0));
               /* for (int j=0; j<checkSchedule(posts.get(0)).size(); j++) {
                    System.out.println("Free time: "+ checkSchedule(posts.get(0)).get(j));
                }*/
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
        if(s.plusMinutes(d).isAfter(LocalTime.of(12,00,00))
                || s.plusMinutes(d).isAfter(LocalTime.of(20,00,00)))
            return false;
        return true;
    }

    public static int getPlan(){
        Scanner in = new Scanner(System.in);
        System.out.print("\n\t1: [30min, Price: 5DH]  \n\t2: [1h, Price: 10DH] \n\t3: [2h, Price: 18DH]  \n\t4: [5h, Price: 40]  \n\t5: [all Day, Price: 65DH]");
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
        LocalTime start = LocalTime.now();
        for (int i=0; i<sessions.size(); i++){
            if(sessions.get(i).getPost().getNumber() == posts.get(numPost-1).getNumber()){
                start = sessions.get(i).getEndTime();
                break;
            }
        }
        LocalTime end = start.plusMinutes(d);
        if(!checkAvSchedules(start, d)){
            System.out.println("Create good");
        }else
            System.out.println("Not good");
//        addPlayer();
//        Player p = players.get(players.size()-1);
//        Session newOne = new Session(p,posts.get(numPost-1), g, start)

    }
}
