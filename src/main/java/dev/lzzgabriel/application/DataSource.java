package dev.lzzgabriel.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class DataSource {
	
	private Connection connection;
	
	@PostConstruct
	public void init() {
		try {
			connection = DriverManager.getConnection("jdbc:postgresql:padrao-dao?user=usuario&password=senha");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@PreDestroy
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection get() {
		return connection;
	}
	
	
	
}
