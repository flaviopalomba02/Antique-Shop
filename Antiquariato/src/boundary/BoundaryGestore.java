package boundary;

import javax.swing.JOptionPane;

import boundaryGestore.LoginGestore;
import control.GestioneNegozio;
import entity.Dipinto;
import entity.Scultura;
import exception.OperationException;

import java.util.ArrayList;

/* questa classe rappresenta il main della vista Gestore, ma demanda l'implementazione dei veri metodi
 * boundary lato gestore nei vari JFRAME del package boundaryGestore
 */

public class BoundaryGestore {
	
	private GestioneNegozio gestioneNegozio = GestioneNegozio.getIstance();
	private static BoundaryGestore boundaryGestore = null;
	
	
	/*quando eseguo la vista cliente, la schermata iniziale sarà quella di loginGestore
	ricordo che non è prevista la funzionalità di registrazione del gestore, per semplicità
	ho supposto che ce ne sia uno solo che accede con credenziali "admin" per lo username
	e "admin" per la chiaveNegozio */
    public static void main(String[] args) {
    	
    	new LoginGestore().setVisible(true);
    	
    }
	
	
	public static BoundaryGestore getIstance() {
		
		if(boundaryGestore == null) {
			boundaryGestore = new BoundaryGestore();
		} 
		
		return boundaryGestore;
	}


	public ArrayList<Dipinto> VisualizzaDipinti() {
		
		ArrayList<Dipinto> dipinti = new ArrayList<Dipinto>();
		
		try {
			dipinti = gestioneNegozio.VisualizzaDipinti();
			
		} catch (OperationException e) {

			e.printStackTrace();
		}
		
		return dipinti; 
	}

	
	public ArrayList<Scultura> VisualizzaSculture() {
		
		ArrayList<Scultura> sculture = new ArrayList<Scultura>();
		
		try {
			sculture = gestioneNegozio.VisualizzaSculture();
			
		} catch (OperationException e) {

			JOptionPane.showMessageDialog(null, e);
		}
		
		return sculture;
	}
	 
	public Dipinto AggiungiDipinto(String nome, String descrizione, double prezzo, String dimensioni, String tecnica) {
		
		Dipinto dipinto = null;
		
		try {
			dipinto = gestioneNegozio.AggiungiDipinto(nome, descrizione, prezzo, dimensioni, tecnica);
			
		} catch (OperationException e) {

			JOptionPane.showMessageDialog(null, e);
		}
		
		return dipinto;
	}

	public Dipinto ModificaDipinto(String codice, String nome, String descrizione, double prezzo, String dimensioni, String tecnica) {
		
		Dipinto dipinto = null;
		
		try {
			dipinto = gestioneNegozio.ModificaDipinto(codice, nome, descrizione, prezzo, dimensioni, tecnica);
			
		} catch (OperationException e) {

			JOptionPane.showMessageDialog(null, e);
		}
		
		return dipinto;
	}
	
	public boolean RimuoviDipinto(String codice) {
		
		try {
			
			return gestioneNegozio.RimuoviDipinto(codice);
			
		} catch (OperationException e) {

			JOptionPane.showMessageDialog(null, e); 
			return false;
		}

	}
	
	
	public Scultura AggiungiScultura(String nome, String descrizione, double prezzo, double peso, double altezza) {
		
		Scultura scultura = null;
		
		try {
			scultura = gestioneNegozio.AggiungiScultura(nome, descrizione, prezzo, peso, altezza);
			
		} catch (OperationException e) {

			JOptionPane.showMessageDialog(null, e);
		}
		
		return scultura;
	}

	public Scultura ModificaScultura(String codice, String nome, String descrizione, double prezzo, double peso, double altezza) {
		
		Scultura scultura = null;
		
		try {
			scultura = gestioneNegozio.ModificaScultura(codice, nome, descrizione, prezzo, peso, altezza);
			
		} catch (OperationException e) {

			JOptionPane.showMessageDialog(null, e);
		}
		
		return scultura;
	}
	
	public boolean RimuoviScultura(String codice) {
		
		try {
			
			return gestioneNegozio.RimuoviScultura(codice);
			
		} catch (OperationException e) {

			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
	
	
	public boolean AccessoGestore(String username, String password) {
		
		try {
			return gestioneNegozio.AccessoGestore(username, password);
			
		} catch (OperationException e) {

			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
}
