package repository;

import java.util.List;

import model.Auteur;

public interface AuteurRepository {

	public void save(Auteur auteur);
	public Auteur findById(int id);
	public List<Auteur> findAll();
	public List<Auteur> findByName(String nom);
	public List<Auteur> findByNameNamedQuery(String nom);
}
