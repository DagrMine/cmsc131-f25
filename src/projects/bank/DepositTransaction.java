package projects.bank;

public class DepositTransaction extends Transaction{
    public DepositTransaction(String accountID, double transactionAmount){
        super(accountID,transactionAmount);
    }
    @Override
    public void execute(Account account){
        account.credit(transactionAmount);
    }
    @Override
    public boolean validate(Account account){
        return false;
    }
}