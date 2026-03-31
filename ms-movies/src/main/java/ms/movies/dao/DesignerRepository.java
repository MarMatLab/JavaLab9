package ms.movies.dao;

import ms.movies.model.Designer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignerRepository extends JpaRepository<Designer, Integer> {
}
