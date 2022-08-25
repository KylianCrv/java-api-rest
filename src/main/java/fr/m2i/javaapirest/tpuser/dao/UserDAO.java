package fr.m2i.javaapirest.tpuser.dao;

import fr.m2i.javaapirest.tpuser.model.User;
import fr.m2i.javaapirest.tpuser.util.SessionHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class UserDAO {

    private final EntityManager entityManager;

    public UserDAO() {
        this.entityManager = SessionHelper.getEntityManager();
    }

    public List<User> findAllUsers() {
        Query findAllQuerry = entityManager.createQuery("SELECT u FROM User u");
        return findAllQuerry.getResultList();
    }

    public User findUserById(int id) {
        User founded = entityManager.find(User.class, id);

        return founded;
    }

    public List<User> findUserByEmailOrLastname(String q, int count) {
        Query findUserByEmailOrLastname = entityManager.createQuery("SELECT u FROM User u WHERE u.email LIKE ?1 OR u.lastname LIKE ?2");
        findUserByEmailOrLastname.setParameter(1, q);
        findUserByEmailOrLastname.setParameter(2, q);
        return findUserByEmailOrLastname.setMaxResults(count).getResultList();
    }

    public boolean create(User user) {
        //Vérifier le param utilisateur

        if (user == null) {
            System.out.println("L'objet utilisateur ne peut pas être null");
            return false;
        }

        EntityTransaction tx = null;

        try {

            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.persist(user);

            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite durant la création de l'utilisateur");
            if (tx != null) {
                tx.rollback();
                return false;
            }
        }
        return true;
    }

    public boolean update(int id, User userData) {

        User toUpdate = entityManager.find(User.class, id);

        if (toUpdate == null) {
            System.out.println("Attention l'utilisateur avec l'id : " + id + " n'existe pas.");
            return false;
        }

        toUpdate.copy(userData);

        EntityTransaction tx = null;

        try {

            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.merge(toUpdate);

            tx.commit();

        } catch (Exception e) {
            System.out.println("Une erreur s'est produite durant la modification de l'utilisateur");
            if (tx != null) {
                tx.rollback();
                return false;
            }
        }
        return true;

    }

    public boolean delete(int id) {

        User toDelete = entityManager.find(User.class, id);

        if (toDelete == null) {
            System.out.println("Attention le produit avec l'id : " + id + " n'existe pas.");
            return false;
        }

        EntityTransaction tx = null;

        try {

            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.remove(toDelete);

            tx.commit();

        } catch (Exception e) {
            System.out.println("Une erreur s'est produite durant la suppression du produit");
            if (tx != null) {
                tx.rollback();
                return false;

            }
        }
        return true;
    }

}
