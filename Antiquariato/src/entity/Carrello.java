package entity;

import java.util.ArrayList;
import java.util.List;

public class Carrello {

	private String usernameCliente;
	private List<LineaProdotto> prodotti;
	 
	
	//valutare se modificare costruttore per far sì che 
	// ci sia un carrello per ogni cliente
	public Carrello(String usernameCliente) {
		super();
		this.usernameCliente = usernameCliente;
		prodotti = new ArrayList<LineaProdotto>();
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
