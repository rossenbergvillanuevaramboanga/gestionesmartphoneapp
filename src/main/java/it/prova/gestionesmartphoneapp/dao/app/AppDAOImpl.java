package it.prova.gestionesmartphoneapp.dao.app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestionesmartphoneapp.model.App;

public class AppDAOImpl implements AppDAO {
	
	private EntityManager entityManager;

	@Override
	public List<App> list() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from App", App.class).getResultList();
	}

	@Override
	public App get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return entityManager.find(App.class, id);
	}

	@Override
	public void update(App o) throws Exception {
		// TODO Auto-generated method stub
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		o = entityManager.merge(o);
	}

	@Override
	public void insert(App o) throws Exception {
		// TODO Auto-generated method stub
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(o);
	}

	@Override
	public void delete(App o) throws Exception {
		// TODO Auto-generated method stub
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(o));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub
		this.entityManager = entityManager;
	}

	@Override
	public App findByIdFetchingSmartphones(Long id) {
		// TODO Auto-generated method stub
		TypedQuery<App> query = entityManager
				.createQuery("select a FROM App a left join fetch a.smartphones s where a.id = :idApp", App.class);
		query.setParameter("idApp", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

}
