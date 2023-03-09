package mx.itson.philaadelphia.entidades;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Representa una multa de tránsito.
 * 
 * Una multa es una infracción cometida por un conductor y registrada por un oficial de tránsito.
 * Contiene información como el ID, el folio, la fecha en que se emitió, el motivo de la infracción,
 * el conductor sancionado y el oficial que la registró.
 * 
 * @author Arian Gastelum
 */
@Entity
public class Multa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String folio;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String motivo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idConductor")
    private Conductor conductor;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idOficial")
    private Oficial oficial;

    public Multa() {}

    public Multa(String folio, Date fecha, String motivo, Conductor conductor, Oficial oficial) {
        this.folio = folio;
        this.fecha = fecha;
        this.motivo = motivo;
        this.conductor = conductor;
        this.oficial = oficial;
    }

    /**
     * @return el ID de la multa
     */
    public int getId() {
        return id;
    }

    /**
     * @param id el ID de la multa a establecer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return el folio de la multa
     */
    public String getFolio() {
        return folio;
    }

    /**
     * @param folio el folio de la multa a establecer
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @return la fecha en que se emitió la multa
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha la fecha en que se emitió la multa a establecer
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return el motivo de la multa
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo el motivo de la multa a establecer
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @return el conductor sancionado por la multa
     */
    public Conductor getConductor() {
        return conductor;
    }

    /**
     * @param conductor el conductor sancionado por la multa a establecer
     */
    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    /**
     * @return el oficial que registró la multa
     */
    public Oficial getOficial() {
        return oficial;
    }

    /**
     * @param oficial el oficial que registró la multa a establecer
     */
    public void setOficial(Oficial oficial) {
        this.oficial = oficial;
    }
}

    