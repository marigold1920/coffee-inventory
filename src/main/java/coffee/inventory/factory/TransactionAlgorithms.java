package coffee.inventory.factory;

import coffee.inventory.chain.PipeLineManager;

interface TransactionAlgorithms {
    void init(PipeLineManager initiator);
}