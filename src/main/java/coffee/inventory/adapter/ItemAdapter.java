package coffee.inventory.adapter;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class ItemAdapter implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer supplier;
    private String unit;
    private String category;
    private String name;
    private String productCode;
    private double price;
    private int quantity;

    public ItemAdapter setSupplier(Integer supplier) {
        this.supplier = supplier;
        return this;
    }
}