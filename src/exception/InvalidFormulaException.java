package exception;


/**
 * Thrown when a formula in a cell cannot be parsed or evaluated.
 */
public class InvalidFormulaException extends TableException {

    public InvalidFormulaException(String formula) {
        super("Invalid formula: " + formula);
    }
}
