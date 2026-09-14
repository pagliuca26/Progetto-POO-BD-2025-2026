package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestisce la connessione con il database PostgreSQL tramite il pattern Singleton,
 * garantendo che esista una sola connessione condivisa in tutta l'applicazione.
 */
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

	/**
	 * Restituisce l'unica istanza attiva della classe ConnessioneDatabase (Singleton).
	 * Se l'istanza non esiste o la connessione risulta chiusa, ne crea una nuova.
	 *
	 * @return l'istanza singleton di ConnessioneDatabase
	 * @throws SQLException se si verifica un errore durante l'apertura della connessione
	 */
//metodo per ottenere l unica istanza attiva della connessione
	public static ConnessioneDatabase getInstance() throws SQLException {
		if (instance == null) {
			instance = new ConnessioneDatabase();
		} else if (instance.connection == null || instance.connection.isClosed()) {
			instance = new ConnessioneDatabase();
		}
		return instance;
	}

	/**
	 * Restituisce l'oggetto Connection attivo per eseguire le operazioni JDBC sul database.
	 *
	 * @return la connessione attiva con il database PostgreSQL
	 */
//metodo get per ottenere la connessione rispettando l incapsulamento
	public Connection getConnection() {
		return connection;
	}
}