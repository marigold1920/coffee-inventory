package coffee.inventory.factory;

import coffee.inventory.enumeration.TransactionType;

public class TransactionAlgorithmsCreator extends Creator {

    public TransactionAlgorithms createAlgorithms(TransactionType type) {
        switch (type) {
        case RECEIPT:
            return new ReceiptAlgorithms();
        case DELIVERY:
            return new DeliveryAlgorithms();
        case STOCKTAKING:
            return new StocktakingAlgorithms();
        default:
            return new ReceiptAlgorithms();
        }
    }

}