package it.marsico.treasure.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	static Connection conn;
	static String url;

	public static void connessioneDB() {
		url = "jdbc:h2:/user/database/db";
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println("Connessione non avvenuta");
			e.printStackTrace();
		}
	}

	public static void chiudiConnessione() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void creaTabellaDialogo() {
		String creaDialogo = 
		"CREATE TABLE IF NOT EXISTS dialogo (id INT NOT NULL AUTO_INCREMENT, personaggio CHAR(10), posizione CHAR(50), conversazione CHAR(300) NOT NULL, PRIMARY KEY(id));";

		try {
			Statement stm = conn.createStatement();
			stm.execute(creaDialogo);
			stm.close();
		} catch (SQLException e) {
			System.out.println("Errore creazione tabella");
			e.printStackTrace();
		}
	}

	public static void inserimentoDialogo() {
		try {
			Statement stmt = conn.createStatement();

			stmt.execute("INSERT INTO dialogo (personaggio, posizione, conversazione) "+
			"VALUES ('Rabber','Taverna del lupo di mare', 'Ei tu!')");
			
			stmt.execute("INSERT INTO dialogo (personaggio, posizione, conversazione) "+
			"VALUES ('Chopper','Taverna del lupo di mare', 'Mh..?')");
			
			stmt.execute("INSERT INTO dialogo (personaggio, posizione, conversazione) "+
			"VALUES ('Rabber','Taverna del lupo di mare', 'Per caso hai qualche oggetto interessante da propormi?')");
			
			stmt.execute("INSERT INTO dialogo (personaggio, posizione, conversazione) "+
			"VALUES ('Chopper','Taverna del lupo di mare', 'Per chi mi hai preso.. un ..djsf.....mercante?')");
			
			stmt.execute("INSERT INTO dialogo (personaggio, posizione, conversazione) "+
			"VALUES ('Rabber','Taverna del lupo di mare', 'E dai non essere scontroso!')");
			
			stmt.execute("INSERT INTO dialogo (personaggio, posizione, conversazione) "+
			"VALUES ('Chopper','Taverna del lupo di mare', 'Ei ma quel sacco di danari che hai vicino alla cintura è mio...o forse no.. aaaah..(beve)')");
			
			stmt.execute("INSERT INTO dialogo (personaggio, posizione, conversazione) "+
			"VALUES ('Rabber','Taverna del lupo di mare', 'Se ti interessa posso dartelo ma solo se hai qualcosa da scambiare. Forza... sembra buono quanto caro quel rum hehehe..')");
			
			stmt.execute("INSERT INTO dialogo (personaggio, posizione, conversazione) "+
			"VALUES ('Chopper','Taverna del lupo di mare', 'Mhh.. brutto inolente..skjfhsj....va bene, ho questa lente che ho rubato ad un povero sventurato la quale barca è stata affondata')");
			
			stmt.execute("INSERT INTO dialogo (personaggio, posizione, conversazione) "+
			"VALUES ('Rabber','Taverna del lupo di mare', 'Risparmiarmi i particolari..')");
			
			stmt.execute("INSERT INTO dialogo (personaggio, posizione, conversazione) "+
			"VALUES ('Chopper','Taverna del lupo di mare', 'Dammi i soldi, prendi quello che ti serve e vattene! sto perdend..sdfs...perdendo la pazienza..')");
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void query() {
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM dialogo";

			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getString("personaggio") + "\t" + rs.getString("conversazione"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void queryClear() throws SQLException{
		connessioneDB();
		Statement stmt = conn.createStatement();
		stmt.execute("TRUNCATE TABLE dialogo");
		stmt.close();
		chiudiConnessione();
;	}

	public static void creaDB(){
		connessioneDB();
		creaTabellaDialogo();
		inserimentoDialogo();
		query();
		chiudiConnessione();
	}
} 