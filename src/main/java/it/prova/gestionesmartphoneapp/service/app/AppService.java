package it.prova.gestionesmartphoneapp.service.app;

import java.util.List;

import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public interface AppService {
	
	//CRUD Operations
	
	public List<App> listAll() throws Exception;
	
	public App caricaSingoloElemento(Long id) throws Exception;
	
	//Caricamento Eager degli Smartphones
	public App caricaSingoloElementoEagerSmartphones(Long id) throws Exception;
	
	public void aggiorna(App app) throws Exception;
	
	public void inserisciNuovo(App app) throws Exception;
	
	public void rimuovi(Long idApp) throws Exception;
	
	public void aggiungiSmartphone(App app, Smartphone smartphone) throws Exception;

	void setSmatphoneDAO(SmartphoneDAO smartphoneDAOInstance);

	void setAppDAO(AppDAO appDAOInstance);

}
