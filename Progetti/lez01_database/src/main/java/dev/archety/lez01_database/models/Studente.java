package dev.archety.lez01_database.models;

public class Studente {

	private int id;
	private String nome;
	private String cognome;
	private String matricola;
	
	public Studente() {
		
	}
	
	public Studente(int id, String nome, String cognome, String matricola) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getMatricola() {
		return matricola;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	
	
	
}
