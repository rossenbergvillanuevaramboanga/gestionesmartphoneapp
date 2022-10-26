package it.prova.gestionesmartphoneapp.service.smartphone;

import java.util.List;

import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public interface SmartphoneService {
	
	//CRUD METHODS
	
	public List<Smartphone> listAll() throws Exception;
	
	public Smartphone caricaSingoloElemento(Long id) throws Exception;
	
	public Smartphone caricaSingoloElementoEager(Long id) throws Exception;
	
	public void aggiorna(Smartphone smartphone) throws Exception;
	
	public void inserisciNuovo(Smartphone smartphone) throws Exception;
	
	public void rimuovi(Long idSmartphone) throws Exception;
	
	public void aggiungiApp(Smartphone smartphone, App app) throws Exception;
	
	public void rimuoviApp(Smartphone smartphone, App app) throws Exception;
	
	void setSmartphoneDAO(SmartphoneDAO smartphoneDAOInstance);
	void setAppDAO(AppDAO appDAOInstance);

}
