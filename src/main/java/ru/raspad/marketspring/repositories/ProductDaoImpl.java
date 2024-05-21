package ru.raspad.marketspring.repositories;

import org.hibernate.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import ru.raspad.marketspring.SessionFactoryUtils;
import ru.raspad.marketspring.dto.Product;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    private SessionFactoryUtils utils;

    public ProductDaoImpl(SessionFactoryUtils utils) {
        this.utils = utils;
    }

    @Override
    public Product findById(Long id) {
        try (Session session = utils.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }

    }
    @Override
    public List<Product> findAll() {
        try (Session session = utils.getSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("select p from Product p").getResultList();
            session.getTransaction().commit();

//            Product product = new Product();
//            product.setTitle("tttt");
//            products = List.of(product);

            return products;
        }


    }

    @Override
    public Product findByTitle(String title) {
        try (Session session = utils.getSession()) {
            session.beginTransaction();
            Product product = session.createQuery("select p from Product p where p.title =: title", Product.class)
                    .setParameter("title", title)
                    .getSingleResult();
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public void save(Product product) {
        try (Session session = utils.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Long id, String title) {
        try (Session session = utils.getSession()) {
            session.beginTransaction();
//            session.createQuery("update User u set u.name :name where u.id = :id")
//                    .setParameter("name", name)
//                    .setParameter("id", id)
//                    .executeUpdate();

            Product product = session.get(Product.class, id);
            product.setTitle(title);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = utils.getSession()) {
            session.beginTransaction();
            session.createQuery("delete Product p where p.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }
}
