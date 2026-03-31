package ms.movies.dao;

import ms.movies.model.Figure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FigureRepository extends JpaRepository<Figure, Integer> {

    List<Figure> findFiguresByDesignerId(int id);
}
