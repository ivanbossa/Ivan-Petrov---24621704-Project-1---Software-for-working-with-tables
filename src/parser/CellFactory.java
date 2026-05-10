package parser;

import model.cell.Cell;

/**
 * Factory interface for creating Cell objects from raw string values.
 * Follows the Factory Pattern and ISP (Interface Segregation Principle).
 */
public interface CellFactory {

    /**
     * Creates and returns the appropriate Cell subtype
     * based on the raw string content read from the file.
     *
     * @param rawValue the raw string from the .table file ("42", "3.14", "\"hello\"", "=R1C1+R2C2")
     * @return a Cell of the correct type
     */
    Cell createCell(String rawValue);
}
