package niket.fc.table;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static java.lang.String.format;
import static niket.fc.table.MultiplicationTableImpl.NewLine;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by nbhumihar on 7/14/16.
 */
public class MultiplicationTableImplTest {

    @Test
    public void shouldThrowIfNullData() {
        try {
            new MultiplicationTableImpl(null);
            fail("Should fail if no data found");
        } catch (MultiplicationTable.InvalidDataException e) {
            assertEquals("No Data Found", e.getMessage());
        }
    }

    @Test
    public void shouldThrowIfEmptyData() {
        try {
            new MultiplicationTableImpl(new long[]{});
            fail("Should fail if no data found");
        } catch (MultiplicationTable.InvalidDataException e) {
            assertEquals("No Data Found", e.getMessage());
        }
    }

    @Test
    public void numberOfDigits() throws MultiplicationTable.InvalidDataException {
        MultiplicationTableImpl multiplicationTable = new MultiplicationTableImpl(new long[]{1});
        assertEquals(1, multiplicationTable.numberOfDigits(1));
        assertEquals(2, multiplicationTable.numberOfDigits(10));
        assertEquals(3, multiplicationTable.numberOfDigits(100));
    }

    @Test
    public void firstHeaderColumnWidth() throws MultiplicationTable.InvalidDataException {
        Map<Integer, Integer> columnWidths = new MultiplicationTableImpl(new long[]{1, 2, 45, 666}).calculateColumnWidths();
        int firstColWidth = columnWidths.get(-1);
        assertEquals(3, firstColWidth);
    }

    @Test
    public void firstColumnWidth() throws MultiplicationTable.InvalidDataException {
        Map<Integer, Integer> columnWidths = new MultiplicationTableImpl(new long[]{1, 2, 45, 666}).calculateColumnWidths();
        int firstColWidth = columnWidths.get(0);
        assertEquals(3, firstColWidth);
    }

    @Test
    public void lastColumnWidth() throws MultiplicationTable.InvalidDataException {
        Map<Integer, Integer> columnWidths = new MultiplicationTableImpl(new long[]{1, 2, 45, 666}).calculateColumnWidths();
        int firstColWidth = columnWidths.get(3);
        assertEquals(6, firstColWidth);
    }

    @Test
    public void columnFormat() throws MultiplicationTable.InvalidDataException {
        MultiplicationTableImpl multiplicationTable = new MultiplicationTableImpl(new long[]{1, 2, 45, 666});
        multiplicationTable.calculateColumnWidths();
        assertEquals("%3s ", multiplicationTable.columnFormat(-1));
        assertEquals("%3s ", multiplicationTable.columnFormat(0));
        assertEquals("%4s ", multiplicationTable.columnFormat(1));
        assertEquals("%5s ", multiplicationTable.columnFormat(2));
        assertEquals("%6s ", multiplicationTable.columnFormat(3));
    }

    @Test
    public void printHeaderRow() throws MultiplicationTable.InvalidDataException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MultiplicationTableImpl multiplicationTable = new MultiplicationTableImpl(new long[]{1, 2, 555, 1234}, new PrintStream(byteArrayOutputStream));
        multiplicationTable.calculateColumnWidths();
        multiplicationTable.printHeaderRow();

        assertEquals(format("        1    2    555    1234 %s", NewLine), new String(byteArrayOutputStream.toByteArray()));
    }

    @Test
    public void printDataRow() throws MultiplicationTable.InvalidDataException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MultiplicationTableImpl multiplicationTable = new MultiplicationTableImpl(new long[]{1, 2, 555, 1234}, new PrintStream(byteArrayOutputStream));
        multiplicationTable.calculateColumnWidths();
        multiplicationTable.printDataRow(0);
        assertEquals(format("   1    1    2    555    1234 %s", NewLine), new String(byteArrayOutputStream.toByteArray()));

        multiplicationTable.printDataRow(1);
        assertEquals(format("   1    1    2    555    1234 %s" +
                "   2    2    4   1110    2468 %s", NewLine, NewLine), new String(byteArrayOutputStream.toByteArray()));

        multiplicationTable.printDataRow(3);
        assertEquals(format("   1    1    2    555    1234 %s" +
                "   2    2    4   1110    2468 %s" +
                "1234 1234 2468 684870 1522756 %s",
                NewLine, NewLine, NewLine), new String(byteArrayOutputStream.toByteArray()));
    }


    @Test
    public void print() throws MultiplicationTable.InvalidDataException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MultiplicationTableImpl multiplicationTable = new MultiplicationTableImpl(new long[]{1, 2, 3, 4}, new PrintStream(byteArrayOutputStream));
        multiplicationTable.print();
        assertEquals(format("  1 2  3  4 %s" +
                        "1 1 2  3  4 %s" +
                        "2 2 4  6  8 %s" +
                        "3 3 6  9 12 %s" +
                        "4 4 8 12 16 %s",
                NewLine, NewLine, NewLine, NewLine, NewLine), new String(byteArrayOutputStream.toByteArray()));

    }
}