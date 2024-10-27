package control;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import database.CarrelloDAO;
import database.OrdineDAO;
import entity.Carrello;
import entity.LineaProdotto;
import entity.Ordine;
import exception.DAOException;
import exception.DBConnectionException;
import exception.OperationException;

public class GestioneAcquisto {
	
	private static GestioneAcquisto gestioneAcquisto = null;
	private static List<Ordine> ordini;
	
	protected GestioneAcquisto() { 
		ordini = new ArrayList<Ordine>();
	}
	 
	public static GestioneAcquisto getIstance() {
		
		if(gestioneAcquisto == null) {
			gestioneAcquisto = new GestioneAcquisto();
		}
		
		return gestioneAcquisto;
	}
	
	//sicome quando inserisco un prodotto nel carrello, inserisco anche la quantità, lo inserisco una sola volta
	// es. se nel carrello c'è il dipinto X con quantità 2, non posso aggiungere di nuovo X se prima non l'ho rimosso
	public static boolean AggiungiProdottoCarrello(String usernameCliente, String codiceProdotto, int quantita) throws OperationException {
		
		try {

			Carrello carrello = VisualizzaCarrello(usernameCliente);
			
			for(LineaProdotto prodotto : carrello.getProdotti()) {
				
				//controllo se il prodotto è già esistente nel carrello
				if(codiceProdotto.equals(prodotto.getCodiceProdotto())) {
					throw new OperationException("Prodotto già esistente nel carrello");
				}
				
			} 
					
			CarrelloDAO.insertIntoCarrello(usernameCliente, codiceProdotto, quantita);
			return true;

		}catch(DBConnectionException dbEx) {
			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");

		}catch(DAOException ex) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
			
	}
	
	public static boolean RimuoviProdottoCarrello(String usernameCliente, String codiceProdotto) throws OperationException {
		
		try {

			Carrello carrello = VisualizzaCarrello(usernameCliente);
			
			for(LineaProdotto prodotto : carrello.getProdotti()) {
				
				if(codiceProdotto.equals(prodotto.getCodiceProdotto())) {
					
					CarrelloDAO.removeFromCarrello(usernameCliente, codiceProdotto);
					return true;
					
				}
			}
			
			throw new OperationException("Prodotto non esistente nel carrello");

		}catch(DBConnectionException dbEx) {
			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");

		}catch(DAOException ex) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
		
	}
	
	
	public static Carrello VisualizzaCarrello(String usernameCliente) throws OperationException {
		
		try {

			return CarrelloDAO.readCarrello(usernameCliente);

		}catch(DBConnectionException dbEx) {
			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");

		}catch(DAOException ex) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
		
	}
	
	
	public static boolean EffettuaOrdine(Date data, String usernameCliente) throws OperationException {
		
		try {

			Carrello carrello = VisualizzaCarrello(usernameCliente);
			Ordine ordine = null;
			
			if(carrello.getProdotti().size() == 0) {
				throw new OperationException("Il carrello è vuoto");
			}
			
			ordine = OrdineDAO.createOrdine(data, PrezzoComplessivoOrdine(usernameCliente), usernameCliente);
			ordini.add(ordine);
			
			return true;

		}catch(DBConnectionException dbEx) {
			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");

		}catch(DAOException ex) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
		
	
		
	}
	
	public List<Ordine> VisualizzaOrdiniCliente(String usernameCliente) throws OperationException{
		
		try {

			return OrdineDAO.readOrdini(usernameCliente);

		}catch(DBConnectionException dbEx) {
			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");

		}catch(DAOException ex) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
		
	}
	
	private static double PrezzoComplessivoOrdine(String usernameCliente) throws OperationException {
		
		try {

			return CarrelloDAO.prezzoComplessivo(usernameCliente);

		}catch(DBConnectionException dbEx) {
			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");

		}catch(DAOException ex) {

			throw new OperationException("Ops, qualcosa e' andato storto..");
		}
		

	}
	

}
