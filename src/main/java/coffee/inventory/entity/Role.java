package coffee.inventory.entity;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Integer id;

    @Column(name = "role_name")
    @Getter
    private String roleName;

    @OneToMany(mappedBy = "roles")
    private Set<AccountRole> roles;
}
