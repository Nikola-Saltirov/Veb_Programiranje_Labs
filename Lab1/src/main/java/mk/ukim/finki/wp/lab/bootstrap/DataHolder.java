package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists = null;
    public static List<Song> songs = null;

    public void init(){
        artists = new ArrayList<>();

        songs=new ArrayList<>();
    }
}
