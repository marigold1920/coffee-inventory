package coffee.inventory.entity;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "unit")
public class Unit {
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Integer id;

    @OneToMany(mappedBy = "unit", cascade = { CascadeType.MERGE })
    private Set<Product> products;

    @Column(name = "name")
    @Getter
    private String name;
}
