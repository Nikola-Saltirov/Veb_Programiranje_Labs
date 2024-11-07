package mk.ukim.finki.wp.lab.repository;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {
    public List<Song> findAll(){
        return DataHolder.songs.stream().toList();
    }

    public Song findById(String id) {
        return DataHolder.songs.stream().filter(i -> i.getTrackId().equals(id)).findFirst().orElse(null);
    }

    public void addArtistToSong(Artist artist, Song song) {
        Song temp=DataHolder.songs.stream().filter(s->s.equals(song)).findFirst().orElse(null);
        if(temp!=null) {
            temp.addArtist(artist);
        }
    }
}
