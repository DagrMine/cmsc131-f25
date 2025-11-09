package projects.bank;

//Class
public class Account {
    // Declarations
    private String accountID, accountName;
    private double accountBal;
    private AccountTypeEnum accountType;

    /**
     * Stores account information
     * 
     * @param acID   stores account ID information as a String
     * @param acName stores account name information as a String
     * @param acBal  stores account balance information as a Double
     * @param acType stores account typing (CHECKING or SAVINGS) as an Enum
     */
    public Account(String acID, String acName, double acBal, AccountTypeEnum acType) {
        // TODO Test account data validation
        // Literally just "Is any account value null or less than 0"
        if ((acID == null) || (acName == null) || (acBal < 0) || (acType == null)) {
            // TODO may have to change this when we do penalties because it prevents
            // overdraft.
            throw new IllegalArgumentException("Account data cannot be null or less than 0.");
        }
        // Declarations/Assignments
        accountID = acID;
        accountName = acName;
        accountBal = roundBalance(acBal);
        accountType = acType;
    }

    // Private Methods
    // Rounds the balance to two decimal places. Fun fact about Math.round, 100 is
    // different from 100.0
    // [dusel] 100 is an int and 100.0 is a floating point
    // I forgot about floats. Using Double.valueOf is probably better then.
    // Another way of doing this would be:
    // accountBal = Math.round(acBal*100.0)/100.0;
    // But I already did it this way soooo i'll leave it as a knowledge reference
    private double roundBalance(double balanceToRound) {
        return Double.valueOf(Math.round(Double.valueOf(balanceToRound * 100))) / 100;
    }

    // Accessors
    /** Returns the balance of the account object. (Double) */
    public double getBal() {
        return accountBal;
    }

    /** Returns the name of the account object. (String) */
    public String getName() {
        return accountName;
    }

    /** Returns the ID of the account object. (String) */
    public String getID() {
        return accountID;
    }

    /** Returns the Enum Type of the account object. (AccountTypeEnum) */
    public AccountTypeEnum getType() {
        return accountType;
    }

    /**
     * Translates an account in the form of a comma separated string to an account
     * object
     * 
     * @param accountLine input a comma separated string that contains the (in this
     *                    order) typing, ID, name, and balance.
     */
    public static Account csvToAccount(String accountLine) {
        if (accountLine != null) {
            String[] tokens = accountLine.split(",");
            String tokenID = tokens[1];
            String tokenName = tokens[2];
            double tokenBal = Double.valueOf(tokens[3]);
            AccountTypeEnum tokenType = AccountTypeEnum.valueOf(tokens[0].toUpperCase());
            Account returnedAccount = new Account(tokenID, tokenName, tokenBal, tokenType);
            return returnedAccount;
        } else {
            throw new IllegalArgumentException("CSV line cannot be null.");
        }
    }

    /**
     * Translates an account object into a comma separated string.
     * 
     * @param accountLine the account object to be turned into a comma separated
     *                    string.
     */
    public String accountToCSV(Account accountLine) {
        // String type = accountLine.getType().name();
        // It combines the type, converted to a string, to lowercase, then combines it
        // with the rest of the account data as one string separated by commas.
        // String token = type.toLowerCase() + "," + accountLine.getID() + "," +
        // accountLine.getName() + "," + accountLine.getBal();
        String token = String.format("%s,%s,%s,%s",
                accountLine.getType().name().toLowerCase(),
                accountLine.getID(),
                accountLine.getName(),
                accountLine.getBal());
        return token;
    }

    // Credit and Debit methods
    /**
     * Adds an amount of money to a given account.
     * 
     * @param balanceChange Added money as double.
     */
    public void credit(double balanceChange) {
        accountBal = roundBalance(balanceChange + accountBal);
    }

    /**
     * Removes an amount of money from a given account.
     * 
     * @param balanceChange Removed money as double.
     */
    public void debit(double balanceChange) {
        accountBal = roundBalance(accountBal - balanceChange);
    }
}