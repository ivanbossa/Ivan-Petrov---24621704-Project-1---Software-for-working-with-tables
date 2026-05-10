package file;

import exception.TableException;
import model.Row;
import model.Table;
import model.cell.Cell;
import parser.CellParser;
import parser.ConcreteCellFactory;
import parser.FormulaEvaluator;
import parser.FormulaParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads a .table file line by line and constructs a Table object.
 *
 * File format:
 *   - Each line = one row
 *   - Cells are separated by '|'
 *   - Integers:  42
 *   - Doubles:   3.14
 *   - Strings:   "hello"
 *   - Formulas:  =R1C1+R2C2
 *
 * After reading all rows, formula cells are evaluated.
 * Follows SRP — only responsible for reading.
 */
public class TableFileReader {

    private final CellParser cellParser;
    private final FormulaEvaluator formulaEvaluator;

    public TableFileReader() {
        this.cellParser = new CellParser(new ConcreteCellFactory());
        this.formulaEvaluator = new FormulaEvaluator(new FormulaParser());
    }

    /**
     * Reads the file at the given path and returns a fully built Table.
     *
     * @param filePath path to the .table file
     * @return populated Table with evaluated formulas
     * @throws TableException if the file cannot be read
     */
    public Table read(String filePath) throws TableException {
        Table table = new Table(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Row<Cell> row = cellParser.parseLine(line);
                table.addRow(row);
            }
        } catch (IOException e) {
            throw new TableException("Could not read file: " + filePath, e);
        }

        formulaEvaluator.evaluateAll(table);

        return table;
    }
}
