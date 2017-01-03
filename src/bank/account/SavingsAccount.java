package bank.account;

//SavingsAccount, diese Klasse ist von der Klasse Account abgeleitet. Das
//bedeut dass von der Klasse Account geerbt wird. Die notwendigen Infos sind
//dort definiert, hier werden die höchstens noch genau für diesen Fall
//erweitert
public class SavingsAccount extends Account {
	
	// Zeile 13 ist eine Klassenvariable, diese ist für alle Instanzen
	// gleich => Instanzen = Instanzvariablen siehe Übung 1 Klasse Account
	// Dies ist eine Konstante, kann nur hier verändert werden
	// WITHDRAW_LIMIT beschreibt das Rückzugslimit, hier 5000
	public static final double WITHDRAW_LIMIT = 5000;
	public static final double INTEREST_RATE = 0.045;
	
	// Das ist ein Konstruktor von SavingsAccount
	public SavingsAccount(String customer, String pin, double balance) {
		
		// Genauer: Ruft den Konstruktor der super-Klasse auf ->
		// Die Attribute werden so in der super Klasse Account abgeholt
		super(customer, pin, balance);
	}

	// Das ist ein Konstruktor von SavingsAccount
	public SavingsAccount(String customer, String pin) {
		
		// Dies ist ein zweiter Konstruktor (Siehe Überladen von Konstruktoren
		// im Script)! haben wir gemacht, damit man auch Konten eröffnen kann,
		// ohne ständig einen Startbetrag mitzugeben. "this(customer, pin,
		// 0.00);" macht dann nichts anderes, als den ersten Konstruktor
		// aufzurufen, und ihm als balance 0.0 mitzugeben und somit ein Konto
		// zu erstellen, das noch kein Geld drauf hat.
		this(customer, pin, 0.0);
	}
	
	@Override
	
	// Überschreibt die Methode withdraw aus der Klasse Account insofern, dass
	// neu noch der Kontostand (darf nicht negativ werden) und das
	// WITHDRAW_LIMIT überprüft werden müssen
	//
	// Boolean entfällt.
		public void withdraw (double amount) throws TransactionException {
		if (amount > balance)
			throw new TransactionException("Der Betrag ist grösser als das Guthaben");
		if(amount > WITHDRAW_LIMIT)
			throw new TransactionException("Der Betrag übersteigt die Limite");
		super.withdraw(amount);
	}
	
	public double getInterestRate() {
		return INTEREST_RATE;
	}
	
	@Override
	public void print() {
		System.out.println("Type Savings Account");
		
		// greift auf die Klasse Account zu und dort auf die in @Override
		// ausführlich definierten 2 Zeilen zu... Somit haben wir hier
		// 3 Zeilen die ausgegeben werden
		super.print();
	}
}