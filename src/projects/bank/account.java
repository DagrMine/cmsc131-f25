package projects.bank;
//Imports
import java.util.Scanner;
//Class
public class account {
    //Declarations
    public String AccountID, AccountName;
    public double AccountBal;
    enum AccountTypeEnum{
        CHECKING,
        SAVINGS
    };
    AccountTypeEnum AccountType;

    public account(String acID, String acName, double acBal, AccountTypeEnum acType){
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