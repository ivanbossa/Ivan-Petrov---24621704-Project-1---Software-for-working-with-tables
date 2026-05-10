package app;

/**
 * Parses a raw line of user input into a command name and its arguments.
 *
 * Example:
 *   Input:  "edit 2 3 =R1C1+R1C2"
 *   Result: commandName = "edit"
 *   args = ["2", "3", "=R1C1+R1C2"]
 *
 * Follows SRP — only responsible for splitting input, nothing else.
 */
public class CommandParser {

    /**
     * Holds the result of parsing one line of input.
     */
    public static class ParsedInput {
        public final String commandName;
        public final String[] args;

        public ParsedInput(String commandName, String[] args) {
            this.commandName = commandName;
            this.args = args;
        }
    }

    /**
     * Parses a raw input line into a ParsedInput.
     *
     * @param input the raw line typed by the user
     * @return ParsedInput with commandName and args, or null if input is blank
     */
    public ParsedInput parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        String trimmed = input.trim();

        // Split on whitespace and keep last input bez da otrqzva
        String[] tokens = trimmed.split("\\s+", 4);

        String commandName = tokens[0];
        String[] args = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, args, 0, args.length);

        return new ParsedInput(commandName, args);
    }
}