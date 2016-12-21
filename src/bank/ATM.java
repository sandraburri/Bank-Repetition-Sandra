package bank;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import bank.account.AccountType;
import bank.account.CredentialsException;
import bank.account.Transaction;
import bank.account.TransactionException;

@SuppressWarnings("nls")
public class ATM {

	// Dies ist ein Objekt vom Typ Bank mit dem Namen bank, ist eine Instanz-
	// variable
	private Bank bank;

	// Das ist der Konstruktor des ATM. Anstatt ein (String customer) geben wir
	// hier (Bank bank) mit
	public ATM(Bank bank) {
		this.bank = bank;
	}

	// Dieser Befehl löst den Import des Scanner aus (Zeile 3)
	// Wird benötigt um Texteingaben zu interpretieren
	private Scanner scanner;

	// Diese Methode bringt das Programm mit run zum laufen, regelt die Ein-
	// und Ausgabe, das was der Kunde sieht und dort wo er eingeben kann...
	public void run() {

		// Die while Schleife wird durchlaufen solange der Benutzer Eingaben
		// tätigt oder abbricht (Exit)
		while (true) {

			// Der while Loop ist hier immer true. Das heisst so lange der
			// Kunde etwas eingibt läuft es immer weiter, bis zum (Exit)

			// Die folgenden 13 Zeilen sind Ausgaben auf der Konsole, sprich
			// für den Kunden ersichtlich Text aber auch Leerzeilen
			System.out.println("   A   TTTTTTT M     M");
			System.out.println("  A A     T    MM   MM");
			System.out.println(" AAAAA    T    M M M M");
			System.out.println("A     A   T    M  M  M");
			System.out.println();
			System.out.println("A  Open Account");
			System.out.println("B  Get Balance");
			System.out.println("C  Get Transaction");
			System.out.println("D  Deposit");
			System.out.println("E  Withdraw");
			System.out.println("F  Close Account");
			System.out.println("X  Exit");
			System.out.println();
			System.out.print("> ");

			// ein neues Objekt wird erzeugt mit = new Scanner(System.in) ,
			// wird während der Erstellung des Scanner Objekts automatisch
			// instanziert
			scanner = new Scanner(System.in);

			// Das Programm wartet auf die Eingabe des Buchstabens UND Enter,
			// nextLine() verlangt das Enter, .toUpperCase() macht aus der
			// Eingabe automatisch Grossbuchstaben, damit das nicht noch
			// kontrolliert werden muss
			String choice = scanner.nextLine().toUpperCase();

			// switch prüft mehrere Zustände der choice Variable ab
			// Jedes case entspricht einem if
			switch (choice) {
			case "A":
				openAccount();
				break;
			case "B":
				getBalance();
				break;
			case "C":
				getTransactions();
				break;
			case "D":
				deposit();
				break;
			case "E":
				withdraw();
				break;
			case "F":
				closeAccount();
				break;
			case "X":
				try {
					bank.printAccounts();
				} catch (CredentialsException e) {

					// Hier wird die Fehlermeldung ausgegeben. Die
					// Fehlermeldung haben wir zuvor mit throw...("....")
					// am entsprechenden Ort definiert
					System.err.println(e.getMessage());
				}
				System.exit(0);
				break;

			// default = alle nicht behandelten Fälle (nicht A,B,C,D,E,X)
			default:

				// Ausgabe bei einem Eingabe Fehler
				System.out.println("Error: Invalid input");
			}

			// Wenn der Kunde einen Buchstaben ausgewählt hat oder eine Zahl
			// eingegeben hat, muss er dies mit Enter bestätigen
			System.out.println("Hite Enter to continue...");

			// Der Scanner gibt dann die entsprechende nächste Zeile aus
			// Die Eingabe wird hier nicht wie bei choice (Zeile 63) in einer
			// Variable gespeichert, stattdessen warten wir einfach nur auf den
			// nextLine Befehl (sprich "Enter")
			scanner.nextLine();
		}
	}

	// Ab hier folgen die Methoden, damit das was der Kunde sieht und er ein-
	// gibt überhaupt durchgeführt werden kann und auch korrekt funktioniert...
	// Ergänzung: In jedem switch-case wird die entsprechende Methode
	// aufgerufen
	// Hier ist nun definiert, was diese Methode genau tut

	// In dieser Methode wird der Account eröffnet
	private void openAccount() {

		// Der Kunde wird hier aufgefordert den Konten Type zu wählen
		System.out.print("Type (Personal/Savings: ");

		// // Neu wird nun der type via den Enum AccountType überprüft
		AccountType type = AccountType.PERSONAL;
		if (scanner.nextLine().toUpperCase().equals("S"))

			// AccountType type = Das muss nicht noch einmal so definiert
			// werden, dies ist bereits in Zeile 119 geschehen
			type = AccountType.SAVINGS;

		// Der Kunde wird mit dieser Ausgabe aufgefordert seinen Namen
		// einzugeben
		System.out.println("Customer: ");

		// Wenn der Kunde seinen Namen eingegeben hat, geht der scanner auf die
		// nächste Zeile
		// Genauer: Speichert die Eingabe (eine Zeile) im String "customer" ab
		String customer = scanner.nextLine();
		System.out.println("PIN: ");
		String pin = scanner.nextLine();

		// Der Anfangskontostand wird hier vom Kunden eingegeben
		System.out.print("Start balance: ");
		double balance = Double.parseDouble(scanner.nextLine());

		// Hier wird dem neuen Bankkonto eine nr vergeben. Die notwendigen
		// Informationen dazu werden bei der Klasse Bank bei openAccount
		// abgeholt.
		Integer nr = bank.openAccount(type, customer, pin, balance);

		// Wenn die Nummer gültig ist. In unserem Fall, wenn die Nummer die
		// MAX_ACCOUNTS nicht überschreitet und der Kunde alles korrekt ein-
		// gegeben hat
		// => bank.openAccount gibt einen Integer zurück (return Wert)
		if (nr != null)
			System.out.println("Account with number " + nr + " opened");
		else
			System.out.println("Faild to oppen account");
	}

	// In dieser Methode wird der Kontostand ausgegeben
	private void getBalance() {

		// Der Kunde wird mit dieser Ausgabe aufgefordert seine Kontonummer
		// einzugeben
		System.out.println("Account Nr: ");

		// Das Parsen fährt durch einen Text und sucht die gewünschten Sachen
		// heraus. In unserem Fall ist ein Int gesucht
		int nr = Integer.parseInt(scanner.nextLine());
		System.out.println("PIN: ");
		String pin = scanner.nextLine();
		try {

			// Double ist hier ein Objekt und kein primitiver Datentyp
			Double balance = bank.getBalance(nr, pin);
			System.out.println("Balance of Account with number " + nr + " is "
			+ balance);
		} catch (CredentialsException e) {
			System.err.println(e.getMessage());
		}
	}

	// Hier wird die Liste sämtlicher Transactionen vom betroffenen Konto
	// ausgegeben
	private void getTransactions() {
		System.out.print("Account Nr: ");
		int nr = Integer.parseInt(scanner.nextLine());
		System.out.print("PIN: ");
		String pin = scanner.nextLine();
		System.out.println("Transactions of account with number " + nr);
		try {
			List<Transaction> transactions = bank.getTransactions(nr, pin);
			for (int i = 0; i < transactions.size(); i++) {
				Transaction transaction = transactions.get(i);
				double amount = transaction.getAmount();
				double balance = transaction.getBalance();
				Date valuta = transaction.getValuta();
				System.out.print("Date: " + valuta + "\n Amount: " + amount +
						"\n New balance is: " + balance + "\n");
			}
		} catch (CredentialsException e) {
			System.err.println(e.getMessage());
		}
	}

	// Mit dieser Methode wird ermöglicht, dass der Kunde Geld auf sein
	// Konto einzahlen kann
	private void deposit() {
		System.out.println("Account Nr: ");
		int nr = Integer.parseInt(scanner.nextLine());
		System.out.println("Amount: ");

		// Das Parsen fährt durch einen Text und sucht die gewünschten Sachen
		// heraus. In unserem Fall ist ein double gesucht
		double amount = Double.parseDouble(scanner.nextLine());
		try {
			bank.deposit(nr, amount);
			System.out.println("Deposit of " + amount + " to account with number " + nr);
		} catch (CredentialsException | TransactionException e) {
			System.err.println(e.getMessage());
		}
	}

	// Mit dieser Methode wird ermöglicht, dass der Kunde Geld von seinem Konto
	// abheben kann
	private void withdraw() {
		System.out.println("Account Nr: ");
		int nr = Integer.parseInt(scanner.nextLine());
		System.out.println("PIN: ");
		String pin = scanner.nextLine();
		System.out.println("Amount: ");
		double amount = Double.parseDouble(scanner.nextLine());
		try {
			bank.withdraw(nr, pin, amount);
			System.out.println("Withdraw of " + amount + " from account with number " + nr);
		} catch (CredentialsException e) {
			System.err.println(e.getMessage());
		} catch (TransactionException e) {
			System.err.println(e.getMessage());
		}
	}

	// Diese Methode ermöglicht es, ein Konto zu schliessen
	private void closeAccount() {
		System.out.println("Account Nr: ");
		int nr = Integer.parseInt(scanner.nextLine());
		System.out.println("PIN: ");
		String pin = scanner.nextLine();
		try {
			bank.closeAccount(nr, pin);
			System.out.println("Account with number " + nr + " closed");
		} catch (CredentialsException e) {
			System.err.println(e.getMessage());
		}
	}
}