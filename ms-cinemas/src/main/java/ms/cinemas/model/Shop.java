package ms.cinemas.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Shop implements Serializable {

    @ElementCollection
    @CollectionTable(name = "FIGURE_SHOP", joinColumns = @JoinColumn(name = "shop_id"))
    @Column(name = "figure_id")
    public Set<Integer> figures;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String logo;

    @Transient
    private List<String> figureNames = new ArrayList<>();

    @Override
    public String toString() {
        return "Cinema{" +
                "name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
