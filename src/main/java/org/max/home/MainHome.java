package org.max.home;

import org.flywaydb.core.Flyway;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

public class DatabaseMigration {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(String[] args) {
        // Инициализация Flyway
        Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://localhost/db?user=root&password=1234").load();

        // Применение миграций
        flyway.migrate();

        try (Session session = getSession()) {
            System.out.println("Выполнение запросов к базе данных...");
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                String entityName = entityType.getName();
                Query<?> query = session.createQuery("from " + entityName);
                System.out.println("Выполнение запроса: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }
}