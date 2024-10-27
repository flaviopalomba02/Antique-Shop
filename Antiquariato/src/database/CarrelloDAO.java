package database;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entity.Carrello;
import entity.LineaProdotto;

import java.util.ArrayList;

import exception.DAOException;
import exception.DBConnectionException;

public class CarrelloDAO {

	public static void insertIntoCarrello(String usernameCliente, String codiceProdotto, int quantita) throws DAOException, DBConnectionException {
		
		String query = "INSERT INTO CARRELLO VALUES (?,?,?);";
		 
		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, usernameCliente);
				statement.setString(2, codiceProdotto);
				statement.setInt(3, quantita); 
				
				statement.executeUpdate();

			} catch (SQLException e) {

				throw new DAOException("Inserimento prodotto nel carrello non riuscito");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
			
	}
	
	
	public static void removeFromCarrello(String usernameCliente, String codiceProdotto) throws DBConnectionException, DAOException {
		
		String query = "DELETE FROM CARRELLO WHERE UsernameCliente=? AND CodiceProdotto=?;";
		
		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, usernameCliente);
				statement.setString(2, codiceProdotto);
				
				statement.executeUpdate();

			} catch (SQLException e) {

				throw new DAOException("Rimozione prodotto dal carrello non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
			
	}
	
	public static Carrello readCarrello(String usernameCliente) throws DBConnectionException, DAOException{
		
		
		Carrello carrello = new Carrello(usernameCliente);
		List<LineaProdotto> prodotti = new ArrayList<LineaProdotto>();
		String codiceProdotto = null;
		int quantita = 0;
		
		String query = "SELECT CodiceProdotto, Quantita FROM Carrello WHERE UsernameCliente=?;";
		
		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, usernameCliente);
				
				ResultSet result = statement.executeQuery();
				
				while(result.next()) {
					
					codiceProdotto = result.getString(1);
					quantita = result.getInt(2);
					prodotti.add(new LineaProdotto(codiceProdotto, quantita));
				}
				
				carrello.setProdotti(prodotti);

			} catch (SQLException e) {

				throw new DAOException("Visualizzazione prodotti carrello non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}		
		
		return carrello;
	}
	
	public static void svuotaCarrello(String usernameCliente) throws DBConnectionException, DAOException{
		
		String query = "DELETE FROM Carrello WHERE UsernameCliente=?;";
		
		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, usernameCliente);
				
				statement.executeUpdate();

			} catch (SQLException e) {

				throw new DAOException("Svuotamento carrello non riuscito");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
		
	}
	
	
	public static double prezzoComplessivo(String usernameCliente) throws DBConnectionException, DAOException{
		
		//query fatta per ricavare prezzo e quantità di ogni prodotto presente nel carrello
		String query = "SELECT sottoquery.Quantita, sottoquery.Prezzo FROM "
				+ " (SELECT C.UsernameCliente, C.Quantita, D.Prezzo FROM Carrello C, Dipinti D WHERE codiceProdotto = D.Codice "
				+ " UNION "
				+ " SELECT C.UsernameCliente, C.Quantita, S.Prezzo FROM Carrello C, Sculture S WHERE codiceProdotto = S.Codice) "
				+ "AS sottoquery WHERE sottoquery.UsernameCliente = ? ;";
		 

		double prezzoComplessivo = 0;
		double prezzo = 0;
		int quantita = 0;
		
		try {

			Connection connection = DBManager.getConnection();

			try {
				
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, usernameCliente);
				
				ResultSet result = statement.executeQuery();
				
				while(result.next()) {
					prezzo = result.getDouble(1);
					quantita = result.getInt(2);
					prezzoComplessivo += prezzo * quantita;
				}

			} catch (SQLException e) {

				throw new DAOException("Creazione ordine non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
		
		return prezzoComplessivo;
	}
}
