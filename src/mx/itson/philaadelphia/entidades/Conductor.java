
package mx.itson.philaadelphia.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Table;

/**
 * Clase que representa la entidad Conductor en la base de datos.
 * Un conductor tiene un ID, número de licencia, fecha de alta y un nombre.
 * 
 * @author Arian Gastelum
 */
@Entity
@Table(name="conductor")
public class Conductor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numlicencia;
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    private String nombre;

    /**
     * @return el ID del conductor
     */
    public int getId() {
        return id;
    }

    /**
     * @param id el ID del conductor a establecer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return el número de licencia del conductor
     */
    public String getNumlicencia() {
        return numlicencia;
    }

    /**
     * @param numlicencia el número de licencia del conductor a establecer
     */
    public void setNumlicencia(String numlicencia) {
        this.numlicencia = numlicencia;
    }

    /**
     * @return la fecha de alta del conductor
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * @param fechaAlta la fecha de alta del conductor a establecer
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * @return el nombre del conductor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre el nombre del conductor a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
