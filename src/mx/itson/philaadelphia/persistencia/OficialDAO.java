package mx.itson.philaadelphia.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.JOptionPane;
import mx.itson.philaadelphia.entidades.Oficial;
import mx.itson.philaadelphia.utilerias.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 * Esta clase se encarga de realizar las operaciones de persistencia relacionadas
 * con la entidad Oficial en la base de datos.
 */
public class OficialDAO {

    /**
     * Método para obtener la lista de oficiales
     * @return la lista completa de oficiales
     */
    public static List<Oficial> obtenerTodos() {
        List<Oficial> oficiales = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Oficial> criteriaQuery = session.getCriteriaBuilder().createQuery(Oficial.class);
            criteriaQuery.from(Oficial.class);
            oficiales = session.createQuery(criteriaQuery).getResultList();

        } catch (Exception ex) {
            System.out.println("Ocurrio un error: " + ex);
        }
        return oficiales;
    }

    /**
     * Método que guarda al nuevo oficial en la base de datos
     * @param nombre nombre del oficial
     * @param telefono telefono del oficial
     * @return true si logra crear el registro
     */
     
    public boolean guardar(String nombre, String telefono) {
        boolean resultado = false;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Oficial o = new Oficial();
            o.setNombre(nombre);
            o.setTelefono(telefono);
            session.save(o);

            session.getTransaction().commit();

            resultado = o.getId() != 0;

        } catch (Exception ex) {
            System.out.println("Ocurrio un error: " + ex);
        }
        return resultado;
    }

    /**
     * Método que elimina al oficial de la base de datos a partir de su id
     * @param id id del oficial a eliminar
     */
    public void eliminarOficial(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Oficial oficial = session.get(Oficial.class, id);
            if (oficial != null) {
                session.delete(oficial);
                transaction.commit();
                JOptionPane.showMessageDialog(null, "Oficial eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un oficial con ID " + id);
            }
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex);
        }
    }
}
