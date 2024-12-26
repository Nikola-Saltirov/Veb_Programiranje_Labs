package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;

import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public String getAddArtistPage(@RequestParam(required = false) String trackId, Model model) {
        if (trackId != null) {
            model.addAttribute("artists", artistService.listArtists().stream()
                    .filter(x->!songService.findByTrackId(trackId).getArtists().contains(x)));
            model.addAttribute("songId", trackId);
            return "artistsList";
        }else{
            return "redirect:/songs?error=NoSelected";
        }
    }

    @PostMapping("/add/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String addArtist(@PathVariable String id,
                            @RequestParam (required = false) Long artistId) {

        if (artistId != null) {
            Song song = songService.findByTrackId(id);
            Artist artist = artistService.findById(artistId).orElse(null);
            artistService.addSongToArtist(artist, song);
            return "redirect:/songDetails/" + song.getId();
        }else{
            return "redirect:/artist?trackId=" + id;
        }
    }

}
