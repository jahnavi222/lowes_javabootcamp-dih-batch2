package com.labs.day5;

//polymorphism via interface
interface IBankAccount {
	boolean deposit(int amount);

	boolean withdraw(int amount);

	void getBalance();
}

class SavingAccount implements IBankAccount {

	private int balance;
	private int preDayLimit;

	@Override
	public boolean deposit(int amount) {
		balance += amount;
		System.out.println("Successfully deposited: " + amount);
		return true;
	}

	@Override
	public boolean withdraw(int amount) {
		if (balance < amount) {
			System.out.println("Insufficient balance");
			return false;
		} else if (preDayLimit + amount > 5000) {
			System.out.println("Withdraw attempt failed");
			return false;
		} else {
			balance -= amount;
			preDayLimit += amount;
			System.out.println("Successfully withdraw: " + amount);
			return true;
		}
	}

	@Override
	public void getBalance() {
		System.out.println("Saving account balance: " + balance);

	}

}

class CurrentAccount implements IBankAccount {

	private int balance;
	

	@Override
	public boolean deposit(int amount) {
		balance += amount;
		System.out.println("Successfully deposited: " + amount);
		return true;
	}

	@Override
	public boolean withdraw(int amount) {
		if (balance < amount) {
			
			System.out.println("Insufficient balance");
			return false;
		}

		balance -= amount;

		System.out.println("Successfully withdraw: " + amount);
		return true;
	}

	@Override
	public void getBalance() {
		System.out.println("current account balance: " + balance);

	}

}

public class TestInterface {

	public static void main(String[] args) {
		IBankAccount savingAcc= new SavingAccount();
		IBankAccount currentAcc= new CurrentAccount();
		
		savingAcc.deposit(2000);
		savingAcc.withdraw(1000);
		savingAcc.withdraw(6000);
		savingAcc.getBalance();

        System.out.println();

       currentAcc.deposit(5000);
       currentAcc.withdraw(6000);
       currentAcc.withdraw(2000);
        currentAcc.getBalance();
	}
;
}
