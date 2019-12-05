package coffee.inventory.entity;

import org.hibernate.annotations.GenericGenerator;

import coffee.inventory.enumeration.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "place_from", referencedColumnName = "id")
    private Warehouse fromWareHouse;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "place_to", referencedColumnName = "id")
    private Warehouse toWareHouse;

    @Column(name = "date_create")
    private LocalDate dateCreate;

    @Column(name = "date_receive")
    private LocalDate dateReceive;

    @OneToMany(mappedBy = "transaction", cascade = { CascadeType.MERGE })
    private Set<TransactionDetail> transactionDetails;

    @Column(name = "type")
    private TransactionType type;
}
