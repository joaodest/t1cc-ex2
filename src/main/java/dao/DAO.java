package dao;

import java.sql.*;
import java.security.*;

public class DAO {
	protected Connection connection;

	public DAO() {
		connection = null;
	}

	public boolean connectToDatabase() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String database = "teste";

		int port = 5432;

		String url = "jdbc:postgresql://" + serverName + ":" + port + "/" + database;
		String username = "postgres";
		String password = "root";
		boolean status = false;

		try {

			Class.forName(driverName);
			connection = DriverManager.getConnection(url, username, password);
			status = (connection == null);

			System.out.println("Conexão efetuada com sucesso.");

		} catch (ClassNotFoundException e) {
			System.err.println("Conexão falhou -- Driver não encontrado -- " + e.getMessage());

		} catch (SQLException e) {
			System.err.println("Conexão falhou -- Falha no DB -- " + e.getMessage());
		}

		return status;
	}

	public boolean close() {
		boolean status = false;
		try {
			connection.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return status;
	}
}
