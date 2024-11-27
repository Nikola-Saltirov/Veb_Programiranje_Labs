package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Data
@AllArgsConstructor
public class Song {
    private String trackId;
    private String title;
    private String genre;
    private Integer releaseYear;
    private List<Artist> performers;
    private List<Integer> grades;
    private Long id;
    private Album album;

    public Song(String trackId, String title, String genre, int releaseYear, Album album) {
        this.id = (long) (Math.random() * 1000);
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.album = album;
        this.performers = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    public Song(String trackId, String title, String genre, int releaseYear, List<Artist> performers, Album album) {
        this.id = (long) (Math.random() * 1000);
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.album = album;
        this.performers = performers;
        this.grades = new ArrayList<>();
    }

    //REFACTOR THESE
    public void addArtist(Artist artist) {
        performers.add(artist);
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public Double getAvg(){
        int sum=0;
        for (Integer grade : grades) {
            sum+=grade;
        }
        return sum/(double)grades.size();
    }
}
