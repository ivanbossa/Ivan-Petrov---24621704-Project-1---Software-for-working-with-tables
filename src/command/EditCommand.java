package command;

import app.ApplicationSession;
import exception.FileNotOpenException;
import exception.InvalidCellReferenceException;
import exception.TableException;
import model.Table;
import model.cell.Cell;
import parser.ConcreteCellFactory;
import parser.FormulaEvaluator;
import parser.FormulaParser;

/**
 * Edits the value of a specific cell.
 * Usage: edit <row> <col> <new_value>
 *
 * After editing, all formula cells are re-evaluated
 * since the changed cell might be referenced by a formula.
 *
 * Throws FileNotOpenException if no file is open.
 * Throws InvalidCellReferenceException if row/col is out of bounds.
 */

public class EditCommand implements Command {

    private final ApplicationSession session;
    private final ConcreteCellFactory cellFactory;
    private final FormulaEvaluator formulaEvaluator;

    public EditCommand(ApplicationSession session) {
        this.session = session;
        this.cellFactory = new ConcreteCellFactory();
        this.formulaEvaluator = new FormulaEvaluator(new FormulaParser());
    }

    @Override
    public String execute(String[] args) throws TableException {
        if (!session.isFileOpen()) {
            throw new FileNotOpenException();
        }

        if (args.length < 3) {
            return "Usage: edit <row> <col> <new_value>";
        }

        int row;
        int col;
        try {
            row = Integer.parseInt(args[0]);
            col = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            return "Row and column must be integers.";
        }

        String newValue = args[2];
        Table table = session.getCurrentTable();

        if (table.getCell(row, col) == null) {
            throw new InvalidCellReferenceException(row, col);
        }

        Cell newCell = cellFactory.createCell(newValue);
        table.setCell(row, col, newCell);

        formulaEvaluator.evaluateAll(table);

        return "Successfully edited cell R" + row + "C" + col;
    }
}