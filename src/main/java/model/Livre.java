package model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="T_LIVRE")
@NamedQuery(name = "Livre.findByNameNamedQuery", query = "SELECT l FROM Livre l WHERE l.nom = :nom")
@NamedQuery(name = "Livre.findAllQuery", query = "SELECT l FROM Livre l")
public class Livre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	
	public Livre(String nom) {
		super();
		this.nom = nom;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auteur_id")
	private Auteur auteur;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Auteur getAuteur() {
		return auteur;
	}
	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}
	
}
