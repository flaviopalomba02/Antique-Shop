package entity;

public class Scultura extends Prodotto{

	private double peso;
	private double altezza;
	
	public Scultura(String codice, String nome, String descrizione, double prezzo, double peso, double altezza) {
		super(codice, nome, descrizione, prezzo);
		this.peso = peso;
		this.altezza = altezza;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltezza() {
		return altezza;
	}

	public void setAltezza(double altezza) {
		this.altezza = altezza;
	}
	
	@Override
	public boolean equals (Object obj) {
		
		if(this == obj)
			return true;
		
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		
		if( !super.equals(obj) ) {
			return false;
		}
		
		Scultura scultura = (Scultura) obj;
		
		return (this.getPeso() == scultura.getPeso()
				&& this.getAltezza() == scultura.getAltezza());
		
	}
}
