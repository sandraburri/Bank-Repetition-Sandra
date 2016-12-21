package bank.account;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Transaction implements Serializable {
	
	// Instanzvariablen
	private double amount; // Betrag
	private double balance; // Kontostand
	
	// Das Datum (und Zeit) zu welchem die Transaktion gemacht wurde. Wir
	// können dafür die Klasse "Date" aus java.util brauchen (zusammen mit
	// Calendar, damit es für uns leserlich ist - tag,monat,jahr,...)
	private Date valuta;
	
	// Konstruktor
	public Transaction(double amount, double balance) {
		this.amount = amount;
		this.balance = balance;
		this.valuta = (Date) Calendar.getInstance().getTime();
	}
	
	// Methoden
	public double getAmount() {
		return this.amount;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public Date getValuta() {
		return this.valuta;
	}
}