package projects.bank;
//Imports
//import java.util.Scanner;
//Class
public class Account {
    //Declarations
    private String accountID, accountName;
    private double accountBal;
    private AccountTypeEnum accountType;

    /**
     * Stores account information
     * @param acID stores account id information as a String
     * @param acName stores account name information as a String
     * @param acBal stores account balance information as a Double
     * @param acType stores account typing (CHECKING or SAVINGS) as an Enum
     */
    public Account(String acID, String acName, double acBal, AccountTypeEnum acType){
        //Declarations/Assignments
        accountID = acID;
        accountName = acName;
        accountBal = acBal;
        accountType = acType;
    }
    //Accessors
    public double getBal(){
        return accountBal;
        }
    public String getName(){
        return accountName;
    }
    public String getID(){
        return accountID;
    }
    public AccountTypeEnum getType(){
        return accountType;
    }
    /** Translates an account in the form of a comma separated string to an account object
     * @param accountLine input a comma separated string that contains the (in this order) typing, ID, name, and balance.
     */
    public static Account parseAccounts(String accountLine){
        String[] tokens = accountLine.split(",");
        String tokenID = tokens[1];
        String tokenName = tokens[2];
        double tokenBal = Double.valueOf(tokens[3]);
        AccountTypeEnum tokenType = AccountTypeEnum.valueOf(tokens[0].toUpperCase());
        Account returnedAccount = new Account (tokenID,tokenName,tokenBal,tokenType);
        return returnedAccount;
    }
    public static String unParseAccounts(Account accountLine){
        //String type = accountLine.getType()+"";
        //String ID = accountLine.getID();
        //Incredibly 'interesting' way of changing an account into a string.
        String token = accountLine.getType()+","+accountLine.getID()+","+accountLine.getName()+","+accountLine.getBal();
        return token;
    }
}