package coffee.inventory.adapter;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class ItemAdapter implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String unitName;
    private String category;
    private String name;
    private double price;
    private int quantity;

}