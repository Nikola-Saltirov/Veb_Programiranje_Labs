package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.JPA.AlbumRepositoryJPA;
import mk.ukim.finki.wp.lab.repository.JPA.ArtistRepositoryJPA;
import mk.ukim.finki.wp.lab.repository.JPA.SongRepositoryJPA;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists = null;
    public static List<Song> songs = null;
    public static List<Album> albums = null;
    private final AlbumRepositoryJPA albumRepositoryJPA;
    private final ArtistRepositoryJPA artistRepositoryJPA;
    private final SongRepositoryJPA songRepositoryJPA;
    private final ArtistService artistService;

    public DataHolder(AlbumRepositoryJPA albumRepositoryJPA, ArtistRepositoryJPA artistRepositoryJPA, SongRepositoryJPA songRepositoryJPA, ArtistService artistService) {
        this.albumRepositoryJPA = albumRepositoryJPA;
        this.artistRepositoryJPA = artistRepositoryJPA;
        this.songRepositoryJPA = songRepositoryJPA;
        this.artistService = artistService;
    }

    @PostConstruct
    public void init(){
        artists = new ArrayList<>();
        artists.add(new Artist("Steve","Harvey","He funny"));
        artists.add(new Artist("Craig","Harvey","He funny"));
        artists.add(new Artist("Frank","Harvey","He funny"));
        artists.add(new Artist("John","Harvey","He funny"));
        artists.add(new Artist("Stuart","Harvey","He funny"));

        artistRepositoryJPA.saveAll(artists);

        albums = new ArrayList<>();
        albums.add(new Album("Harveys", "Jazz", "2024"));
        albums.add(new Album("That thing over there", "Rap", "2024"));
        albums.add(new Album("Anotha one", "Rock", "1900"));
        albums.add(new Album("Shining Diamonds", "Metal", "1976"));
        albums.add(new Album("Suspension", "Jazz", "2013"));

        albumRepositoryJPA.saveAll(albums);

        songs=new ArrayList<>();
        songs.add(new Song("1","One","Jazz",2024,artists.subList(0,1),albums.get(0)));
        songs.add(new Song("2","Two","Rap",2024,artists.subList(1,2),albums.get(1)));
        songs.add(new Song("3","Three","Rock",1900,artists.subList(2,3),albums.get(2)));
        songs.add(new Song("4","Four","Metal",1976,artists.subList(3,4),albums.get(3)));
        songs.add(new Song("5","Five","Jazz",2013,artists.subList(4,5),albums.get(4)));

        songRepositoryJPA.saveAll(songs);

        artistService.addSongToArtist(artists.get(0), songs.get(0));
        artistService.addSongToArtist(artists.get(1), songs.get(1));
        artistService.addSongToArtist(artists.get(2), songs.get(2));
        artistService.addSongToArtist(artists.get(3), songs.get(3));
        artistService.addSongToArtist(artists.get(4), songs.get(4));
    }
}
