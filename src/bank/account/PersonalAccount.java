package bank.account;

// PersonalAccount, diese Klasse ist von der Klasse Account abgeleitet. Das
// bedeut dass von der Klasse Account geerbt wird. Die notwendigen Infos sind
// dort definiert, hier werden die höchstens noch genau für diesen Fall
// erweitert
public class PersonalAccount extends Account {
	
	// Das ist die Methode PersonalAccount mit ihren Attributen()
	public PersonalAccount(String customer, String pin, double balance) {
		
		// Die Attribute werden in der super Klasse Account abgeholt
		super(customer, pin, balance);
	}

	// Die Methode PersonalAccount
	public PersonalAccount(String customer, String pin) {
		
		// Dies ist ein zweiter Konstruktor (Siehe Überladen von Konstruktoren
		// im Script)! haben wir gemacht, damit man auch Konten eröffnen kann,
		// ohne ständig einen Startbetrag mitzugeben. "this(customer, pin,
		// 0.00);" macht dann nichts anderes, als den ersten Konstruktor
		// aufzurufen, und ihm als balance 0.0 mitzugeben und somit ein Konto
		// zu erstellen, das noch kein Geld drauf hat.
		this(customer, pin, 0.0);
	}
}