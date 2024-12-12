package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists = null;
    public static List<Song> songs = null;
    public static List<Grade> grades = null;
    public static Song selectedSong = null;
    public static List<Album> albums;
    public static String filterGenre;

    @PostConstruct
    public void init(){
        artists = new ArrayList<>();
        artists.add(new Artist("Steve","Harvey","He funny"));
        artists.add(new Artist("Craig","Harvey","He funny"));
        artists.add(new Artist("Frank","Harvey","He funny"));
        artists.add(new Artist("John","Harvey","He funny"));
        artists.add(new Artist("Stuart","Harvey","He funny"));

        albums = new ArrayList<>();
        albums.add(new Album("Harveys", "Jazz", "2024"));
        albums.add(new Album("That thing over there", "Rap", "2024"));
        albums.add(new Album("Anotha one", "Rock", "1900"));
        albums.add(new Album("Shining Diamonds", "Metal", "1976"));
        albums.add(new Album("Suspension", "Jazz", "2013"));

        grades = new ArrayList<>();
        grades.add(new Grade(1));
        grades.add(new Grade(2));
        grades.add(new Grade(3));
        grades.add(new Grade(4));
        grades.add(new Grade(5));


        songs=new ArrayList<>();
        songs.add(new Song("1","One","Jazz",2024,artists.subList(0,1),albums.get(0)));
        songs.add(new Song("2","Two","Rap",2024,artists.subList(1,2),albums.get(1)));
        songs.add(new Song("3","Three","Rock",1900,artists.subList(2,3),albums.get(2)));
        songs.add(new Song("4","Four","Metal",1976,artists.subList(3,4),albums.get(3)));
        songs.add(new Song("5","Five","Jazz",2013,artists.subList(4,5),albums.get(4)));

        selectedSong=null;
        filterGenre=null;
    }
}
