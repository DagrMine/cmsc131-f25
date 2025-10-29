package projects.bank;

public class WithdrawalTransaction extends Transaction{
    public WithdrawalTransaction(String accountID, double transactionAmount){
        super(accountID, transactionAmount);
    }
    @Override
    public void execute(Account account){
        account.credit(transactionAmount);
    }
    public boolean validate(Account account){
        return false;
    }
}
