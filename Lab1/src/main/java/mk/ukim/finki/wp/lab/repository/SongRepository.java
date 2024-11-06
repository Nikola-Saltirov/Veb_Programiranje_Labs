package mk.ukim.finki.wp.lab.repository;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {
    public List<Song> findAll(){
        return DataHolder.songs;
    }

    public Optional<Song> findById(String id) {
        return DataHolder.songs.stream().filter(i -> i.getTrackId().equals(id)).findFirst();
    }

    public Optional<Artist> addArtistToSong(Artist artist, Song song) {
        Song temp=this.findById(song.getTrackId()).orElse(null);
        if (temp!=null) {
            temp.getPerformers().add(artist);
            return Optional.of(artist);
        }else{
            return Optional.empty();
        }
    }

}
