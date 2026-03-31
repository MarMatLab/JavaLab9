package ms.cinemas.dao;

import ms.cinemas.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    List<Shop> findShopsByFiguresIsContaining(int id);
}
