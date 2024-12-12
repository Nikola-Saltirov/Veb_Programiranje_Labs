package mk.ukim.finki.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.MissingSongArguments;
import mk.ukim.finki.wp.lab.model.exceptions.SongNotFoundException;
import mk.ukim.finki.wp.lab.repository.JPA.GradeRepositoryJPA;
import mk.ukim.finki.wp.lab.repository.JPA.SongRepositoryJPA;
import mk.ukim.finki.wp.lab.repository.inMemory.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.ukim.finki.wp.lab.bootstrap.DataHolder.artists;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepositoryJPA songRepository;
    private final GradeRepositoryJPA gradeRepository;
    private final SongRepository sr;

    public SongServiceImpl(SongRepositoryJPA songRepository, GradeRepositoryJPA gradeRepository, SongRepository sr) {
        this.songRepository = songRepository;
        this.gradeRepository = gradeRepository;
        this.sr = sr;
    }


    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    public void addArtistToSong(Artist artist, Song song) { //Zoshto vrakja Artist?
        List<Artist> artists = song.getArtists();
        artists.add(artist);
        song.setArtists(artists);
        songRepository.save(song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public void addGrade(String songId, Integer grade) {
        Song song=songRepository.findByTrackId(songId);
        List<Grade> grades = song.getGrades();
        grades.add(new Grade(grade));
        song.setGrades(grades);
        songRepository.save(song);
    }
    public Song findBySongId(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if(id == null) {
            throw new SongNotFoundException(id.toString());
        }
        songRepository.deleteById(id);
    }

    @Override
    public List<String> getGenres() {
        return sr.getGenres();
    }

    @Override
    public void setFilter(String genre) {
        sr.setFilter(genre);
    }

    @Override
    public void addNewSong(String title, String trackId, String genre, int releaseYear, Album album) {
        if(title == null || title.isEmpty()
                || trackId == null || trackId.isEmpty()
                || genre == null || genre.isEmpty() || album == null) {
            throw new SongNotFoundException(trackId.toString());
        }

        songRepository.save(new Song(trackId, title, genre, releaseYear, album));
    }

    @Override
    public void editSong(Long songId, String title, String trackId, String genre, int releaseYear, Album album) {
        if(songId == null
                || title == null || title.isEmpty()
                || trackId == null || trackId.isEmpty()
                || genre == null || genre.isEmpty() || album == null) {
            throw new MissingSongArguments();
        }

        Song editedSong = findBySongId(songId);
        editedSong.setTitle(title);
        editedSong.setTrackId(trackId);
        editedSong.setGenre(genre);
        editedSong.setReleaseYear(releaseYear);
        editedSong.setAlbum(album);
        songRepository.save(editedSong);
    }


}
