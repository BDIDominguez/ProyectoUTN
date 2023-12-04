package entidades;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@DiscriminatorValue("Tecnico")
public class Tecnico extends Empleado{
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
      name = "tecnico_especializacion", 
      joinColumns = @JoinColumn(name = "tecnico_id"), 
      inverseJoinColumns = @JoinColumn(name = "especializacion_id"))
    List<Especializacion> especializaciones;

    public Tecnico() {
    }

    public Tecnico(int legajo, String nombre, int dni, String Celular, String correo) {
        super(legajo, nombre, dni, Celular, correo);
    }

    public Tecnico(List<Especializacion> espe) {
        this.especializaciones = espe;
    }

    public Tecnico(List<Especializacion> espe, int legajo, String nombre, int dni, String Celular, String correo) {
        super(legajo, nombre, dni, Celular, correo);
        this.especializaciones = espe;
    }

    public List<Especializacion> getEspecializaciones() {
        return especializaciones;
    }

    public void setEspecializaciones(List<Especializacion> especializaciones) {
        this.especializaciones = especializaciones;
    }
    
}
