package model.cell;

/**
 * Represents a cell containing a formula (e.g. =R1C1+R2C3).
 * The raw formula string is stored, and the computed result is cached after evaluation.
 */
public class FormulaCell extends Cell {

    private final String formula;      // raw formula string, e.g. "=R1C1+R2C3"
    private double computedValue;      // result after evaluation
    private boolean isError;           // true if division by zero or invalid reference

    public FormulaCell(String formula) {
        super(CellType.FORMULA);
        this.formula = formula;
        this.computedValue = 0.0;
        this.isError = false;
    }

    public String getFormula() {
        return formula;
    }

    public void setComputedValue(double value) {
        this.computedValue = value;
        this.isError = false;
    }

    public void setError(boolean error) {
        this.isError = error;
    }

    public boolean isError() {
        return isError;
    }

    @Override
    public String getDisplayValue() {
        if (isError) {
            return "ERROR";
        }
        // Display as integer if the result is a whole number
        if (computedValue == Math.floor(computedValue) && !Double.isInfinite(computedValue)) {
            return String.valueOf((long) computedValue);
        }
        return String.valueOf(computedValue);
    }

    @Override
    public double getNumericValue() {
        return computedValue;
    }
}