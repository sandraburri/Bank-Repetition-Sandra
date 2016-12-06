package bank;

import bank.account.Account;
import bank.account.PersonalAccount;
import bank.account.SavingsAccount;

public class Bank {

	// Zeile 13 ist eine Klassenvariable, diese ist für alle Instanzen
	// gleich => Instanzen = Instanzvariablen siehe Übung 1 Klasse Account
	// Dies ist eine Konstante, kann nur hier verändert werden
	// MAX_ACCOUNTS beschreibt die Grösse des Arrays
	private static final int MAX_ACCOUNTS = 1000;

	// Zu beginn hat es 0 Konten
	private int numAccounts = 0;

	// Zur Verwaltung der Bankkonten dient ein Array [], welcher nur die
	// MAX_ACCOUNTS enthalten kann
	// Mittels new Account wird ein Objekt erstellt
	private Account[] accounts = new Account[MAX_ACCOUNTS];

	// Die beiden folgenden CodeZeilen sind Klassenvariablen und Konstanten
	// = 1 und = 2 dienen hier nicht als Mengenangabe sondern als Zuordnung
	// = 1 und = 2 definieren den type für die Methode openAccount
	public static final int PERSONAL_ACCOUNT = 1;
	public static final int SAVINGS_ACCOUNT = 2;

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
	private Account getAccount(int nr) {
		if (nr >= 0 && nr < numAccounts)
			return accounts[nr];
		else {
			return null;
		}
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
	public Integer openAccount(Integer type, String customer, String pin,
			double balance) {
		if (numAccounts >= MAX_ACCOUNTS)
			return null;
		Integer nr = numAccounts++;

		// Account account; ist ein Objekt vom Typ Account mit dem Namen
		// Account
		Account account;

		// type wird überprüft... siehe ganz Oben wo der definiert wird
		if (type == PERSONAL_ACCOUNT)
			account = new PersonalAccount(customer, pin, balance);
		else {
			account = new SavingsAccount(customer, pin, balance);
		}
		accounts[nr] = account;
		return nr;
	}

	// Hier wird das Konto ausgeglichen. Dazu braucht es die nr und den pin.
	// Es wir kontrolliert ob der account exisitiert und der eingegebene pin
	// gültig ist. Falls ja wird der Kontostand ausgegeben.
	public Double getBalance(int nr, String pin) {

		// getAccount(nr) gibt das gewünschte Konto zurück. Dies wird
		// vorübergehend in der variable "account" gespeichert, damit wir in
		// den folgenden Zeilen unkompliziert Methoden darauf anwenden können
		// (zB account.getCheckPIN())
		Account account = getAccount(nr);
		if (account != null && account.checkPIN(pin))

			// Wie oben beschrieben ist in der Variable "account" das
			// gewünschte Konto gespeichert. Da es sich dabei um ein Objekt vom
			// Typ "Account" handelt ( Account account = ...), werden die
			// Methoden aus der Account-Klasse verwendet. account.getBalance()
			// ruft also die getBalance()-Methode aus der Account Klasse auf.
			return account.getBalance();
		else {
			return null;
		}
	}

	// Hier wird Geld auf das Konto gelegt. Dazu braucht es die nr und den
	// Betrag. Es wird überprüft ob das Konto existiert. Falls ja wird der
	// Betrag welcher einbezahlt wurde ausgegeben.
	// In diesem Stadium der Übung haben wir noch einen Boolean zurückgegeben,
	// der aussagt, ob das ein-/auszahlen geklappt hat account.deposit(amount)
	// => Aufruf der deposit-Methode aus der Account Klasse => Hat als
	// Rückgabewert Boolean.
	public boolean deposit(int nr, double amount) {
		Account account = getAccount(nr);
		if (account == null)
			return false;
		return account.deposit(amount);
	}

	// Hier wird Geld vom Konto entfernt. Dazu braucht es die nr den pin und
	// den Betrag. Es wird überprüft ob das Konto existiert und ob der pin
	// korrekt ist. Falls ja wird der Betrag welcher abgehoben wurde ausgegeben
	public boolean withdraw(int nr, String pin, double amount) {
		Account account = getAccount(nr);
		if (account == null || !account.checkPIN(pin))
			return false;

		// Hier speichern wir den Boolean, der die withdraw-Methode aus der
		// Account-Klasse zurückgibt zuerst in der Variable "result" und geben
		// anschliessend den Wert dieser Variable zurück... Könnte man
		// natürlich auch so machen wie bei deposit.
		boolean result = account.withdraw(amount);
		return result;
	}

	// Hier wird das Konto geschlossen. Dazu braucht es die nr und den pin.
	// Es wird überprüft ob das Konto existiert und ob der pin korrekt
	// ist. Falls ja wird das Konto geschlossen.
	public boolean closeAccount(int nr, String pin) {
		Account account = getAccount(nr);
		if (account == null || !account.checkPIN(pin))
			return false;

		// Hier setzten wir das Konto-Objekt auf null. Somit wird es gleich
		// behandelt, wie wenn ein Konto von der getAccount()-Methode nicht
		// gefunden wird (gibt auch null zurück).
		accounts[nr] = null;

		// Genau wie bei deposit/withdraw haben wir auch hier booleans als
		// Rückgabewert verwendet, damit wir wissen ob das löschen erfolgreich
		// war.
		return true;
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
	public void printAccounts() {

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