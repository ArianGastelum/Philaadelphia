
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
 * Clase que realiza operaciones de acceso a datos de la entidad Conductor
 * mediante el uso de Hibernate.
 * 
 * @author Arian Gastelum
 */
public class ConductorDAO {
    
    /**
     * Método que obtiene una lista de todos los conductores.
     * 
     * @return Una lista de objetos de tipo Conductor que contiene todos los 
     * conductores en la base de datos.
     */ 
    public List <Conductor> obtenerTodos(){
    List <Conductor> conductores = new ArrayList<>();
    try{
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        CriteriaQuery <Conductor> criteriaQuery = session.getCriteriaBuilder().createQuery(Conductor.class);
        criteriaQuery.from(Conductor.class);
        conductores = session.createQuery(criteriaQuery).getResultList();
        
    }catch(Exception ex){
    System.out.println("Ocurrio un error: " + ex);
    }
    return conductores;
    }
     /**
     * Método que obtiene un conductor por su ID.
     * 
     * @param id El ID del conductor a buscar.
     * @return Un objeto de tipo Conductor que corresponde al ID especificado, 
     * o null si no se encontró ningún conductor con ese ID.
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
     * Método que actualiza un conductor existente en la base de datos.
     * 
     * @param conductor El objeto de tipo Conductor con los nuevos datos a guardar.
     * @return true si se pudo actualizar el conductor, false en caso contrario.
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
        System.out.println("Ocurrio un error: " + ex);
    }
    
    return resultado;
}

 /**
     * Método que actualiza un conductor existente en la base de datos a partir de 
     * su ID.
     * 
     * @param id El ID del conductor a actualizar.
     * @param nombre El nuevo nombre del conductor.
     * @param numLicencia El nuevo número de licencia del conductor.
     * @return true si se pudo actualizar el conductor, false en caso contrario.
     */
public static boolean editar(int id, String nombre, String numLicencia ){
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
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
/**
 * Método que elimina el conductor existente en la base de datos a partir de su id
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
        JOptionPane.showMessageDialog(null, "Ocurrió un error: "+ ex);
    }
}




    /**
     * Método que guarda los datos del conductor a registrar
     * @param nombre nombre del conductor a registrar
     * @param numLicencia numero de licencia del conductor
     * @param fechaAlta fecha de alta de la licencia
     * @return true si se pudo registrar el conductor, false en caso contrario.
     */
    public boolean guardar(String nombre,String numLicencia,Date fechaAlta){
    boolean resultado = false;
    
    try{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Conductor c = new Conductor();
        c.setNombre(nombre);
        c.setNumlicencia(numLicencia);
        c.setFechaAlta(fechaAlta);
        session.save(c);
        
        session.getTransaction().commit();
        
        resultado = c.getId() !=0;
        
    }catch(Exception ex){
    System.out.println("Ocurrio un error: " + ex);
    }
    return resultado;
    }
   
    }