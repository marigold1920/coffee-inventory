package coffee.inventory.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StocktakingStatus {
    @JsonProperty("FULL")
    FULL,
    @JsonProperty("LACK")
    LACK,
    @JsonProperty("REDUNDANT")
    REDUNDANT
}