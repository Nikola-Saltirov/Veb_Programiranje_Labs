package mk.ukim.finki.wp.lab.model.exceptions;


public class NoSongSelectedException extends RuntimeException {
    public NoSongSelectedException() {
        super("No song selected");
    }
}
