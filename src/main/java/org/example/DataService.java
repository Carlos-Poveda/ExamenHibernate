package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DataService {
    SessionFactory sessionFactory;

    public DataService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Save película
    public Pelicula savePelicula(Pelicula pelicula) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(pelicula);
            transaction.commit();
            return pelicula;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    // Find opiniones by usuario
    public List<Opinion> findOpinionesByUsuario(String usuario) {
        try (Session session = sessionFactory.openSession()) {
            Query<Opinion> query = session.createQuery(
                    "FROM Opinion o WHERE o.usuario = :usuario", Opinion.class);
            query.setParameter("usuario", usuario);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    // Agregar opinión a película
    public boolean agregarOpinion(Integer peliculaId, Opinion nuevaOpinion) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Pelicula pelicula = session.find(Pelicula.class, peliculaId);
            if (pelicula != null) {
                pelicula.addOpinion(nuevaOpinion);
                session.merge(pelicula);
                transaction.commit();
                return true;
            }
            transaction.rollback();
            return false;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // Find películas by puntuación
    public List<Pelicula> findPeliculasByPuntuacion(Integer minPuntuacion) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT DISTINCT p FROM Pelicula p " +
                    "JOIN FETCH p.opiniones o " +
                    "WHERE o.puntuacion <= :puntuacion";
            Query<Pelicula> query = session.createQuery(hql, Pelicula.class);
            query.setParameter("puntuacion", minPuntuacion);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
