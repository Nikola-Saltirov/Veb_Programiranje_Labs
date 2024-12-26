package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final AlbumService albumService;
    private final SongService songService;
    private final ArtistService artistService;

    public SongController(AlbumService albumService, SongService songService, ArtistService artistService) {
        this.albumService = albumService;
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("albums", albumService.findAll());
        model.addAttribute("songs", songService.listSongs());
        if (error != null) {
            model.addAttribute("error", "PLEASE SELECT A SONG");
        }
        return "listSongs";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveSong(@RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam String releaseYear,
                           @RequestParam Long albumId) {

        try {
            songService.addNewSong(title, trackId, genre, Integer.parseInt(releaseYear),
                    albumService.findById(albumId).orElse(null));
        }
        catch (Exception e) {
            return "redirect:/add";
        }
        return "redirect:/songs";
    }
    @PostMapping("/edit/{songId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editSong(@PathVariable Long songId,
                           @RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam String releaseYear,
                           @RequestParam Long albumId) {

        try {
            songService.editSong(songId, title, trackId, genre, Integer.parseInt(releaseYear),
                    albumService.findById(albumId).orElse(null));
        }
        catch (Exception e){
            return "redirect:/addSong";
        }
        return "redirect:/songs";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditSongForm(@PathVariable Long id, Model model) {
        model.addAttribute("albums", albumService.findAll());
        model.addAttribute("song", songService.findBySongId(id));
        model.addAttribute("songId", id);
        return "addSong";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAddSongPage(Model model) {
        model.addAttribute("albums", albumService.findAll());
        return "addSong";
    }



    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSong(@PathVariable Long id) {
        try {
            artistService.removeSongFromArtists(id);
            songService.deleteSongById(id);
        }
        catch (Exception e) {
            return "redirect:/songs";
        }
        return "redirect:/songs";
    }

    @PostMapping("/details")
    public String detailsSong(@RequestParam(required = false) String trackId) {
        if (trackId != null) {
            return "redirect:/songDetails/" + songService.findByTrackId(trackId).getId();
        }
        else {
            return "redirect:/songs?error=SelectSong";
        }
    }

}
