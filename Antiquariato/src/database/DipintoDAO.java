package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Dipinto;
import exception.DAOException;
import exception.DBConnectionException;

 
public class DipintoDAO{

	public static Dipinto createDipinto(String nome, String descrizione, double prezzo, String dimensioni, String tecnica) throws DBConnectionException, DAOException{
		
		String codice = codiceProssimoDipinto(); 
		Dipinto dipinto = null;
		
		String query = "INSERT INTO Dipinti VALUES (?,?,?,?,?,?);";
		
		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, codice);
				statement.setString(2, nome);
				statement.setString(3, descrizione);
				statement.setDouble(4, prezzo);
				statement.setString(5, tecnica);
				statement.setString(6, dimensioni);
				
				statement.executeUpdate();
				
				dipinto = new Dipinto(codice, nome, descrizione, prezzo, tecnica, dimensioni);

			} catch (SQLException e) {

				throw new DAOException("Creazione Dipinto non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
		
		return dipinto;
		
	}
	
	public static Dipinto updateDipinto(String codice, String nome, String descrizione, double prezzo, String tecnica, String dimensioni) throws DBConnectionException, DAOException{
		

		Dipinto dipinto = null;
		
		String query = "UPDATE Dipinti SET Nome=?, Descrizione=?, Prezzo=?, Tecnica=?, Dimensioni=? WHERE Codice=?;";
		
		
		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, nome);
				statement.setString(2, descrizione);
				statement.setDouble(3, prezzo);
				statement.setString(4, tecnica);
				statement.setString(5, dimensioni);
				statement.setString(6, codice);
				
				statement.executeUpdate();
				
				dipinto = new Dipinto(codice, nome, descrizione, prezzo, tecnica, dimensioni);

			} catch (SQLException e) {

				throw new DAOException("Modifica dipinto non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}	
		
		return dipinto;
		
	}
	
	public static void deleteDipinto(String codice) throws DBConnectionException, DAOException{
		
		String query = "DELETE FROM Dipinti WHERE Codice=?;";
		
		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1,codice);
				
				statement.executeUpdate();

			} catch (SQLException e) {

				throw new DAOException("Eliminazione dipinto non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
		
	}
	
	public static ArrayList<Dipinto> readDipinti() throws DBConnectionException, DAOException{
		
		ArrayList<Dipinto> dipinti = new ArrayList<Dipinto>();
		Dipinto dipinto = null;
		
		String query = "SELECT * FROM Dipinti;";
		
		
		try {

			Connection connection = DBManager.getConnection();

			try {
				
				PreparedStatement statement = connection.prepareStatement(query);
				
				ResultSet result = statement.executeQuery();
				
				while(result.next()) {
					dipinto = new Dipinto(result.getString(1), result.getString(2), result.getString(3), 
							result.getDouble(4), result.getString(5), result.getString(6));
					dipinti.add(dipinto);
					
				}
				

			} catch (SQLException e) {

				throw new DAOException("Visualizzazione dipinti non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
		
		return dipinti;	
	}
	
	
	public static Dipinto readDipinto(String codice) throws DBConnectionException, DAOException{
		
		Dipinto dipinto = null;
		
		String query = "SELECT * FROM Dipinti WHERE Codice=?;";
		
		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1,codice);
				
				ResultSet result = statement.executeQuery();
				
				if(result.next()) {
					dipinto = new Dipinto(result.getString(1), result.getString(2), result.getString(3), 
							result.getDouble(4), result.getString(5), result.getString(6));				
				}

			} catch (SQLException e) {

				throw new DAOException("Visualizzazione dipinto non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
		
		return dipinto;	
	}
	
	
	private static String codiceProssimoDipinto() throws DBConnectionException, DAOException{
		
		/* i codici sono 1S, 2S ecc per le sculture e 1D,  2D ecc per i dipinti, per capire qual è
		 * il codice del prossimo dipinto/scultura estraggo il primo carattere del codice , ovvero
		 * il numero, del dipinto/scultura che ha il codice con il numero più alto  grazie alla 
		 * query sottostante. Successivamente, lo incremento
		 * */
	    String sql = "SELECT Codice FROM Dipinti ORDER BY CAST(SUBSTRING(codice, 1, LENGTH(codice)-1) AS UNSIGNED) DESC LIMIT 1";
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
		                codice = nuovoNumero + "D";
		            } 
		        } else {
	                codice = "1D";
	            }
		        
			} catch (SQLException e) {

				throw new DAOException("Errore nell'assegnazione del codice del Prodotto");

			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
		
		return codice;
	}
}

