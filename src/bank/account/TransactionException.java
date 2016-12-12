package bank.account;

public class TransactionException extends Exception {
	public TransactionException(String message) {
		super(message);
	}
}

// Diese Klasse ermöglicht es uns später, bei absehbaren möglichen falschen
// User-Eingaben eine entsprechende Fehlermeldung auszugeben für
// fehlgeschlagenene Tansactionen