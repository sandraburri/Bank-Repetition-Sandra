package bank.account;

public class Account { // Klassenkopf
	private String customer; // Instanzvariablen, Attributte, Felder Zeilen 4-6
	private String pin;
	private double balance;

	// Konstruktor der Klasse Account Zeilen 8-12
	public Account(String customer, String pin, double balance) {
		this.customer = customer;
		this.pin = pin;
		this.balance = balance;
	}

	// Die Methode Account
	// Warum ist hier in Zeile 19 balance nicht aufgeführt aber ist in Zeile
	// 20 erforderlich? Diese Methode sagt doch aus, was der Account alles
	// umfasst?
	public Account(String customer, String pin) {
		this(customer, pin, 0.00);
	}

	// getter für Customer
	public String getCustomer() {
		return customer;
	}

	// boolean, ein Pin kann nur korrekt oder falsch sein, das muss überprüft
	// werden
	public boolean checkPIN(String pin) { // was bedeutet diese Warnung???
		if (pin.equals(this.pin))
			return true;
		else {
			return false;
		}
	}

	public double getBalance() {
		return balance;
	}

	public boolean deposit(double amount) {
		if (amount < 0)
			return false;
		this.balance = this.balance + amount;
		return true;
	}

	public boolean withdraw (double amount) {
		if (amount < 0)
		return false;
		this.balance = this.balance - amount;
		return true;
	}
}
