package mk.ukim.finki.wp.lab.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Artist {
    private Long id;
    private String name;
    private String lastName;
    private String bio;
}
