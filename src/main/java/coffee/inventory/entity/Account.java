package coffee.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private int id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "accounts", fetch = FetchType.EAGER)
    private Set<AccountRole> accountRoles;

    @OneToOne(mappedBy = "accounts")
    private Employee employee;

    public List<GrantedAuthority> grantedAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        accountRoles.forEach(accountRole -> authorities.add(new SimpleGrantedAuthority(accountRole.getRoles().getRoleName())));

        return authorities;
    }
}
