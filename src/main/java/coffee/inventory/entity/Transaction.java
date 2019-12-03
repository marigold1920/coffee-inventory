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

    enum TransactionType {

        RECEIPT("Receipt") {
            @Override
            int getValue() {
                return 1;
            }
        },
        DELIVERY("Delivery") {
            @Override
            int getValue() {
                return 2;
            }
        },
        STOCKTAKING("Stocktaking") {
            @Override
            int getValue() {
                return 3;
            }
        };

        private String name;

        private TransactionType(String name) {
            this.name = name;
        }

        abstract int getValue();

        public String getName() {
            return name;
        }
    }

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

    private TransactionType type;
}
