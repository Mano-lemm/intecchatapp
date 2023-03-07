package repository;

import connection.EntityManagerProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import models.Message;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MessageRepository {
    public void createMessage(Message message) {
        EntityManager em = EntityManagerProvider.getEm();
        em.getTransaction().begin();
        em.persist(message);
        em.getTransaction().commit();
        em.close();
    }

    public Optional<Message> readMessage(Long id) {
        EntityManager em = EntityManagerProvider.getEm();
        Optional<Message> optionalMessage = Optional.of(em.find(Message.class, id));
        em.close();
        return optionalMessage;
    }

    public List<Message> readMessages(List<Long> ids){
        EntityManager em = EntityManagerProvider.getEm();
        String idsString = ids.stream().map(String::valueOf).collect(Collectors.joining(","));
        Query query = em.createQuery("select id, body, receivers, sender from Message where id in (" + idsString + ")", Message.class);
        List<Message> resultList = (List<Message>) query.getResultList();
        em.close();
        return resultList;
    }

    public List<Message> readAllMessages(){
        EntityManager em = EntityManagerProvider.getEm();
        Query query = em.createQuery("select id, body, receivers, sender from Message", Message.class);
        List<Message> resultList = (List<Message>) query.getResultList();
        em.close();
        return resultList;
    }

    public void updateMessage(Message message) {
        EntityManager em = EntityManagerProvider.getEm();
        em.getTransaction().begin();
        em.merge(message);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteMessage(Message message) {
        EntityManager em = EntityManagerProvider.getEm();
        em.getTransaction().begin();
        em.remove(message);
        em.getTransaction().commit();
        em.close();
    }
}
