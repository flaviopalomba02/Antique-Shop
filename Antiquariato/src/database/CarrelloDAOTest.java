package database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.DAOException;
import exception.DBConnectionException;

public class CarrelloDAOTest {

	@Test
	public void testRemoveFromCarrello() throws DBConnectionException, DAOException {
		
		String codiceProdotto = "1D";
		String usernameCliente = "dario";
		
		CarrelloDAO.removeFromCarrello(usernameCliente, codiceProdotto);
		
		assertEquals(CarrelloDAO.readCarrello(usernameCliente).getProdotti().size(), 0);
	}

}
