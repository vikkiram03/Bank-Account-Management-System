// Bank Account Managememnt System

class Account
{
    private int accountNumber;
    private String accountHolderName;
    private double balance;

    // Getters
    public int getaccountNumber() { return accountNumber; }
    public String getaccountHolderName() { return accountHolderName; }
    public double checkBalance() { return balance; }

    // Setters
    public void setaccountNumber(int accountNumber) { this.accountNumber = accountNumber; }
    public void setaccountHolderName(String accountHolderName) { this.accountHolderName = accountHolderName; }
    public void setbalance(double balance) { this.balance = balance; }

    // Parameterized Constructor
    public Account(int accountNumber, String accountHolderName, double balance)
    {
        this.setaccountNumber(accountNumber);
        this.setaccountHolderName(accountHolderName);
        this.setbalance(balance);
    }

    // Method to deposit amount into account balance
    public void deposit(double amount)
    {
        if (amount > 0)
        {
            balance += amount;
            System.out.println("Rs. " + amount + " deposited");
        }
    }

    // Method to withdraw amount from account balance
    public void withdraw(double amount)
    {
        if (amount > 0 && amount <= balance)
        {
            balance -= amount;
            System.out.println("Rs. " + amount + " withdrawn");
        }
        else
        {
            System.out.println("Insufficient Funds");
        }
    }
}

class SavingsAccount extends Account
{
    private double interestRate;

    // Getters
    public double getInterestRate() { return interestRate; }

    // Setters
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }

    // Parameterized Constructor
    public SavingsAccount(int accountNumber, String accountHolderName, double balance, double interestRate)
    {
        super(accountNumber, accountHolderName, balance);
        this.setInterestRate(interestRate);
    }

    // Method to calculate interest
    public void calculateInterest()
    {
        double interest = checkBalance() * interestRate/100;
        deposit(interest);
        System.out.println("Interest calculated : Rs. " + interest);
    }

    // Method to display account details
    public void displayAccountDetails()
    {
        System.out.println("Savings account details: ");
        System.out.println("Account number: " + super.getaccountNumber());
        System.out.println("Account holder: " + super.getaccountHolderName());
        System.out.println("Balance: Rs. " + super.checkBalance());
        System.out.println("Interest Rate: " + interestRate + "%");
    }
}

class CurrentAccount extends Account
{
    private double overdraftlimit;

    // Getters
    public double getoverdraftlimit() { return overdraftlimit; }

    // Setters
    public void setoverdraftlimit(double overdraftlimit) { this.overdraftlimit = overdraftlimit; }

    // Parameterized Constructor
    public CurrentAccount(int accountNumber, String accountHolderName, double balance, double overdraftlimit)
    {
        super(accountNumber, accountHolderName, balance);
        this.setoverdraftlimit(overdraftlimit);
    }

    // Method to withdraw amount from account balance => overriden by the method from its parent (Account)
    @Override
    public void withdraw(double amount)
    {
        if (amount > 0 && amount <= (checkBalance() + overdraftlimit))
        {
            double newbal = checkBalance() - amount;
            super.setbalance(newbal);
            System.out.println("Rs. " + amount + " withdrawn");
        }
        else
        {
            System.out.println("Overdraft limit exceeded");
        }
    }

    // Method to display account details
    public void displayAccountDetails()
    {
        System.out.println("Current Account details: ");
        System.out.println("Account number: " + super.getaccountNumber());
        System.out.println("Account holder: " + super.getaccountHolderName());
        System.out.println("Balance: Rs. " + super.checkBalance());
        System.out.println("Overdraft Limit: Rs. " + getoverdraftlimit());
    }
}

public class Assignment1 
{
    public static void main(String[] args) 
    {
        SavingsAccount sa = new SavingsAccount(501, "Vikram", 10000, 5.0);
        CurrentAccount ca = new CurrentAccount(502, "Rahul", 25000, 500);

        sa.displayAccountDetails();
        sa.deposit(500);
        sa.calculateInterest();
        sa.displayAccountDetails();

        System.out.println();

        ca.displayAccountDetails();
        ca.withdraw(5000);
        ca.displayAccountDetails();
    }    
}
