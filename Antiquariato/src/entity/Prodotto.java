package entity;

import java.util.Objects;

public abstract class Prodotto {
	
	private String codice;
	private String nome;
	private String descrizione;
	private double prezzo;
	private ImmagineProdotto[] immagini;
	
	public Prodotto(String codice, String nome, String descrizione, double prezzo) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		immagini = new ImmagineProdotto[4];
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}
	

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	@Override
	public String toString() {
		return "[codice=" + codice + ", nome=" + nome + ", descrizione=" 
				+ descrizione + ", prezzo=" + prezzo + ", ";
	}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Prodotto other = (Prodotto) obj;
        return Objects.equals(codice, other.codice)
                && Objects.equals(nome, other.nome)
                && Objects.equals(descrizione, other.descrizione)
                && prezzo == other.prezzo;
    }
}
