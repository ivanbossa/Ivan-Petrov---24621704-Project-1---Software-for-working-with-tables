package app;

import model.Table;

/**
 * Holds the current state of the application.
 * Follows the Singleton Pattern — only one session can exist at a time.
 *
 * Stores:
 *  - the currently open Table
 *  - the current file path
 *  - whether the application is still running
 */
public class ApplicationSession {

    private static ApplicationSession instance;

    private Table currentTable;
    private String currentFilePath;
    private boolean running;

    private ApplicationSession() {
        this.running = true;
    }

    /**
     * Returns the single instance of ApplicationSession.
     */
    public static ApplicationSession getInstance() {
        if (instance == null) {
            instance = new ApplicationSession();
        }
        return instance;
    }

    public boolean isFileOpen() {
        return currentTable != null;
    }

    public Table getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(Table currentTable) {
        this.currentTable = currentTable;
    }

    public String getCurrentFilePath() {
        return currentFilePath;
    }

    public void setCurrentFilePath(String currentFilePath) {
        this.currentFilePath = currentFilePath;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}