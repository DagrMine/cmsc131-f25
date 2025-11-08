/* TODO/comments
 * by having valdate return false no matter what, you're blocking all withdrawals from executing. surely you want to do something differents
 * 
 * adding to this I have no idea what the validate method is for I just added it because of the example.
 * TODO move validation to validate method in transaction subclasses
 */
package projects.bank;

public class WithdrawalTransaction extends Transaction{
    public WithdrawalTransaction(String accountID, double transactionAmount){
        super(accountID, transactionAmount);
    }
    @Override
    public void execute(Account account, Audit audit){
        account.debit(getTransactionAmount());
        audit.write(this, "Withdrawal successfully executed for: " + getTransactionAmount(), AuditTypeEnum.INFO);
    }
    @Override
    public boolean validate(Account account){
        return false;
    }
}
