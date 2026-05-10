package command;

import app.ApplicationSession;
import exception.FileNotOpenException;
import exception.TableException;
import file.TableFileWriter;

public class SaveCommand implements Command {

    private final ApplicationSession session;
    private final TableFileWriter writer;

    public SaveCommand(ApplicationSession session) {
        this.session = session;
        this.writer = new TableFileWriter();
    }

    @Override
    public String execute(String[] args) throws TableException {
        if (!session.isFileOpen()) {
            throw new FileNotOpenException();
        }

        String filePath = session.getCurrentFilePath();
        writer.write(session.getCurrentTable(), filePath);

        return "Successfully saved " + filePath;
    }
}