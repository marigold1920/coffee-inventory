package coffee.inventory.entity;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "warehouse_item")
public class WarehouseItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Getter
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse wareHouse;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @Getter
    private Item item;

    @Column(name = "quantity")
    private int quantity;
}
