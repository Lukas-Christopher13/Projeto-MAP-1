package com.biblioteca.Projeto1.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Initdb {

	public static Connection dataBase = DataBase.getConnection();
	public static Statement statement;

	public static void main(String[] args) throws Exception{
		statement = dataBase.createStatement();
        criarTabelas();
	}

	private static void criarTabelas() {
        SQLScripts[] sqlScripts = SQLScripts.values();


		for (SQLScripts sqlScript: sqlScripts) {
		    try {
				statement.execute(sqlScript.getScript());
				System.out.println(sqlScript);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
