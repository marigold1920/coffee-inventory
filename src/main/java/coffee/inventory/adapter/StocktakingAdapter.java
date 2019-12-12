package coffee.inventory.adapter;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StocktakingAdapter implements Serializable{

    private static final long serialVersionUID = 1L;

    private int warehouseId;
    private ArrayList<StocktakingDetailsAdapter> listStock;

}
