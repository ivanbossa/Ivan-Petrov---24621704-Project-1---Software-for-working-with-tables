package model;
import model.cell.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single row in the spreadsheet.
 * Uses Generics so it can hold any subtype of Cell.
 */
public class Row<T extends Cell> {

    private final List<T> cells;

    public Row() {
        this.cells = new ArrayList<>();
    }

    public void addCell(T cell) {
        cells.add(cell);
    }

    /**
     * Returns the cell at the given 1-based column index.
     */
    public T getCell(int columnIndex) {
        int idx = columnIndex - 1;
        if (idx < 0 || idx >= cells.size()) {
            return null;
        }
        return cells.get(idx);
    }

    /**
     * Replaces the cell at the given 1-based column index.
     */
    public void setCell(int columnIndex, T cell) {
        int idx = columnIndex - 1;
        if (idx >= 0 && idx < cells.size()) {
            cells.set(idx, cell);
        }
    }

    public int getSize() {
        return cells.size();
    }

    public List<T> getCells() {
        return cells;
    }
}