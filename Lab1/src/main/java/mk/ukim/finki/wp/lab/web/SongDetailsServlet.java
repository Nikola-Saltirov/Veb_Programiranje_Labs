package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.SongService;
import mk.ukim.finki.wp.lab.service.impl.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;


@WebServlet(name = "SongDetailsServlet", urlPatterns = "/songDetails")
public class SongDetailsServlet extends HttpServlet {
    private final SongService songService;
    private final SpringTemplateEngine templateEngine;

    public SongDetailsServlet(SongService songService, SpringTemplateEngine templateEngine) {
        this.songService = songService;
        this.templateEngine = templateEngine;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String songId = req.getParameter("songId");
        Song song = songService.findByTrackId(songId);

        context.setVariable("songTitle", song.getTitle());
        context.setVariable("genre", song.getGenre());
        context.setVariable("year", song.getReleaseYear());
        context.setVariable("artists", song.getPerformers());
        context.setVariable("grade", song.getAvg());

        templateEngine.process("songDetails.html",context,resp.getWriter());
    }
}
