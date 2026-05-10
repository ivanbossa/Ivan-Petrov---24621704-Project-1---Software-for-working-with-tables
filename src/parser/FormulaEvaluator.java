package parser;

import exception.InvalidCellReferenceException;
import exception.InvalidFormulaException;
import model.Table;
import model.cell.Cell;
import model.cell.FormulaCell;
import parser.FormulaParser.ParsedFormula;

/**
 * Evaluates all FormulaCell objects in a Table.
 *
 * For each FormulaCell:
 *   1. Parse the formula with FormulaParser
 *   2. Look up the two referenced cells in the Table
 *   3. Apply the operator
 *   4. Store the result (or ERROR) back into the FormulaCell
 *
 * Follows SRP — evaluation is separate from parsing.
 */
public class FormulaEvaluator {

    private final FormulaParser formulaParser;

    public FormulaEvaluator(FormulaParser formulaParser) {
        this.formulaParser = formulaParser;
    }

    /**
     * Evaluates all formula cells in the given table.
     * Results are stored directly in each FormulaCell.
     *
     * @param table the table whose formula cells should be evaluated
     */
    public void evaluateAll(Table table) {
        for (int r = 1; r <= table.getRowCount(); r++) {
            for (int c = 1; c <= table.getRow(r).getSize(); c++) {
                Cell cell = table.getCell(r, c);
                if (cell instanceof FormulaCell) {
                    evaluateCell((FormulaCell) cell, table);
                }
            }
        }
    }

    /**
     * Evaluates a single FormulaCell in the context of the given table.
     */
    private void evaluateCell(FormulaCell formulaCell, Table table) {
        try {
            ParsedFormula parsed = formulaParser.parse(formulaCell.getFormula());

            // Resolve left operand
            Cell leftCell = table.getCell(parsed.left.getRow(), parsed.left.getColumn());
            if (leftCell == null) {
                throw new InvalidCellReferenceException(
                        parsed.left.getRow(), parsed.left.getColumn());
            }

            // Resolve right operand
            Cell rightCell = table.getCell(parsed.right.getRow(), parsed.right.getColumn());
            if (rightCell == null) {
                throw new InvalidCellReferenceException(
                        parsed.right.getRow(), parsed.right.getColumn());
            }

            double leftVal  = leftCell.getNumericValue();
            double rightVal = rightCell.getNumericValue();

            double result = applyOperator(leftVal, rightVal, parsed.operator);
            formulaCell.setComputedValue(result);

        } catch (InvalidFormulaException | InvalidCellReferenceException e) {
            // Invalid formula would display 0
            // division by zero returns ERROR
            formulaCell.setComputedValue(0.0);
        }
    }

    /**
     * Applies the arithmetic operator to the two values.
     * Division by zero sets the cell to ERROR state.
     */
    private double applyOperator(double left, double right, char operator)
            throws InvalidFormulaException {
        switch (operator) {
            case '+': return left + right;
            case '-': return left - right;
            case '*': return left * right;
            case '/':
                if (right == 0) {
                    throw new InvalidFormulaException("Division by zero");
                }
                return left / right;
            default:
                throw new InvalidFormulaException("Unknown operator: " + operator);
        }
    }
}