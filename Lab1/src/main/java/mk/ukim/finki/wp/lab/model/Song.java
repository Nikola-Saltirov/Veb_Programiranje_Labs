package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

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
