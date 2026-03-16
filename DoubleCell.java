public class DoubleCell extends Cell {
    private double value;

    public DoubleCell(String rawValue) {
        super(rawValue);
        this.value = Double.parseDouble(rawValue.trim());
    }

    @Override
    public String getDisplayValue() {
        return String.valueOf(value);
    }

    @Override
    public double getNumericalValue() {
        return value;
    }
}