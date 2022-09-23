import java.util.ArrayList;

public class Post {

    private static int number = 0;
    private Device[] devices;
    private ArrayList<Game> games;

    public Post(Device[] devices, ArrayList<Game> games) {
        this.devices = devices;
        this.games = games;
    }

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        Post.number = number;
    }

    public Device[] getDevice() {
        return devices;
    }

    public void setDevice(Device[] devices) {
        this.devices = devices;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

}
