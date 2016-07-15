package niket.fc.table;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Prints Multiplication table for the given data array.
 */
class MultiplicationTableImpl implements MultiplicationTable {
    static final String NewLine = System.getProperty("line.separator");
    private static final int HEADER_COLUMN_INDEX = -1;//-1 stands for the header/first column
    private final long[] data;
    private final PrintStream printStream;
    private final Map<Integer, Integer> columnWidths;

    MultiplicationTableImpl(long[] data) throws InvalidDataException {
        this(data, System.out);
    }

    MultiplicationTableImpl(long[] data, PrintStream printStream) throws InvalidDataException {
        if (data == null || data.length == 0) throw new InvalidDataException("No Data Found");
        this.data = data;
        this.printStream = printStream;
        this.columnWidths = new HashMap<>(data.length + 1);
    }

    @Override
    public void print() {
        calculateColumnWidths();
        printHeaderRow();
        for (int rowIndex = 0; rowIndex < data.length; rowIndex++) {
            printDataRow(rowIndex);
        }
    }

    Map<Integer, Integer> calculateColumnWidths() {
        columnWidths.put(HEADER_COLUMN_INDEX, numberOfDigits(lastNumber()));
        for (int i = 0; i < data.length; i++)
            columnWidths.put(i, numberOfDigits(multiply(i, data.length - 1)));
        return columnWidths;
    }

    int numberOfDigits(long number) {
        return (int) (Math.log10(number) + 1);
    }

    private long lastNumber() {
        return data[data.length - 1];
    }

    void printHeaderRow() {
        printStream.printf(columnFormat(HEADER_COLUMN_INDEX), " ");
        for (int colIndex = 0; colIndex < data.length; colIndex++) {
            printStream.printf(columnFormat(colIndex), data[colIndex]);
        }
        printStream.println();
    }

    /**
     * This format helps to pad the cell number to fit the column width.
     *
     * @param columnIndex
     * @return
     */
    String columnFormat(int columnIndex) {
        return "%" + columnWidths.get(columnIndex) + "s ";
    }

    void printDataRow(int rowIndex) {
        printStream.printf(columnFormat(HEADER_COLUMN_INDEX), data[rowIndex]);
        for (int colIndex = 0; colIndex < data.length; colIndex++) {
            printStream.printf(columnFormat(colIndex), multiply(rowIndex, colIndex));
        }
        printStream.println();
    }

    private long multiply(int rowIndex, int colIndex) {
        return data[rowIndex] * data[colIndex];
    }

}
