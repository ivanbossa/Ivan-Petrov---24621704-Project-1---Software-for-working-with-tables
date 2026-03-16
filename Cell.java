/** Абстрактен клас представляващ клетка в таблицата*/
public abstract class Cell {
    protected String rawValue;

    public Cell(String rawValue) {
        this.rawValue = rawValue;
    }

    /** Връща стойността на клетката като текст за принтиране */
    public abstract String getDisplayValue();

    /** Връща числената стойност на клетката, използвана във формули */
    public abstract double getNumericalValue();

    /** Връща оригиналния текст на клетката (за запис във файл) */
    public String getRawValue() {
        return rawValue;
    }
}