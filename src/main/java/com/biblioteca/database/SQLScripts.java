package com.biblioteca.database;

public enum SQLScripts {
	
	AB("""
		CREATE TABLE AB (
			Email varchar(50) PRIMARY KEY NOT NULL,
			Senha varchar(20) NOT NULL,
			Nome varchar(20) NOT NULL,
			Cargo varchar(20) NOT NULL
		)"""),

	LIVRO("""
		CREATE TABLE Livro (
			Titulo TEXT PRIMARY KEY NOT NULL,
			Email varchar(50),
			Autor varchar(50) NOT NULL,
			Ano int,
			isbn TEXT,
			Editora varchar(50),
			Tipo varchar NOT NULL,
			FOREIGN KEY (Email) REFERENCES AB(Email)
		)"""),

	LEITOR("""
		CREATE TABLE Leitor (
			Cpf int PRIMARY KEY NOT NULL,
			Email varchar(50) NOT NULL,
			Senha varchar(20) NOT NULL,
			Nome varchar(50) NOT NULL,
			Tipo varcahr(20) NOT NULL,
			FOREIGN KEY (Email) REFERENCES AB(Email)
		)"""),

	RESERVA("""
		CREATE TABLE Reserva (
		    Reserva_id int PRIMARY KEY,
			Situacao varchar(20) NOT NULL,
			None_do_Livro varchar(50) NOT NULL,
			Periodo int NOT NULL
		)"""),

	LEITOR_FAZ_RESERVA("""
		CREATE TABLE Leitor_Faz_Reserva (
			Cpf int,
			Reserva_id int,
			PRIMARY KEY(Cpf, Reserva_id),
			FOREIGN KEY (Cpf) REFERENCES Leitor(Cpf),
			FOREIGN KEY (Reserva_id) REFERENCES Reserva(Reserva_id)
		)"""),
	
	EMPRESTIMO("""
		CREATE TABLE Emprestimo (
			Emprestimo_id int PRIMARY KEY,
			Situacao varchar(20) NOT NULL,
			Data varchar(20) NOT NULL,
			Data_da_devolucao varchar(20) NOT NULL,
			Data_prevista_para_devolucao varchar(20) NOT NULL 
		)"""),
	
	LEITOR_REALIZA_EMPRESTIMO("""
		CREATE TABLE Leitor_Realiza_Emprestimo(
			Cpf int,
			Emprestimo_id int,
			PRIMARY KEY(Cpf, Emprestimo_id),
			FOREIGN KEY (Cpf) REFERENCES Leitor(Cpf),
		    FOREIGN KEY (Emprestimo_id) REFERENCES Emprestimo(Emprestimo_id)
		)"""),

	COPIAS("""
		CREATE TABLE Copias(
			Sequencia INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
			Titulo TEXT,
			Situacao varchar(20) NOT NULL,
		    Liberacao_para_emprestimo BIT NOT NULL,
			FOREIGN KEY (Titulo) REFERENCES Livro(Titulo)
		)""");

	
//Copias (sequencia,  #Titulo, situação, liberação_para_emprestimos)
	
	private String script;

	SQLScripts(String script) {
		this.script = script;
	}

	public String getScript() {
		return script;
	}
}

/*
A/B (Id_A/B, Email, Senha, Nome, Cargo)

Livro (Titulo, #Id_A/B, Autor, Ano, isbn, Editora, tipo)

Leitor (Leitor_id , #Id_A/B , Email, Senha, Nome, Tipo)

Reserva (Id_reserva, #Leitor_id, Situação, nome_do_livro, Periodo)

Leitor Faz Reserva (#Leitor_id ,#Id_reserva)

Emprestimo (Id_Emprestimo, situação, data, data_da_devolução, data_prevista_para_devolução)

Leitor realiza Emprestimo (#Leitor_id, #Id_Emprestimo)

Copias (sequencia,  #Titulo, situação, liberação_para_emprestimos)

 */
