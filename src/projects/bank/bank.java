package projects.bank;

public class Bank {
    private Account[] bankAccounts;
    private int numberOfAccounts = 0;
    
    public Bank() {
        bankAccounts = new Account[100];
    }

    public void addAccount(Account newAccount){
        //BankAccounts.set;

        numberOfAccounts++;
    }
    //Count the number of accounts
    public int countAccounts() {
        for(int i= 0; i < bankAccounts.length; i++){
            if (bankAccounts[i] == null){
                return i+1;
            }
        } System.out.println("Accounts are at maximum");
        return bankAccounts.length;
    }
    //Input and account ID to return a bank account.
    public Account accountsByID(String accountID) {
        for (int i = 0; i < bankAccounts.length; i++) {
            if (bankAccounts[i].getID() == accountID) {
                return bankAccounts[i];
            }
        } return null;

        // for (int i = 0; (BankAccounts[i]).AccountID != accountID; i++){
        // if((BankAccounts[i]).AccountID == accountID){
        // return BankAccounts[i];
        // }
        // }
    }
    //Input an account ID to return an account number/account index
    public int getAccountIndexById(String accountID) {
        for (int i = 0; i < bankAccounts.length; i++) {
            if (bankAccounts[i].getID() == accountID) {
                return i;
            }
        } return -1;
    }

    // added as the course notes recommend adding this method though it is not required
    public Account accountsByName(String accountName) {
        for (int i = 0; i < bankAccounts.length; i++) {
            if (bankAccounts[i].getName() == accountName) {
                return bankAccounts[i];
            }
        } return null;
    }
}
