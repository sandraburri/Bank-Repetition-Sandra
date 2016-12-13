package bank.account;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
	
	// Instanzvariablen
	private double amount; // Betrag
	private double balance; // Kontostand
	private Date valuta; // valuta = Geschäftsabschluss ??? was hat das mit dem
	// Datum zu tun
	
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