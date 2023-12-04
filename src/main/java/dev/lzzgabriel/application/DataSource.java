package dev.lzzgabriel.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	
	private static DataSource instance;
	
	private Connection connection;
	
	public static DataSource getInstance() {
		if (instance == null) {
			instance = new DataSource();
		}
		return instance;
	}
	
	public Connection openConnection() throws SQLException, Exception {
		Class.forName("org.postgresql.Driver");
		connection = DriverManager.getConnection("jdbc:postgresql:padrao-dao?user=usuario&password=senha");
		connection.setAutoCommit(false);
		return connection;
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
