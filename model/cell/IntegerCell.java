package model.cell;


/**
 * Represents a cell containing an integer value.
 */
public class IntegerCell extends Cell {

    private int value;

    public IntegerCell(int value) {
        super(CellType.INTEGER);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String getDisplayValue() {
        return String.valueOf(value);
    }

    @Override
    public double getNumericValue() {
        return (double) value;
    }
}