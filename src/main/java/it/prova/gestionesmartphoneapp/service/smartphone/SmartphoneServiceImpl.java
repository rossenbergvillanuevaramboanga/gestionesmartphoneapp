package it.prova.gestionesmartphoneapp.service.smartphone;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public class SmartphoneServiceImpl implements SmartphoneService {
	
	private SmartphoneDAO smartphoneDAO;
	private AppDAO appDAO;

	@Override
	public void setSmartphoneDAO(SmartphoneDAO smartphoneDAOInstance) {
		// TODO Auto-generated method stub
		this.smartphoneDAO = smartphoneDAOInstance;
	}

	@Override
	public void setAppDAO(AppDAO appDAOInstance) {
		// TODO Auto-generated method stub
		this.appDAO = appDAOInstance;
	}

	@Override
	public List<Smartphone> listAll() throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			smartphoneDAO.setEntityManager(entityManager);
	
			return smartphoneDAO.list();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Smartphone caricaSingoloElemento(Long id) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			smartphoneDAO.setEntityManager(entityManager);
	
			return smartphoneDAO.get(id);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Smartphone caricaSingoloElementoEager(Long id) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			smartphoneDAO.setEntityManager(entityManager);
	
			return smartphoneDAO.findByIdFetchApps(id);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Smartphone smartphone) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			
			smartphoneDAO.setEntityManager(entityManager);
	
			smartphoneDAO.update(smartphone);
			
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
	public void inserisciNuovo(Smartphone smartphone) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			
			smartphoneDAO.setEntityManager(entityManager);
	
			smartphoneDAO.insert(smartphone);
			
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
	public void rimuovi(Long idSmartphone) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			
			smartphoneDAO.setEntityManager(entityManager);
	
			smartphoneDAO.update(smartphoneDAO.get(idSmartphone));
			
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
	public void aggiungiApp(Smartphone smartphone, App app) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			
			smartphoneDAO.setEntityManager(entityManager);
	
			smartphone = entityManager.merge(smartphone);
			app = entityManager.merge(app);
			
			smartphone.getApps().add(app);
			app.getSmartphones().add(smartphone);
			
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
	public void rimuoviApp(Smartphone smartphone, App app) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			
			smartphoneDAO.setEntityManager(entityManager);
	
			smartphone = entityManager.merge(smartphone);
			app = entityManager.merge(app);
			
			smartphone.getApps().remove(app);
			app.getSmartphones().remove(smartphone);
			
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
