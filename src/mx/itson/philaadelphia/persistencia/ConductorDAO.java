package mx.itson.philaadelphia.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.JOptionPane;
import mx.itson.philaadelphia.entidades.Conductor;
import mx.itson.philaadelphia.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Esta clase se encarga de realizar las operaciones de persistencia relacionadas
 * con la entidad Conductor en la base de datos.
 */
public class ConductorDAO {
    
    /**
     * Obtiene una lista de todos los conductores en la base de datos.
     * @return Una lista con todos los conductores.
     */
    public List<Conductor> obtenerTodos() {
        List<Conductor> conductores = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession(); 
            CriteriaQuery<Conductor> criteriaQuery = session.getCriteriaBuilder().createQuery(Conductor.class);
            criteriaQuery.from(Conductor.class);
            conductores = session.createQuery(criteriaQuery).getResultList();
        } catch(Exception ex) {
            System.out.println("Ocurrió un error: " + ex);
        }
        return conductores;
    }
    
    /**
     * Obtiene un objeto Conductor de la base de datos por su ID.
     * @param id El ID del conductor que se desea obtener.
     * @return Un objeto Conductor si se encontró, o null si no se encontró.
     */
    public Conductor obtenerConductorPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Conductor conductor = session.get(Conductor.class, id);
            return conductor;
        } catch (Exception ex) {
            System.out.println("Ocurrió un error al obtener el conductor con ID " + id + ": " + ex);
            return null;
        }
    }
    
 /**
 * Actualiza la información del conductor en la base de datos.
 * @param conductor El objeto Conductor a actualizar.
 * @return true si la operación fue exitosa, false si no lo fue.
 */
public boolean actualizar(Conductor conductor){
    boolean resultado = false;
    try{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(conductor);
        session.getTransaction().commit();
        resultado = true;
    }catch(Exception ex){
        System.out.println("Ocurrio un error al actualizar el conductor: " + ex);
    }
    return resultado;
}

/**
 * Edita un conductor en la base de datos a partir de su ID.
 * @param id El ID del conductor a editar.
 * @param nombre El nuevo nombre del conductor.
 * @param numLicencia El nuevo número de licencia del conductor.
 * @return true si la operación fue exitosa, false si no lo fue.
 */
public boolean editar(int id, String nombre, String numLicencia ){
    boolean resultado = false;
    try{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Conductor conductor = session.get(Conductor.class, id);
        if(conductor != null){
            conductor.setNombre(nombre);
            conductor.setNumlicencia(numLicencia);
            session.saveOrUpdate(conductor);
            session.getTransaction().commit();
            resultado = true;
        }       
    }catch(HibernateException ex){
        System.err.println("Ocurrio un error al editar el conductor: " + ex.getMessage());
    }
    return resultado;
}

/**
 * Elimina un conductor de la base de datos a partir de su ID.
 * @param id El ID del conductor a eliminar.
 */
public void eliminarConductor(int id) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        Conductor conductor = session.get(Conductor.class, id);
       if (conductor != null) {
                session.delete(conductor);
                transaction.commit();
                JOptionPane.showMessageDialog(null, "Conductor eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un conductor con ID " + id);
            }
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex);
        }
}
}