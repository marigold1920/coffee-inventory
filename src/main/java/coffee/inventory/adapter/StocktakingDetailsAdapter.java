package coffee.inventory.adapter;

import lombok.*;

import java.io.Serializable;

import coffee.inventory.enumeration.StocktakingStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StocktakingDetailsAdapter implements Serializable {

    private static final long serialVersionUID = 1L;

    private  StocktakingStatus status;
    private String note;
    private int oldQuantity;
    private int newQuantity;
    private int warehouseItemId;
}
