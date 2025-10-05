package projects.bank;

import java.util.ArrayList;

public class bank {
    //Declarations
    account newAccount;
    public account[] BankAccounts = new account[10];
    public bank(){

    }
    public void addAccount(){
        //could return bool if successful. Not right now though.
        BankAccounts.set;
    }
    public int countAccounts(){
        return BankAccounts.size();
    }
    public account accountsByID(String accountID){
        for (int i = 0; (BankAccounts[i]).AccountID != accountID; i++){
            if((BankAccounts[i]).AccountID == accountID){
                return BankAccounts[i];
            }
        }
    }
    public account accountsByName(){
        
    }
}
