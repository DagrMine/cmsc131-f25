package projects.bank;

public class DepositTransaction extends Transaction{
    //TODO Return boolean on a success. Add data validation for.... something. 
    //I can't do null because it errors if a null is input before the method even begins requiring data validation in whatever method uses this one.
    /**Deposit money into a bank account.
     * @param moneyAmount must be a double.
     */
    public void depositMoney(double moneyAmount){
            super.accountBalance = super.accountBalance+moneyAmount;
    }
}
