package projects.bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Audit {
    // Declarations
    FileWriter auditWriter;
    File fileFile;

    // Initializer
    /**
     * Initializes the audit object with a given file name.
     * 
     * @return False if the initialization fails.
     *         True if the initialization succeeds.
     */
    public boolean open(String fileName) {
        if (fileName == null) {
            return false;
        }
        fileFile = new File(fileName);
        try {
            auditWriter = new FileWriter(fileFile);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Closes the audit object.
     * 
     * @return false if the closing fails.
     *         True if it succeeds.
     * 
     */
    public boolean close() {
        try {
            auditWriter.close();
            auditWriter = null;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // Private Methods
    // Another way to do this is by making a method that instead converts the enum
    // to a string. Idk if thats more efficient or not honestly.
    //
    // Currently Unused
    /**
     * Takes an AuditTypeEnum type and returns text that corresponds to each typing.
     * 
     * @param infoType INFO, WARN, ERROR, ALERT
     * @return The type surrounded by | as a string. Null value returns |TYPE: NULL|
     * 
     */
    private String auditType(AuditTypeEnum infoType) {
        switch (infoType) {
            case INFO:
                return "|INFO|";

            case WARN:
                return "|WARN|";

            case ERROR:
                return "|ERROR|";

            case ALERT:
                return "|ALERT|";

            default:
                return "|TYPE: NULL|";
        }
    }

    /**
     * Writes a file. Literally just an error catcher.
     * 
     * @param s String to write.
     * @return False if an IOException error is thrown. True otherwise.
     * 
     */
    private boolean writeFile(String s) {
        try {
            auditWriter.write(s);
        } catch (IOException e) {
            return false;
            // TODO Handle exception
            // Can't print to log but also dont want to throw.
        }
        return true;
    }

    /*
     * Write Methods
     * In Order:
     * General purpose audit
     * Account audit
     * Transaction audit
     * 
     */

    /**
     * General purpose audit
     * 
     * @param textInfo String of information to accompany the log/audit
     * @param infoType Type of information to write using AuditTypeEnum
     * @see AuditTypeEnum
     */
    public boolean write(String textInfo, AuditTypeEnum infoType) {
        // writeFile(auditType(infoType) + textInfo);
        return writeFile(String.format("|%s| %s%n",
                infoType,
                textInfo));
    }

    /**
     * Audit an account
     * 
     * @param account  Account to be audited
     * @param textInfo String of information to accompany the log/audit
     * @param infoType Type of information to write using AuditTypeEnum
     * @see AuditTypeEnum
     * @see Account
     */
    public boolean write(Account account, String textInfo, AuditTypeEnum infoType) {
        String accountID;
        if (account == null) {
            accountID = "Null Account Error";
        } else
            accountID = account.getID();
        // String typeString = auditType(infoType);
        // Old writeFile(typeString + textInfo + " Account ID: " + account.getID());
        return writeFile(String.format("|%s| %s |Account ID: %s|%n",
                infoType,
                textInfo,
                accountID));
    }

    /**
     * Audit a transaction
     * 
     * @param transaction Transaction to be audited
     * @param textInfo    String of information to accompany the log/audit
     * @param infoType    Type of information to write using AuditTypeEnum
     * @see AuditTypeEnum
     * @see Transaction
     */
    public boolean write(Transaction transaction, String textInfo, AuditTypeEnum infoType) {
        String transactionID;
        String logMessage;
        if (transaction == null) {
            transactionID = "Null Transaction Error";
        } else
            transactionID = transaction.getAccountID();
            logMessage = textInfo;
        // Old writeFile(typeString + textInfo + " Transaction ID: " +
        // transaction.getAccountID());
        return writeFile(String.format("|%s| %s |Transaction ID: %s|%n",
                infoType,
                logMessage,
                transactionID));
    }
}
