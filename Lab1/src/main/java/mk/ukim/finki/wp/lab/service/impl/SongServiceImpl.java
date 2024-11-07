package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.ArtistNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.NoSongSelectedException;
import mk.ukim.finki.wp.lab.model.exceptions.SongNotFoundException;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }


    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findById(trackId);
    }

    @Override
    public void addGrade(String songId, Integer grade) {
        Song song=songRepository.findById(songId);
        song.addGrade(grade);
    }

    @Override
    public void addArtistToSong(Artist artist, Song song) {
        songRepository.addArtistToSong(artist, song);
        System.out.println("Check");
    }

}
