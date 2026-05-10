package command;

import exception.TableException;

public class HelpCommand implements Command {

    @Override
    public String execute(String[] args) throws TableException {
        return  "Available commands:\n" +
                "  open <filepath>          - Open a .table file\n" +
                "  close                    - Close the currently open file\n" +
                "  save                     - Save the current file\n" +
                "  saveas <filepath>        - Save the current file to a new path\n" +
                "  print                    - Print the table to the console\n" +
                "  edit <row> <col> <value> - Edit a cell (value can be number, \"string\" or =R1C1+R2C2)\n" +
                "  help                     - Show this help message\n" +
                "  exit                     - Exit the application";
    }
}