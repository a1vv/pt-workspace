import java.util.ArrayList;
import java.util.Scanner;

public class BankApplication {
	private static Scanner scan;
	private static Bank bank;
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		bank = new Bank();
		runApplication();
	}
	
	private static void runApplication() {
		boolean isRunning = true;
		while(isRunning) {
			printMenu(); 
			int choice = readInt();
			switch(choice) {
				case 1:
					findAccounts();
					break;
				case 2:
					findCustomer();
					break;
				case 3:
					deposit(false);
					break;
				case 4:
					deposit(true);
					break;
				case 5:	
					transfer();
					break;
				case 6:
					createAccount();
					break;
				case 7:
					removeAccount();
					break;
				case 8:
					printAccounts();
					break;
				case 9:		
					isRunning = false;
					println("Application exited.");
					break;
				default:
					println("Invalid choice, try again.");
					runApplication();
			}
		}
	}
	
	private static void findAccounts() {
		println("Customer ID: ");
		long id = readLong();
		ArrayList<BankAccount> accounts = bank.findAccountsForHolder(id);
		if(accounts.size() < 1) {
			println("No accounts found.");
		} else {
			for(BankAccount a : accounts) {
				println(a);
			}
		}
		toContinue();
	}
	
	private static void findCustomer() {
		print("Name: ");
		String name = scan.nextLine();
		ArrayList<Customer> customers = bank.findByPartofName(name);
		if(customers.size() < 1) {
			println("No customers found.");
		} else {
			for( Customer c : customers) {
				println(c);
			}
		}
		toContinue();
	}
	
	// withdraw==true om withdraw, false om deposit
	private static void deposit(boolean withdraw) {
		print("Account Number: ");
		int accNbr = readInt();
		print("Amount: ");
		int amount = readInt();
		changeBalance(withdraw, accNbr, amount);
		toContinue();
	}
	// returnerar true om den lyckades
	private static boolean changeBalance(boolean withdraw, int accNbr, int amount) {
		if(amount < 0) {
			println("Amount can't be negative.");
			return false;
		}
		for(BankAccount a : bank.getAllAccounts()) {
			if(a.getAccountNumber() == accNbr) {
				if(withdraw) {
					if(amount > a.getAmount()) {
						println("Insufficient balance. (balance: " + a.getAmount() + ")");
						return false;
					}
					a.withdraw(amount);
					println(amount + " withdrawn from account #" + a.getAccountNumber());
					amount = -amount; // för att den ska printa rätt "previous" balans sen.
				} else {
					a.deposit(amount);
					println(amount + " deposited into account #" + a.getAccountNumber());
				}
				println("#" + a.getAccountNumber() + " balance: " + a.getAmount() + " (previously " + (a.getAmount()-amount) + ")");
				return true;
			}
		}
		println("Account #" + accNbr + " not found.");
		return false;
	}
	
	private static void transfer() {
		print("from account: ");
		int fromAcc = readInt();
		print("to account: ");
		int toAcc = readInt();
		print("amount: ");
		int amount = readInt();
		if(changeBalance(true, fromAcc, amount)) {
			changeBalance(false, toAcc, amount);
		}
		toContinue();
	}
	
	private static void createAccount() {
		print("Name of holder: ");
		String holderName = scan.nextLine();
		print("Holder ID: ");
		long idNr = readLong();
		print("You've entered: " + holderName + ", " + idNr + "\n"
				+ "Is this correct? (y/n) \n");
		String choice = scan.nextLine();
		if(choice.equals("y")) {
			int accNbr = bank.addAccount(holderName, idNr);
			println("Account created: " + accNbr); 
			toContinue();
		} else {
			println("OK. Try again.");
			createAccount();
		}
	}
	
	private static void removeAccount() {
		println("Please specify the account number you wish to remove.");
		int number = readInt();
		//TODO: skriv ut detaljer om kontot, ex ägare. för extra tydlighet
		print("You've entered: " + number + "\n"
				+ "Is this correct? (y/n) \n");
		
		if(scan.nextLine().equals("y")) {
			if(bank.removeAccount(number)) {
				println("Account removed.");
				toContinue();
			} else {
				println("No such account exists");
				toContinue();
			}
			
		} else {
			println("OK. Try again.");
			removeAccount();
		}
	}
	
	private static void printAccounts() {
		int i = 0;
		for(BankAccount a : bank.getAllAccounts()) {
			println(a);
			i++;
		}
		if(i < 1) {
			println("No accounts exist.");
		}
		toContinue();
	}
	
	private static void printMenu() {
		System.out.print("- - - - - - - - - - - - - - - - - - - - - \n"
				+ "1. Hitta konto utifrån innehavare \n"
				+ "2. Sök kontoinnehavare utifrån (del av) namn \n"
				+ "3. Sätt in \n"
				+ "4. Ta ut \n"
				+ "5. Överföring\n"
				+ "6. Skapa Konto \n"
				+ "7. Ta bort konto \n"
				+ "8. Skriv ut konton \n"
				+ "9. Avsluta \n"
				+ "- - - - - - - - - - - - - - - - - - - - - \n");
	}
	
	private static void toContinue() {
		println("Press any key to continue.");
		scan.nextLine();
	}
	
	// kallas varje gång en int ska läsas in via scannern. Använder nextLine så att
	// allting läses in
	private static int readInt() {
		int number = -1;
		try {
			number = Integer.parseInt(scan.nextLine());
		} catch(NumberFormatException e) {
			println("Invalid input. Must be a number.");
			number = readInt();
		}
		return number;
	}
	
	private static long readLong() {
		long number = -1;
		try {
			number = Long.parseLong(scan.nextLine());
		} catch(NumberFormatException e) {
			println("Invalid input. Must be a number.");
			number = readLong();
		}
		return number;
	}
	
	// tar vilket argument som helst och skriver ut det. Kortare att skriva
	private static void println(Object a) {
		System.out.println(a);
	}
	private static void print(Object a) {
		System.out.print(a);
	}
}
