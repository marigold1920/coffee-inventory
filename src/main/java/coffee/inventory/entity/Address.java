package coffee.inventory.entity;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Integer id;

    @OneToOne(mappedBy = "address")
    private Employee employee;

    @OneToOne(mappedBy = "address")
    private Warehouse wareHouse;

    @Column(name = "name")
    @Getter
    private String name;
}
