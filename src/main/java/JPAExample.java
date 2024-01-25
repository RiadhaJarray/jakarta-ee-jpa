import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Auteur;
import model.Livre;
import repository.AuteurRepository;
import repository.AuteurRepositoryImpl;
import repository.LivreRepository;
import repository.LivreRepositoryImpl;

public class JPAExample {

	public static void main(String[] args) {
        // Créer l'EntityManagerFactory en utilisant le nom de l'unité de persistance défini dans persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_JPA");

        // Créer l'EntityManager
        EntityManager entityManager = emf.createEntityManager();
        
        AuteurRepository auteurRepository = new AuteurRepositoryImpl(entityManager);
        LivreRepository livreRepository = new LivreRepositoryImpl(entityManager);
        
        try {
            // Commencer une transaction
        	entityManager.getTransaction().begin();

            // Votre logique JPA ici

            // Exemple : Créer un nouvel auteur
            Auteur newAuteur = new Auteur("Riadh");
            Livre newLivre1 = new Livre("livre1");
            Livre newLivre2 = new Livre("livre2");
            Livre newLivre3 = new Livre("livre3");
            

            // Sauvegarder l'auteur dans la base de données
            auteurRepository.save(newAuteur);
            livreRepository.save(newLivre1);
            livreRepository.save(newLivre2);
            livreRepository.save(newLivre3);

            // Exemple : Trouver tous les auteurs
            List<Auteur> auteurs = auteurRepository.findAll();
            for (Auteur a : auteurs) {
                System.out.println("Auteur ID: " + a.getId() + ", Nom: " + a.getNom());
            }
            List<Auteur> auteurByNom = auteurRepository.findByName("Riadh");
            for (Auteur a : auteurByNom) {
                System.out.println("AuteurByNom, Auteur  ID: " + a.getId() + ", Nom: " + a.getNom());
            }
            
            List<Livre> livres = livreRepository.findAll();
            for (Livre l : livres) {
                System.out.println("Livre ID: " + l.getId() + ", Nom: " + l.getNom());
            }
            
            Livre livreById = livreRepository.findById(16);
                System.out.println("livreById, Livre  ID: " + livreById.getId() + ", Nom: " + livreById.getNom());

            try {
            	Livre livreByIdNonValid = livreRepository.findById(7);
            	System.out.println("livreByIdNonValid, Livre  ID: " + livreById.getId() + ", Nom: " + livreById.getNom());
            }
            catch (Exception e) {
            	System.out.println("livreByIdNonValid, Livre not found");
            }
            
            List<Livre> livreByNom = livreRepository.findByName("livre1");
            for (Livre a : livreByNom) {
                System.out.println("livreByNom, livre  ID: " + a.getId() + ", Nom: " + a.getNom());
            }
            
            List<Livre> livreByNomNamedQuery = livreRepository.findByNameNamedQuery("livre2");
            for (Livre a : livreByNomNamedQuery) {
                System.out.println("livreByNomNamedQuery, livre  ID: " + a.getId() + ", Nom: " + a.getNom());
            }
            
            
            newLivre3.setAuteur(newAuteur);
            livreRepository.save(newLivre3);
            
            // Valider et appliquer les modifications dans la base de données
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            // Gérer les exceptions
            if (entityManager.getTransaction().isActive()) {
            	entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Fermer l'EntityManager
        	entityManager.close();

            // Fermer l'EntityManagerFactory
            emf.close();
        }
        
	}
}
