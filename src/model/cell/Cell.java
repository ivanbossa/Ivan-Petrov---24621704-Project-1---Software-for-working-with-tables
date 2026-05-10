package model.cell;


/**
 * Abstract base class for all cell types.
 * Every cell has a type and must be able to return its value as a String (for display)
 * and as a double (for formula calculations).
 */
public abstract class Cell {

    protected CellType type;

    public Cell(CellType type) {
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    /**
     * Returns the value of the cell as a String for display purposes.
     */
    public abstract String getDisplayValue();

    /**
     * Returns the numeric value of the cell for use in formula evaluation.
     * String cells return 0 by default.
     */
    public abstract double getNumericValue();

    @Override
    public String toString() {
        return getDisplayValue();
    }
}
