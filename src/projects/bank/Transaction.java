package projects.bank;

public abstract class Transaction {
    // Declarations
    protected String accountID;
    protected double transactionAmount;

    // Constructor
    /**
     * @param ID     account ID.
     * @param amount amount deposited or withdrawn.
     */
    protected Transaction(String ID, double amount) {
        accountID = ID;
        transactionAmount = amount;
    }

    // Return Methods
    public String getAccountID() {
        return accountID;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    // Abstract methods
    /** Executes a transaction, credit for deposit or debit for withdrawal.
     * @param account The account to add (credit) or remove (debit) funds from.
     */
    abstract void execute(Account account);

    abstract boolean validate(Account account);

    // Transaction Factory
    /**Creates a transaction from a given CSV string in the format: transaction type, account ID, amount to change.
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
