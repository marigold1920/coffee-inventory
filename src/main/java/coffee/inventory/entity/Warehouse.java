package coffee.inventory.entity;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Table(name = "warehouse")
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Integer id;

    @Column(name = "display_name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "supplier", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @Setter
    private Set<Item> items;

    @OneToMany(mappedBy = "warehouse", cascade = { CascadeType.MERGE })
    private Set<WarehouseItem> warehouseItems;

    @Column(name = "type")
    private String type;
}
