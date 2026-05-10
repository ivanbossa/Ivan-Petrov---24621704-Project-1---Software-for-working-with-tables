package command;

import app.ApplicationSession;

/**
 * Factory that maps a command name string to the correct Command object.
 * Follows the Factory Pattern — the caller doesn't need to know which
 * Command subclass to instantiate.
 *
 * Supported commands: open, close, save, saveas, print, edit, exit
 */
public class CommandFactory {

    private final ApplicationSession session;

    public CommandFactory(ApplicationSession session) {
        this.session = session;
    }

    /**
     * Returns the Command object corresponding to the given command name.
     *
     * @param commandName the name typed by the user (case-insensitive)
     * @return the matching Command, or null if unknown
     */
    public Command createCommand(String commandName) {
        if (commandName == null) return null;

        switch (commandName.toLowerCase()) {
            case "open":    return new OpenCommand(session);
            case "close":   return new CloseCommand(session);
            case "save":    return new SaveCommand(session);
            case "save as":  return new SaveAsCommand(session);
            case "print":   return new PrintCommand(session);
            case "edit":    return new EditCommand(session);
            case "exit":    return new ExitCommand(session);
            default:        return null;

            //vmesto stringove da e case command type open - da ima commandhandler i enum s komandite
            // da sloja help command - i da nqma system out nqkude drugade vmesto v main-a
        }
    }
}
