package coffee.inventory.strategy;

import coffee.inventory.enumeration.TransactionType;

public class TransactionAlgorithmsCreator extends Creator {

    public TransactionAlgorithms createAlgorithms(TransactionType type) {
        switch (type) {
        case RECEIPT:
            return new ReceiptAlgorithms();
        case DELIVERY:
            return new DeliveryAlgorithms();
        default:
            return new ReceiptAlgorithms();
        }
    }

}