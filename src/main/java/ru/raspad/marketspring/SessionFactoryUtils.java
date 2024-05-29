package ru.raspad.marketspring;

import jakarta.annotation.PostConstruct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class SessionFactoryUtils {
    private SessionFactory factory;
    @PostConstruct //TODO: CLOSE CONNECTION
    public void init(){
        factory = new Configuration()
                .configure("old repository/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }

    public void shutdown(){
        if (factory!=null){
            factory.close();
        }
    }
}
