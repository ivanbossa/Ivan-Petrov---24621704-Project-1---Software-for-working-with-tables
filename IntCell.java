
public class IntCell extends Cell {
    private int value;

    public IntCell(String rawValue) {
        super(rawValue);
        this.value = Integer.parseInt(rawValue.trim());
    }

    @Override
    public String getDisplayValue() {
        return String.valueOf(value);
    }

    @Override
    public double getNumericalValue() {
        return (double) value;
    }
}