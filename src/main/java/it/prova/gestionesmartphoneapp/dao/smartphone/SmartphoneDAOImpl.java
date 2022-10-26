package it.prova.gestionesmartphoneapp.dao.smartphone;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public class SmartphoneDAOImpl implements SmartphoneDAO {
	
	private EntityManager entityManager;
	

	@Override
	public List<Smartphone> list() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Smartphone", Smartphone.class).getResultList();
	}

	@Override
	public Smartphone get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return entityManager.find(Smartphone.class, id);
	}

	@Override
	public void update(Smartphone o) throws Exception {
		// TODO Auto-generated method stub
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		o = entityManager.merge(o);
		
	}

	@Override
	public void insert(Smartphone o) throws Exception {
		// TODO Auto-generated method stub
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(o);
	}

	@Override
	public void delete(Smartphone o) throws Exception {
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
	public Smartphone findByIdFetchApps(Long id) {
		// TODO Auto-generated method stub
		TypedQuery<Smartphone> query = entityManager
				.createQuery("select s FROM Smartphone s left join fetch s.apps a where s.id = :idSmartphone", Smartphone.class);
		query.setParameter("idSmartphone", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

}
