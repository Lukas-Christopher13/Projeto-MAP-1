package com.biblioteca.Projeto1.database;

public enum SQLScripts {
	
	AB("""
		CREATE TABLE AB (
			AB_Id int NOT NULL PRIMARY KEY,
			Email varchar(50) NOT NULL,
			Senha varchar(20) NOT NULL,
			Nome varchar(20) NOT NULL,
			Cargo varchar(20) NOT NULL 
		)"""),

	LIVRO("""
		CREATE TABLE Livro (
			Titulo TEXT PRIMARY KEY NOT NULL,
			AB_Id int,
			Autor varchar(50) NOT NULL,
			Ano int,
			isbn TEXT,
			Editora varchar(50),
			Tipo varchar NOT NULL,
			FOREIGN KEY (AB_Id) REFERENCES AB(AB_Id) 
		)"""),

	LEITOR("""
		CREATE TABLE Leitor (
			Leitor_id int PRIMARY KEY NOT NULL,
			AB_Id int,
			Email varchar(50) NOT NULL,
			Senha varchar(20) NOT NULL,
			Nome varchar(50) NOT NULL,
			Tipo varcahr(20) NOT NULL,
			FOREIGN KEY (AB_Id) REFERENCES AB(AB_Id)
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
			Leitor_id int,
			Reserva_id int,
			PRIMARY KEY(Leitor_id, Reserva_id),
			FOREIGN KEY (Leitor_id) REFERENCES Leitor(Leitor_id),
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
			Leitor_id int,
			Emprestimo_id int,
			PRIMARY KEY(Leitor_id, Emprestimo_id),
			FOREIGN KEY (Leitor_id) REFERENCES Leitor(Leitor_id),
		    FOREIGN KEY (Emprestimo_id) REFERENCES Emprestimo(Emprestimo_id)
		)"""),

	COPIAS("""
		CREATE TABLE Copias(
			Sequencia int PRIMARY KEY,
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
