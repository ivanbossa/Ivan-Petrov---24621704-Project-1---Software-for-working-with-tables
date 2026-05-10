package command;

import app.ApplicationSession;
import exception.TableException;

/**
 * Exits the application.
 * Usage: exit
 *
 * If a file is currently open, reminds the user to save first,
 * then exits regardless.
 */
public class ExitCommand implements Command {

    private final ApplicationSession session;

    public ExitCommand(ApplicationSession session) {
        this.session = session;
    }

    @Override
    public String execute(String[] args) throws TableException {
        String message = "";

        if (session.isFileOpen()) {
            message = "Warning: " + session.getCurrentFilePath()
                    + " is still open. Any unsaved changes will be lost.\n";
        }

        return message + "Exiting the program...";
    }
}

