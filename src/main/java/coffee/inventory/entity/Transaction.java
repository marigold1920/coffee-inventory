package coffee.inventory.entity;

import org.hibernate.annotations.GenericGenerator;

import coffee.inventory.enumeration.TransactionStatus;
import coffee.inventory.enumeration.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "place_from", referencedColumnName = "id")
    @Setter
    private Warehouse fromWarehouse;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "place_to", referencedColumnName = "id")
    @Setter
    private Warehouse toWarehouse;

    @Column(name = "date_create")
    private LocalDate dateCreate;

    @Column(name = "date_receive")
    private LocalDate dateReceive;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransactionStatus status;

    @OneToMany(mappedBy = "transaction", cascade = { CascadeType.PERSIST })
    @Setter
    private Set<TransactionDetails> transactionDetails;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType type;

    public void setType(TransactionType type) {
        this.type = type;
        this.status = type == TransactionType.RECEIPT ? TransactionStatus.RECEIPTED : TransactionStatus.PROCESSING;
    }
}
