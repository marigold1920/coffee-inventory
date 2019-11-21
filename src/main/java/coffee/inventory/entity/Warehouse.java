package coffee.inventory.entity;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;

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
    private int id;

    @Column(name = "display_name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "supplier", cascade = { CascadeType.MERGE })
    private Set<Item> items;

    @OneToMany(mappedBy = "wareHouse", cascade = { CascadeType.MERGE })
    private Set<WarehouseItem> wareHouseItems;

    @OneToMany(mappedBy = "fromWareHouse", cascade = { CascadeType.MERGE })
    private Set<Transaction> transactionSend;

    @OneToMany(mappedBy = "toWareHouse", cascade = { CascadeType.MERGE })
    private Set<Transaction> transactionReceive;

    private int type;
}
