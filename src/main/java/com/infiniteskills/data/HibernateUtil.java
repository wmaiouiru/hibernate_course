package com.infiniteskills.data;

import com.infiniteskills.data.entities.User;
import org.hibernate.HibernateError;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory5();

    private static SessionFactory buildSessionFactory4(){
        try{
            // hibernate.properties version
            // configuration.addAnnotatedClass(User.class);
            Configuration configuration = new Configuration();
            return configuration.
                    buildSessionFactory(
                        new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties())
                            .build()
                    );
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("There was an error building the factory");
        }
    }

    private static SessionFactory buildSessionFactory5(){
        try {
            StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(standardServiceRegistry)
                    .getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (HibernateError he){
            System.out.println("Session Factory Creation Failure");
            throw he;
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
