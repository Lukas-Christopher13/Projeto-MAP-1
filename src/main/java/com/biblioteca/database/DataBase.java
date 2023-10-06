package com.biblioteca.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Singleton
public class DataBase {
	private static Connection connection = null;

	private DataBase()  {

	}

	//Separai os try/catch para deixar o codigo mais organizado
	public static Connection getConnection() {
        if(connection == null) {
			try {
			    connection = DriverManager.getConnection(
				    "jdbc:sqlite:/home/lukas/Desenvolvimento/java/Projeto parte1/Projeto1/Base_De_Dados.db"
			    );
		    } catch (SQLException e) {
			    e.printStackTrace();
		    }	
		}
		return connection;
	}

	private void tryCloseConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
