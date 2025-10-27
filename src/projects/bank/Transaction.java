package projects.bank;

public abstract class Transaction {
    //Declarations
    private String accountID;
    protected double accountBalance;
    //Return Methods
    public String getAccountID(){
        return accountID;
    }
    public double getAccountBalance(){
        return accountBalance;
    }
    
}
