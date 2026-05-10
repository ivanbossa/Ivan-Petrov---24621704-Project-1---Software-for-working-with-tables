package parser;

import exception.InvalidFormulaException;
import model.cell.CellReference;

/**
 * Parses a formula string ("=R1C1+R2C3") into its components:
 * - left operand  (CellReference) | operator (+, -, *, /) | right operand (CellReference)
 *
 * Formulas are always in the form:
 *   =RxCy OP RxCy
 * where OP is opperand and is one of: + - * /
 *
 * Follows SRP — this class only parses, it does NOT evaluate.
 */
public class FormulaParser {

    // Regex to match a full formula: =R<int>C<int> <op> R<int>C<int>
    private static final String FORMULA_REGEX =
            "=R(\\d+)C(\\d+)([+\\-*/])R(\\d+)C(\\d+)";

    /**
     * Represents the parsed components of a formula.
     */
    public static class ParsedFormula {
        public final CellReference left;
        public final char operator;
        public final CellReference right;

        public ParsedFormula(CellReference left, char operator, CellReference right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }
    }

    /**
     * Parses the formula string and returns a ParsedFormula.
     *
     * @param formula the raw formula string, "=R1C1+R2C3"
     * @return ParsedFormula containing left, operator, and right
     * @throws InvalidFormulaException if the formula does not match the expected format
     */
    public ParsedFormula parse(String formula) throws InvalidFormulaException {
        if (formula == null || !formula.matches(FORMULA_REGEX)) {
            throw new InvalidFormulaException(formula);
        }

        // Remove the leading '=' and split manually
        String body = formula.substring(1); // "R1C1+R2C3"

        // Find the operator position (skip the first R...C... part)
        int opIndex = findOperatorIndex(body);
        if (opIndex == -1) {
            throw new InvalidFormulaException(formula);
        }

        String leftPart  = body.substring(0, opIndex);      // "R1C1"
        char operator    = body.charAt(opIndex);             // '+'
        String rightPart = body.substring(opIndex + 1);     // "R2C3"

        CellReference left  = parseCellReference(leftPart,  formula);
        CellReference right = parseCellReference(rightPart, formula);

        return new ParsedFormula(left, operator, right);
    }

    /**
     * Finds the index of the arithmetic operator in the formula body.
     * Skips the first character (always R for row) to avoid treating a negative
     * row number as an operator.
     */
    private int findOperatorIndex(String body) {
        for (int i = 1; i < body.length(); i++) {
            char c = body.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                return i;
            }
        }
        return -1;
    }

    /**
     * Parses "RxCy" into a CellReference.
     */
    private CellReference parseCellReference(String ref, String originalFormula)
            throws InvalidFormulaException {
        // ref is in the form R<digits>C<digits>
        int cIndex = ref.indexOf('C');
        if (!ref.startsWith("R") || cIndex == -1) {
            throw new InvalidFormulaException(originalFormula);
        }
        try {
            int row    = Integer.parseInt(ref.substring(1, cIndex));
            int column = Integer.parseInt(ref.substring(cIndex + 1));
            return new CellReference(row, column);
        } catch (NumberFormatException e) {
            throw new InvalidFormulaException(originalFormula);
        }
    }
}