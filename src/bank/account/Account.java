package bank.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
//
// Wird von Java schon fixfertig zur Verfügung gestellt, da müssen wir nix mehr
// schreiben
public abstract class Account implements Printable, Serializable{ // Klassenkopf
	private String customer;
	private String pin;
	
	// Hier wird die Liste von Transaktionen erstellt. Dabei handelt es sich um
	// eine Instanzvariable, d.h. jeder Account hat seine eigene Liste an
	// Transaktionen, die auf dem Konto getätigt wurden. In den Methoden
	// deposit und withdraw werden dementsprechend neue Transaktionen erstellt
	// und zur Liste hinzugefügt
	private List<Transaction> transactions = new ArrayList<Transaction>();

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

	// Der boolean entfällt hier. Wir arbeiten nun mit Exceptions. Wenn ein
	// Fehler eintritt, wird eine Exception geworfen, ansonsten wird der
	// Ablauf normal fortgesetzt. Eine Exception bedeutet immer einen
	// Unterbruch des gewöhlichen Ablaufs
	// throws CredentialsException meldet, dass hier eventuell eine
	// entsprechende Exception geworfen werden muss
	public void checkPIN(String pin) throws CredentialsException {
		
		// Überprüft den eingegebenen Pin mit dem hinterlegten Pin
		if (!pin.equals(this.pin))
			
			// Falls der Pin falsch ist, wird diese Fehlermeldung geworfen
			throw new CredentialsException("Invalide PIN");
	}

	public double getBalance() {
		return balance;
	}

	// Boolean entfällt. Dass der Betrag dem Kontostand hinzugefügt wird
	// schreiben wir nur noch balance += amount;
	public void deposit(double amount) throws TransactionException {
		if (amount < 0)
			throw new TransactionException("Invalide Sume");
		balance += amount;
		Transaction transaction = new Transaction(amount, balance);
		transactions.add(transaction);
	}

	// Boolean entfällt. Dass der Betrag vom Kontostand abgezogen wird
	// schreiben wir nur noch balance -= amount;
	public void withdraw(double amount) throws TransactionException {
		if (amount < 0)
			throw new TransactionException("Invalide Sume");
		balance -= amount;
		Transaction transaction = new Transaction(amount, balance);
		transactions.add(transaction);
	}
	
	// Die erstellte Transaction Liste wird hier ausgegeben
	public List<Transaction> getTransactions() {
		return this.transactions;
	}
	
	// Ist eine Schnittstelle und gilt einheitlich für alle abgeleiteten
	// Klassen
	public abstract double getInterestRate();

	@Override

	// Die Methode print wird überschrieben, damit sie unseren Anforderungen
	// entspricht
	public void print() {
		System.out.println("Customer: " + this.getCustomer());
		System.out.println("Balance: " + this.getBalance());
	}
}