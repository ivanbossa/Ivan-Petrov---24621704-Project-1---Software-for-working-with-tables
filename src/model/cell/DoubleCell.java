package model.cell;
/**
 * Represents a cell containing a double value.
 */
public class DoubleCell extends Cell {

    private double value;

    public DoubleCell(double value) {
        super(CellType.DOUBLE);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String getDisplayValue() {
        // Avoid printing unnecessary trailing zeros (e.g. 3.5 instead of 3.500000)
        if (value == Math.floor(value) && !Double.isInfinite(value)) {
            return String.format("%.1f", value);
        }
        // Print up to 6 significant decimal places, trimming trailing zeros
        return String.valueOf(value);
    }

    @Override
    public double getNumericValue() {
        return value;
    }
}
