package repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.Livre;

public class LivreRepositoryImpl implements LivreRepository{

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_JPA");
	EntityManager entityManager = emf.createEntityManager();
	
	public LivreRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void save(Livre livre) {
		// TODO Auto-generated method stub
		entityManager.persist(livre);
	}

	@Override
	public Livre findById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Livre.class, id);
	}

	@Override
	public List<Livre> findAll() {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("SELECT l FROM Livre l");
        return query.getResultList();
	}

	@Override
	public List<Livre> findByName(String nom) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("SELECT a FROM Livre a WHERE a.nom = :nom");
        query.setParameter("nom", nom);
        return query.getResultList();
	}

	@Override
	public List<Livre> findByNameNamedQuery(String nom) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("Livre.findByNameNamedQuery");
        query.setParameter("nom", nom);
        return query.getResultList();
	}

}
