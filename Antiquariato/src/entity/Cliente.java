package entity;

public class Cliente {
	
	private String username;
	private String password;
	private String numeroTelefono;
	private CartaCredito cartaCredito;
	
	public Cliente(String username, String password, String numeroTelefono, CartaCredito cartaCredito) {
		super();
		this.username = username;
		this.password = password;
		this.numeroTelefono = numeroTelefono;
		this.cartaCredito = cartaCredito;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public CartaCredito getCartaCredito() {
		return cartaCredito;
	}

	public void setCartaCredito(CartaCredito cartaCredito) {
		this.cartaCredito = cartaCredito;
	}
}
