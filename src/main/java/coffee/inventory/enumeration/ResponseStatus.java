package coffee.inventory.enumeration;

public enum ResponseStatus {
    SUCCESS() {
        @Override
        public String getValue() {

            return "Successfully!";
        }
    },
    OK() {
        @Override
        public String getValue() {

            return "Successfully!";
        }
    },
    UNAUTHENTICATE() {
        @Override
        public String getValue() {

            return "Username and password is not correct!";
        }
    },
    SERVER_ERROR() {
        @Override
        public String getValue() {

            return "Server was running busily!";
        }
    },
    BAD_REQUEST() {
        @Override
        public String getValue() {

            return "Bad request!";
        }
    },
    RESOURCE_NOT_FOUND() {
        @Override
        public String getValue() {

            return "Resource was not recored in system inventory!";
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