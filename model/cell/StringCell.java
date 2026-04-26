package model.cell;

/**
 * Represents a cell containing a string value.
 * String values in the file are enclosed in double quotes: "hello"
 */
public class StringCell extends Cell {

    private String value;

    public StringCell(String value) {
        super(CellType.STRING);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getDisplayValue() {
        return value;
    }

    /**
     * Strings do not have a numeric value.
     * Returns 0.0 so formulas referencing a string cell produce 0.
     */
    @Override
    public double getNumericValue() {
        return 0.0;
    }
}