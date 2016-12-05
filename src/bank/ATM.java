package bank;

import java.util.Scanner;

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

	// Dieser Befehl l�st den Import des Scanner aus (Zeile 3)
	// Wird ben�tigt um Texteingaben zu interpretieren
	private Scanner scanner;

	// Diese Methode bringt das Programm mit run zum laufen, regelt die Ein-
	// und Ausgabe, das was der Kunde sieht und dort wo er eingeben kann...
	public void run() {
		
		// Die while Schleife wird durchlaufen solange der Benutzer Eingaben
		// t�tigt oder abbricht (Exit)
		while (true) {
			
			// Der while Loop ist hier immer true. Das heisst so lange der
			// Kunde etwas eingibt l�uft es immer weiter, bis zum (Exit)
			
			// Die folgenden 14 Zeilen sind Ausgaben auf der Konsole, sprich
			// f�r den Kunden ersichtlich Text aber auch Leerzeilen
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
			// wird w�hrend der Erstellung des Scanner Objekts automatisch
			// instanziert
			scanner = new Scanner(System.in);
			
			// Das Programm wartet auf die Eingabe des Buchstabens UND Enter,
			// nextLine() verlangt das Enter, .toUpperCase() macht aus der
			// Eingabe automatisch Grossbuchstaben, damit das nicht noch
			// kontrolliert werden muss
			String choice = scanner.nextLine().toUpperCase();
			
			// switch pr�ft mehrere Zust�nde der choice Variable ab
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
									
									// default = alle nicht behandelten F�lle
									// (nicht A,B,C,D,E,X)
									default:
										
										// Ausgabe bei einem Eingabe Fehler
										System.out.println("Error: Invalid input");
			}
			
			// Wenn der Kunde einen Buchstaben ausgew�hlt hat oder eine Zahl
			// eingegeben hat, muss er dies mit Enter best�tigen
			System.out.println("Hite Enter to continue...");
			
			// Der Scanner gibt dann die entsprechende n�chste Zeile aus
			// Die Eingabe wird hier nicht wie bei choice (Zeile 63) in einer
			// Variable gespeichert, stattdessen warten wir einfach nur auf den
			// nextLine Befehl (sprich "Enter")
			scanner.nextLine();
		}
	}

	// Ab hier folgen die Methoden, damit das was der Kunde sieht und er ein-
	// gibt �berhaupt durchgef�hrt werden kann und auch korrekt funktioniert...
	// Erg�nzung: In jedem switch-case wird die entsprechende Methode
	// aufgerufen
	// Hier ist nun definiert, was diese Methode genau tut
	
	// In dieser Methode wird der Account er�ffnet
	private void openAccount() {
		
		// Der Kunde wird hier aufgefordert den Konten Type zu w�hlen
		System.out.print("Type (Personal/Savings: ");
		int type = Bank.PERSONAL_ACCOUNT;
		if (scanner.nextLine().toUpperCase().equals("S"))
			type = Bank.SAVINGS_ACCOUNT;

		// Der Kunde wird mit dieser Ausgabe aufgefordert seinen Namen
		// einzugeben
		System.out.println("Customer: ");

		// Wenn der Kunde seinen Namen eingegeben hat, geht der scanner auf die
		// n�chste Zeile
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

		// Wenn die Nummer g�ltig ist. In unserem Fall, wenn die Nummer die
		// MAX_ACCOUNTS nicht �berschreitet und der Kunde alles korrekt ein-
		// gegeben hat
		// => bank.openAccount gibt einen Integer zur�ck (return Wert)
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

		// Das Parsen f�hrt durch einen Text und sucht die gew�nschten Sachen
		// heraus. In unserem Fall ist ein Int gesucht
		int nr = Integer.parseInt(scanner.nextLine());
		System.out.println("PIN: ");
		String pin = scanner.nextLine();

		// Double ist hier ein Objekt und kein primitiver Datentyp
		Double balance = bank.getBalance(nr, pin);

		// Hier wird �berpr�ft ob die Balance �berhaupt existiert. Falls nein
		// ist die Kontonummer oder der Pin falsch...
		if (balance != null)
			System.out.println("Balance of Account with number " + nr + " is "
		    + balance);
		else
			System.out.println("Wrong Accountnumber or pin");
	}

	// Mit dieser Methode wird erm�glicht, dass der Kunde Geld auf sein
	// Konto einzahlen kann
	private void deposit() {
		System.out.println("Account Nr: ");
		int nr = Integer.parseInt(scanner.nextLine());
		System.out.println("Amount: ");		

		// Das Parsen f�hrt durch einen Text und sucht die gew�nschten Sachen
		// heraus. In unserem Fall ist ein double gesucht
		double amount = Double.parseDouble(scanner.nextLine());

		// richtig oder falsch Infos dazu werden in der Klasse Bank bei deposit
		// abgeholt
		boolean deposit = bank.deposit(nr, amount);
		if (deposit == true)
			System.out.println("Deposit of " + amount + " to account with number " + nr);
		else
			System.out.println("Deposit failed");
	}

	// Mit dieser Methode wird erm�glicht, dass der Kunde Geld von seinem Konto
	// abheben kann
	private void withdraw() {
		System.out.println("Account Nr: ");
		int nr = Integer.parseInt(scanner.nextLine());
		System.out.println("PIN: ");
		String pin = scanner.nextLine();
		System.out.println("Amount: ");
		double amount = Double.parseDouble(scanner.nextLine());
		boolean withdraw = bank.withdraw(nr, pin, amount);
		if (withdraw == true)
			System.out.println("Withdraw of " + amount + " from account with number " + nr);
		else
			System.out.println("Wrong Accountnumber or pin");
	}
	
	// Diese Methode erm�glicht es, ein Konto zu schliessen
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