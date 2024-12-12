package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
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
        model.addAttribute("error", error);
        model.addAttribute("genres", songService.getGenres());
        return "listSongs";
    }

    @PostMapping("/add")
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
    public String getEditSongForm(@PathVariable Long id, Model model) {
        model.addAttribute("albums", albumService.findAll());
        model.addAttribute("song", songService.findBySongId(id));
        model.addAttribute("songId", id);
        return "addSong";
    }

    @GetMapping("/add")
    public String getAddSongPage(Model model) {
        model.addAttribute("albums", albumService.findAll());
        return "addSong";
    }



    @PostMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        try {
            artistService.removeSongFromArtists(id);
            songService.deleteById(id);
        }
        catch (Exception e) {
            return "redirect:/songs";
        }
        return "redirect:/songs";
    }
    @PostMapping("/filtered")
    public String filterSongs(@RequestParam String genre){
        songService.setFilter(genre);
        return "redirect:/songs";
    }
}
