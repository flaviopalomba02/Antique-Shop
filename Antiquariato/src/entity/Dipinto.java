package entity;

import java.util.Objects;

public class Dipinto extends Prodotto{
	
	private String dimensioneTela;
	private String tecnica;

	public Dipinto(String codice, String nome, String descrizione, double prezzo, String dimensioneTela, String tecnica) {
		super(codice, nome, descrizione, prezzo);
		this.dimensioneTela = dimensioneTela;
		this.tecnica = tecnica;
	}

	public String getDimensioneTela() {
		return dimensioneTela;
	}

	public void setDimensioneTela(String dimensioneTela) {
		this.dimensioneTela = dimensioneTela;
	}

	public String getTecnica() {
		return tecnica;
	}

	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}

	@Override
	public String toString() {

		return super.toString() + "dimensioneTela=" + dimensioneTela + ", tecnica=" + tecnica + "]";
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Dipinto other = (Dipinto) obj;
        return Objects.equals(dimensioneTela, other.dimensioneTela)
                && Objects.equals(tecnica, other.tecnica);
    }


	


}
