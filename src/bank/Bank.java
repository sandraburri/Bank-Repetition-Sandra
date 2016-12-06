package bank;

import bank.account.Account;
import bank.account.PersonalAccount;
import bank.account.SavingsAccount;

public class Bank {

	// Zeile 13 ist eine Klassenvariable, diese ist f�r alle Instanzen
	// gleich => Instanzen = Instanzvariablen siehe �bung 1 Klasse Account
	// Dies ist eine Konstante, kann nur hier ver�ndert werden
	// MAX_ACCOUNTS beschreibt die Gr�sse des Arrays
	private static final int MAX_ACCOUNTS = 1000;

	// Zu beginn hat es 0 Konten
	private int numAccounts = 0;

	// Zur Verwaltung der Bankkonten dient ein Array [], welcher nur die
	// MAX_ACCOUNTS enthalten kann
	// Mittels new Account wird ein Objekt erstellt
	private Account[] accounts = new Account[MAX_ACCOUNTS];

	// Die beiden folgenden CodeZeilen sind Klassenvariablen und Konstanten
	// = 1 und = 2 dienen hier nicht als Mengenangabe sondern als Zuordnung
	// = 1 und = 2 definieren den type f�r die Methode openAccount
	public static final int PERSONAL_ACCOUNT = 1;
	public static final int SAVINGS_ACCOUNT = 2;

	// Hier braucht es keinen Konstruktor, da diese Klasse auf die importierte
	// Klasse bank.account.Account zugreift. Falls kein Konstructor erstellt
	// wird, macht JAVA selber einen, dieser kann jedoch wirr sein...

	// Alles was jetzt folgt sind Methoden

	// Die Methode gibt den Account mit der entsprechenden Kontonummer zur�ck.
	// Anstelle des getters k�nnte man �berall auch direkt auf das accounts[]
	// array zugreifen. Die Methode erm�glicht aber zuk�nftig einfachere
	// Anpassung und ist beliebig erweiterbar, ohne dass man den ganzen Code
	// �berall anpassen muss, wo man einen account abfragen m�chte. Bereits
	// hier �berpr�ft man zus�tzlich, ob die Kontonummer �berhaupt Sinn macht
	// (>0 und < numAccounts), h�tte man keinen getter, m�sste man das ganze
	// if/else konstrukt �berall einf�gen, was nicht nur schrecklich aussieht,
	// sondern auch sehr unpraktisch zu schreiben ist.
	private Account getAccount(int nr) {
		if (nr >= 0 && nr < numAccounts)
			return accounts[nr];
		else {
			return null;
		}
	}

	// Hier wird ein Account er�ffnet. Dazu braucht es den Customer und den Pin
	// Zuerst wird kontrolliert ob die MAX_ACCOUNTS Zahl nicht �berschritten
	// wird. Falls alles in Ordnung ist, wird die Anzahl Accounts um 1
	// erh�ht, ein neuer Account erstellt und die Account nr zur�ck gegeben.
	// Hier haben wir Integer benutzt, damit wir im Falle, dass keine weiteren
	// Accounts erstellt werden d�rfen (if Bedingung), null zur�ckgeben k�nnen.
	// int als primitiver Datentyp bietet diese M�glcihkeit nicht, Integer als
	// Objekt jedoch schon.
	//
	// Die Methode wird so erg�nzt, dass der Konten type und die balance
	// �berpr�ft werden k�nnen (Integer type, double balance)
	public Integer openAccount(Integer type, String customer, String pin,
			double balance) {
		if (numAccounts >= MAX_ACCOUNTS)
			return null;
		Integer nr = numAccounts++;

		// Account account; ist ein Objekt vom Typ Account mit dem Namen
		// Account
		Account account;

		// type wird �berpr�ft... siehe ganz Oben wo der definiert wird
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
	// g�ltig ist. Falls ja wird der Kontostand ausgegeben.
	public Double getBalance(int nr, String pin) {

		// getAccount(nr) gibt das gew�nschte Konto zur�ck. Dies wird
		// vor�bergehend in der variable "account" gespeichert, damit wir in
		// den folgenden Zeilen unkompliziert Methoden darauf anwenden k�nnen
		// (zB account.getCheckPIN())
		Account account = getAccount(nr);
		if (account != null && account.checkPIN(pin))

			// Wie oben beschrieben ist in der Variable "account" das
			// gew�nschte Konto gespeichert. Da es sich dabei um ein Objekt vom
			// Typ "Account" handelt ( Account account = ...), werden die
			// Methoden aus der Account-Klasse verwendet. account.getBalance()
			// ruft also die getBalance()-Methode aus der Account Klasse auf.
			return account.getBalance();
		else {
			return null;
		}
	}

	// Hier wird Geld auf das Konto gelegt. Dazu braucht es die nr und den
	// Betrag. Es wird �berpr�ft ob das Konto existiert. Falls ja wird der
	// Betrag welcher einbezahlt wurde ausgegeben.
	// In diesem Stadium der �bung haben wir noch einen Boolean zur�ckgegeben,
	// der aussagt, ob das ein-/auszahlen geklappt hat account.deposit(amount)
	// => Aufruf der deposit-Methode aus der Account Klasse => Hat als
	// R�ckgabewert Boolean.
	public boolean deposit(int nr, double amount) {
		Account account = getAccount(nr);
		if (account == null)
			return false;
		return account.deposit(amount);
	}

	// Hier wird Geld vom Konto entfernt. Dazu braucht es die nr den pin und
	// den Betrag. Es wird �berpr�ft ob das Konto existiert und ob der pin
	// korrekt ist. Falls ja wird der Betrag welcher abgehoben wurde ausgegeben
	public boolean withdraw(int nr, String pin, double amount) {
		Account account = getAccount(nr);
		if (account == null || !account.checkPIN(pin))
			return false;

		// Hier speichern wir den Boolean, der die withdraw-Methode aus der
		// Account-Klasse zur�ckgibt zuerst in der Variable "result" und geben
		// anschliessend den Wert dieser Variable zur�ck... K�nnte man
		// nat�rlich auch so machen wie bei deposit.
		boolean result = account.withdraw(amount);
		return result;
	}

	// Hier wird das Konto geschlossen. Dazu braucht es die nr und den pin.
	// Es wird �berpr�ft ob das Konto existiert und ob der pin korrekt
	// ist. Falls ja wird das Konto geschlossen.
	public boolean closeAccount(int nr, String pin) {
		Account account = getAccount(nr);
		if (account == null || !account.checkPIN(pin))
			return false;

		// Hier setzten wir das Konto-Objekt auf null. Somit wird es gleich
		// behandelt, wie wenn ein Konto von der getAccount()-Methode nicht
		// gefunden wird (gibt auch null zur�ck).
		accounts[nr] = null;

		// Genau wie bei deposit/withdraw haben wir auch hier booleans als
		// R�ckgabewert verwendet, damit wir wissen ob das l�schen erfolgreich
		// war.
		return true;
	}

	// Gibt s�mtliche existierende Bankkonten aus
	// Die for-schleife iteriert �ber alle m�glichen Kontonummern (von 0 bis
	// MAX_ACCOUNTS)
	// Bei jeder Kontonummer wird mit dem if-statement gepr�ft, ob mit dieser
	// Kontonummer ein Konto existiert. Falls ja, wird die print()-Methode auf
	// dieses Konto aufgerufen (Oder besser gesagt die print()-Methode der
	// Klasse des entsprechenden Accounttyps - diese wiederum ruft dann die
	// Methode der Superklasse auf) Geschlossene Konten sollten nicht
	// aufgelistet werden, da diese "null" sind und dadurch das if-statement
	// nicht erf�llt ist
	public void printAccounts() {

		// count Z�hlt von 0 bis zur maximalen Anzahl Konten, nach jedem
		// Durchlauf wird es um 1 erh�ht
		for (int count = 0; count <= MAX_ACCOUNTS; count++) {

			// Ist ein Konto nicht "null" wird der Befehl in der Folgezeile
			// (oder innerhalb {}) ausgef�hrt
			if (getAccount(count) != null)

				// Sofern das if erf�llt ist, wird das Konto ausgegeben
				// Die printmetode wird auf das Konto mit der Kontonummer count
				// aufgerufen. Diese print-Methode gibt wiederum die ganzen
				// Informationen zum Konto als "println" in der Konsole aus
				getAccount(count).print();
		}
	}
}