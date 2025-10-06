package projects.bank;

//Imports
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Test
public class AccountTest {
    // public static void main(String[] args) {
    //     //Account Test Personal
    //     Account Account00 = new Account (
    //         "N000000",
    //         "Aime Naut Aperson",
    //         9245.1489,
    //         AccountTypeEnum.CHECKING
    //         ){};
    //     System.out.println("Id: "+Account00.getID());
    //     System.out.println("Name: "+Account00.getName());
    //     System.out.println("Balance: "+Account00.getBal());
    //     System.out.println("Account Type: "+Account00.getType());
    // }

    @Test
    public void testConstructor() {
        Account account = new Account("N000000", "Aime Naut Aperson", 9245.1489, AccountTypeEnum.CHECKING);
        assertEquals("N000000", account.getID());
    }
}
