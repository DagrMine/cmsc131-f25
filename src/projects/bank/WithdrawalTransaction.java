package projects.bank;

public class WithdrawalTransaction extends Transaction{
    /** Withdrawal an amount of money from a bank account. Returns true if the balance is sufficent and the transaction can and does go through.
     * Returns false if otherwise.
     * @param moneyAmount must be a double.
     */
    public boolean withdrawalMoney(double moneyAmount){
        if (moneyAmount <= super.accountBalance){
            super.accountBalance = super.accountBalance - moneyAmount;
            return true;
        } else return false;
    }
}
