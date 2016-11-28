package bank.account;

// Eine abstrakte Klasse kann nicht instanziert werden. (Aus Wiki Pedia; was
// bedeutet das hier in diesem Fall? Warum verwenden wir hier diese Klasse als
// abstrakt, was hat das f�r Auswirkungen auf die anderen Klassen (Account/
// PersonalAccount und SavingsAccount)?
public abstract class Account { // Klassenkopf
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
	public Account(String customer, String pin) {
		
		// Dies ist ein zweiter Konstruktor (Siehe �berladen von Konstruktoren
		// im Script)! haben wir gemacht, damit man auch Konten er�ffnen kann,
		// ohne st�ndig einen Startbetrag mitzugeben. "this(customer, pin,
		// 0.00);" macht dann nichts anderes, als den ersten Konstruktor
		// aufzurufen, und ihm als balance 0.0 mitzugeben und somit ein Konto
		// zu erstellen, das noch kein Geld drauf hat.
		this(customer, pin, 0.00);
	}

	// getter f�r Customer
	public String getCustomer() {
		return customer;
	}

	// boolean, ein Pin kann nur korrekt oder falsch sein, das muss �berpr�ft
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