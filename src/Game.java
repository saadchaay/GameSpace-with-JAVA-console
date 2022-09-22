public class Game {

    private enum type {Football, Guerre};
    private String name;

    public Game(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
