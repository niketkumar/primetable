package niket.fc.table;

/**
 * Created by nbhumihar on 7/14/16.
 */
public interface MultiplicationTable {

    void print();

    class Factory {
        public MultiplicationTable create(long[] data) throws InvalidDataException {
            return new MultiplicationTableImpl(data, System.out);
        }
    }

    class InvalidDataException extends Exception {
        public InvalidDataException(String msg) {
            super(msg);
        }
    }
}
