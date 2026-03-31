package ms.movies.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Figure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String poster;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name="directorid")
    private Designer designer; // director_id

    private float rating;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Figure figure = (Figure) o;

        if (id != figure.id) return false;
        if (Float.compare(figure.rating, rating) != 0) return false;
        if (title != null ? !title.equals(figure.title) : figure.title != null) return false;
        return poster != null ? poster.equals(figure.poster) : figure.poster == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "title='" + title + '\'' +
                ", designer=" + designer +
                ", rating=" + rating +
                '}';
    }
}
