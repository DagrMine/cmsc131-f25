package projects.bank;

//Imports
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Test
public class BankTest {
    @Test
    public void testConstructor() {
        //Testing bank initialization
        Bank bank = new Bank();
        assertTrue(bank != null);
    }
    @Test
    public void testMisc(){
        Bank bank = new Bank();
        Account account = new Account("Heavy","Mikhail", 400000.12, AccountTypeEnum.CHECKING);
        Account account2 = new Account("J222222","Steve Lobs", 8.3*(Math.pow(10,9)), AccountTypeEnum.SAVINGS);
        bank.addAccount(account);
        bank.addAccount(account2);
        //Add account
        assertEquals(account,bank.getAccountByIndex(0));
        assertEquals(account2,bank.getAccountByIndex(1));
    }
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
        //Return accounts from a given name //TODO add a way to return multiple accounts with the same owner
        assertEquals(bank.getAccountsByName("Zelda"),"1,2,3,");
        //Count the number of active accounts
        assertEquals(4,bank.countAccounts());
        //Return an account by its ID
        assertEquals(account2,bank.getAccountByID("G124052"));
        //Return an account index using its ID
        assertEquals(0,bank.getAccountIndexById("E111111"));
    }
}
