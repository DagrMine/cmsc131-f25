package projects.bank;

//Imports
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Test
public class AccountTest {

    // TODO add data validation test for constructor
    // TODO static factory throws on null input
    // TODO static factory preserves data
    // Done toCSV output is correct

    @Test
    public void testConstructor() {
        Account account = new Account("N000000", "Aime Naut Aperson", 9245.1489, AccountTypeEnum.CHECKING);
        assertEquals("N000000", account.getID());
        assertEquals("Aime Naut Aperson", account.getName());
        //Checks rounding as well.
        assertEquals(9245.15, account.getBal());
        assertEquals(AccountTypeEnum.CHECKING, account.getType());
        //TODO Validation
    }
    @Test
    public void testParsing(){
        Account account = new Account("EJ521953", "Jim Yim Whim", 125923.55, AccountTypeEnum.CHECKING);
        assertEquals("checking,EJ521953,Jim Yim Whim,125923.55", account.accountToCSV(account));
        assertEquals(account.getID(), Account.csvToAccount("checking,EJ521953,Jim Yim Whim,125923.55").getID());
        assertEquals(account.getType(), Account.csvToAccount("checking,EJ521953,Jim Yim Whim,125923.55").getType());
        //Null factory
        //Account account2;
        assertNull(Account.csvToAccount(null));
        //TODO Assertthrows on an exception
        //assertThrows(NullPointerException.class,);
    }
    @Test
    public void testCreditDebit(){
        Account account = new Account(
            "R582925",
            "Joseph S",
            125.59,
            AccountTypeEnum.CHECKING
        );
        account.debit(5);
        assertEquals(120.59, account.getBal());
        account.credit(10);
        assertEquals(130.59, account.getBal());
    }
}
