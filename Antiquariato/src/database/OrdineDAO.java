package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import entity.Carrello;
import entity.LineaProdotto;
import entity.Ordine;

import java.util.ArrayList;

import exception.DAOException;
import exception.DBConnectionException;

public class OrdineDAO { 

	public static Ordine createOrdine(Date data, double prezzoComplessivo, String usernameCliente)
			throws DAOException, DBConnectionException {

		int id = 0;
		Carrello carrello = CarrelloDAO.readCarrello(usernameCliente);
		List<LineaProdotto> prodotti = new ArrayList<LineaProdotto>();
		Ordine ordine = null; 
 
		String query = "INSERT INTO ORDINI (Data, PrezzoComplessivo, UsernameCliente) VALUES (?,?,?);";

		try {

			try {

				Connection connection = DBManager.getConnection();

				PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setDate(1, data);
				statement.setDouble(2, prezzoComplessivo);
				statement.setString(3, usernameCliente);

				statement.executeUpdate();

				ResultSet result = statement.getGeneratedKeys();
				if (result.next()) {
					id = result.getInt(1);
				}

				// invio resoconto ordine
				prodotti = createDettaglioOrdine(id, carrello);

				ordine = new Ordine(id, data, prezzoComplessivo, usernameCliente);
				ordine.getProdotti().addAll(prodotti);

				//se effettuo l'ordine, il carrello del cliente in questione si svuoterà 
				CarrelloDAO.svuotaCarrello(usernameCliente);

			} catch (SQLException e) {

				throw new DAOException("Creazione ordine non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Errore connessione al Database");

		}

		return ordine;
	}

	private static List<LineaProdotto> createDettaglioOrdine(int idOrdine, Carrello carrello) throws DAOException, DBConnectionException {

		List<LineaProdotto> prodotti = new ArrayList<LineaProdotto>();

		String query = "INSERT INTO DettagliOrdine VALUES (?,?,?);";

		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);

				for (LineaProdotto prodotto : carrello.getProdotti()) {

					statement.setInt(1, idOrdine);
					statement.setString(2, prodotto.getCodiceProdotto());
					statement.setInt(3, prodotto.getQuantita());

					statement.executeUpdate();

					prodotti.add(new LineaProdotto(prodotto.getCodiceProdotto(), prodotto.getQuantita()));
				}

			} catch (SQLException e) {

				throw new DAOException("Invio resoconto ordine non riuscito");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}

		return prodotti;
	}
	
	public static List<Ordine> readOrdini(String usernameCliente) throws DAOException, DBConnectionException {
		
		List<Ordine> ordini = new ArrayList<Ordine>();
		Ordine ordine = null;
		String query = "SELECT * FROM Ordini WHERE UsernameCliente=?;";

		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				
				statement.setString(1, usernameCliente);

				ResultSet result = statement.executeQuery();
				
				while(result.next()) {
					int id = result.getInt(1);
					Date data = result.getDate(2);
					double prezzoComplessivo = result.getDouble(3);
					ordine = new Ordine(id, data, prezzoComplessivo, usernameCliente);
					ordine.setProdotti(readDettaglioOrdine(id));
					ordini.add(ordine);
					
				}

			} catch (SQLException e) {

				throw new DAOException("Lettura ordine non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}

		return ordini;
		
	}
	
	private static List<LineaProdotto> readDettaglioOrdine(int idOrdine) throws DAOException, DBConnectionException {

		LineaProdotto prodotto = null;
		List<LineaProdotto> prodotti = new ArrayList<LineaProdotto>();

		String query = "SELECT CodiceProdotto, Quantita FROM DettagliOrdine WHERE IDOrdine = ?;";

		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, idOrdine);
				
				ResultSet result = statement.executeQuery();
				
				while(result.next()) {
					
					String codiceProdotto = result.getString(1);
					int quantita = result.getInt(2);
					prodotto = new LineaProdotto(codiceProdotto, quantita);
					prodotti.add(prodotto);
				}
				
				

			} catch (SQLException e) {

				throw new DAOException("Invio resoconto ordine non riuscito");

			} 

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}

		return prodotti;
	}
}

