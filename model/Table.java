package model;

import model.cell.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the entire spreadsheet table.
 * Contains a list of rows, each of which contains cells.
 */
public class Table {

    private final List<Row<Cell>> rows;
    private String filePath;  // path to the loaded .table file

    public Table() {
        this.rows = new ArrayList<>();
    }

    public Table(String filePath) {
        this.rows = new ArrayList<>();
        this.filePath = filePath;
    }

    public void addRow(Row<Cell> row) {
        rows.add(row);
    }

    /**
     * Returns the row at the given 1-based row index.
     */
    public Row<Cell> getRow(int rowIndex) {
        int idx = rowIndex - 1;
        if (idx < 0 || idx >= rows.size()) {
            return null;
        }
        return rows.get(idx);
    }

    /**
     * Returns the cell at the given 1-based row and column indices.
     */
    public Cell getCell(int row, int column) {
        Row<Cell> r = getRow(row);
        if (r == null) return null;
        return r.getCell(column);
    }

    /**
     * Replaces the cell at the given 1-based row and column.
     */
    public void setCell(int row, int column, Cell cell) {
        Row<Cell> r = getRow(row);
        if (r != null) {
            r.setCell(column, cell);
        }
    }

    public int getRowCount() {
        return rows.size();
    }

    /**
     * Returns the maximum number of columns across all rows.
     */
    public int getMaxColumnCount() {
        int max = 0;
        for (Row<Cell> row : rows) {
            if (row.getSize() > max) {
                max = row.getSize();
            }
        }
        return max;
    }

    public List<Row<Cell>> getRows() {
        return rows;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}