package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.CartaCredito;
import entity.Cliente;
import exception.DAOException;
import exception.DBConnectionException;

public class ClienteDAO { 

	public static Cliente createCliente(String username, String password, String numeroTelefono, CartaCredito cartaCredito) throws DBConnectionException, DAOException{
		
		String query = "INSERT INTO Clienti VALUES (?,?,?,?);";
		Cliente cliente = null;
		
		try {

			Connection connection = DBManager.getConnection();

			try {
				
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, username);
				statement.setString(2, password);
				statement.setString(3, numeroTelefono);
				statement.setString(4, cartaCredito.name()); 
				
				statement.executeUpdate();
				
				cliente = new Cliente(username, password, numeroTelefono, cartaCredito);

			} catch (SQLException e) {

				throw new DAOException("Creazione cliente non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
		
		return cliente;
					
	}
	
	public static ArrayList<Cliente> readClienti() throws DBConnectionException, DAOException{
		
		ArrayList<Cliente> clienti = new ArrayList<Cliente>();
		Cliente cliente = null;
		 
		String query = "SELECT * FROM Clienti;";
		
		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				
				ResultSet result = statement.executeQuery();
				
				while(result.next()) {
					cliente = new Cliente(result.getString(1), result.getString(2), result.getString(3), 
							CartaCredito.valueOf(result.getString(4)));
					clienti.add(cliente);
				}
				
			} catch (SQLException e) {

				throw new DAOException("Visualizzazione clienti non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
		
		
		return clienti;	
	}
	
	
	public static Cliente readCliente(String username) throws DBConnectionException, DAOException{
	
		Cliente cliente = null;
		 
		String query = "SELECT * FROM Clienti WHERE Username=?;";
		
		try {

			Connection connection = DBManager.getConnection();

			try {

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, username);
				
				ResultSet result = statement.executeQuery();
				
				if(result.next()) {
					cliente = new Cliente(result.getString(1), result.getString(2), result.getString(3), 
							CartaCredito.valueOf(result.getString(4)));
				} 

			} catch (SQLException e) {

				throw new DAOException("Visualizzazione cliente non riuscita");

			} finally {

				DBManager.closeConnection();
			}

		} catch (SQLException e) {

			throw new DBConnectionException("Connessione al Database non riuscita");

		}
		
		return cliente;	
	}
}
 