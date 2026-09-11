package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDatabase {

	//istanza statica per il singleton
	private static ConnessioneDatabase instance;

	//attributi per la connessione al database
	private Connection connection = null;
	private final String nome = "postgres";
	private final String password = "password";
	private final String url = "jdbc:postgresql://localhost:5432/Boxy";
	private final String driver = "org.postgresql.Driver";

	//costruttore privato per impedire la creazione diretta di altre istanze
	private ConnessioneDatabase() throws SQLException {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, nome, password);
		} catch (ClassNotFoundException ex) {
			System.out.println("errore caricamento driver: " + ex.getMessage());
		}
	}

	//metodo per ottenere l unica istanza attiva della connessione
	public static ConnessioneDatabase getInstance() throws SQLException {
		if (instance == null) {
			instance = new ConnessioneDatabase();
		} else if (instance.connection == null || instance.connection.isClosed()) {
			instance = new ConnessioneDatabase();
		}
		return instance;
	}

	//metodo get per ottenere la connessione rispettando l incapsulamento
	public Connection getConnection() {
		return connection;
	}
}