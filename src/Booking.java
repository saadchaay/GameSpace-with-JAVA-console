import java.time.LocalTime;

public class Booking {

    private Player player;
    private Post post;
    private Game game;
    private int schedule;
    private LocalTime start_time;
    private LocalTime end_time;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
