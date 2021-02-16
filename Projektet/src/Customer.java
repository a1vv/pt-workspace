
public class Customer {
	private String name;
	private long id;
	private int nbr;
	private static int customerNbr = 100;
	
	/**
	* Skapar en kund (kontoinnehavare) med namnet ’name’ och id-nummer ’idNr’.
	* Kunden tilldelas också ett unikt kundnummer.
	*/
	public Customer(String name, long idNr) {
		this.name = name;
		this.id = idNr;
		
		customerNbr++;
		this.nbr = customerNbr;
		
		
	}
	/** Tar reda på kundens namn. */
	public String getName() {
		return name;
	}
	/** Tar reda på kundens personnummer. */
	public long getIdNr() {
		return id;
	}
	/** Tar reda på kundens kundnummer. */
	public int getCustomerNr() {
		return nbr;
	}
	/** Returnerar en strängbeskrivning av kunden. */
	public String toString() {
		return name + ", id " + id + ", kundnr " + nbr;
	}
}
