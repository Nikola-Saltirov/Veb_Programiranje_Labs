package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;

public interface SongService {
    List<Song> listSongs();
    void addArtistToSong(Artist artist, Song song);
    Song findByTrackId(String trackId);

    void addNewSong(String title, String trackId, String genre, int releaseYear, Album album);

    void editSong(Long songId, String title, String trackId, String genre, int i, Album album);

    Song findBySongId(Long id);

    void deleteSongById(Long id);
}
