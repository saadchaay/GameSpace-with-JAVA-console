public class Post {

    private static int number = 0;
    private String console;
    private String display;

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        Post.number = number;
    }

    public String getConsole() {
        return console;
    }

    public String getDisplay() {
        return display;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

}
