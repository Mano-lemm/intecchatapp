package repository;

import connection.EntityManagerProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import models.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepository {
    public void createUser(User user) {
        EntityManager em = EntityManagerProvider.getEm();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public Optional<User> readUser(Long id) {
        EntityManager em = EntityManagerProvider.getEm();
        Optional<User> optionalUser = Optional.of(em.find(User.class, id));
        em.close();
        return optionalUser;
    }

    public List<User> readUsers(List<Long> ids){
        EntityManager em = EntityManagerProvider.getEm();
        String idsString = ids.stream().map(String::valueOf).collect(Collectors.joining(","));
        Query query = em.createNativeQuery("select id, name, password from users where id in (" + idsString + ")", User.class);
        List<User> resultList = (List<User>) query.getResultList();
        em.close();
        return resultList;
    }

    public List<User> readAllUsers(){
        EntityManager em = EntityManagerProvider.getEm();
        Query query = em.createNativeQuery("select id, name, password from users", User.class);
        List resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public void updateUser(User user) {
        EntityManager em = EntityManagerProvider.getEm();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteUser(User user) {
        EntityManager em = EntityManagerProvider.getEm();
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }
}
