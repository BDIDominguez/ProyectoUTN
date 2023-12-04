package entidades;


import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@DiscriminatorValue("Operador")
public class Operador extends Empleado implements Serializable{

    public Operador() {
    }

    public Operador(int legajo, String nombre, int dni, String Celular, String correo) {
        super(legajo, nombre, dni, Celular, correo);
    }
    
    
}
