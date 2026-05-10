package command;

import app.ApplicationSession;
import exception.FileNotOpenException;
import exception.TableException;
import file.TableFileWriter;

public class SaveAsCommand implements Command {

    private final ApplicationSession session;
    private final TableFileWriter writer;

    public SaveAsCommand(ApplicationSession session) {
        this.session = session;
        this.writer = new TableFileWriter();
    }

    @Override
    public String execute(String[] args) throws TableException {
        if (!session.isFileOpen()) {
            throw new FileNotOpenException();
        }

        if (args.length < 1) {
            return "Usage: saveas <filepath>";
        }

        String newFilePath = args[0];
        writer.write(session.getCurrentTable(), newFilePath);

        session.setCurrentFilePath(newFilePath);
        session.getCurrentTable().setFilePath(newFilePath);

        return "Successfully saved as " + newFilePath;
    }
}