package bank.account;

public class CredentialsException extends Exception {
	public CredentialsException(String message) {
		super(message);
	}
}

// Diese Klasse erm�glicht es uns sp�ter, bei absehbaren m�glichen falschen
// User-Eingaben zu den Zugangsdaten eine entsprechende Fehlermeldung
// auszugeben