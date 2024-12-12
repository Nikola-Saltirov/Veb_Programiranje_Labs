package mk.ukim.finki.wp.lab.repository.JPA;

import mk.ukim.finki.wp.lab.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepositoryJPA extends JpaRepository<Grade, Long> {

}
