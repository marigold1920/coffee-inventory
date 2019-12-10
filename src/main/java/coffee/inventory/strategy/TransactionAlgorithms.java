package coffee.inventory.strategy;

import coffee.inventory.chain.PipeLineManager;

interface TransactionAlgorithms {
    void init(PipeLineManager initiator);
}