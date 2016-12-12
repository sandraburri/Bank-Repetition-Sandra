package bank.account;

public class TransactionException extends Exception {
	public TransactionException(String message) {
		super(message);
	}
}

// Diese Klasse erm�glicht es uns sp�ter, bei absehbaren m�glichen falschen
// User-Eingaben eine entsprechende Fehlermeldung auszugeben f�r
// fehlgeschlagenene Tansactionen