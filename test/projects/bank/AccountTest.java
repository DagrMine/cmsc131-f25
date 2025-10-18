package projects.bank;

//Imports
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Test
public class AccountTest {

    // TODO add data validation test for constructor

    @Test
    public void testConstructor() {
        Account account = new Account("N000000", "Aime Naut Aperson", 9245.1489, AccountTypeEnum.CHECKING);
        assertEquals("N000000", account.getID());
        assertEquals("Aime Naut Aperson", account.getName());
        assertEquals(9245.1489, account.getBal());
        assertEquals(AccountTypeEnum.CHECKING, account.getType());
    }
    @Test
    public void testParsing(){
        Account account = new Account("EJ521953", "Jim Yim Whim", 125923.55, AccountTypeEnum.CHECKING);
        assertEquals("checking,EJ521953,Jim Yim Whim,125923.55", account.accountToCSV(account));
        assertEquals(account.getID(), Account.CSVToAccount("checking,EJ521953,Jim Yim Whim,125923.55").getID());
        assertEquals(account.getType(), Account.CSVToAccount("checking,EJ521953,Jim Yim Whim,125923.55").getType());
    }
}
