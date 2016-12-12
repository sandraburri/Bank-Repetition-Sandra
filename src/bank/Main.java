package bank;

import bank.account.CredentialsException;

public class Main {

	public static void main(String[] args) throws CredentialsException {

		// Es wird ein neues Objekt Bank erzeugt
		Bank bank = new Bank();

		// Es wird ein neues Objekt ATM erzeugt
		ATM atm = new ATM(bank);
		
		// führt die run Methode aus, bringt den ATM zum laufen
		atm.run();
	}
}