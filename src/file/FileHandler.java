package file;

import exception.TableException;
import model.Table;

/**
 * Interface for reading and writing Table objects to/from files.
 * Follows ISP — split into read and write if needed in the future.
 */
public interface FileHandler {

    /**
     * Reads a .table file and returns a Table object.
     *
     * @param filePath path to the .table file
     * @return parsed Table
     * @throws TableException if the file cannot be read or parsed
     */
    Table readTable(String filePath) throws TableException;

    /**
     * Writes a Table object to a file.
     *
     * @param table    the table to save
     * @param filePath destination file path
     * @throws TableException if the file cannot be written
     */
    void writeTable(Table table, String filePath) throws TableException;
}