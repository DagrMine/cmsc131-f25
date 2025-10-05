package projects.bank;
//Imports
import java.util.Scanner;
//Class
public class Account {
    //Declarations
    public String AccountID, AccountName;  // TODO camelCase, visibility
    public double AccountBal;  // TODO camelCase, visibility
    enum AccountTypeEnum{  // TODO put it its own file
        CHECKING,
        SAVINGS
    };
    AccountTypeEnum AccountType;  // TODO camelCase

    public Account(String acID, String acName, double acBal, AccountTypeEnum acType){
        //Declarations/Assignments
        AccountID = acID;
        AccountName = acName;
        AccountBal = acBal;
        AccountType = acType;
    }
    //Accessors
    public double getBal(){
        return AccountBal;
        }
    public String getName(){
        return AccountName;
    }
    public String getID(){
        return AccountID;
    }
    public AccountTypeEnum getType(){
        return AccountType;
    }
}