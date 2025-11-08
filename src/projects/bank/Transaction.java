/* TODO/comments
 * Validate id and amount in constructor
 * you can simplify the transaction type logic in createTransaction
     - use TransactionTypeEnum.valueOf to figure out what type of transaction to return. Just convert tokenID to uppercase first.
     - use a simple if-else pattern to return a withdrawal or a deposit
     - don't return null! your valueOf call will throw on your behalf if an invalid transaction type is passed
     - NB: if you return null here, then you'll have to worry about your `transaction == null` in all downstream code. that will be so annoying
 */
package projects.bank;

public abstract class Transaction {
    // Declarations
    private String accountID;
    private double transactionAmount;

    // Constructor
    /**
     * @param id     account ID.
     * @param amount amount deposited or withdrawn.
     */
    protected Transaction(String id, double amount) {
        accountID = id;
        transactionAmount = amount;
    }

    // Return Methods
    /**
     * 
     * @return The account ID associated with the transaction.
     * 
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * 
     * @return The amount of money associated with the transaction.
     * 
     */
    public double getTransactionAmount() {
        return transactionAmount;
    }

    // Abstract methods
    /**
     * Executes a transaction, credit for deposit or debit for withdrawal.
     * 
     * @param account The account to add (credit) or remove (debit) funds from.
     */
    abstract void execute(Account account, Audit audit);

    abstract boolean validate(Account account);
    //TODO Validate method isnt made or used anywhere.

    // Transaction Factory
    /**
     * Creates a transaction from a given CSV string in the format: transaction
     * type, account ID, amount to change.
     * 
     * @param transactionLine a CSV line as a string.
     * @see tokenType Transaction Type (withdrawal/deposit)
     * @see tokenID Account ID (String)
     * @see tokenAmount Amount to be changed (double)
     */
    public static Transaction createTransaction(String transactionLine) {
        if (transactionLine != null) {
            // Tokens Array
            String[] tokens = transactionLine.split(",");
            // Tokens
            String tokenType = tokens[0];
            String tokenID = tokens[1];
            double tokenAmount;

            // Validation against bad third [2] token
            try {
                tokenAmount = Double.valueOf(tokens[2]);
            } catch (Exception e) {
                
                return null;
            }
            if (tokenAmount < 0) {
                return null;
            }

            switch (tokenType.toLowerCase()) {
                case "withdrawal":
                    return new WithdrawalTransaction(tokenID, tokenAmount);
                case "deposit":
                    return new DepositTransaction(tokenID, tokenAmount);
                default: // Validation against bad first [0] token
                    return null;
            }
        }
        return null;
    }
}
