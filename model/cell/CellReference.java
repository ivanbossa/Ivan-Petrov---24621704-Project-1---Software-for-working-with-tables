package model.cell;

/**
 * Represents a cell reference in the format RxCy (e.g. R1C2 = row 1, column 2).
 * Used inside formula expressions like =R1C1+R2C3
 */
public class CellReference {

    private final int row;    // 1-based row index
    private final int column; // 1-based column index

    public CellReference(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "R" + row + "C" + column;
    }
}
