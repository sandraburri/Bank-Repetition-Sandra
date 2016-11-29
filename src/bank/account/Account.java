package bank.account;

// Eine abstrakte Klasse kann nicht instanziert werden.
// Das machen wir, weil wir keine reinen Accounts mehr haben wollen. Wir wollen
// nur noch PersonalAccounts und SavingsAccounts haben, das heisst wir
// instanzieren nur noch diese Beiden...
public abstract class Account { // Klassenkopf
	private String customer; // Instanzvariablen, Attributte, Felder Zeilen
	// 8-13
	private String pin;
	
	// protected, weil private nicht vererbt werden kann
	protected double balance;

	// Konstruktor der Klasse Account Zeilen 16-19
	public Account(String customer, String pin, double balance) {
		this.customer = customer;
		this.pin = pin;
		this.balance = balance;
	}

	// Die Methode Account
	public Account(String customer, String pin) {
		
		// Dies ist ein zweiter Konstruktor (Siehe Überladen von Konstruktoren
		// im Script)! haben wir gemacht, damit man auch Konten eröffnen kann,
		// ohne ständig einen Startbetrag mitzugeben. "this(customer, pin,
		// 0.00);" macht dann nichts anderes, als den ersten Konstruktor
		// aufzurufen, und ihm als balance 0.0 mitzugeben und somit ein Konto
		// zu erstellen, das noch kein Geld drauf hat.
		this(customer, pin, 0.00);
	}

	// getter für Customer
	public String getCustomer() {
		return customer;
	}

	// boolean, ein Pin kann nur korrekt oder falsch sein, das muss überprüft
	// werden
	public boolean checkPIN(String pin) {
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