package niket.fc.table;

/**
 * Prints Multiplication table for the given data set.
 * The first row and column of the table should have the data elements,
 * with each cell containing the product of the elements for the corresponding row and column.
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
