package command;

import app.ApplicationSession;
import exception.TableException;

/**
 * Central command handler.
 * Parses raw user input, maps it to a CommandType,
 * creates the right Command and returns its String result.
 *
 * All output is returned as String — no System.out here.
 */
public class CommandHandler {

    private final ApplicationSession session;

    public CommandHandler(ApplicationSession session) {
        this.session = session;
    }

    /**
     * Processes a raw input line and returns the result as a String.
     *
     * @param input raw line from the user
     * @return result message to be printed by the caller
     * @throws TableException if the command fails
     */
    public String processInput(String input) throws TableException {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }

        String trimmed = input.trim();
        String[] tokens = trimmed.split("\\s+", 4);

        CommandType type = parseCommandType(tokens[0]);
        String[] args = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, args, 0, args.length);

        Command command = createCommand(type);

        if (command == null) {
            return "Unknown command: " + tokens[0] + ". Type 'help' for available commands.";
        }

        return command.execute(args);
    }

    /**
     * Maps a command name string to a CommandType enum value.
     */
    private CommandType parseCommandType(String name) {
        switch (name.toLowerCase()) {
            case "open":    return CommandType.OPEN;
            case "close":   return CommandType.CLOSE;
            case "save":    return CommandType.SAVE;
            case "saveas":  return CommandType.SAVEAS;
            case "print":   return CommandType.PRINT;
            case "edit":    return CommandType.EDIT;
            case "help":    return CommandType.HELP;
            case "exit":    return CommandType.EXIT;
            default:        return CommandType.UNKNOWN;
        }
    }

    /**
     * Creates the correct Command object based on the CommandType.
     */
    private Command createCommand(CommandType type) {
        switch (type) {
            case OPEN:    return new OpenCommand(session);
            case CLOSE:   return new CloseCommand(session);
            case SAVE:    return new SaveCommand(session);
            case SAVEAS:  return new SaveAsCommand(session);
            case PRINT:   return new PrintCommand(session);
            case EDIT:    return new EditCommand(session);
            case HELP:    return new HelpCommand();
            case EXIT:    return new ExitCommand(session);
            default:      return null;
        }
    }
}