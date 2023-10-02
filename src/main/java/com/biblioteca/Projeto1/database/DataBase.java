package com.biblioteca.Projeto1.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Singleton
public class DataBase {
	private static DataBase dataBase = null;
	private Connection connection;

	//construtor privado
	private DataBase()  {
		tryDataBase();
	}

	//Separai os try/catch para deixar o codigo mais organizado
	private void tryDataBase() {
		try {
			this.connection = DriverManager.getConnection(
				"jdbc:sqlite:/home/lukas/Desenvolvimento/java/Projeto parte1/Projeto1/Base_De_Dados.db"
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	//função statica get para o singleton
	public static Connection getConnection() {
		if(dataBase == null) {
			dataBase = new DataBase();
		}
		return dataBase.connection;
	}

    public void closeConnection() {
		tryCloseConnection();
	}

	private void tryCloseConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
