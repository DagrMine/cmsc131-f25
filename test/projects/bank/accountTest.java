package projects.bank;
//Imports
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projects.bank.account.AccountTypeEnum;
//Test
public class accountTest {
    public static void main(String[] args) {
        //Account Test Personal
        account Account00 = new account ("N000000","Aime Naut Aperson",9245.1489,AccountTypeEnum.CHECKING){};
        System.out.println("Id: "+Account00.getID());
        System.out.println("Name: "+Account00.getName());
        System.out.println("Balance: "+Account00.getBal());
        System.out.println("Account Type: "+Account00.getType());
    }
}
