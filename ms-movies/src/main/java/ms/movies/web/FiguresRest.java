package ms.movies.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.movies.dao.FigureRepository;
import ms.movies.model.Figure;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/webapi")
@RequiredArgsConstructor
public class FiguresRest {

    private final FigureRepository figureRepository;

    @GetMapping("/figures")
    public List<Figure> getMovies() {
        log.info("about to get ze figjurs");
        return figureRepository.findAll();
    }

    @GetMapping("/figures/{id}")
    public Figure getMovie(@PathVariable int id) {
        log.info("about to retrieve figure {}", id);
        return figureRepository.findById(id).orElse(null);
    }

    @GetMapping("/designers/{id}/figuress")
    public List<Figure> getFiguresByDesigner(@PathVariable int id) {
        log.info("about to retrieve figures by designer {}", id);
        return figureRepository.findFiguresByDesignerId(id);
    }

    @PostMapping("/figures")
    public Figure addMovie(@RequestBody Figure figure) {
        figureRepository.save(figure);
        return figure;
    }
}
