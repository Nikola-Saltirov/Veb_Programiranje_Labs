package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Song {
    private String trackId;
    private String title;
    private String genre;
    private Integer releaseYear;
    private List<Artist> performers;

    public Song(String trackId, String title, String genre, Integer releaseYear) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
    }
}
