/** TODO / comments on processTransactions
 * your index value could be -1, which will throw an ArrayIndexOutOfBoundsException. you need to correct you code to handle this situation. currently it looks like you're assuming that bankAccounts[-1] == null but that's not how array indexing works in Java
 * again, if you block accounts and transactions from being null, your code will be cleaner
 * you aren't using your transactions' validate method. type CTRL+F and search for .validate to see for yourself. red flag! 
 * it's better to return an int that represets how many transactions were processed. returning a boolean is like saying "zero transactions processed" (false) or "at least one transaction was processed" (true). more precision makes the code more testable!
 */
package projects.bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Bank {
    private Account[] bankAccounts;
    private int numberOfAccounts = 0;

    public Bank() {
        bankAccounts = new Account[1000];
    }

    // Made into a private method because I get the feeling I might want to use it
    // later
    //TODO Test reinitializing of bank
    /**Re-initializes the current bank object with 1000 more account spaces.
     * Used in the addAccount and loadAccounts methods
     */
    private void reInitializeBank() {
        Account[] tempBank = new Account[bankAccounts.length];
        System.arraycopy(bankAccounts, 0, tempBank, 0, bankAccounts.length);
        bankAccounts = new Account[tempBank.length + 1000];
        bankAccounts = tempBank;
    }

    // Add and remove accounts functions
    // Done copy account array into a new and bigger account array when the first
    // array is full.
    /**
     * Adds an account to the bank at the next empty index in the array and returns
     * true if it succeeds.
     * Returns false if unable to add more accounts.
     * 
     * @param newAccount
     */
    public boolean addAccount(Account newAccount) {
        // Null validation
        if (newAccount == null) {
            return false;
        }
        // Validates the last bank spot is open.
        // If the bank is full it reinitializes the bank with a larger size.
        if (bankAccounts[bankAccounts.length - 1] != null) {
            reInitializeBank();
        }
        for (int i = 0; i < bankAccounts.length; i++) {
            if (bankAccounts[i] == null) {
                bankAccounts[i] = newAccount;
                numberOfAccounts++;
                return true;
            }
        }
        // TODO Print unique message to audit log to change the bank size internally.
        System.out.println("There are no available account slots. Please change internally later.");
        return true;
    }

    // Still not necessary buuuuuuuuut I feel it would be incomplete without it.
    // Also I made a way for the add account to adapt for this already and would be
    // sad if I didnt get the chance to see if it works.
    /**
     * Removes an account from the bank array using a given array index value.
     * 
     * @param index an index value belonging to a bank object that defines an
     *              account.
     */
    public void removeAccountByIndex(int index) {
        bankAccounts[index] = null;
        numberOfAccounts--;
    }

    /**
     * Removes all accounts from the bank object with a given name.
     * 
     * @param accountName Name of the accounts to be removed as a string.
     */
    public void removeAccountsByName(String accountName) {
        int numberOfAccountsStatic = numberOfAccounts;
        for (int i = 0; i < numberOfAccountsStatic; i++) {
            if (bankAccounts[i].getName().equals(accountName)) {
                bankAccounts[i] = null;
                numberOfAccounts--;
            }
        }
    }

    /**
     * Count the number of accounts
     * Literally just returns the number of accounts. The counting is done by add
     * and remove functions. May cause issues if something goes wrong.
     */
    public int countAccounts() {
        return numberOfAccounts;
    }

    // Search Functions

    /**
     * Input an account ID to return a bank account.
     * 
     * @param accountID the account ID as a string. Returns null if none is found.
     */
    public Account getAccountByID(String accountID) {
        for (int i = 0; i < bankAccounts.length; i++) {
            if (bankAccounts[i].getID().equals(accountID)) {
                return bankAccounts[i];
            }
        }
        return null;
        /*
         * for (int i = 0; (BankAccounts[i]).AccountID != accountID; i++){
         * if((BankAccounts[i]).AccountID == accountID){
         * return BankAccounts[i];
         * }
         * }
         */
    }

    /**
     * Input an account ID to return an account number/account index
     * 
     * @param accountID
     */
    public int getAccountIndexById(String accountID) {
        if (numberOfAccounts != 0) {
            for (int i = 0; i < numberOfAccounts; i++) {
                if (bankAccounts[i].getID().equals(accountID)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns an account for the given array index number
     * 
     * @param accountIndex
     */
    public Account getAccountByIndex(int accountIndex) {
        return bankAccounts[accountIndex];
    }

    // added as the course notes recommend adding this method though it is not
    // required. It's also fun and might be useful later.
    /**
     * Gets the array index values for a given name and returns them as a string
     * separated by commas (,). If no accounts are found, it returns null.
     * 
     * @param accountName Name of the accounts to search for.
     */
    public String getAccountsByNameString(String accountName) {
        String accountNameIndexes = "";
        for (int i = 0; i < numberOfAccounts; i++) {
            if (bankAccounts[i].getName().equals(accountName)) {
                accountNameIndexes = accountNameIndexes + i + ",";
            }
        }
        if (accountNameIndexes == "") {
            return null;
        } else
            return accountNameIndexes;
    }

    /**
     * Gets the accounts under a given name and returns them as an array beginning
     * at index 0. If no accounts are found, it returns null.
     * 
     * @param accountName Name of the accounts to search for.
     */
    public Account[] getAccountsByNameArray(String accountName) {
        // Declarations
        Account[] accounts = new Account[numberOfAccounts];
        // Counter for the returned array
        int a = 0;
        // Function
        for (int i = 0; i < numberOfAccounts; i++) {
            if (bankAccounts[i].getName().equals(accountName)) {
                accounts[a] = bankAccounts[i];
                a++;
            }
        }
        // Null validation
        if (accounts[0].equals(null)) {
            return null;
        } else
            return accounts;
    }

    // Bank to CSV methods

    /**
     * Loads accounts into a bank array object from a CSV file.
     * 
     * @param fileName Name of the CSV file as a String.
     */
    public boolean loadAccounts(String fileName) {
        // Null validation
        if (fileName == null) {
            return false;
        }

        // Declarations
        File inputFile = new File(fileName);
        int i = 0;

        // Function
        try (Scanner scanFile = new Scanner(inputFile)) {
            while (scanFile.hasNextLine()) {
                //TODO Test reInitializeBank of loadAccounts method
                if (numberOfAccounts == bankAccounts.length){
                    reInitializeBank();
                }
                bankAccounts[i] = Account.csvToAccount(scanFile.nextLine());
                // increases account counter for use in the countAccounts function
                numberOfAccounts++;
                // Move to the next array position
                i++;
                // TODO end - what do you mean by this? Did you write this or did VS?
            }
            return true;

        } // Error Catching
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Writes accounts from a bank array object to a CSV file
     * 
     * @param fileName Name of the CSV file as a String.
     */
    public boolean writeAccounts(String fileName) {

        // Null validation
        if (fileName == null) {
            return false;
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < bankAccounts.length; i++) {
                if (bankAccounts[i] != null) {
                    String bankLine = bankAccounts[i].accountToCSV(bankAccounts[i]);
                    writer.write(bankLine + System.lineSeparator());
                }
            }
            return true;

        } catch (IOException e) {// Bad fileName validation
            e.printStackTrace();
            return false;
        }
    }

    // Transactions
    /**
     * Processes and executes transactions from a CSV file.
     * 
     * @param inputFile the name of the CSV file given as a string.
     */
    public boolean processTransactions(String fileName) {
        File inputFile = new File(fileName);
        try (Scanner scanFile = new Scanner(inputFile)) {
            // Declarations
            Account modAccount;
            Transaction trans;
            int index;

            while (scanFile.hasNextLine()) {
                trans = Transaction.createTransaction(scanFile.nextLine());
                index = getAccountIndexById(trans.getAccountID());
                modAccount = bankAccounts[index];
                // Null validation and missing account validation
                if (trans != null && modAccount != null) {
                    trans.execute(modAccount);
                    bankAccounts[index] = modAccount;
                    // TODO Audit phase needs to register that the account WAS found.
                } // TODO audit phase needs to register that the account was NOT found or the
                  // transaction was invalid.
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
            // TODO: handle exception
        }
    }
}
