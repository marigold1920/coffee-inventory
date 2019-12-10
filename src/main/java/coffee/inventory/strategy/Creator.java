package coffee.inventory.strategy;

import coffee.inventory.chain.PipeLineManager;
import coffee.inventory.enumeration.TransactionType;

public abstract class Creator {
    public final TransactionType RECEIPT = TransactionType.RECEIPT;
    public final TransactionType DELIVERY = TransactionType.DELIVERY;

    public abstract TransactionAlgorithms createAlgorithms(TransactionType type);

    public void initData(TransactionType type, PipeLineManager initiator) {
        createAlgorithms(type).init(initiator);
    }
}