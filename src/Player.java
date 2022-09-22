import java.util.Random;
public class Player {

    private static int codePlayer;
    private String firstName;
    private String lastName;

    public Player(String firstName, String lastName) {
        this.codePlayer = (int)Math.floor(Math.random() * 99999);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getCodePlayer() {
        return codePlayer;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setCodePlayer(int codePlayer) {
        this.codePlayer = codePlayer;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
