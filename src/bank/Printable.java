package bank;

public interface Printable {

	// Die Methode definiert was gemacht wird aber nicht wie
	public void print();
}

// Ein Interface erstellen geht wie in einem Package eine neue Klasse erstellen

// Ein Interface enthählt keine Implementierungen

// Ein Inteface ist eine Schnittstelle. Ist ein abstrakter Datentyp

// Warum genau verwenden wir hier ein Interface? Für schön? Könnte man das
// nicht auch in einer Klasse selber machen?
// Ja, könnte man auch problemlos ohne Interface machen, ist mehr oder weniger
// nur zum üben...
// Der einzige Vorteil, den man hier dadurch hat ist, sollte man irgendwann
// einen neuen Kontotyp erstellen, so vergisst man die print-Methode nicht.
// Aus diesem Grund kann man Interfaces auch verwenden um sicherzustellen, dass
// gewissen Funktionen implementiert werden. (Kann so auch wie ein Vertrag
// zwischen Kunden und Entwickler verwendet werden, wenn der Kunde bestimmte
// Funktionen wünscht, aber es ihm egal ist, wie diese implementiert werden,
// so lange sie wie gewünscht funktionieren)