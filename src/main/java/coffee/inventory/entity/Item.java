package coffee.inventory.entity;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Getter
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "product_code")
    @Getter
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @Setter
    private Warehouse supplier;

    @OneToMany(mappedBy = "warehouse", cascade = { CascadeType.PERSIST })
    private Set<WarehouseItem> warehouseItems;

    @OneToMany(mappedBy = "item", cascade = { CascadeType.PERSIST })
    private Set<TransactionDetails> transactionDetails;

    public void addWarehouseItem(WarehouseItem warehouseItem) {
        warehouseItems.add(warehouseItem);
    }
}
