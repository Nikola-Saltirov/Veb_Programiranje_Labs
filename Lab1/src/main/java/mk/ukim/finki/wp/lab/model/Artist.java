package mk.ukim.finki.wp.lab.model;


import lombok.Data;

@Data
public class Artist {
    private Long id;
    private String name;
    private String lastName;
    private String bio;

    public Artist(Long id, String name, String lastName, String bio) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.bio = bio;
    }
}
