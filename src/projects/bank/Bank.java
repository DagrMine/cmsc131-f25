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

                        //Add and remove accounts functions
//TODO copy account array into a new and bigger account array when the first array is full.
    /** Adds an account to the bank at the next empty index in the array and returns true if it succeeds.
     * Returns false if unable to add more accounts.
     * @param newAccount
    */
    public boolean addAccount(Account newAccount){
        for(int i= 0; i < bankAccounts.length; i++){
            if (bankAccounts[i] == null){
                bankAccounts[i] = newAccount;
                numberOfAccounts++;
                return true;
            }
        }
        System.arraycopy(newAccount, numberOfAccounts, newAccount, numberOfAccounts, numberOfAccounts);
        System.out.println("There are no available account slots. Please change internally later.");
        return false;
    }
    //Still not necessary buuuuuuuuut I feel it would be incomplete without it. 
    //Also I made a way for the add account to adapt for this already and would be sad if I didnt get the chance to see if it works.
    /** Removes an account from the bank array using a given array index value.
     * @param index an index value belonging to a bank object that defines an account.
     */
    public void removeAccountByIndex(int index){
        bankAccounts[index] = null;
        numberOfAccounts--;
    }
    /** Removes all accounts from the bank object with a given name.
     * @param accountName Name of the accounts to be removed as a string.
     */
    public void removeAccountsByName(String accountName){
        for (int i = 0; i < numberOfAccounts; i++) {
            if (bankAccounts[i].getName().equals(accountName)) {
            bankAccounts[i] = null;
            numberOfAccounts--;
        }
    }
    }
    /**Count the number of accounts
     * 
    */
    public int countAccounts() {
        return numberOfAccounts;
    }

//Search Functions

    /** Input an account ID to return a bank account.
     * @param accountID the account ID as a string. Returns null if none is found.
    */
    public Account getAccountByID(String accountID) {
        for (int i = 0; i < bankAccounts.length; i++) {
            if (bankAccounts[i].getID().equals(accountID)) {
                return bankAccounts[i];
            }
        } return null;
/*
        for (int i = 0; (BankAccounts[i]).AccountID != accountID; i++){
        if((BankAccounts[i]).AccountID == accountID){
        return BankAccounts[i];
        }
        }
*/
    }
    /**
     * Input an account ID to return an account number/account index
    * @param accountID
    */
    public int getAccountIndexById(String accountID) {
        // TODO need only loop up to numberOfAccounts
        // TODO BUG: throws NullPointerException when numberOfAccounts == 0
        for (int i = 0; i < bankAccounts.length; i++) {
            try {
                if (bankAccounts[i].getID() == accountID) {
                return i;
            }
            } catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("TODO resolve bug");
            }
        } return -1;
    }
    /** 
     * Returns an account for the given array index number
     * @param accountIndex
    */
    public Account getAccountByIndex(int accountIndex){
        return bankAccounts[accountIndex];
    }

    // added as the course notes recommend adding this method though it is not required. It's also fun and might be useful later.
    /**Gets the array index values for a given name and returns them as a string separated by commas (,). If no accounts are found, it returns null.
     * @param accountName Name of the accounts to search for.
     */
    public String getAccountsByNameString(String accountName) {
        String accountNameIndexes = "";
        for (int i = 0; i < numberOfAccounts; i++) {
            if (bankAccounts[i].getName().equals(accountName)) {
                accountNameIndexes = accountNameIndexes+i+",";
            }
        }
        if (accountNameIndexes == "") {
            return null;            
        } else return accountNameIndexes;
    }
    /**Gets the accounts under a given name and returns them as an array beginning at index 0. If no accounts are found, it returns null.
     * @param accountName Name of the accounts to search for.
     */
    public Account[] getAccountsByNameArray(String accountName){
        //Declarations
        Account[] accounts = new Account[numberOfAccounts];
        //Counter for the returned array
        int a = 0;
        //Function
        for (int i = 0; i < numberOfAccounts; i++) {
            if (bankAccounts[i].getName().equals(accountName)) {
                accounts[a] = bankAccounts[i];
                a++;
            }
        }
        //Null validation
        if (accounts[0].equals(null)) {
            return null;
        } else return accounts;
    }
    /**Loads accounts into a bank array object from a CSV file.
     * @param fileName Name of the CSV file as a String.
     */
    public void loadAccounts(String fileName){
        //Declarations
        File inputFile = new File(fileName);
        String thisLine;
        Account accountLine;
        int i=0;
        //Function
        try(Scanner scanFile = new Scanner(inputFile)){
            while (scanFile.hasNextLine()){
                thisLine = scanFile.nextLine();
                accountLine = Account.CSVToAccount(thisLine);
                bankAccounts[i] = accountLine;
                //increases account counter for use in the countAccounts function
                numberOfAccounts++;
                //Move to the next array position
                i++;
            }
        } //Error Catching
        catch(FileNotFoundException E) {
            E.printStackTrace();
        }
    }
    /** Writes accounts from a bank array object to a CSV file
     * @param fileName Name of the CSV file as a String.
    */
    public void writeAccounts(String fileName){
        try(FileWriter writer = new FileWriter(fileName)){
            for(int i = 0; i < bankAccounts.length; i++){
                if (bankAccounts[i] != null){
                    String unParsed = bankAccounts[i].accountToCSV(bankAccounts[i]);
                    writer.write(unParsed+"\n");
                }
            }
        } catch(IOException E) {
            E.printStackTrace();
        }
    }
}
