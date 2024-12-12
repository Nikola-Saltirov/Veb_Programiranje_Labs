package mk.ukim.finki.wp.lab.repository.JPA;
import mk.ukim.finki.wp.lab.model.Album;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepositoryJPA extends JpaRepository<Album, Long> {

}