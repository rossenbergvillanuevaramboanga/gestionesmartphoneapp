package it.prova.gestionesmartphoneapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "smartphone")
public class Smartphone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "marca")
	private String marca;
	@Column(name = "modello")
	private String modello;
	@Column(name = "prezzo")
	private Integer prezzo;
	@Column(name = "versioneos")
	private String versioneOS;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "smartphone_app", joinColumns = @JoinColumn(name = "smarthphone_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "app_id", referencedColumnName = "ID"))
	private Set<App> apps = new HashSet<App>();
	
	public Smartphone() {
		// TODO Auto-generated constructor stub
	}
	
	public Smartphone(String marca, String modello, Integer prezzo, String versioneOS) {
		super();
		this.marca = marca;
		this.modello = modello;
		this.prezzo = prezzo;
		this.versioneOS = versioneOS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public String getVersioneOS() {
		return versioneOS;
	}

	public void setVersioneOS(String versioneOS) {
		this.versioneOS = versioneOS;
	}

	public Set<App> getApps() {
		return apps;
	}

	public void setApps(Set<App> apps) {
		this.apps = apps;
	}

	public void removeFromApp(App app) {
		// TODO Auto-generated method stub
		this.apps.remove(app);
		app.getSmartphones().remove(this);
		
	}
	
	
}
