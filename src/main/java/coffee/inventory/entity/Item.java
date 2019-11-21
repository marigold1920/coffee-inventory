package coffee.inventory.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Warehouse supplier;

    @OneToMany(mappedBy = "wareHouse", cascade = { CascadeType.MERGE })
    private Set<WarehouseItem> wareHouseItems;

    @OneToMany(mappedBy = "item", cascade = { CascadeType.MERGE })
    private Set<TransactionDetail> transactionDetails;
}
