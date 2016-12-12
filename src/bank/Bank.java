package bank;

import bank.account.Account;
import bank.account.AccountType;
import bank.account.CredentialsException;
import bank.account.PersonalAccount;
import bank.account.SavingsAccount;
import bank.account.TransactionException;

public class Bank {

	// Zeile 13 ist eine Klassenvariable, diese ist für alle Instanzen
	// gleich => Instanzen = Instanzvariablen siehe Übung 1 Klasse Account
	// Dies ist eine Konstante, kann nur hier verändert werden
	// MAX_ACCOUNTS beschreibt die Grösse des Arrays
	private static final int MAX_ACCOUNTS = 1000;

	// Zu Beginn hat es 0 Konten
	private int numAccounts = 0;

	// Zur Verwaltung der Bankkonten dient ein Array [], welcher nur die
	// MAX_ACCOUNTS enthalten kann
	// Mittels new Account wird ein Objekt erstellt
	private Account[] accounts = new Account[MAX_ACCOUNTS];

	// Hier konnten 2 CodeZeilen entfernt werden, da dies nun über den Enum
	// AccountType geregelt wird

	// Hier braucht es keinen Konstruktor, da diese Klasse auf die importierte
	// Klasse bank.account.Account zugreift. Falls kein Konstructor erstellt
	// wird, macht JAVA selber einen, dieser kann jedoch wirr sein...

	// Alles was jetzt folgt sind Methoden

	// Die Methode gibt den Account mit der entsprechenden Kontonummer zurück.
	// Anstelle des getters könnte man überall auch direkt auf das accounts[]
	// array zugreifen. Die Methode ermöglicht aber zukünftig einfachere
	// Anpassung und ist beliebig erweiterbar, ohne dass man den ganzen Code
	// überall anpassen muss, wo man einen account abfragen möchte. Bereits
	// hier überprüft man zusätzlich, ob die Kontonummer überhaupt Sinn macht
	// (>0 und < numAccounts), hätte man keinen getter, müsste man das ganze
	// if/else konstrukt überall einfügen, was nicht nur schrecklich aussieht,
	// sondern auch sehr unpraktisch zu schreiben ist.
	private Account getAccount(int nr) throws CredentialsException {
		if (nr < 0 || nr >= numAccounts)
			throw new CredentialsException("Ungültige Kontonummer");
		Account account = accounts[nr];
		if (account == null)
			throw new CredentialsException("Wrong Account");
		return account;
		}

	// Hier wird ein Account eröffnet. Dazu braucht es den Customer und den Pin
	// Zuerst wird kontrolliert ob die MAX_ACCOUNTS Zahl nicht überschritten
	// wird. Falls alles in Ordnung ist, wird die Anzahl Accounts um 1
	// erhöht, ein neuer Account erstellt und die Account nr zurück gegeben.
	// Hier haben wir Integer benutzt, damit wir im Falle, dass keine weiteren
	// Accounts erstellt werden dürfen (if Bedingung), null zurückgeben können.
	// int als primitiver Datentyp bietet diese Möglcihkeit nicht, Integer als
	// Objekt jedoch schon.
	//
	// Die Methode wird so ergänzt, dass der Konten type und die balance
	// überprüft werden können (Integer type, double balance)
	//
	// Neu wird nun der type via den Enum AccountType überprüft
	public Integer openAccount(AccountType type, String customer, String pin,
			double balance) {
		if (numAccounts >= MAX_ACCOUNTS)
			return null;
		Integer nr = numAccounts++;

		// Account account; ist ein Objekt vom Typ Account mit dem Namen
		// Account
		Account account;

		// type wird überprüft... siehe ganz Oben wo der definiert wird
		if (type == AccountType.PERSONAL)
			account = new PersonalAccount(customer, pin, balance);
		else {
			account = new SavingsAccount(customer, pin, balance);
		}
		try {
			accounts[nr] = account;
		}
		catch (RuntimeException e) {
		}

		return nr;
	}

	// Hier wird das Konto ausgeglichen. Dazu braucht es die nr und den pin.
	// Es wir kontrolliert ob der account exisitiert und der eingegebene pin
	// gültig ist. Falls ja wird der Kontostand ausgegeben.
	public Double getBalance(int nr, String pin) throws CredentialsException {

		// getAccount(nr) gibt das gewünschte Konto zurück. Dies wird
		// vorübergehend in der variable "account" gespeichert, damit wir in
		// den folgenden Zeilen unkompliziert Methoden darauf anwenden können
		// (zB account.getCheckPIN())
		Account account = getAccount(nr);
		account.checkPIN(pin);
		if (account == null)
			throw new CredentialsException("Wrong Account");

		// Wie oben beschrieben ist in der Variable "account" das
		// gewünschte Konto gespeichert. Da es sich dabei um ein Objekt vom
		// Typ "Account" handelt ( Account account = ...), werden die
		// Methoden aus der Account-Klasse verwendet. account.getBalance()
		// ruft also die getBalance()-Methode aus der Account Klasse auf.
		return account.getBalance();
	}

	// Hier wird Geld auf das Konto gelegt. Dazu braucht es die nr und den
	// Betrag. Es wird überprüft ob das Konto existiert. Falls ja wird der
	// Betrag welcher einbezahlt wurde ausgegeben.
	// In diesem Stadium der Übung haben wir noch einen Boolean zurückgegeben,
	// der aussagt, ob das ein-/auszahlen geklappt hat account.deposit(amount)
	// => Aufruf der deposit-Methode aus der Account Klasse => Hat als
	// Rückgabewert Boolean.
	public void deposit(int nr, double amount) throws CredentialsException,
	TransactionException {
		Account account = getAccount(nr);
		if (account == null)
			throw new CredentialsException("Wrong Account");
		account.deposit(amount);
	}

	// Hier wird Geld vom Konto entfernt. Dazu braucht es die nr den pin und
	// den Betrag. Es wird überprüft ob das Konto existiert und ob der pin
	// korrekt ist. Falls ja wird der Betrag welcher abgehoben wurde ausgegeben
	public void withdraw(int nr, String pin, double amount) throws
	CredentialsException, TransactionException{
		Account account = getAccount(nr);
		account.checkPIN(pin);
		if (account == null)
			throw new CredentialsException("Account existiert nicht");
			account.withdraw(amount);
	}

	// Hier wird das Konto geschlossen. Dazu braucht es die nr und den pin.
	// Es wird überprüft ob das Konto existiert und ob der pin korrekt
	// ist. Falls ja wird das Konto geschlossen.
	public void closeAccount(int nr, String pin) throws CredentialsException {
		Account account = getAccount(nr);
		account.checkPIN(pin);
		if (account == null)
			throw new CredentialsException("Wrong Account");
			accounts [nr] = null;
	}

	// Gibt sämtliche existierende Bankkonten aus
	// Die for-schleife iteriert über alle möglichen Kontonummern (von 0 bis
	// MAX_ACCOUNTS)
	// Bei jeder Kontonummer wird mit dem if-statement geprüft, ob mit dieser
	// Kontonummer ein Konto existiert. Falls ja, wird die print()-Methode auf
	// dieses Konto aufgerufen (Oder besser gesagt die print()-Methode der
	// Klasse des entsprechenden Accounttyps - diese wiederum ruft dann die
	// Methode der Superklasse auf) Geschlossene Konten sollten nicht
	// aufgelistet werden, da diese "null" sind und dadurch das if-statement
	// nicht erfüllt ist
	public void printAccounts() throws CredentialsException {

		// count Zählt von 0 bis zur maximalen Anzahl Konten, nach jedem
		// Durchlauf wird es um 1 erhöht
		for (int count = 0; count <= MAX_ACCOUNTS; count++) {

			// Ist ein Konto nicht "null" wird der Befehl in der Folgezeile
			// (oder innerhalb {}) ausgeführt
			if (getAccount(count) != null)

				// Sofern das if erfüllt ist, wird das Konto ausgegeben
				// Die printmetode wird auf das Konto mit der Kontonummer count
				// aufgerufen. Diese print-Methode gibt wiederum die ganzen
				// Informationen zum Konto als "println" in der Konsole aus
				getAccount(count).print();
		}
	}
}