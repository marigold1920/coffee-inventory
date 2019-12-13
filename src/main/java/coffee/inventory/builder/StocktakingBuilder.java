package coffee.inventory.builder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import coffee.inventory.adapter.StocktakingDetailsAdapter;
import coffee.inventory.entity.Stocktaking;
import coffee.inventory.entity.StocktakingDetails;
import coffee.inventory.helper.PoolService;

public class StocktakingBuilder {
    protected Stocktaking stocktaking;
    protected PoolService poolService;

    public StocktakingBuilder(PoolService poolService, Integer warehouseId) {
        this.poolService = poolService;
        stocktaking = Stocktaking.builder().date(LocalDate.now()).warehouse(poolService.getWarehouse(warehouseId))
                .build();
    }

    public StocktakingBuilder makeDetails(Collection<StocktakingDetailsAdapter> stocktakingDetailsAdapters) {
        this.stocktaking.setStocktakingDetails(
                stocktakingDetailsAdapters.stream().map(this::createStocktakingDetails).collect(Collectors.toSet()));

        return this;
    }

    private StocktakingDetails createStocktakingDetails(StocktakingDetailsAdapter s) {
        return StocktakingDetails.builder().newQuantity(s.getNewQuantity()).note(s.getNote()).status(s.getStatus())
                .warehouseItem(poolService.getWarehouseitemById(s.getWarehouseItemId())).stocktaking(this.stocktaking)
                .build();
    }

    public Stocktaking build() {

        return stocktaking;
    }
}