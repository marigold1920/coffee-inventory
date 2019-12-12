package coffee.inventory.factory;

import coffee.inventory.chain.PipeLineManager;
import coffee.inventory.enumeration.Level;

public class DeliveryAlgorithms implements TransactionAlgorithms {

    public void init(PipeLineManager initiator) {
        initiator.doPipeLine(Level.ITEM);
    }

}