public class Player {

    private int codePlayer;
    private String firstName;
    private String lastName;

    public Player(int codePlayer, String firstName, String lastName) {
        this.codePlayer = codePlayer;
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
