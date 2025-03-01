package mk.ukim.finki.wp.lab.repository.inMemory;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {
    public List<Artist> findAll() {
        return DataHolder.artists.stream().toList();
    }

    public Optional<Artist> findById(Long id) {
        return DataHolder.artists.stream().filter(x->x.getId() == id).findFirst();
    }

}
