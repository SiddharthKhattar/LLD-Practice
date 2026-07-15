import java.util.ArrayList;
import java.util.List;

// 1) DepositOnlyAccount Interface
interface DepositOnlyAccount{
    void deposit(double amount);
}

interface WithdrawableAccount extends DepositOnlyAccount{
    void withdraw(double amount);
}

class SavingAccount implements WithdrawableAccount{
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

class CurrentAccount implements WithdrawableAccount{
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

class FixedTermAccount implements DepositOnlyAccount{
    private double balance;

    public FixedTermAccount(){
        balance = 0;
    }

    @Override
    public void deposit(double amount){
        balance += amount;
        System.out.println("Deposited: " + amount + ". Your New Balance in Fixed Term Account is : " + balance);
    }
}

class BankClient{
    private List<WithdrawableAccount> withdrawableAccounts;
    private List<DepositOnlyAccount> depositOnlyAccounts;

    public BankClient(List<WithdrawableAccount> withdrawableAccounts, List<DepositOnlyAccount> depositOnlyAccounts){
        this.withdrawableAccounts = withdrawableAccounts;
        this.depositOnlyAccounts = depositOnlyAccounts;
    }

    public void processTransactions(){
        for(WithdrawableAccount acc: withdrawableAccounts){
            acc.deposit(1000);
            acc.withdraw(200);
        }

        for(DepositOnlyAccount acc: depositOnlyAccounts){
            acc.deposit(1000);
        }
    }

    
}



public class LSPFollowed {
    public static void main(String[] args) {
        List<WithdrawableAccount> withdrawableAccounts = new ArrayList<>();
        withdrawableAccounts.add(new SavingAccount());
        withdrawableAccounts.add(new CurrentAccount());

        List<DepositOnlyAccount> depositOnlyAccounts = new ArrayList<>();
        depositOnlyAccounts.add(new FixedTermAccount());

        BankClient client = new BankClient(withdrawableAccounts, depositOnlyAccounts);
        client.processTransactions();   
    } 
}
