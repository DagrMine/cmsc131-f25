package projects.bank;

public class DepositTransaction extends Transaction{
    public DepositTransaction(String accountID, double transactionAmount){
        super(accountID,transactionAmount);
    }
    @Override
    public void execute(Account account){
        account.debit(transactionAmount);
    }
    public boolean validate(Account account){
        return false;
    }
}