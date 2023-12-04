package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
public class Incidente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @ManyToOne
    Tecnico tecnico;
    @Temporal(TemporalType.DATE)
    Date inicio;
    @Temporal(TemporalType.DATE)
    Date finalizado;
    @ManyToOne
    Aplicacion aplicacion;
    @ManyToOne
    Cliente cliente;
    Date tiempoRep;
    @Enumerated(EnumType.STRING)
    EstadoIncidente estado;

          
    
}
