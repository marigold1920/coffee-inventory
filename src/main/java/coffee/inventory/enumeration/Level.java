package coffee.inventory.enumeration;

public enum Level {
    CATEGORY() {
        @Override
        public int getValue() {

            return 6;
        }
    },
    UNIT() {
        @Override
        public int getValue() {

            return 5;
        }
    },
    PRODUCT() {
        @Override
        public int getValue() {

            return 4;
        }
    },
    ITEM() {
        @Override
        public int getValue() {

            return 3;
        }
    },
    WAREHOUSEITEM() {
        @Override
        public int getValue() {

            return 2;
        }
    },
    WAREHOUSE() {
        @Override
        public int getValue() {

            return 1;
        }
    };

    public abstract int getValue();
}