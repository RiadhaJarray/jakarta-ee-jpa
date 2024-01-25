package repository;

import java.util.List;

import model.Livre;

public interface LivreRepository {

	public void save(Livre livre);
	public Livre findById(int id);
	public List<Livre> findAll();
	public List<Livre> findByName(String nom);
	public List<Livre> findByNameNamedQuery(String nom);
}
