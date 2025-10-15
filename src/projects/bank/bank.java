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

//Modify Functions

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
        System.out.println("There are no available account slots.");
        return false;
    }
    //not necessary for now
    //public boolean removeAccountByIndex(){

    //}
    //Count the number of accounts
    //TODO change to adapt for values removed from the array.
    public int countAccounts() {
        return numberOfAccounts;
    }

//Search Functions

    /** Input an account ID to return a bank account.
     * @param
    */
    public Account getAccountByID(String accountID) {
        for (int i = 0; i < bankAccounts.length; i++) {
            if (bankAccounts[i].getID() == accountID) {
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
        for (int i = 0; i < bankAccounts.length; i++) {
            if (bankAccounts[i].getID() == accountID) {
                return i;
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

    // added as the course notes recommend adding this method though it is not required
    //TODO maybe return an array of accounts instead of a string of indexes separated by commas
    public String getAccountsByName(String accountName) {
        String accountNameIndexes = "";
        for (int i = 0; i < numberOfAccounts; i++) {
            if (bankAccounts[i].getName() == accountName) {
                accountNameIndexes = accountNameIndexes+i+",";
            }
        }
        if (accountNameIndexes == "") {
            return null;            
        } else return accountNameIndexes;
    }
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
                //Testing to see why its breaking
                System.out.println(bankAccounts[i]);
                System.out.println(i);
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
