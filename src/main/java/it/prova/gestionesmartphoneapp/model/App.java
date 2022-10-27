package it.prova.gestionesmartphoneapp.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "app")
public class App {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "datainstallazione")
	private Date dataInstallazione;
	
	@UpdateTimestamp
	private LocalDateTime dataUltimoAggiornamento;
	
	@Column(name = "versione")
	private String versione;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "apps")
	private Set<Smartphone> smartphones = new HashSet<Smartphone>();

	public App() {

	}

	public App(String nome, String versione) {
		super();
		this.nome = nome;
		this.versione = versione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInstallazione() {
		return dataInstallazione;
	}

	public void setDataInstallazione(Date dataInstallazione) {
		this.dataInstallazione = dataInstallazione;
	}

	public LocalDateTime getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(LocalDateTime dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public String getVersione() {
		return versione;
	}

	public void setVersione(String versione) {
		this.versione = versione;
	}

	public Set<Smartphone> getSmartphones() {
		return smartphones;
	}

	public void setSmartphones(Set<Smartphone> smartphones) {
		this.smartphones = smartphones;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
