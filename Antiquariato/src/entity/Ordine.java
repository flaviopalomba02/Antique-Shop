package entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Ordine {
	
	private int id;
	private Date data;
	private double prezzoComplessivo;
	private String usernameCliente;
	private List<LineaProdotto> prodotti; 
	
	public Ordine(int id, Date data, double prezzoComplessivo, String usernameCliente) {
		super();
		this.id = id;
		this.data = data;
		this.prezzoComplessivo = prezzoComplessivo;
		this.usernameCliente = usernameCliente;
		prodotti = new ArrayList<LineaProdotto>();
	}
 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getPrezzoComplessivo() {
		return prezzoComplessivo;
	}

	public void setPrezzoComplessivo(double prezzoComplessivo) {
		this.prezzoComplessivo = prezzoComplessivo;
	}

	public String getUsernameCliente() {
		return usernameCliente;
	}

	public void setUsernameCliente(String usernameCliente) {
		this.usernameCliente = usernameCliente;
	}

	public List<LineaProdotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<LineaProdotto> prodotti) {
		this.prodotti = prodotti;
	}
	
	
	
	

}
