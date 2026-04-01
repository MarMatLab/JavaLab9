package ms.cinemas.web;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.cinemas.dao.ShopRepository;
import ms.cinemas.model.Shop;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/webapi")
@RequiredArgsConstructor
public class ShopRest {

    private final ShopRepository shopRepository;

    @Value("http://localhost:8090/webapi")
    private String figureServiceUrl;

    @GetMapping("/shops")
    public List<Shop> getShops() {
        log.info("about to retrieve all shops");
        List<Shop> shops = shopRepository.findAll();
        shops.forEach(this::fillFigureNames);
        return shops;
    }

    @GetMapping("/figures/{id}/shops")
    public List<Shop> getShopsByFigure(@PathVariable int id) {
        log.info("about to retrieve shops by figure {}", id);
        List<Shop> shops = shopRepository.findShopsByFiguresIsContaining(id);
        shops.forEach(this::fillFigureNames);
        return shops;
    }

    private void fillFigureNames(Shop shop) {
        shop.getFigures().forEach(figureId -> {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<FigureDTO> responseEntity = restTemplate.exchange(
                    figureServiceUrl + "/figures/" + figureId,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    FigureDTO.class
            );

            String figureName = responseEntity.getBody().getTitle();
            shop.getFigureNames().add(figureName);
        });
    }

    @Data
    static class FigureDTO {
        private int id;
        private String title;
        private String poster;
        private float rating;
    }

}
