package parser;

import model.cell.*;

/**
 * Concrete implementation of CellFactory.
 * Decides which Cell subtype to instantiate based on the raw string value.
 *
 * Rules:
 *  - Starts with '='          → FormulaCell
 *  - Wrapped in double quotes → StringCell  ("hello")
 *  - Contains a '.'           → DoubleCell  (3.14)
 *  - Otherwise parseable int  → IntegerCell (42)
 *  - Anything else            → StringCell  (fallback)
 */
public class ConcreteCellFactory implements CellFactory {

    @Override
    public Cell createCell(String rawValue) {
        if (rawValue == null) {
            return new StringCell("");
        }

        String trimmed = rawValue.trim();

        // Formula cell
        if (trimmed.startsWith("=")) {
            return new FormulaCell(trimmed);
        }

        // String cell
        if (trimmed.startsWith("\"") && trimmed.endsWith("\"")) {
            String content = trimmed.substring(1, trimmed.length() - 1);
            return new StringCell(content);
        }

        // Double cell
        if (trimmed.contains(".")) {
            try {
                double value = Double.parseDouble(trimmed);
                return new DoubleCell(value);
            } catch (NumberFormatException e) {
                return new StringCell(trimmed);
            }
        }

        // Integer cell
        try {
            int value = Integer.parseInt(trimmed);
            return new IntegerCell(value);
        } catch (NumberFormatException e) {
            // Fallback — treat as plain string (without quotes)
            return new StringCell(trimmed);
        }
    }
}