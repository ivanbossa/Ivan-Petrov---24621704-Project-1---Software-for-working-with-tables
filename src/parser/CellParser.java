package parser;

import model.Row;
import model.cell.Cell;

/**
 * Parses a single line from a .table file into a Row of Cell objects.
 * Cells in the file are separated by the '|' character.
 *
 * Follows SRP — this class only handles splitting a line into cells.
 * The actual cell creation is delegated to CellFactory.
 */
public class CellParser {

    private final CellFactory cellFactory;

    public CellParser(CellFactory cellFactory) {
        this.cellFactory = cellFactory;
    }

    /**
     * Parses one line (one row) from the file.
     * Example line 42 | "hello" | =R1C1+R2C2 | 3.14
     *
     * @param line raw line string from the .table file
     * @return a Row containing the parsed cells
     */
    public Row<Cell> parseLine(String line) {
        Row<Cell> row = new Row<>();

        if (line == null || line.trim().isEmpty()) {
            return row;
        }

        String[] parts = line.split("\\|", -1);

        for (String part : parts) {
            Cell cell = cellFactory.createCell(part.trim());
            row.addCell(cell);
        }

        return row;
    }
}