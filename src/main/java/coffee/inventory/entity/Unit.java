package coffee.inventory.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "unit")
public class Unit {
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private int id;

    @OneToMany(mappedBy = "unit", cascade = { CascadeType.MERGE })
    private Set<Product> products;

    @Column(name = "name")
    private String name;
}
