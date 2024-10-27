package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Scultura;
import exception.DAOException;
import exception.DBConnectionException;

public class SculturaDAO{

	public static Scultura createScultura(String nome, String descrizione, double prezzo, double peso, double altezza) throws DBConnectionException, DAOException{
		
		Scultura scultura = null; 
		String codice = codiceProssimaScultura();
		
		String query = "INSERT INTO Sculture VALUES (?,?,?,?,?,?);";
		
		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, codice); 
				statement.setString(2, nome);
				statement.setString(3, descrizione);
				statement.setDouble(4, prezzo);
				statement.setDouble(5, peso);
				statement.setDouble(6, altezza);
				
				statement.executeUpdate();
				
				scultura = new Scultura(codice, nome, descrizione, prezzo, peso, altezza);

			} catch (SQLException e) {

				throw new DAOException("Creazione scultura non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}

		return scultura;
		
	}
	
	public static Scultura updateScultura(String codice, String nome, String descrizione, double prezzo, double peso, double altezza) throws DBConnectionException, DAOException{
		
		Scultura scultura = null;
		
		String query = "UPDATE SCULTURE SET Nome=?, Descrizione=?, Prezzo=?, Peso=?, Altezza=? WHERE Codice=?;";
		
		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, nome);
				statement.setString(2, descrizione);
				statement.setDouble(3, prezzo);
				statement.setDouble(4, peso);
				statement.setDouble(5, altezza);
				statement.setString(6, codice);
				
				statement.executeUpdate();
				
				scultura = new Scultura(codice, nome, descrizione, prezzo, peso, altezza);

			} catch (SQLException e) {

				throw new DAOException("Modifica scultura non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
		
		return scultura;
		
	}
	
	public static void deleteScultura(String codice) throws DBConnectionException, DAOException{
		
		String query = "DELETE FROM Sculture WHERE Codice=?;";
		
		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1,codice);
				
				statement.executeUpdate();

			} catch (SQLException e) {

				throw new DAOException("Eliminazione scultura non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
	
	}
	
	public static ArrayList<Scultura> readSculture() throws DBConnectionException, DAOException{
		
		ArrayList<Scultura> sculture = new ArrayList<Scultura>();
		Scultura scultura = null;
		
		String query = "SELECT * FROM Sculture;";
		
		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				
				ResultSet result = statement.executeQuery();
				
				while(result.next()) {
					scultura = new Scultura(result.getString(1), result.getString(2), result.getString(3), 
							result.getDouble(4), result.getDouble(5), result.getDouble(6));
					sculture.add(scultura);
					
				}

			} catch (SQLException e) {

				throw new DAOException("Visualizzazione sculture non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
		
		return sculture;
	
	}
	
	/* i codici sono 1S, 2S ecc per le sculture e 1D,  2D ecc per i dipinti, per capire qual è
	 * il codice del prossimo dipinto/scultura estraggo il primo carattere del codice , ovvero
	 * il numero, del dipinto/scultura che ha il codice con il numero più alto  grazie alla 
	 * query sottostante. Successivamente, lo incremento
	 * */
	private static String codiceProssimaScultura() throws DBConnectionException, DAOException{
		
	    String sql = "SELECT Codice FROM Sculture ORDER BY CAST(SUBSTRING(codice, 1, LENGTH(codice)-1) AS UNSIGNED) DESC LIMIT 1";
	    String codice = "";
		
		try {

			Connection connection = DBManager.getConnection();

			try {
				
		        Statement statement = connection.createStatement();
		        ResultSet resultSet = statement.executeQuery(sql);

		        if (resultSet.next()) { 
		            String ultimoCodice = resultSet.getString(1);
		            if (ultimoCodice != null) {
		                int nuovoNumero = Integer.parseInt(ultimoCodice.substring(0, ultimoCodice.length() - 1)) + 1;
		                codice = nuovoNumero + "S";
		            } 
		        } else {
	                codice = "1S";
	            }
		        
			} catch (SQLException e) {

				throw new DAOException("Errore nell'assegnazione del codice del Prodotto");

			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
		
		return codice;
	}
	
	
	public static Scultura readScultura(String codice) throws DBConnectionException, DAOException{
		
		Scultura scultura = null;
		
		String query = "SELECT * FROM Sculture WHERE Codice=?;";
		
		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1,codice);
				
				ResultSet result = statement.executeQuery();
				
				if(result.next()) {
					scultura = new Scultura(result.getString(1), result.getString(2), result.getString(3), 
							result.getDouble(4), result.getDouble(5), result.getDouble(6));				
				}

			} catch (SQLException e) {

				throw new DAOException("Visualizzazione scultura non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}

		return scultura;	
	}
}
