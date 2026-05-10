import app.ApplicationSession;
import command.CommandHandler;
import exception.TableException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ApplicationSession session = ApplicationSession.getInstance();
        CommandHandler commandHandler = new CommandHandler(session);

        System.out.println("Welcome to Spreadsheet Application. Type 'help' for available commands.");
        System.out.println("-----------------------------------------------------------------------");

        do {
            System.out.print("> ");
            String line = br.readLine().trim();
            String result = "";

            try {
                result = commandHandler.processInput(line);
            } catch (TableException e) {
                System.out.println(e.getMessage());
            }

            System.out.println(result);

            if (result.equals("Exiting the program...") ||
                    result.endsWith("Exiting the program...")) break;

        } while (true);
    }
}