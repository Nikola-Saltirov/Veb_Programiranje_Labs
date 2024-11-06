package mk.ukim.finki.wp.lab.model.exceptions;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(Long id) {
        super(String.format("Artist with id %s is not found", id));
    }
}
