package coffee.inventory.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import coffee.inventory.enumeration.StocktakingStatus;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stocktaking_details")
public class StocktakingDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private int id;

    @Column(name = "new_quantity")
    private int newQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StocktakingStatus status;

    @Column(name = "note")
    private String note;

    @Column(name = "old_quantity")
    private int oldQuantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "stocktaking_id", referencedColumnName = "id")
    private Stocktaking stocktaking;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "warehouseitem_id", referencedColumnName = "id")
    private WarehouseItem warehouseItem;

    @PrePersist
    private void updateWarehouseItemQuantity() {
        oldQuantity = warehouseItem.getQuantity();
        warehouseItem.setQuantity(newQuantity);
    }
}
