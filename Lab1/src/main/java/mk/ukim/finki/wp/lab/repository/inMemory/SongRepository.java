package mk.ukim.finki.wp.lab.repository.inMemory;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class SongRepository {
    public List<Song> findAll(){
        return null;
    }

    public Song findByTrackId(String trackId) {
        return DataHolder.songs.stream().filter(x -> x.getTrackId().equals(trackId)).findFirst().orElse(null);
    }

    public Song findBySongId(Long id) {
        return DataHolder.songs.stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
    }

    public int findIndexById(Long id) {
        for (int i = 0; i < DataHolder.songs.size(); i++) {
            if (DataHolder.songs.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void addArtistToSong(Artist artist, Song song) {
        findByTrackId(song.getTrackId()).getArtists().add(artist);
    }

    public void deleteSong(Long id) {
        DataHolder.songs.removeIf(x->x.getId().equals(id));
    }

    public void saveSong(Song song) {
        int idx = findIndexById(song.getId());
        if(idx == -1) {
            DataHolder.songs.add(song);
        }
        else {
            DataHolder.songs.set(idx, song);
        }
    }

    public void setFilter(String genre) {
        return;
    }

    public List<String> getGenres() {
        List<Song> songs=DataHolder.songs;
        List<String> genres=songs.stream().map(Song::getGenre).toList();
        Set<String> g1=new HashSet<>();
        g1.addAll(genres);
        return g1.stream().toList();
    }
}
