package app;

import command.Command;
import command.CommandFactory;
import exception.TableException;

import java.util.Scanner;

/**
 * The main console loop of the application.
 * Reads user input, parses it, finds the right command and executes it.
 *
 * Follows SRP — only responsible for the input/output loop.
 * Command creation is delegated to CommandFactory.
 * Input parsing is delegated to CommandParser.
 */
public class ConsoleInterface {

    private final ApplicationSession session;
    private final CommandParser commandParser;
    private final CommandFactory commandFactory;
    private final Scanner scanner;

    public ConsoleInterface() {
        this.session = ApplicationSession.getInstance();
        this.commandParser = new CommandParser();
        this.commandFactory = new CommandFactory(session);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the application loop.
     * Keeps reading input until the user types "exit".
     */
    public void start() {
        System.out.println("Spreadsheet Application");
        System.out.println("Commands: open, close, save, saveas, print, edit, exit");
        System.out.println("-------------------------------------------");

        while (session.isRunning()) {
            System.out.print("> ");
            String input = scanner.nextLine();

            CommandParser.ParsedInput parsed = commandParser.parse(input);
            if (parsed == null) {
                continue;
            }

            Command command = commandFactory.createCommand(parsed.commandName);
            if (command == null) {
                System.out.println("Unknown command: " + parsed.commandName);
                continue;
            }

            try {
                command.execute(parsed.args);
            } catch (TableException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}