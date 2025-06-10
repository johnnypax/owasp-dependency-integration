package dev.archety.lez02_es_dal.models;

public class Contatto {

	private int id;				//contattoID sul database
	private String nome;
	private String cogn;
	private String mail;
	private String tele;
	
	public Contatto() {
		
	}
	
	public Contatto(String nome, String cogn, String mail, String tele) {
		super();
		this.nome = nome;
		this.cogn = cogn;
		this.mail = mail;
		this.tele = tele;
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
	public String getCogn() {
		return cogn;
	}
	public void setCogn(String cogn) {
		this.cogn = cogn;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	
	@Override
	public String toString() {
		return "Contatto [id=" + id + ", nome=" + nome + ", cogn=" + cogn + ", mail=" + mail + ", tele=" + tele + "]";
	}
}
