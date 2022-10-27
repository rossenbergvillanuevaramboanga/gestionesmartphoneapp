package it.prova.gestionesmartphoneapp.service.app;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public class AppServiceImpl implements AppService {
	
	private SmartphoneDAO smartphoneDAO;
	private AppDAO appDAO;

	@Override
	public void setSmatphoneDAO(SmartphoneDAO smartphoneDAOInstance) {
		// TODO Auto-generated method stub
		this.smartphoneDAO = smartphoneDAOInstance;
	}

	@Override
	public void setAppDAO(AppDAO appDAOInstance) {
		// TODO Auto-generated method stub
		this.appDAO = appDAOInstance;
	}

	@Override
	public List<App> listAll() throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			appDAO.setEntityManager(entityManager);
			return appDAO.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public App caricaSingoloElemento(Long id) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			appDAO.setEntityManager(entityManager);
			return appDAO.get(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public App caricaSingoloElementoEagerSmartphones(Long id) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			appDAO.setEntityManager(entityManager);
			return appDAO.findByIdFetchingSmartphones(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(App app) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			
			appDAO.setEntityManager(entityManager);
	
			appDAO.update(app);
			
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void inserisciNuovo(App app) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			
			appDAO.setEntityManager(entityManager);
	
			appDAO.insert(app);
			
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Long idApp) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			
			appDAO.setEntityManager(entityManager);
			smartphoneDAO.setEntityManager(entityManager);
			
			for(Smartphone smartphone : smartphoneDAO.list()) {
				smartphone.getApps().remove(appDAO.get(idApp));
			}
			
			smartphoneDAO.disinstallApp(idApp);
			appDAO.delete(appDAO.get(idApp));
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiungiSmartphone(App app, Smartphone smartphone) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			
			smartphoneDAO.setEntityManager(entityManager);
	
			smartphone = entityManager.merge(smartphone);
			app = entityManager.merge(app);
			
			app.getSmartphones().add(smartphone);
			smartphone.getApps().add(app);
			
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
