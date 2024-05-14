package ru.raspad.marketspring.repositories;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.raspad.marketspring.SessionFactoryUtils;
import ru.raspad.marketspring.dto.Customer;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    private SessionFactoryUtils utils;

    public CustomerDaoImpl(SessionFactoryUtils utils) {
        this.utils = utils;
    }

    @Override
    public Customer findById(Long id) {
        try (Session session = utils.getSession()) {
            session.getTransaction();
            Customer customer = session.get(Customer.class, id);
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public List<Customer> findAll() {
        try (Session session = utils.getSession()) {
            session.getTransaction();
            List<Customer> customers = session.createQuery("select c from Customer c").getResultList();
            session.getTransaction().commit();
            return customers;
        }
    }

    @Override
    public Customer findByName(String name) {
        try (Session session = utils.getSession()) {
            session.getTransaction();
            Customer customer = session.createQuery("select c from Customer c where c.name =: name", Customer.class)
                    .setParameter("name", name)
                    .getSingleResult();
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public void save(Customer customer) {
        try (Session session = utils.getSession()) {
            session.getTransaction();
            session.saveOrUpdate(customer);
            session.getTransaction().commit();
        }

    }

    @Override
    public void update(Long id, String name) {
        try (Session session = utils.getSession()) {
            session.getTransaction();
            Customer customer = session.get(Customer.class, id);
            customer.setName(name);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = utils.getSession()) {
            session.getTransaction();
            session.createQuery("delete  Customer c where c.id =: id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }
}
