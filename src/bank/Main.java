package bank;

public class Main {

	public static void main(String[] args) {

		// Es wird ein neues Objekt Bank erzeugt
		Bank bank = new Bank();

		// Es wird ein neues Objekt ATM erzeugt
		ATM atm = new ATM(bank);
		
		// was passiert hier genau?
		atm.run();
	}
}