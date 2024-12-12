package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;

import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/artist")
public class ArtistController {

    ArtistService artistService;
    SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @PostMapping
    public String getAddArtistPage(@RequestParam String trackId, @RequestParam String grade, Model model) {
        model.addAttribute("artists", artistService.listArtists().stream().filter(x->!songService.findByTrackId(trackId).getArtists().contains(x)));
        model.addAttribute("songId", trackId);
        String resultString = grade.replaceAll(",", "");
        songService.addGrade(trackId,Integer.parseInt(resultString));
        return "artistsList";
    }

    @PostMapping("/add/{id}")
    public String addArtist(@PathVariable String id,
                            @RequestParam(required = false) Long artistId) {
        Song song = songService.findByTrackId(id);
        if (artistId != null) {
            Artist artist = artistService.findById(artistId).orElse(null);
            songService.addArtistToSong(artist, song);
        }
        return "redirect:/songDetails/" + song.getId();
    }

}
