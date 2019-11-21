package coffee.inventory.entity;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "place_from", referencedColumnName = "id")
    private WareHouse fromWareHouse;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "place_to", referencedColumnName = "id")
    private WareHouse toWareHouse;

    @Column(name = "date_create")
    private LocalDate dateCreate;

    @Column(name = "date_receive")
    private LocalDate dateReceive;

    @OneToMany(mappedBy = "transaction", cascade = { CascadeType.MERGE })
    private Set<TransactionDetail> transactionDetails;

    private int type;
}
