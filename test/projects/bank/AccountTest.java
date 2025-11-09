package projects.bank;

//Imports
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Test
public class AccountTest {

    // Done add data validation test for constructor
    // TODO static factory throws on null input
    // TODO static factory preserves data
    // Done toCSV output is correct
    // TODO rewrite data validation to account for constructor validation.

    @Test
    public void testConstructor() {
        Account account = new Account("N000000", "Aime Naut Aperson", 9245.1489, AccountTypeEnum.CHECKING);
        assertEquals("N000000", account.getID());
        assertEquals("Aime Naut Aperson", account.getName());
        // Checks rounding as well.
        assertEquals(9245.15, account.getBal());
        assertEquals(AccountTypeEnum.CHECKING, account.getType());
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Account account2 = new Account(null, null, -10, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Account account3 = new Account(null, "eee", 0, AccountTypeEnum.CHECKING);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Account account4 = new Account("eeee", "eeee", 0, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Account account5 = new Account("eeeee00", null, 0, AccountTypeEnum.SAVINGS);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Account account2 = new Account("AAAAAAAAAAAAAA", "eeeeeeeeee", -10, AccountTypeEnum.CHECKING);
        });
    }

    @Test
    public void testFactories() {
        Account account = new Account("EJ521953", "Jim Yim Whim", 125923.55, AccountTypeEnum.CHECKING);
        assertEquals("checking,EJ521953,Jim Yim Whim,125923.55", account.accountToCSV(account));
        assertEquals(account.getID(), Account.csvToAccount("checking,EJ521953,Jim Yim Whim,125923.55").getID());
        assertEquals(account.getType(), Account.csvToAccount("checking,EJ521953,Jim Yim Whim,125923.55").getType());
        //Errors
        assertThrows(IllegalArgumentException.class, () -> {
            Account.csvToAccount("checking,EJ521953,Jim Yim Whim,-1000.4");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Account.csvToAccount(null);
        });
    }

    @Test
    public void testCreditDebit() {
        Account account = new Account(
                "R582925",
                "Joseph S",
                125.59,
                AccountTypeEnum.CHECKING);
        account.debit(5);
        assertEquals(120.59, account.getBal());
        account.credit(10);
        assertEquals(130.59, account.getBal());
    }
}
