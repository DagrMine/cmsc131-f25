package projects.bank;

import java.util.ArrayList;

public class Bank {
    //Declarations
    Account newAccount;
    public Account[] BankAccounts = new Account[10];
    public Bank(){

    }
    public void addAccount(){
        //could return bool if successful. Not right now though.
        BankAccounts.set;
    }
    public int countAccounts(){
        return BankAccounts.size();
    }
    // TODO double-check problem spec. Should return an index.
    public Account accountsByID(String accountID){
        for (int i = 0; (BankAccounts[i]).AccountID != accountID; i++){
            if((BankAccounts[i]).AccountID == accountID){
                return BankAccounts[i];
            }
        }
    }
    // not part of spec, can be removed
    public Account accountsByName(){
        
    }
}
