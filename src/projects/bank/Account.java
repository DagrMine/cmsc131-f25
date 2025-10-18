package projects.bank;
//Imports
//import java.util.Scanner;
//Class
public class Account {
    //Declarations
    private String accountID, accountName;
    private double accountBal;
    private AccountTypeEnum accountType;

//TODO Data validation to ensure that balance does not have more than two decimal places
//TODO General data validation for the account class
    /**
     * Stores account information
     * @param acID stores account ID information as a String
     * @param acName stores account name information as a String
     * @param acBal stores account balance information as a Double
     * @param acType stores account typing (CHECKING or SAVINGS) as an Enum
     */
    public Account(String acID, String acName, double acBal, AccountTypeEnum acType){
        // TODO add data validation for String and AccountTypeEnum arguments
        //Declarations/Assignments
        accountID = acID;
        accountName = acName;
        accountBal = acBal;
        accountType = acType;
    }
    //Accessors
    /** Returns the balance of the account object. (Double)*/
    public double getBal(){
        return accountBal;
        }
    /**Returns the name of the account object. (String)*/
    public String getName(){
        return accountName;
    }
    /**Returns the ID of the account object. (String)*/
    public String getID(){
        return accountID;
    }
    /**Returns the Enum Type of the account object. (AccountTypeEnum)*/
    public AccountTypeEnum getType(){
        return accountType;
    }
    /** Translates an account in the form of a comma separated string to an account object
     * @param accountLine input a comma separated string that contains the (in this order) typing, ID, name, and balance.
     */
    public static Account CSVToAccount(String accountLine){
        String[] tokens = accountLine.split(",");
        String tokenID = tokens[1];
        String tokenName = tokens[2];
        double tokenBal = Double.valueOf(tokens[3]);
        AccountTypeEnum tokenType = AccountTypeEnum.valueOf(tokens[0].toUpperCase());
        Account returnedAccount = new Account (tokenID,tokenName,tokenBal,tokenType);
        return returnedAccount;
    }
    /** Translates an account object into a comma separated string.
     * @param accountLine the account object to be turned into a comma separated string.
     */
    public String accountToCSV(Account accountLine){
        String type = accountLine.getType()+"";
        //String ID = accountLine.getID();
        //Incredibly 'interesting' way of changing an account into a string. I'm commiting to it.
        //It combines the type, converted to a string, to lowercase, then combines it with the rest of the account data as one string separated by commas.
        String token = type.toLowerCase()+","+accountLine.getID()+","+accountLine.getName()+","+accountLine.getBal();
        return token;
    }
}