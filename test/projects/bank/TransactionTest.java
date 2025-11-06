package projects.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {
    @Test
    public void testReturnMethods(){
        WithdrawalTransaction testTransaction = new WithdrawalTransaction("TestID", 100);
        assertEquals("TestID",testTransaction.getAccountID());
        assertEquals(100,testTransaction.getTransactionAmount());
        //TODO why can I do this? I set all the variables to protected??? Do I need to set the abstract class to protected or something?
        testTransaction.accountID = "to";
    }

    @Test
    public void testTransactionFactory(){
        Transaction testTransaction1 = Transaction.createTransaction("withdrawal,E111111,1000.35");
        assertEquals("E111111",testTransaction1.getAccountID());
        assertEquals(Double.valueOf(1000.35), testTransaction1.getTransactionAmount());
        //TODO find a way to call the withdrawal class here
        //assertEquals(testTransaction1.getClass(),);
        //DATA VALIDATION
        //Bad ID
        Transaction testTransaction2 = Transaction.createTransaction("withdrawal,null,-100.4522");
        //Bad amount
        Transaction testTransaction3 = Transaction.createTransaction("withdrawal,A1233333,sixetyseven");
        //Bad type
        Transaction testTransaction4 = Transaction.createTransaction("removal,A522555,100.4522");
        //Null data
        Transaction testTransaction5 = Transaction.createTransaction(null);
        //Altered type
        Transaction testTransaction6 = Transaction.createTransaction("wItHDRAWal,J435350,40");
        
        //Assertions
        assertEquals(null,testTransaction2);
        assertEquals(null,testTransaction3);
        assertEquals(null,testTransaction4);
        assertEquals(null,testTransaction5);
        assertEquals(testTransaction6.getClass(),testTransaction1.getClass());
        String w = "eee";
        w.contains("eee");
    }
}
