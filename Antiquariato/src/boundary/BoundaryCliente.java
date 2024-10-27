package boundary;

import java.sql.Date;
import java.util.List;

import javax.swing.JOptionPane;

import boundaryCliente.Login;
import control.GestioneAcquisto;
import control.GestioneNegozio;
import entity.Carrello;
import entity.CartaCredito;
import entity.Dipinto;
import entity.LineaProdotto;
import entity.Ordine;
import entity.Prodotto;
import entity.Scultura;
import exception.OperationException;

/* questa classe rappresenta il main della vista Cliente, ma demanda l'implementazione dei veri metodi
 * boundary lato cliente nei vari JFRAME del package boundaryCliente
 */

public class BoundaryCliente {
	
	private static BoundaryCliente boundaryCliente;
	private GestioneNegozio gestioneNegozio = GestioneNegozio.getIstance();
	private static GestioneAcquisto gestioneAcquisto = GestioneAcquisto.getIstance();
	
	
	//quando eseguo la vista cliente, la schermata iniziale sarà quella di login
    public static void main(String[] args) {
    	
    	new Login().setVisible(true);

    }
	
	
	
	public static BoundaryCliente getIstance() {
		
		if(boundaryCliente == null) {
			boundaryCliente = new BoundaryCliente();
		} 
		
		return boundaryCliente;
	}

	public boolean AccessoCliente(String username, String password) {
		
		try {
			return gestioneNegozio.AccessoCliente(username, password);
			
		} catch (OperationException e) {
			
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
	
	public boolean Registrazione(String username, String password, String numeroTelefono, CartaCredito cartaCredito) {
		
		try {
			return gestioneNegozio.RegistrazioneCliente(username, password, numeroTelefono, cartaCredito);
			
		} catch (OperationException e) {
			
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
	
	public boolean AggiungiProdottoCarrello(String usernameCliente, String codiceProdotto, int quantita) {
		
		try {
			return gestioneAcquisto.AggiungiProdottoCarrello(usernameCliente, codiceProdotto, quantita);
			
		} catch (OperationException e) {

			JOptionPane.showMessageDialog(null, e); 
			return false;
		}
	}
	
	public Dipinto VisualizzaDipinto(String codice) {
		
		try {
			return gestioneNegozio.VisualizzaDipinto(codice);
			
		} catch (OperationException e) {
			
			JOptionPane.showMessageDialog(null, e); 
			return null;
		}
	} 
	
	public Scultura VisualizzaScultura(String codice) {
		
		try {
			return gestioneNegozio.VisualizzaScultura(codice);
			
		} catch (OperationException e) {
			
			JOptionPane.showMessageDialog(null, e); 
			return null;
		}
	}
	
	public static Carrello VisualizzaCarrello(String usernameCliente) {
		
		try {
			return gestioneAcquisto.VisualizzaCarrello(usernameCliente);
			
		} catch (OperationException e) {
			
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}
	
	public Prodotto VisualizzaProdotto(String codice) {
		
		try {
			return gestioneNegozio.VisualizzaProdotto(codice);
			
		} catch (OperationException e) {
			
			JOptionPane.showMessageDialog(null, e);
			return null;		}
	}
	
	
	public static boolean RimuoviProdottoCarrello(String usernameCliente, String codiceProdotto) {
		
		try {
			return gestioneAcquisto.RimuoviProdottoCarrello(usernameCliente, codiceProdotto);
			
		} catch (OperationException e) {

			JOptionPane.showMessageDialog(null, e);
			return false;		}
	}
	
	public boolean EffettuaOrdine(Date data, String usernameCliente) {
		
		try {
			return gestioneAcquisto.EffettuaOrdine(data, usernameCliente);
			
		} catch (OperationException e) {
			
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
	
	public List<Ordine> VisualizzaOrdiniCliente(String usernameCliente) {
		

		try {
			return gestioneAcquisto.VisualizzaOrdiniCliente(usernameCliente);
			
		} catch (OperationException e) {

			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}
	
	//è un modo per stampare nome e quantità anziché codice e quantita
	public String stampaLineaProdotto(LineaProdotto lineaProdotto) {
		
		try {
			return gestioneNegozio.StampaLineaProdotto(lineaProdotto);
			
		} catch (OperationException e) {

			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
