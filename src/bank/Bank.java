package bank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import bank.account.Account;
import bank.account.AccountType;
import bank.account.CredentialsException;
import bank.account.PersonalAccount;
import bank.account.SavingsAccount;
import bank.account.Transaction;
import bank.account.TransactionException;

public class Bank implements Serializable {
	
	// Es wird ein String erstellt, mit dem Namen Datei
	private static final String DB_FILE = "Datei";

	// private static final int MAX_ACCOUNTS = 1000; k�nnen wir l�schen da wir
	// ab jetzt mit einer Map arbeiten

	// Zu Beginn hat es 0 Konten
	private int numAccounts = 0;

	// private Account[] accounts = new Account[MAX_ACCOUNTS]; wird
	// umgeschrieben von einem Array in eine Map

	// So erstellt man eine Map mit bereits existierenden Argumenten
	private Map<Integer, Account> accounts = new HashMap<Integer, Account>();
	
	// Hier konnten 2 CodeZeilen entfernt werden, da dies nun �ber den Enum
	// AccountType geregelt wird

	// Wir erstellen einen Konstruktor um die Bankdaten zu laden
	public Bank() {
		File newBank = new File(DB_FILE);
		if (newBank.exists())
			loadData();
		else {
			try {
				newBank.createNewFile();
			}
			catch (IOException e) {
				System.err.println("Fehler beim erstellen der Datei " +
			e.getMessage());
			}
		}
		
	}

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
	private Account getAccount(int nr) throws CredentialsException {
		if (nr < 0 || nr >= numAccounts)
			throw new CredentialsException("Ung�ltige Kontonummer");
		
		// Der beim Schl�ssel mit Nummer "nr" abgespeicherte Wert (vom Typ
		// "Account") wird in der Variable namens "account" gespeichert
		// (welcher nat�rlich auch vom Typ "Account" ist)
		Account account = accounts.get(nr);
		if (account == null)
			throw new CredentialsException("Wrong Account");
		return account;
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
	//
	// Neu wird nun der type via den Enum AccountType �berpr�ft
	public Integer openAccount(AccountType type, String customer, String pin,
			double balance) {
		
		// if (numAccounts >= MAX_ACCOUNTS) braucht es nicht mehr
		// return null; braucht es nicht mehr
		
		Integer nr = numAccounts++;

		// Account account; ist ein Objekt vom Typ Account mit dem Namen
		// Account
		Account account;

		// type wird �berpr�ft... siehe ganz Oben wo der definiert wird
		if (type == AccountType.PERSONAL)
			account = new PersonalAccount(customer, pin, balance);
		else {
			account = new SavingsAccount(customer, pin, balance);
		}

		// So wird eine unchecked Exception geschrieben, hier wird keine
		// Exception geworfen. Das Programm beendet nicht gleich sondern l�uft
		// noch weiter
		try {

			// Der neue account wird im Array abgespeichert, falls hier ein
			// Fehler passiert wird gecatcht, falls alles gut geht geht es
			// weiter im Code, hier mit return nr
			//
			// Neu nicht mehr im Array, sondern in der Map, an der Position
			// mit Schl�ssel "nr"
			accounts.put(nr, account);
		}

		// Das ist der Fehler der abgefangen wird, durch das l�uft das Programm
		// weiter
		catch (RuntimeException e) {
		}
		
		// Hier werden die Bankdaten gespeichert
		saveData();
		return nr;
	}

	// Hier wird das Konto ausgeglichen. Dazu braucht es die nr und den pin.
	// Es wir kontrolliert ob der account exisitiert und der eingegebene pin
	// g�ltig ist. Falls ja wird der Kontostand ausgegeben.
	public Double getBalance(int nr, String pin) throws CredentialsException {

		// getAccount(nr) gibt das gew�nschte Konto zur�ck. Dies wird
		// vor�bergehend in der variable "account" gespeichert, damit wir in
		// den folgenden Zeilen unkompliziert Methoden darauf anwenden k�nnen
		// (zB account.getCheckPIN())
		Account account = getAccount(nr);
		account.checkPIN(pin);
		if (account == null)
			throw new CredentialsException("Wrong Account");

		// Wie oben beschrieben ist in der Variable "account" das
		// gew�nschte Konto gespeichert. Da es sich dabei um ein Objekt vom
		// Typ "Account" handelt ( Account account = ...), werden die
		// Methoden aus der Account-Klasse verwendet. account.getBalance()
		// ruft also die getBalance()-Methode aus der Account Klasse auf.
		return account.getBalance();
	}

	// Hier wird Geld auf das Konto gelegt. Dazu braucht es die nr und den
	// Betrag. Es wird �berpr�ft ob das Konto existiert. Falls ja wird der
	// Betrag welcher einbezahlt wurde ausgegeben.
	// In diesem Stadium der �bung haben wir noch einen Boolean zur�ckgegeben,
	// der aussagt, ob das ein-/auszahlen geklappt hat account.deposit(amount)
	// => Aufruf der deposit-Methode aus der Account Klasse => Hat als
	// R�ckgabewert Boolean.
	public void deposit(int nr, double amount) throws CredentialsException,
	TransactionException {
		Account account = getAccount(nr);
		if (account == null)
			throw new CredentialsException("Wrong Account");
		account.deposit(amount);
		saveData();
	}

	// Hier wird Geld vom Konto entfernt. Dazu braucht es die nr den pin und
	// den Betrag. Es wird �berpr�ft ob das Konto existiert und ob der pin
	// korrekt ist. Falls ja wird der Betrag welcher abgehoben wurde ausgegeben
	public void withdraw(int nr, String pin, double amount) throws
	CredentialsException, TransactionException {
		Account account = getAccount(nr);
		account.checkPIN(pin);
		if (account == null)
			throw new CredentialsException("Account existiert nicht");
		account.withdraw(amount);
		saveData();
	}

	// Hier wird das Konto geschlossen. Dazu braucht es die nr und den pin.
	// Es wird �berpr�ft ob das Konto existiert und ob der pin korrekt
	// ist. Falls ja wird das Konto geschlossen.
	public void closeAccount(int nr, String pin) throws CredentialsException {
		Account account = getAccount(nr);
		account.checkPIN(pin);
		if (account == null)
			throw new CredentialsException("Wrong Account");
		
		// accounts [nr] = null; wird von String in Map umgeschrieben
		
		// In einer Map wird eine Nummer entfernt und nicht als ung�ltig
		// erkl�rt
		accounts.remove(nr);
		saveData();
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
	public void printAccounts() throws CredentialsException {

		// count Z�hlt von 0 s�mtliche Konten durch, nach jedem Durchlauf wird
		// es um 1 erh�ht
		for (int count = 0; count <= numAccounts; count++) {

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
	
	private void saveData() {
		try {
			
			// In Form eines DB-Files mit dem Namen Datei
			ObjectOutputStream out = new ObjectOutputStream(new
					FileOutputStream(DB_FILE));
			
			// Schreibt die ganze Klasse in die Datei Namens Datei
			out.writeObject(this);
			
			// Streams m�ssen immer geschlossen werden
			out.close();
		}
		catch (IOException e) {
			System.err.println("Daten konnten nicht gesichert werden" +
		e.getMessage());
		}
	}
	
	private void loadData() {
		try {
			
			// Liest die ganze Klasse von der Datei Namens Datei
			ObjectInputStream in = new ObjectInputStream(new
					FileInputStream(DB_FILE));
			
			// Liest die Datei aus und speichert die Daten in einer Variable
			// Namens Bank
			Bank bank = (Bank) in.readObject();
			in.close();
			this.numAccounts = bank.numAccounts;
			this.accounts = bank.accounts;
		}
		catch (Exception e) {
			System.err.println("Daten konnten nicht geladen werden" +
		e.getMessage());
		}
	}
	
	// Gibt eine Liste zur�ck mit allen Transaktionen die auf diesem Konten
	// statt gefunden haben wenn die Nummer und der Pin korrekt eingegeben
	// wurden
	public List<Transaction> getTransactions(int nr, String pin) throws
	CredentialsException {
		Account account = getAccount(nr);
		account.checkPIN(pin);
		return account.getTransactions();
	}
}