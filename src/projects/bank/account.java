package projects.bank;
//Imports
//import java.util.Scanner;
//Class
public class Account {
    //Declarations
    private String accountID, accountName;
    private double accountBal;
    private AccountTypeEnum accountType;

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
}