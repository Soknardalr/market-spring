package ru.raspad.marketspring.repositories;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.raspad.marketspring.SessionFactoryUtils;
import ru.raspad.marketspring.entity.CustomerDao;

import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private SessionFactoryUtils utils;
    public CustomerRepositoryImpl(@Autowired SessionFactoryUtils utils) {
        this.utils = utils;
    }
    @Override
    public CustomerDao findById(Long id) {
        try (Session session = utils.getSession()) {
            session.getTransaction();
            CustomerDao customerDao = session.get(CustomerDao.class, id);
            session.getTransaction().commit();
            return customerDao;
        }
    }
    @Override
    public List<CustomerDao> findAll() {
        try (Session session = utils.getSession()) {
            session.getTransaction();
            List<CustomerDao> customerDaos = session.createQuery("select c from CustomerDao c").getResultList();
            session.getTransaction().commit();
            return customerDaos;
        }
    }
    @Override
    public CustomerDao findByName(String name) {
        try (Session session = utils.getSession()) {
            session.getTransaction();
            CustomerDao customerDao = session.createQuery("select c from CustomerDao c where c.name =: name", CustomerDao.class)
                    .setParameter("name", name)
                    .getSingleResult();
            session.getTransaction().commit();
            return customerDao;
        }
    }
    @Override
    public void save(CustomerDao customerDao) {
        try (Session session = utils.getSession()) {
            session.getTransaction();
            session.saveOrUpdate(customerDao);
            session.getTransaction().commit();
        }

    }
    @Override
    public void update(Long id, String name) {
        try (Session session = utils.getSession()) {
            session.getTransaction();
            CustomerDao customerDao = session.get(CustomerDao.class, id);
            customerDao.setName(name);
            session.getTransaction().commit();
        }
    }
    @Override
    public void deleteById(Long id) {
        try (Session session = utils.getSession()) {
            session.getTransaction();
            session.createQuery("delete  CustomerDao c where c.id =: id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }
}
