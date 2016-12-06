package bank.account;

// PersonalAccount, diese Klasse ist von der Klasse Account abgeleitet. Das
// bedeut dass von der Klasse Account geerbt wird. Die notwendigen Infos sind
// dort definiert, hier werden die höchstens noch genau für diesen Fall
// erweitert
public class PersonalAccount extends Account {
	
	// Das ist der Konstruktor von PersonalAccount
	public PersonalAccount(String customer, String pin, double balance) {
		
		// Ruft den Konstruktor der super-Klasse auf ->
		// Die Attribute werden so in der super Klasse Account abgeholt
		super(customer, pin, balance);
	}

	// Das ist der Konstruktor von PersonalAccount
	public PersonalAccount(String customer, String pin) {
		
		// Dies ist ein zweiter Konstruktor (Siehe Überladen von Konstruktoren
		// im Script)! haben wir gemacht, damit man auch Konten eröffnen kann,
		// ohne ständig einen Startbetrag mitzugeben. "this(customer, pin,
		// 0.00);" macht dann nichts anderes, als den ersten Konstruktor
		// aufzurufen, und ihm als balance 0.0 mitzugeben und somit ein Konto
		// zu erstellen, das noch kein Geld drauf hat.
		this(customer, pin, 0.0);
	}
	
	@Override
	public void print() {
		System.out.println("Type Personal Account");
		
		// greift auf die Klasse Account zu und dort auf die in @Override
		// ausführlich definierten 2 Zeilen zu... Somit haben wir hier
		// 3 Zeilen die ausgegeben werden
		super.print();
	}
}