package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.ArtistNotFoundException;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "ArtistServlet", urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet {
    private final ArtistService artistService;
    private final SongService songService;
    private final SpringTemplateEngine templateEngine;

    public ArtistServlet(ArtistService artistService, SongService songService, SpringTemplateEngine templateEngine) {
        this.artistService = artistService;
        this.songService = songService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String songId=req.getParameter("songId");

        context.setVariable("artists", artistService.listArtists().stream()
                .filter(x-> !songService.findByTrackId(songId)
                        .getPerformers().contains(x)).collect(Collectors.toList()));

        context.setVariable("selectedSong",songId);
        templateEngine.process("artistsList.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long artistId = Long.parseLong(req.getParameter("artistId"));
        String songId = req.getParameter("songId");
        songService.addArtistToSong(artistService.ArtistfindById(artistId),songService.findByTrackId(songId));
        resp.sendRedirect("/songDetails?songId="+songId);
    }
}
