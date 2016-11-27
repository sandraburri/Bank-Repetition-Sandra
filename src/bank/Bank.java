package bank;

import bank.account.Account;

public class Bank {

	// Dies ist eine Konstante, kann nur hier verändert werden
	private static final int MAX_ACCOUNTS = 1000;

	// Zeilen 8 und 14 sind Klassenvariablen, diese sind für alle Instanzen
	// gleich => Instanzen = Instanzvariablen siehe Übung 1
	// Zu beginn hat es 0 Konten
	// Zu den Klassenvariablen siehe Postit im Ordner.....
	private int numAccounts = 0;

	// Zur Verwaltung der Bankkonten dient ein Array [], welcher nur die
	// MAX_ACCOUNTS enthalten kann
	// Mittels new Account wird ein Objekt erstellt
	private Account[] accounts = new Account[MAX_ACCOUNTS];

	// Hier braucht es keinen Konstruktor, da diese Klasse auf die importierte
	// Klasse bank.account.Account zugreift. Falls kein Konstructor erstellt
	// wird, macht JAVA selber einen, dieser kann jedoch wirr sein...

	// Alles was jetzt folgt sind Methoden
		
	// Was ist die genaue Beschreibung dieser Methode? Die braucht es, damit
	// die nachfolgenden Methoden überhaupt ausgeführt werden können. (Keine
	// Fehlermeldungen ausgeben)
	private Account getAccount(int nr) {
		if (nr >= 0 && nr < numAccounts)
			return accounts[nr];
		else {
			return null;
		}
	}
	
	// Hier wird ein Account eröffnet. Dazu braucht es den Customer und den Pin.
	// Zuerst wird kontrolliert ob die MAX_ACCOUNTS Zahl nicht überschritten
	// wird. Falls alles in Ordnung ist, wird die Anzahl Accounts um 1
	// erhöht, ein neuer Account erstellt und die Account nr zurück gegeben.
	// ??? Warum Integer und nicht int?
	public Integer openAccount(String customer, String pin) {
		if (numAccounts >= MAX_ACCOUNTS)
			return null;
		Integer nr = numAccounts++;
		Account account = new Account(customer, pin);
		accounts[nr] = account;
		return nr;
	}
	
	// Hier wird das Konto ausgeglichen. Dazu braucht es die nr und den pin.
	// Es wir kontrolliert ob der account exisitiert und der eingegebene pin
	// gültig ist. Falls ja wird der Kontostand ausgegeben.
	public Double getBalance(int nr, String pin) {
		Account account = getAccount(nr); // was genau macht diese Zeile, die
		// hat es auch in den weiteren Methoden. Bestimmt die das Konto womit
		// gerade gearbeitet wird?
		if (account != null && account.checkPIN(pin))
			return account.getBalance(); // Bitte für die Theorie hier erläutern
		// was mit account.getBalance() genau erreicht wird
		else {
			return null;
		}
	}
	
	// Hier wird Geld auf das Konto gelegt. Dazu braucht es die nr und den
	// Betrag. Es wird überprüft ob das Konto existiert. Falls ja wird der
	// Betrag welcher einbezahlt wurde ausgegeben.
	public boolean deposit(int nr, double amount) {
		Account account = getAccount(nr);
		if(account == null)
			return false;
		return account.deposit(amount);
	}
	
	// Hier wird Geld vom Konto entfernt. Dazu braucht es die nr den pin und den
	// Betrag. Es wird überprüft ob das Konto existiert und ob der pin korrekt
	// ist. Falls ja wird der Betrag welcher abgehoben wurde ausgegeben.
	public boolean withdraw (int nr, String pin, double amount) {
		Account account = getAccount(nr);
		if (account == null || !account.checkPIN(pin))
			return false;
		boolean result = account.withdraw(amount); // warum wird das hier in 2
		// Zeilen geschrieben? Oben haben wir auch einen boolean und eine
		// Überprüfung, aber die Ausgabe erfolgt in einer Zeile...
		return result;
	}
	
	// Hier wird das Konto geschlossen. Dazu braucht es die nr und den pin.
	// Es wird überprüft ob das Konto existiert und ob der pin korrekt
	// ist. Falls ja wird das Konto geschlossen.
	public boolean closeAccount(int nr, String pin) {
		Account account = getAccount(nr);
		if (account == null || !account.checkPIN(pin))
			return false;
		accounts[nr] = null; // Wird hier das Konto auf ungültig gesetzt?
		return true; // Erfolgt hier die Bestätigung, dass das Konto geschlossen
		// wurde?
	}
}
