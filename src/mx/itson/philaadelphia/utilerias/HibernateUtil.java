/*
 * Clase utilitaria para manejar la sesión de Hibernate.
 * Esta clase utiliza la biblioteca Hibernate para crear la sesión de base de datos.
 */
package mx.itson.philaadelphia.utilerias;

import org.hibernate.HibernateException; // Biblioteca para manejar excepciones de Hibernate
import org.hibernate.SessionFactory; // Clase que representa la sesión de Hibernate
import org.hibernate.cfg.Configuration; // Clase de configuración de Hibernate
import org.hibernate.service.ServiceRegistry; // Clase para el registro de servicios de Hibernate

public class HibernateUtil {
    
    private static SessionFactory factory; // Objeto de la clase SessionFactory
    private static ServiceRegistry serviceRegistry; // Objeto de la clase ServiceRegistry

    /*
     * Este bloque static se ejecuta cuando la clase es cargada por el primer
     * vez en la memoria. Aquí se inicializa la sesión de Hibernate.
     */
    static {
        try{
            Configuration configuration = new Configuration(); // Crea una instancia de Configuration
            configuration.configure(); // Lee el archivo de configuración hibernate.cfg.xml
            factory = configuration.buildSessionFactory(); // Crea una instancia de SessionFactory
        } catch(HibernateException ex){
            // Si ocurre una excepción, muestra un mensaje de error
            System.out.println("No se pudo crear la sesión de Hibernate debido al error: " + ex);
        }
    }

    /*
     * Método para obtener la sesión de Hibernate.
     * @return Objeto SessionFactory que representa la sesión de Hibernate.
     */
    public static SessionFactory getSessionFactory() {		
        return factory;
    }

    /*
     * Método para establecer la sesión de Hibernate.
     * @param factory Objeto SessionFactory que representa la sesión de Hibernate.
     */
    public static void setSessionFactory(SessionFactory factory) {
            HibernateUtil.factory = factory;
    }

    /*
     * Método para obtener el registro de servicios de Hibernate.
     * @return Objeto ServiceRegistry que representa el registro de servicios de Hibernate.
     */
    public static ServiceRegistry getServiceRegistry() {
            return serviceRegistry;
    }

    /*
     * Método para establecer el registro de servicios de Hibernate.
     * @param serviceRegistry Objeto ServiceRegistry que representa el registro de servicios de Hibernate.
     */
    public static void setServiceRegistry(ServiceRegistry serviceRegistry) {
            HibernateUtil.serviceRegistry = serviceRegistry;
    }
}
