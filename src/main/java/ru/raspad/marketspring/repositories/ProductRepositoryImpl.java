package ru.raspad.marketspring.repositories;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.raspad.marketspring.SessionFactoryUtils;
import ru.raspad.marketspring.entity.ProductDao;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private SessionFactoryUtils utils;

    public ProductRepositoryImpl(SessionFactoryUtils utils) {
        this.utils = utils;
    }

    @Override
    public ProductDao findById(Long id) {
        try (Session session = utils.getSession()) {
            session.beginTransaction();
            ProductDao productDao = session.get(ProductDao.class, id);
            session.getTransaction().commit();
            return productDao;
        }

    }
    @Override
    public List<ProductDao> findAll() {
        try (Session session = utils.getSession()) {
            session.beginTransaction();
            List<ProductDao> productDaos = session.createQuery("select p from ProductDao p").getResultList();
            session.getTransaction().commit();

//            ProductDto product = new ProductDto();
//            product.setTitle("tttt");
//            productDaos = List.of(product);

            return productDaos;
        }


    }

    @Override
    public ProductDao findByTitle(String title) {
        try (Session session = utils.getSession()) {
            session.beginTransaction();
            ProductDao productDao = session.createQuery("select p from ProductDao p where p.title =: title", ProductDao.class)
                    .setParameter("title", title)
                    .getSingleResult();
            session.getTransaction().commit();
            return productDao;
        }
    }

    @Override
    public void save(ProductDao productDao) {
        try (Session session = utils.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(productDao);
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

            ProductDao productDao = session.get(ProductDao.class, id);
            productDao.setTitle(title);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = utils.getSession()) {
            session.beginTransaction();
            session.createQuery("delete ProductDao p where p.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }
}
