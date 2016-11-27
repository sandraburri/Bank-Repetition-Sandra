package bank;

import java.util.Scanner;

@SuppressWarnings("nls")
public class ATM {

	// Wie sagt man dem und was macht das? Kann das nicht einordnen.
	// Ist von mir aus kein Konstruktor, keine Variable und es wird auch kein
	// Objekt erzeugt
	private Bank bank;

	// Ist das der Konstruktor des ATM? Steht in der () das Bank wie ein int /
	// String .... ?
	public ATM(Bank bank) {
		this.bank = bank;
	}

	// Dieser Befehl löst den Import des Scanner aus (Zeile 3). Den Scanner
	// braucht man um mit dem Kunden zu interagieren
	private Scanner scanner;

	// Was bedeutet das? Es bringt etwas zum laufen, aber wie genau und was?
	// => Erklärung bei Bestellung Pizza auch noch ergänzen
	public void run() {
		
		// Die while Schleife wird durchlaufen solange der Benutzer Eingaben
		// tätigt oder abbricht (Exit)
		while (true) {
			
			// Bei der Bestellung im Pizzakurier war von einem Loop die Rede.
			// Von wo bis wo geht hier der Loop und wie funktioniert der hier?
			
			// Die folgenden 14 Zeilen sind Ausgaben auf der Konsole, sprich
			// für den Kunden ersichtlich Text aber auch Leerzeilen
			System.out.println("   A   TTTTTTT M     M");
			System.out.println("  A A     T    MM   MM");
			System.out.println(" AAAAA    T    M M M M");
			System.out.println("A     A   T    M  M  M");
			System.out.println();
			System.out.println("A  Open Account");
			System.out.println("B  Get Balance");
			System.out.println("C  Get Transactions");
			System.out.println("D  Deposit");
			System.out.println("E  Withdraw");
			System.out.println("F  Close Account");
			System.out.println("X  Exit");
			System.out.println();
			System.out.print("> ");
			
			// ein neues Objekt wird erzeugt mit = new Scanner(System.in) ,
			// wird während der Erstellung des Scanner Objekts automatisch
			// instanziert => Erklärung bei Bestellung Pizza auch noch ergänzen
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
						deposit();
						break;
						case "D":
							withdraw();
							break;
							case "E":
								closeAccount();
								break;
								case "X":
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
			scanner.nextLine();
		}
	}

	// Ab hier folgen die Methoden, damit das was der Kunde sieht und er ein-
	// gibt überhaupt durchgeführt werden kann und auch korrekt funktioniert...
	// Warum passiert das erst hier? Wäre es nicht logischer zuerst das Werk-
	// zeug zu produzieren und dann erst mit dem Kunden zu interagieren?

	// In dieser Methode wird der Account eröffnet
	private void openAccount() {

		// Der Kunde wird mit dieser Ausgabe aufgefordert seinen Namen
		// einzugeben
		System.out.println("Customer: ");

		// Wenn der Kunde seinen Namen eingegeben hat, geht der scanner auf die
		// nächste Zeile
		String customer = scanner.nextLine();
		System.out.println("PIN: ");
		String pin = scanner.nextLine();

		// Hier wird dem neuen Bankkonto eine nr vergeben. Die notwendigen
		// Informationen dazu werden bei der Klasse Bank bei openAccount
		// abgeholt.
		// Warum wird hier das Int als Integer geschrieben?
		Integer nr = bank.openAccount(customer, pin);

		// Wenn die Nummer gültig ist. In unserem Fall, wenn die Nummer die
		// MAX_ACCOUNTS nicht überschreitet und der Kunde alles korrekt ein-
		// gegeben hat
		if (nr != null)
			System.out.println("Account with number " + nr + " opened");
		else
			System.out.println("Faild to oppen account");
	}

	// In dieser Methode wird der Kontostand ausgegeben
	private void getBalance() {

		// Der Kunde wird mit dieser Ausgabe aufgefordert seine Kontonummer
		// einzugeben
		System.out.println("Account Nr: "); // warum hat der Dozent hier in
		// seinem Beispiel print und nicht println? Ist das von Bedeutung?

		// Was wird hier mit dem parse genau erreicht/gemacht und warum?
		// Die Kontonummer ist doch bereits ein int und keinen String?
		int nr = Integer.parseInt(scanner.nextLine());
		System.out.println("PIN: ");
		String pin = scanner.nextLine();

		// Warum ist hier Double gross geschrieben?
		Double balance = bank.getBalance(nr, pin);

		// Wenn der Kontostand gültig ist, sprich nicht negativ ist (oder zielt
		// das hier auf die Kundeneingabe ab? Sprich korrekte Kontonummer
		// und korrekter pin?)
		if (balance != null)
			System.out.println("Balance of Account with number " + nr + " is " + balance);
		else
			System.out.println("Wrong Accountnumber or pin");
	}

	// Mit dieser Methode wird ermöglicht, dass der Kunde Geld auf sein
	// Konto einzahlen kann
	private void deposit() {
		System.out.println("Account Nr: ");
		int nr = Integer.parseInt(scanner.nextLine());

		// ??
		double amount = Double.parseDouble(scanner.nextLine());

		// richtig oder falsch Infos dazu werden in der Klasse Bank bei deposit
		// abgeholt
		boolean deposit = bank.deposit(nr, amount);
		if (deposit == true)
			System.out.println("Deposit of " + amount + " to account with number " + nr);
		else
			System.out.println("Deposit failed");
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
		boolean withdraw = bank.withdraw(nr, pin, amount);
		if (withdraw = true)
			System.out.println("Withdraw of " + amount + " from account with number " + nr);
		else
			System.out.println("Wrong Accountnumber or pin");
	}
	
	// Diese Methode ermöglicht es, ein Konto zu schliessen
	private void closeAccount() {
		System.out.println("Account Nr: ");
		int nr = Integer.parseInt(scanner.nextLine());
		System.out.println("PIN: ");
		String pin = scanner.nextLine();
		boolean closeAccount = bank.closeAccount(nr, pin);
		if (closeAccount == true)
			System.out.println("Account with number " + nr + " closed");
		else
			System.out.println("Wrong Accountnumber or pin");
	}
}
