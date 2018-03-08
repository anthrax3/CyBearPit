package is.stma.judgebean.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Resource extends AbstractEntity {

    /* Fields --------------------------------------------------------------- */
    @Column(nullable = false, unique = true)
    private String name;

    /* Overrides ------------------------------------------------------------ */
    @Override
    public String getName() {
        return name;
    }

    /* Getters and Setters -------------------------------------------------- */
    public void setName(String name) {
        this.name = name;
    }

    /* Methods -------------------------------------------------------------- */
}