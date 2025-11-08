package projects.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class AuditTest {
    //@BeforeEach
    //public void setup(){
    //    Audit testAudit = new Audit();
    //} idk why this doesnt work and im too tired and dont have enough time to bother.

    @Test
    public void testInitialize() {
        Audit testAudit = new Audit();
        assertTrue(testAudit.open("data/Test/auditTest.log"));
        assertTrue(testAudit.close());
        assertFalse(testAudit.close());
        assertFalse(testAudit.open(null));
        assertFalse(testAudit.close());
    }

    @Test
    public void testWrite() {
        //TODO Why does the file write only when this test is called alone?
        Audit testAudit = new Audit();
        Account account = new Account("U124245", "Joe", 1000.52, AccountTypeEnum.CHECKING);
        Transaction transaction = new WithdrawalTransaction("U124245", 60);
        Account accountNull = null;
        Transaction transactionNull = null;


        testAudit.open("data/Test/auditTest.log");
        // Write Default
        assertTrue(testAudit.write("Test 1, Control", AuditTypeEnum.INFO));
        assertTrue(testAudit.write("Test 2, Null Type", null));
        assertTrue(testAudit.write(null, AuditTypeEnum.ALERT));
        assertTrue(testAudit.write(null, null));
        // Accounts
        assertTrue(testAudit.write(account, "Account Test 1, Control", AuditTypeEnum.ERROR));
        assertTrue(testAudit.write(account, "Account Test 2, Null Type", null));
        assertTrue(testAudit.write(accountNull, "Account Test 3, Null Account", AuditTypeEnum.WARN));
        assertTrue(testAudit.write(accountNull, null, null));
        // Transactions
        assertTrue(testAudit.write(transaction, "Transaction Test 1, Control", AuditTypeEnum.ALERT));
        assertTrue(testAudit.write(transaction, "Transaction Test 2, Null Type", null));
        assertTrue(testAudit.write(transactionNull, "Transaction Test 3, Null Transaction", AuditTypeEnum.ALERT));
        assertTrue(testAudit.write(transactionNull, null, null));


        testAudit.close();
    }
    @Test
    public void testBankAudit(){
        Bank bank = new Bank();
        //TODO This is going to take a while. Copy code from 
    }
    /*
     * Testing Checklist
     * 
     * WithdrawalTransaction
     * DepositTransaction
     * Bank
     * Write DONE
     * ProcessTransactions
     * Null Validation DONE
     * Initialization DONE
     */
}
