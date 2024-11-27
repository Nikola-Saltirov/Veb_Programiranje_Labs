package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/songDetails")
public class SongDetailsController {

    SongService songService;

    public SongDetailsController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/{id}")
    public String getSongDetailsPage(@PathVariable Long id,
                                     Model model) {

        Song song = songService.findBySongId(id);
        model.addAttribute("songTitle", song.getTitle());
        model.addAttribute("genre", song.getGenre());
        model.addAttribute("year", song.getReleaseYear());
        model.addAttribute("album", song.getAlbum().getName());
        model.addAttribute("artists", song.getPerformers());
        //TODO
        model.addAttribute("grade", song.getAvg());
        return "songDetails";
    }
}
