package command;

/**
 * Enum representing all supported commands in the application.
 * Used instead of raw String comparison in the command handler.
 */
public enum CommandType {
    OPEN,
    CLOSE,
    SAVE,
    SAVEAS,
    PRINT,
    EDIT,
    HELP,
    EXIT,
    UNKNOWN
}