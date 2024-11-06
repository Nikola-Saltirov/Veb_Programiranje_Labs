package mk.ukim.finki.wp.lab.model.exceptions;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(String trackId) {
        super(String.format("Song with id %s is not found", trackId));
    }
}
