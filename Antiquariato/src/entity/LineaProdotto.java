package entity;

public class LineaProdotto {

	private String codiceProdotto; 
	private int quantita;
	
	public LineaProdotto(String codiceProdotto, int quantita) {
		super();
		this.codiceProdotto = codiceProdotto;
		this.quantita = quantita;
	}

	public String getCodiceProdotto() {
		return codiceProdotto;
	} 

	public void setCodiceProdotto(String codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	@Override
	public String toString() {
		return "[codice=" + codiceProdotto + ", quantita=" + quantita + "]";
	}
}
