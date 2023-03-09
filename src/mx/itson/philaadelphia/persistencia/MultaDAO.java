/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author DELL
 */
public class MultaDAO {
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
    
    public Multa obtenerPorId(int id) {
    Multa multa = null;
    try {
        Session session = HibernateUtil.getSessionFactory().openSession();
        multa = session.get(Multa.class, id);
    } catch(Exception ex) {
        System.out.println("Ocurrió un error: " + ex);
    }
    return multa;
}
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

}