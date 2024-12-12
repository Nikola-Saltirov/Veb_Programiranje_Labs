package mk.ukim.finki.wp.lab.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue
    private Long id;
    private Integer grade;

    @ManyToMany
    @JoinTable(
            name = "song_grade",
            joinColumns = @JoinColumn(name = "grade_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> grades;

    public Grade(Integer grade) {
        this.grade = grade;
    }
}
