package coffee.inventory.entity;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "account_role")
public class AccountRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private int id;

    @ManyToOne( cascade = { CascadeType.MERGE })
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account accounts;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roles;
}
