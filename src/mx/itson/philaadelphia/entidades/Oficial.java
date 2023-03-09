/*

La clase Oficial representa a los oficiales de tránsito que pueden emitir multas.
Esta clase es una entidad está mapeada a la tabla "oficial" en la base de datos.

*/
package mx.itson.philaadelphia.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oficial")
public class Oficial {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String nombre;
private String telefono;

public int getId() {
return id;
}

public void setId(int id) {
this.id = id;
}

public String getNombre() {
return nombre;
}

public void setNombre(String nombre) {
this.nombre = nombre;
}

public String getTelefono() {
return telefono;
}

public void setTelefono(String telefono) {
this.telefono = telefono;
}

 @Override
    public String toString(){
        return this.nombre;
    }   
}