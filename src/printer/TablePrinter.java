package printer;

import model.Row;
import model.Table;
import model.cell.Cell;

import java.util.List;

/**
 * Formats a Table into a human-readable String with aligned columns
 * and visible row/column coordinates.
 *
 * Example output:
 *        C1       C2       C3
 *  R1 |  10    |  20    |  30
 *  R2 |  hello |  3.14  |  world
 *  R3 |  30    |  33.14 |  94.2
 *
 * No System.out here — returns the result as a String.
 */
public class TablePrinter {

    private static final String SEPARATOR = " | ";
    private static final String EMPTY     = "";

    /**
     * Builds and returns the formatted table as a String.
     *
     * @param table the table to format
     * @return formatted string representation
     */
    public String print(Table table) {
        if (table.getRowCount() == 0) {
            return "(table is empty)";
        }

        int colCount  = table.getMaxColumnCount();
        int rowCount  = table.getRowCount();
        int[] colWidths = calculateColumnWidths(table, colCount);

        // izpisvane na kletkata (R12 primerno)
        int rowLabelWidth = String.valueOf(rowCount).length() + 1; // +1 idva ot R

        StringBuilder sb = new StringBuilder();

        //  Column row: C1, C2 i tn
        sb.append(padLeft(EMPTY, rowLabelWidth + 2));
        for (int c = 1; c <= colCount; c++) {
            String colLabel = "C" + c;
            sb.append(" ").append(String.format("%-" + colWidths[c - 1] + "s", colLabel));
            if (c < colCount) sb.append(SEPARATOR);
        }
        sb.append("\n");

        // liniite deto delqt tablicata
        sb.append("-".repeat(rowLabelWidth + 2));
        for (int c = 1; c <= colCount; c++) {
            sb.append("-".repeat(colWidths[c - 1] + 1));
            if (c < colCount) sb.append("-".repeat(SEPARATOR.length()));
        }
        sb.append("\n");


        List<Row<Cell>> rows = table.getRows();
        for (int r = 0; r < rows.size(); r++) {
            Row<Cell> row = rows.get(r);

            // Row label: R1, R2 i tn
            String rowLabel = "R" + (r + 1);
            sb.append(String.format("%-" + rowLabelWidth + "s", rowLabel)).append(" |");

            for (int c = 1; c <= colCount; c++) {
                Cell cell  = row.getCell(c);
                String val = (cell == null) ? "" : cell.getDisplayValue();
                sb.append(" ").append(String.format("%-" + colWidths[c - 1] + "s", val));
                if (c < colCount) sb.append(SEPARATOR);
            }

            sb.append("\n");
        }

        return sb.toString().stripTrailing();
    }

    /**
     * Calculates the max display width for each column,
     * also accounting for the column header label width (C12 example).
     */
    private int[] calculateColumnWidths(Table table, int colCount) {
        int[] widths = new int[colCount];

        for (int c = 1; c <= colCount; c++) {
            widths[c - 1] = String.valueOf("C" + c).length();
        }

        for (Row<Cell> row : table.getRows()) {
            for (int c = 1; c <= colCount; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    int len = cell.getDisplayValue().length();
                    if (len > widths[c - 1]) {
                        widths[c - 1] = len;
                    }
                }
            }
        }

        return widths;
    }

    private String padLeft(String s, int width) {
        return String.format("%" + width + "s", s);
    }
}