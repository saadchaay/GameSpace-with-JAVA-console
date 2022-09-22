import java.util.ArrayList;

public class Post {

    private static int number = 0;
    private String type;
    private Device[] devices;
    private ArrayList<Game> games;

    public Post(String type, Device[] devices, ArrayList<Game> games) {
        this.type = type;
        this.devices = devices;
        this.games = games;
    }

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        Post.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
