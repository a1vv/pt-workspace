

public class BankAccount {
	private double amount;
	private int accNbr;
	private Customer holder;
	private static int lastNbr = 1000;
	
	/**
	* Skapar ett nytt bankkonto åt en innehavare med namn ’holderName’ och
	* id ’holderId’. Kontot tilldelas ett unikt kontonummer och innehåller
	* inledningsvis 0 kr.
	*/
	public BankAccount(String holderName, long holderId) {
		holder = new Customer(holderName, holderId);
		setup();
		
	}
	/**
	* Skapar ett nytt bankkonto med innehavare ’holder’. Kontot tilldelas
	* ett unikt kontonummer och innehåller inledningsvis 0 kr.
	*/
	public BankAccount(Customer holder) {
		this.holder = holder;
		setup();
	}
	
	private void setup() {
		amount = 0;
		accNbr = lastNbr + 1;
		lastNbr = accNbr;
	}
	
	/** Tar reda på kontots innehavare */
	public Customer getHolder() {
		return holder;
	}
	/** Tar reda på det kontonummer som identifierar detta konto. */
	public int getAccountNumber() {
		return accNbr;
	}
	/** Tar reda på hur mycket pengar som finns på kontot */
	public double getAmount() {
		return amount;
	}
	/** Sätter in beloppet 'amount' på kontot */
	public void deposit(double amount) {
		this.amount += amount;
	}
	/**
	 * Tar ut beloppet 'amount' från kontot. Om kontot saknar täckning 
	 * blir saldot negativt.
	 */
	public void withdraw(double amount) {
		this.amount -= amount;
	}
	
	public String toString() {
		return "account " + accNbr + " (" + holder + ") : " + amount; 
	}
}
