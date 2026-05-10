package command;

import exception.TableException;

/**
 * Interface for all commands.
 * Each command returns a String result to be printed by the caller (Main).
 * No System.out allowed inside command implementations.
 */
public interface Command {

    /**
     * Executes the command and returns a result message.
     *
     * @param args arguments passed by the user
     * @return result message to be displayed
     * @throws TableException if something goes wrong
     */
    String execute(String[] args) throws TableException;
}