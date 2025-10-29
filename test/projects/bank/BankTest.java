package projects.bank;

//Imports
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Test
public class BankTest {

    @Test // unnecessary test
    public void testConstructor() {
        //Testing bank initialization
        Bank bank = new Bank();
        assertTrue(bank != null);
    }

    // TODO test data validation in `add` method
    // Done test add success returns true
    // TODO test add fail returns false
    
    @Test
    public void testAddRemoveAccounts(){
        Bank bank = new Bank();
        Account account = new Account("Heavy","Mikhail", 400000.12, AccountTypeEnum.CHECKING);
        Account account2 = new Account("J222222","Steve Lobs", 8.3*(Math.pow(10,9)), AccountTypeEnum.SAVINGS);
        Account account3 = null;
        //True/false
        assertEquals(true,bank.addAccount(account));
        assertEquals(true,bank.addAccount(account2));
        assertEquals(false,bank.addAccount(account3));
        //Add account
        assertEquals(account,bank.getAccountByIndex(0));
        assertEquals(account2,bank.getAccountByIndex(1));
        //Remove Account
        Account account4 = new Account("A535823","Steve Lobs", 10, AccountTypeEnum.CHECKING);
        Account account5 = new Account("J432342","Steve Lobs", 900, AccountTypeEnum.SAVINGS);
        bank.addAccount(account4);
        bank.addAccount(account5);
        bank.removeAccountsByName("Steve Lobs");
        //TODO ask in class about whether it is good to apply null validation to the return methods in account.
        //assertEquals(null, bank.getAccountByIndex(3).getName());
        //assertEquals(null, bank.getAccountByIndex(4));
    }
    //TODO Add tests for removing accounts.

    @Test
    public void testSearch(){
        //Load in test data
        Bank bank = new Bank();
        Account account1 = new Account("E111111","Bim Jeam", 12405.345, AccountTypeEnum.SAVINGS);
        Account account2 = new Account("G124052","Zelda", 4125.3, AccountTypeEnum.CHECKING);
        Account account3 = new Account("A85294","Zelda", 9528.49, AccountTypeEnum.SAVINGS);
        Account account4 = new Account("J516208","Zelda", 4125.3, AccountTypeEnum.CHECKING);
        bank.addAccount(account1);
        bank.addAccount(account2);
        bank.addAccount(account3);
        bank.addAccount(account4);
        //Return an account using the array index number
        assertEquals(account2,bank.getAccountByIndex(1));
        assertEquals(null,bank.getAccountByIndex(6));
        //Return accounts from a given name
        assertEquals(bank.getAccountsByNameString("Zelda"),"1,2,3,");
        assertEquals(bank.getAccountsByNameArray("Zelda")[2].getID(),"J516208");
        //Count the number of active accounts
        assertEquals(4,bank.countAccounts());
        //Return an account by its ID
        assertEquals(account2,bank.getAccountByID("G124052"));
        //Return an account index using its ID
        assertEquals(0,bank.getAccountIndexById("E111111"));
    }

    @Test  
    public void testWriteAccounts() {
        Bank bank = new Bank();
        Account account1 = new Account("E111111","Bim Jeam", 12405.345, AccountTypeEnum.SAVINGS);
        Account account2 = new Account("G124052","Zelda", 4125.3, AccountTypeEnum.CHECKING);
        Account account3 = new Account("A85294","Zelda", 9528.49, AccountTypeEnum.SAVINGS);
        Account account4 = new Account("J516208","Zelda", 4125.3, AccountTypeEnum.CHECKING);
        bank.addAccount(account1);
        bank.addAccount(account2);
        bank.addAccount(account3);
        bank.addAccount(account4);

        //write accounts
        bank.writeAccounts("accountsTest.csv");
        assertEquals(true, bank.writeAccounts("accountsTest.csv"));
        assertEquals(false, bank.writeAccounts(null));
        //TODO I don't think I should be able to make a file with no file extension but that's a problem for later seeing as I fixed several right now.
        //assertEquals(false, bank.writeAccounts("accountsWrong"));
        
        //test if the accounts are written
        File testFile = new File("accountsTest.csv");
        Scanner testScanner;
        try {
            testScanner = new Scanner(testFile);
            assertEquals("savings,E111111,Bim Jeam,12405.35",testScanner.nextLine());
            assertEquals("checking,G124052,Zelda,4125.3",testScanner.nextLine());
            assertEquals("savings,A85294,Zelda,9528.49",testScanner.nextLine());
            assertEquals("checking,J516208,Zelda,4125.3",testScanner.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Done? Unsure if I need to be more thorough : test writeAccounts failure mode.
    // Done test writeAccounts returns true on succeed

    @Test
    public void testLoadAccounts() {
        //load accounts
        //ENSURE THIS STEP IS DONE WITH A WORKING ACCOUNTSTEST.CSV FILE ALREADY MADE
        Bank bank = new Bank();
        assertEquals(true, bank.loadAccounts("accountsTest.csv"));
        Account account2 = new Account("G124052","Zelda", 4125.3, AccountTypeEnum.CHECKING);
        //verify accounts are loaded
        assertEquals(account2.getBal(),(bank.getAccountByID("G124052")).getBal());
        assertEquals(4, bank.countAccounts());
        //Failure validation
        assertEquals(false, bank.loadAccounts(null));
        assertEquals(false, bank.loadAccounts("oneWholeSkib.csv"));
    } 

    // TODO test find method failure returns correct value
    // Done test loadAccounts failure mode returns correct value
    // Done test loadAccounts returns true on succeed

}
