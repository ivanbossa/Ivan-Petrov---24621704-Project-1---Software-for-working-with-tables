package command;

import app.ApplicationSession;
import exception.FileNotOpenException;
import exception.TableException;

public class CloseCommand implements Command {

    private final ApplicationSession session;

    public CloseCommand(ApplicationSession session) {
        this.session = session;
    }

    @Override
    public String execute(String[] args) throws TableException {
        if (!session.isFileOpen()) {
            throw new FileNotOpenException();
        }

        String filePath = session.getCurrentFilePath();
        session.setCurrentTable(null);
        session.setCurrentFilePath(null);

        return "Successfully closed " + filePath;
    }
}