package file;

import exception.TableException;
import model.Row;
import model.Table;
import model.cell.Cell;
import model.cell.CellType;
import model.cell.FormulaCell;
import model.cell.StringCell;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Writes a Table object back to a .table file.
 *
 * Each row is written as a single line with cells separated by ' | '.
 * Cell values are written in their original format:
 * Integer or Double  is raw number
 * String is wrapped in double quotes
 * Formulas are original formula string (=R1C1+R2C2)
 *
 * Follows SRP — only responsible for writing.
 */
public class TableFileWriter {

    /**
     * Writes the table to the given file path.
     *
     * @param table    the table to write
     * @param filePath destination file path
     * @throws TableException if the file cannot be written
     */
    public void write(Table table, String filePath) throws TableException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            List<Row<Cell>> rows = table.getRows();

            for (int i = 0; i < rows.size(); i++) {
                Row<Cell> row = rows.get(i);
                StringBuilder line = new StringBuilder();

                List<Cell> cells = row.getCells();
                for (int j = 0; j < cells.size(); j++) {
                    line.append(serializeCell(cells.get(j)));
                    if (j < cells.size() - 1) {
                        line.append(" | ");
                    }
                }

                writer.write(line.toString());

                if (i < rows.size() - 1) {
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new TableException("Could not write file: " + filePath, e);
        }
    }

    /**
     * Converts a Cell back to its file representation.
     */
    private String serializeCell(Cell cell) {
        if (cell.getType() == CellType.STRING) {
            return "\"" + ((StringCell) cell).getValue() + "\"";
        }
        if (cell.getType() == CellType.FORMULA) {
            return ((FormulaCell) cell).getFormula();
        }
        return cell.getDisplayValue();
    }
}