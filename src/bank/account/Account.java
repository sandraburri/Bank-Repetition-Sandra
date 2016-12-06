package bank.account;

import bank.Printable;

// Eine abstrakte Klasse kann nicht instanziert werden.
// Das machen wir, weil wir keine reinen Accounts mehr haben wollen. Wir wollen
// nur noch PersonalAccounts und SavingsAccounts haben, das heisst wir
// instanzieren nur noch diese Beiden...

// implements Printable implementiert das Interface Printable
// Die einzig direkte Auswirkung hier ist, dass mindestens entweder hier in der
// abstrakten Klasse, oder in beiden konkreten Subklassen print() implementiert
// werden muss. (Kannst ja mal ausprobieren, was passiert, wenn du die Methode
// hier und in einer Subklasse auskommentierst - Eclipse wird dich aufforden
// die print-Methode zu implementieren, da das Interface das so verlangt)
public abstract class Account implements Printable { // Klassenkopf
	private String customer; 
	private String pin;
	
	// protected, weil private nicht vererbt werden kann
	protected double balance;

	// Konstruktor der Klasse Account
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
	
	@Override
	
	// Die Methode print wird überschrieben, damit sie unseren Anforderungen
	// entspricht
	public void print() {
		System.out.println("Customer: " + this.getCustomer());
		System.out.println("Balance: " + this.getBalance());
	}
}