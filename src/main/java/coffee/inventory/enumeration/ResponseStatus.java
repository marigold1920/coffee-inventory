package coffee.inventory.enumeration;

public enum ResponseStatus {
    SUCCESS() {
        @Override
        public String getValue() {

            return "Successfully!";
        }
    },
    RESOURCE_NOT_FOUND() {
        @Override
        public String getValue() {

            return " was not recored in system inventory!";
        }
    },
    INVALID_RESOURCE() {
        @Override
        public String getValue() {

            return "Invalid resource!";
        }
    };

    public abstract String getValue();
}