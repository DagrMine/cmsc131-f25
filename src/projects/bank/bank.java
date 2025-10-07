package projects.bank;

public class Bank {
    private Account[] bankAccounts;
    private int numberOfAccounts = 0;
    
    public Bank() {
        bankAccounts = new Account[100];
    }

//Modify Functions

    //Adds an account to the bank at the next empty index in the array and returns true if it succeeds.
    //@param newAccount
    public boolean addAccount(Account newAccount){
        for(int i= 0; i < bankAccounts.length; i++){
            if (bankAccounts[i] == null){
                bankAccounts[i] = newAccount;
                numberOfAccounts++;
                return true;
            }
        }
        //TODO automatically create a new array with a much larger size if the bankAccounts array is full.
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

    //Input an account ID to return a bank account.
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
    //Input an account ID to return an account number/account index
    public int getAccountIndexById(String accountID) {
        for (int i = 0; i < bankAccounts.length; i++) {
            if (bankAccounts[i].getID() == accountID) {
                return i;
            }
        } return -1;
    }
    //Returns an account for the given array index number
    public Account getAccountByIndex(int accountIndex){
        return bankAccounts[accountIndex];
    }

    // added as the course notes recommend adding this method though it is not required
    //TODO modify for one owner of multiple accounts
    public Account getAccountsByName(String accountName) {
        for (int i = 0; i < bankAccounts.length; i++) {
            if (bankAccounts[i].getName() == accountName) {
                return bankAccounts[i];
            }
        } return null;
    }
}
