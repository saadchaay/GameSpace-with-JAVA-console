import java.time.LocalTime;

public class Session {

    private Player player;
    private Post post;
    private Game game;
    private LocalTime startTime;
    private LocalTime endTime;

    public Session(Player player, Post post, Game game, LocalTime startTime, LocalTime endTime) {
        this.player = player;
        this.post = post;
        this.game = game;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

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
