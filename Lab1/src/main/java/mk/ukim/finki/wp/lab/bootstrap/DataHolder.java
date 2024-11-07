package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists = null;
    public static List<Song> songs = null;
    public static Song selectedSong = null;

    @PostConstruct
    public void init(){
        artists = new ArrayList<>();
        artists.add(new Artist(1L,"Steve","Harvey","He funny"));
        artists.add(new Artist(2L,"Craig","Harvey","He funny"));
        artists.add(new Artist(3L,"Frank","Harvey","He funny"));

        songs=new ArrayList<>();
        songs.add(new Song("1","One","metal",1984,new ArrayList<>(),new ArrayList<>()));
        songs.add(new Song("1","Two","metal",1984,new ArrayList<>(),new ArrayList<>()));
        songs.add(new Song("1","Three","metal",1984,new ArrayList<>(),new ArrayList<>()));
        songs.add(new Song("1","Four","metal",1984,new ArrayList<>(),new ArrayList<>()));
        songs.add(new Song("1","Five","metal",1984,new ArrayList<>(),new ArrayList<>()));

        selectedSong=null;
    }
}
