import java.util.ArrayList;
import java.util.List;

// Account interface
interface Account {
    void deposit(double amount);
    void withdraw(double amount);
}

class SavingAccount implements Account{
    private double balance;

    public SavingAccount(){
        balance = 0;
    }

    @Override
    public void deposit(double amount){
        balance += amount;
        System.out.println("Deposited: " + amount + ". Your New Balance in Savings Account is : " + balance);
    }

    public void withdraw(double amount){
        if(balance >= amount){
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ". Your New Balance in Savings Account is : " + balance);
        }else{
            System.out.println("Not ENOUGH CASH! stranger");
        }
    }
}


class CurrentAccount implements Account{
    private double balance;

    public CurrentAccount(){
        balance = 0;
    }

    @Override
    public void deposit(double amount){
        balance += amount;
        System.out.println("Deposited: " + amount + ". Your New Balance in Current Account is : " + balance);
    }

    public void withdraw(double amount){
        if(balance >= amount){
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ". Your New Balance in Current Account is : " + balance);
        }else{
            System.out.println("Not ENOUGH CASH! stranger");
        }
    }
}

class FixedTermAccount implements Account{
    private double balance;

    public FixedTermAccount(){
        balance = 0;
    }

    @Override
    public void deposit(double amount){
        balance += amount;
        System.out.println("Deposited: " + amount + ". Your New Balance in Fixed Term Account is : " + balance);
    }

    public void withdraw(double amount){
        throw new UnsupportedOperationException("Withdrawal not allowed in Fixed Term Account! stranger");
    }
}


class BankClient{
    private List<Account> accounts;

    public BankClient(List<Account> accounts){
        this.accounts = accounts;
    }

    public void processTransactions(){
        for (Account acc: accounts){
            acc.deposit(100);
            
            //Checking account type explicitly
            if(acc instanceof FixedTermAccount){
                System.out.println("Skipping Withdrawal for Fixed Term Account");
            }else{
                try{
                    acc.withdraw(40);
                }catch (UnsupportedOperationException e){
                    System.out.println("Exception: " + e.getMessage());
                }
            }
        }
    }
}




public class LSPFollowedWrongly {
    public static void main(String[] args){
        List<Account> accounts = new ArrayList<>();
        accounts.add(new SavingAccount());
        accounts.add(new CurrentAccount());
        accounts.add(new FixedTermAccount());

        BankClient client = new BankClient(accounts);
        client.processTransactions();
    }
}
