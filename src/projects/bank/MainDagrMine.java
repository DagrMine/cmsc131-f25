package projects.bank;

public class MainDagrMine {
    public static void main(String[] args) {
        // testing java
        test2();
    }

    private static void test1() {
        Account Account00 = new Account(
                "N000000",
                "Aime Naut Aperson",
                9245.1489,
                AccountTypeEnum.CHECKING) {
        };
        System.out.println("Id: " + Account00.getID());
        System.out.println("Name: " + Account00.getName());
        System.out.println("Balance: " + Account00.getBal());
        System.out.println("Account Type: " + Account00.getType());
        Bank bank = new Bank();
        Account account = new Account("E111111", "Bim Jeam", 12405.345, AccountTypeEnum.SAVINGS);

        System.out.println(bank.addAccount(account));
        System.out.println(bank.getAccountByIndex(1));
        System.out.println(account);
    }
    private static void test2(){
        String stringle = "A list of audit types: %s %s %s %s";
        String stringle2 = String.format(stringle, AuditTypeEnum.ALERT,AuditTypeEnum.ERROR,AuditTypeEnum.WARN,AuditTypeEnum.INFO);
        System.out.println(stringle2);
        String w = "eee";
        w.contains("eee");
    }
    private static void test3(){
        //TODO Test array copy functionality from the bank class.
    }
}
