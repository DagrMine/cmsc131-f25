package projects.bank;

public class MainDagrMine {
    public static void main(String[] args) {
         //Account Test Personal
         Account Account00 = new Account (
             "N000000",
             "Aime Naut Aperson",
             9245.1489,
             AccountTypeEnum.CHECKING
             ){};
         System.out.println("Id: "+Account00.getID());
         System.out.println("Name: "+Account00.getName());
         System.out.println("Balance: "+Account00.getBal());
         System.out.println("Account Type: "+Account00.getType());
        Bank bank = new Bank();
        Account account = new Account("E111111","Bim Jeam", 12405.345, AccountTypeEnum.SAVINGS);
        
        System.out.println(bank.addAccount(account));
        System.out.println(bank.getAccountByIndex(1));
        System.out.println(account);
    }
}
