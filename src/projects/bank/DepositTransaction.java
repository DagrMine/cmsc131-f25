package projects.bank;

public class DepositTransaction extends Transaction{
    public DepositTransaction(String accountID, double transactionAmount){
        super(accountID,transactionAmount);
    }
    @Override
    public void execute(Account account, Audit audit){
        account.credit(getTransactionAmount());
        audit.write(this, "Deposit successfully executed for: " + getTransactionAmount(), AuditTypeEnum.INFO);
    }
    @Override
    public boolean validate(Account account){
        return false;
    }
}