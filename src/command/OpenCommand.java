package command;

import app.ApplicationSession;
import exception.FileAlreadyOpenException;
import exception.TableException;
import file.TableFileReader;
import model.Table;

import java.io.File;

public class OpenCommand implements Command {

    private final ApplicationSession session;
    private final TableFileReader reader;

    public OpenCommand(ApplicationSession session) {
        this.session = session;
        this.reader = new TableFileReader();
    }

    @Override
    public String execute(String[] args) throws TableException {
        if (args.length < 1) {
            return "Usage: open <filepath>";
        }

        if (session.isFileOpen()) {
            throw new FileAlreadyOpenException(session.getCurrentFilePath());
        }

        String filePath = args[0];
        File file = new File(filePath);

        Table table;
        if (file.exists()) {
            table = reader.read(filePath);
        } else {
            table = new Table(filePath);
        }

        session.setCurrentTable(table);
        session.setCurrentFilePath(filePath);

        if (file.exists()) {
            return "Successfully opened " + filePath;
        } else {
            return "File does not exist. Created empty table: " + filePath;
        }
    }
}