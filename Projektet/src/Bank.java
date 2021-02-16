import java.util.ArrayList;

public class Bank {
	private ArrayList<BankAccount> bankAccounts;
	
	/** Skapar en ny bank utan konton. */
	public Bank() {
		bankAccounts = new ArrayList<BankAccount>();
	}
	
	/** 
	 * Öppna ett nytt konto i banken. Om det redan finns en 
	 * kontoinnehavare med de givna uppgifterna ska inte en ny Customer
	 * skapas, utan istället den befintliga användas. Det nya kontonummret
	 * returneras.
	 */
	public int addAccount(String holderName, long idNr) {
		for (BankAccount a : bankAccounts) {
			if(a.getHolder().getIdNr() == idNr) {
				BankAccount account = new BankAccount(a.getHolder());
				bankAccounts.add(account);
				return account.getAccountNumber();
			}
		}
		BankAccount account = new BankAccount(holderName, idNr);
		bankAccounts.add(account);
		return account.getAccountNumber();
	}
	/**
	 * returnerar den kontoinnehavaren som har det givna id-numret,
	 * eller null om ingen sådan finns.
	 */
	public Customer findHolder(long idNr) {
		for(BankAccount a : bankAccounts) {
			if(a.getHolder().getIdNr() == idNr) {
				return a.getHolder();
			}
		}
		return null;
	}
	
	/**
	 * Tar bort konto med nummer 'number' från banken. Returnerar true om
	 * kontot fanns (och kunde tas bort), annars false.
	 */
	public boolean removeAccount(int number) {
		for(BankAccount a : bankAccounts) {
			if(a.getAccountNumber() == number) {
				bankAccounts.remove(a);
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * Returnerar en lista innehållande samtliga bankkonton i banken.
	 * Listan är sorterad på kontoinnehavarnas namn.
	 */
	public ArrayList<BankAccount> getAllAccounts() {
		ArrayList<BankAccount> sortedList = new ArrayList<BankAccount>();
		
		// om listan är tom ska den inte sorteras
		if (bankAccounts.size() < 1) {
            return bankAccounts;
        }
		
		// lägg in alla konton i den nya listan
		for(BankAccount account : bankAccounts) {
			sortedList.add(account);
		}
		
		// loopa igenom varje element i listan. kolla om elementen under är större, och lägg i så fall dessa före
		for (int i = 0; i < sortedList.size(); i++) {
			for (int j = i + 1; j < sortedList.size(); j++) {
				BankAccount selected = sortedList.get(i);
				BankAccount other = sortedList.get(j);
				
				// om selected är "större" än other, byt plats på dem.
				if(selected.getHolder().getName().toLowerCase().compareTo(other.getHolder().getName().toLowerCase()) > 0) {
					sortedList.set(i, other);
					sortedList.set(j, selected);
				}
			}
		}
		return sortedList;
	}
	
	/**
	 * Söker upp alla bankkonton som innehas av kunden med id-nummer 'idNr'.
	 * Kontona returneras i en lista. Kunderna antas ha unika id-nummer.
	 */
	public ArrayList<BankAccount> findAccountsForHolder(long idNr) {
		ArrayList<BankAccount> list = new ArrayList<BankAccount>();
		for(BankAccount a : bankAccounts) {
			if(a.getHolder().getIdNr() == idNr) {
				list.add(a);
			}
		}
		return list;
	}
	
	/** Söker upp kunder utifrån en sökning på namn eller del av namn. Alla
	 * personer vars namn innehåller strängen 'namePart inkluderas i 
	 * resultatet. Sökningen är "case insensitive", det vill
	 * säga gör ingen skillnad på stora och små bokstäver.
	 */
	public ArrayList<Customer> findByPartofName(String namePart) {
		ArrayList<Customer> list = new ArrayList<Customer>();
		for(BankAccount a : bankAccounts) {
			if(a.getHolder().getName().toLowerCase().indexOf(namePart.toLowerCase())>-1) {
				list.add(a.getHolder());
			}
		}
		return list;
	}
}
