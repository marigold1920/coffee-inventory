package coffee.inventory.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TransactionType {
    @JsonProperty("RECEIPT")
    RECEIPT, 
    @JsonProperty("DELIVERY")
    DELIVERY,
    @JsonProperty("STOCKTAKING")
    STOCKTAKING;
}