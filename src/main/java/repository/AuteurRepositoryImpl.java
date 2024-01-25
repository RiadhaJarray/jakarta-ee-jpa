package repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.Auteur;

public class AuteurRepositoryImpl implements AuteurRepository{

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_JPA");
	EntityManager entityManager = emf.createEntityManager();
	
	public AuteurRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void save(Auteur auteur) {
		// TODO Auto-generated method stub
		entityManager.persist(auteur);
	}

	@Override
	public Auteur findById(int id) {
		// TODO Auto-generated method stub
		
		return entityManager.find(Auteur.class, id);
	}

	@Override
	public List<Auteur> findAll() {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("SELECT a FROM Auteur a");
        return query.getResultList();
	}

	@Override
	public List<Auteur> findByName(String nom) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("SELECT a FROM Auteur a WHERE a.nom = :nom");
        query.setParameter("nom", nom);
        return query.getResultList();
	}

	@Override
	public List<Auteur> findByNameNamedQuery(String nom) {
		// TODO Auto-generated method stub
		 Query query = entityManager.createNamedQuery("Auteur.findByNameNamedQuery");
	        query.setParameter("nom", nom);
	        return query.getResultList();
	}

}
