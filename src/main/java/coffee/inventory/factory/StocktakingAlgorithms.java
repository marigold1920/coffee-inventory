package coffee.inventory.factory;

import coffee.inventory.chain.PipeLineManager;
import coffee.inventory.enumeration.Level;

public class StocktakingAlgorithms implements TransactionAlgorithms {

    @Override
    public void init(PipeLineManager initiator) {
        initiator.doPipeLine(Level.WAREHOUSEITEM);
    }

}