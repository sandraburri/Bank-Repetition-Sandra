package bank.account;

public class CredentialsException extends Exception {
	public CredentialsException(String message) {
		super(message);
	}
}

// Diese Klasse ermöglicht es uns später, bei absehbaren möglichen falschen
// User-Eingaben zu den Zugangsdaten eine entsprechende Fehlermeldung
// auszugeben