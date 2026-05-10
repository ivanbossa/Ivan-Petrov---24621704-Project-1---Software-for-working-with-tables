package command;

import app.ApplicationSession;
import exception.FileNotOpenException;
import exception.TableException;
import printer.TablePrinter;

/**
 * Prints the current table to the console with aligned columns.
 * Usage: print
 *
 * Throws FileNotOpenException if no file is currently open.
 */

public class PrintCommand implements Command {

    private final ApplicationSession session;
    private final TablePrinter printer;

    public PrintCommand(ApplicationSession session) {
        this.session = session;
        this.printer = new TablePrinter();
    }

    @Override
    public String execute(String[] args) throws TableException {
        if (!session.isFileOpen()) {
            throw new FileNotOpenException();
        }

        return printer.print(session.getCurrentTable());
    }
}