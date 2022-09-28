import java.util.ArrayList;
import java.util.Arrays;

public class Post {

    private static int c = 0;
    private int number;
    private Device[] devices;
    private ArrayList<Game> games;
    private boolean status ;

    public Post(Device[] devices, ArrayList<Game> games) {
        this.number = c;
        this.devices = devices;
        this.games = games;
        this.status = true;
        ++c;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
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

    @Override
    public String toString() {
        String games = "";
        int i=0;
        for (Game game: this.games){
            if(i == (this.games.size()-1))
                games += (i+1) + ": " + game.getName() + ".";
            else games += (i+1) + ": " + game.getName() + ", ";
            i++;
        }
        String av = isStatus()?"Available":"Not Available";
        return "\nPost" +
                " number = " + (number+1) +
                ", " + av + "\nConsole: " + devices[0].getName() + " | Screen: " + devices[1].getName() +
                ", Games = " + games;
    }
}
