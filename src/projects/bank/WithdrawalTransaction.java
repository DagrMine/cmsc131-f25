/* TODO/comments
 * by having valdate return false no matter what, you're blocking all withdrawals from executing. surely you want to do something differents
 */
package projects.bank;

public class WithdrawalTransaction extends Transaction{
    public WithdrawalTransaction(String accountID, double transactionAmount){
        super(accountID, transactionAmount);
    }
    @Override
    public void execute(Account account){
        account.debit(getTransactionAmount());
    }
    @Override
    public boolean validate(Account account){
        return false;
    }
}
