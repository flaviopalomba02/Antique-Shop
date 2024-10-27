package control;

import java.util.ArrayList;
import java.util.List;

import database.ClienteDAO;
import database.DipintoDAO;
import database.SculturaDAO;
import entity.CartaCredito;
import entity.Cliente;
import entity.Dipinto;
import entity.LineaProdotto;
import entity.Prodotto;
import entity.Scultura;
import exception.DAOException;
import exception.DBConnectionException;
import exception.OperationException;


public class GestioneNegozio {
	
	private final String usernameGestore = "admin";
	private final String passwordGestore = "admin";
	
	private static GestioneNegozio gestioneNegozio = null; 
	private List<Prodotto> prodotti; 
	private List<Cliente> clienti;
	
	protected GestioneNegozio() {
		
		prodotti = new ArrayList<Prodotto>();
		clienti = new ArrayList<Cliente>();
		
	} 
	 
	public static GestioneNegozio getIstance() {
		
		if(gestioneNegozio == null) {
			gestioneNegozio = new GestioneNegozio();
		}
		
		return gestioneNegozio;
	}
	
	public Dipinto AggiungiDipinto(String nome, String descrizione, double prezzo, String dimensioni, String tecnica) throws OperationException {
		
		List<Dipinto> dipinti = new ArrayList<Dipinto>();
		Dipinto dipinto = null;
		
		try { 
 
			dipinti.addAll(DipintoDAO.readDipinti());
			
			for(Dipinto dipinto1 : dipinti) {
				
				if(nome.equals(dipinto1.getNome())) {
					throw new OperationException("Dipinto con nome inserito già esistente");
				}
				
			}
			
			dipinto = DipintoDAO.createDipinto(nome, descrizione, prezzo, dimensioni, tecnica);
			//lo aggiungo alla lista di prodotti di GestioneNegozio
			prodotti.add(dipinto);

		}catch(DBConnectionException dbEx) {
			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");

		}catch(DAOException ex) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
	
		return dipinto;
	}
	
	public Scultura AggiungiScultura(String nome, String descrizione, double prezzo, double peso, double altezza) throws OperationException {
		
		List<Scultura> sculture = new ArrayList<Scultura>();
		Scultura scultura = null;
		
		try {

			sculture.addAll(SculturaDAO.readSculture());
			
			for(Scultura scultura1 : sculture) {
				
				if(nome.equals(scultura1.getNome())) {
					throw new OperationException("Scultura con nome inserito già esistente");
				}
				
			}
			
			scultura = SculturaDAO.createScultura(nome, descrizione, prezzo, peso, altezza);
			//lo aggiungo alla lista di prodotti di GestioneNegozio
			prodotti.add(scultura);

		}catch(DBConnectionException dbEx) {
			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");

		}catch(DAOException ex) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
		
		return scultura;
	}
	
	public Dipinto ModificaDipinto(String codice, String nome, String descrizione, double prezzo, String dimensioni, String tecnica) throws OperationException {
		
		List<Dipinto> listaDipinti = new ArrayList<Dipinto>();
		Dipinto dipintoDaModificare = null;
		Dipinto dipintoModificato = null;
		
		try {

			listaDipinti.addAll(DipintoDAO.readDipinti()); //salvo la lista dei dipinti
			dipintoDaModificare = DipintoDAO.readDipinto(codice);

			for(Dipinto dipinto : listaDipinti) {
				
				//se il nuovo nome del dipinto è uguale a quello di un altro dipinto NON va bene
				//ho supposto nel database che nome sia unique
					if(nome.equals(dipinto.getNome()) && !dipinto.equals(dipintoDaModificare)) {
					
					throw new OperationException("Dipinto con nome inserito già esistente");
				}
				
			}
			

			dipintoModificato = DipintoDAO.updateDipinto(codice, nome, descrizione, prezzo, dimensioni, tecnica);
			prodotti.remove(dipintoDaModificare);
			prodotti.add(dipintoModificato);

		}catch(DBConnectionException dbEx) {
			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");

		}catch(DAOException ex) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
		

		
		return dipintoModificato;
	}
	
	public Scultura ModificaScultura(String codice, String nome, String descrizione, double prezzo, double peso, double altezza) throws OperationException {
		
		List<Scultura> listaSculture = new ArrayList<Scultura>();
		Scultura sculturaDaModificare = null;
		Scultura sculturaModificata = null;
		
		try {

			listaSculture.addAll(SculturaDAO.readSculture()); //salvo la lista delle sculture
			sculturaDaModificare = SculturaDAO.readScultura(codice);
			
			for(Scultura scultura : listaSculture) {
				
				//se il nuovo nome della scultura è uguale a quello di un'altra scultura NON va bene
				//ho supposto nel database che nome sia unique
				if(nome.equals(scultura.getNome()) && !scultura.equals(sculturaDaModificare)) {
					
					throw new OperationException("Scultura con nome inserito già esistente");
				}
				
			}
			
			sculturaModificata = SculturaDAO.updateScultura(codice, nome, descrizione, prezzo, peso, altezza);
			prodotti.remove(sculturaDaModificare);
			prodotti.add(sculturaModificata);

		}catch(DBConnectionException dbEx) {
			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");

		}catch(DAOException ex) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
		
		
		return sculturaModificata;
	}
	
	public boolean RimuoviDipinto(String codice) throws OperationException {
		
		try {

			Dipinto dipinto = DipintoDAO.readDipinto(codice);
			
			if(dipinto != null) {
				
				DipintoDAO.deleteDipinto(codice);
				prodotti.remove(dipinto);
				return true;
				
			} else { 
				throw new OperationException("Prodotto non esistente");
			}

		}catch(DBConnectionException dbEx) {
			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");

		}catch(DAOException ex) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
		

		
	}

	public boolean RimuoviScultura(String codice) throws OperationException {
		
		try {

			Scultura scultura = SculturaDAO.readScultura(codice);
			
			if(scultura != null) {
				
				SculturaDAO.deleteScultura(codice);
				prodotti.remove(scultura);
				return true;
				
			} else {
				throw new OperationException("Prodotto non esistente");
			}

		}catch(DBConnectionException dbEx) {
			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");

		}catch(DAOException ex) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
		
	}
	
	
	public ArrayList<Dipinto> VisualizzaDipinti() throws OperationException{
		
		try {
			return DipintoDAO.readDipinti();
			
		} catch (DBConnectionException e) {

			throw new OperationException("Lista dei dipinti non disponibile"); 
			
		} catch (DAOException e) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
	}
	
	public ArrayList<Scultura> VisualizzaSculture() throws OperationException{
		
		try {
			return SculturaDAO.readSculture();
			
		} catch (DBConnectionException e) {

			throw new OperationException("Lista delle sculture non disponibile");
			
		} catch (DAOException e) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
	}
	
	public Dipinto VisualizzaDipinto(String codice) throws OperationException{
		
		try {
			return DipintoDAO.readDipinto(codice);
			
		} catch (DBConnectionException e) {

			throw new OperationException("Errore nella visualizzazione del dipinto");
			
		} catch (DAOException e) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
	}
	
	public Scultura VisualizzaScultura(String codice) throws OperationException{
		
		try {
			return SculturaDAO.readScultura(codice);
			
		} catch (DBConnectionException e) {

			throw new OperationException("Errore nella visualizzazione della scultura");
			
		} catch (DAOException e) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
	}
	
	public Prodotto VisualizzaProdotto(String codice) throws OperationException {
		
		Dipinto dipinto = null;
		Scultura scultura = null;
		
		dipinto = VisualizzaDipinto(codice);
		scultura = VisualizzaScultura(codice);
		
		if(dipinto != null) {
			
			return dipinto;
			
		} else if (scultura != null){

			return scultura;
			
		} else {
			return null;
		}
		

	}
	
	//metodo che ho aggiunto per far sì che a schermo si visualizzino nome del prodotto e quantità
	// e non codice e quantità come prevede il metodo toString() di LineaProdotto
	public String StampaLineaProdotto(LineaProdotto prodotto) throws OperationException {
		
		return "Prodotto: " + (VisualizzaProdotto(prodotto.getCodiceProdotto())).getNome() + ", Quantita: " + prodotto.getQuantita();
	}
	
	
	public boolean RegistrazioneCliente(String username, String password, String numeroTelefono, CartaCredito cartaCredito) throws OperationException {
		
		try {

			Cliente cliente = ClienteDAO.readCliente(username);
			
			if(cliente != null) {
				throw new OperationException("Username già esistente");
			} 
			
			Cliente nuovoCliente = ClienteDAO.createCliente(username, password, numeroTelefono, cartaCredito);
			clienti.add(nuovoCliente);
			return true;

		}catch(DBConnectionException dbEx) {
			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");

		}catch(DAOException ex) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
		
	}
	
	public boolean AccessoCliente(String username, String password) throws OperationException {
		
		try {

			Cliente cliente = ClienteDAO.readCliente(username);
			
			if(cliente != null && password.equals(cliente.getPassword())) {
				return true;	
			} else {
				throw new OperationException("Credenziali non valide");
			}

		}catch(DBConnectionException dbEx) {
			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");

		}catch(DAOException ex) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
		
	}
	
	//per accedere come gestore, username: admin , password: admin
	public boolean AccessoGestore(String username, String password) throws OperationException {
		
		if(username.equals(usernameGestore) && password.equals(passwordGestore)) {
			return true;	
		} else {
			throw new OperationException("Credenziali non valide");
		}
	}
}
