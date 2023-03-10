
package mx.itson.philaadelphia.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.JOptionPane;
import mx.itson.philaadelphia.entidades.Conductor;
import mx.itson.philaadelphia.entidades.Multa;
import mx.itson.philaadelphia.entidades.Oficial;
import mx.itson.philaadelphia.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static org.hibernate.criterion.Projections.id;

/**
 * Esta clase se encarga de realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) en la tabla "multa" de la base de datos.
 * Además, proporciona un método para obtener todas las multas de la base de datos.
 * @author Arian Gastelum
 */



public class MultaDAO {
    
    /**
     * Este método devuelve una lista con todas las multas almacenadas en la base de datos.
     * @return una lista con todas las multas almacenadas en la base de datos.
     */
    public static List<Multa> obtenerTodos() {
        List<Multa> multas = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession(); 
            CriteriaQuery<Multa> criteriaQuery = session.getCriteriaBuilder().createQuery(Multa.class);
            criteriaQuery.from(Multa.class);
            multas = session.createQuery(criteriaQuery).getResultList();
        } catch(Exception ex) {
            System.out.println("Ocurrió un error: " + ex);
        }
        return multas;
    }
    
    /**
     * Este método guarda una multa en la base de datos.
     * @param folio el folio de la multa.
     * @param motivo el motivo de la multa.
     * @param fecha la fecha en la que se impuso la multa.
     * @param conductor el conductor que recibió la multa.
     * @param oficial el oficial que impuso la multa.
     * @return true si la multa se guardó correctamente, false si no.
     */
public static boolean guardarMulta(String folio, String motivo, Date fecha, Conductor conductor, Oficial oficial){
    boolean resultado = false;    
    try{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Multa m = new Multa(folio, fecha, motivo, conductor, oficial);
        m.setFolio(folio);
        m.setMotivo(motivo);
        m.setFecha(fecha);
        m.setConductor(conductor);
        m.setOficial(oficial);
        session.save(m);
        
        session.getTransaction().commit();
        
        resultado = m.getId() !=0;
        
    }catch(Exception ex){
        System.out.println("Ocurrio un error: " + ex);
    }
    return resultado;
    }

   /**
     * Este método elimina una multa de la base de datos a partir de su id.
     * @param idMulta el id de la multa a eliminar.
     * @return true si la multa se eliminó correctamente, false si no.
     */
public static boolean eliminarMulta(int idMulta) {
     boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Multa multa = session.get(Multa.class, idMulta);
            
            if(multa!=null){
                session.delete(multa);
                session.getTransaction().commit();
            resultado = true;
            }
        }catch(HibernateException ex){
             System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
/**
 * Actualiza los datos de una multa existente en la base de datos.
 * 
 * @param idMulta   El identificador único de la multa a editar.
 * @param folio     El nuevo folio de la multa.
 * @param motivo    El nuevo motivo de la multa.
 * @param fecha     La nueva fecha en que se emitió la multa.
 * @param conductor El nuevo conductor asociado a la multa.
 * @param oficial   El nuevo oficial que emitió la multa.
 * @return          true si la multa fue actualizada exitosamente, false en caso contrario.
 */
public static boolean editarMulta(int idMulta, String folio, String motivo, Date fecha, Conductor conductor, Oficial oficial) {
    boolean resultado = false;
    try {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Multa multa = session.get(Multa.class, idMulta);
        if (multa != null) {
            multa.setFolio(folio);
            multa.setMotivo(motivo);
            multa.setFecha(fecha);
            multa.setConductor(conductor);
            multa.setOficial(oficial);
            session.update(multa);
            tx.commit();
            resultado = true;
        }
    } catch (HibernateException ex) {
        System.err.println("Ocurrio un error: " + ex.getMessage());
    }
    return resultado;
}
 public static Multa obtenerPorId(int id){
        Multa multa = null;
        try{
                Session session = HibernateUtil.getSessionFactory().openSession();
                multa = session.get(Multa.class, id);
        }catch(HibernateException ex){
                System.err.println("Ocurrio un error: " + ex.getMessage());
        }
    return multa;
    }


}