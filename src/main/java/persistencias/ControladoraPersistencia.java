package persistencias;

import entidades.Aplicacion;
import entidades.Cliente;
import entidades.Especializacion;
import entidades.Incidente;
import entidades.Operador;
import entidades.Tecnico;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencias.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {
    AplicacionJpaController apJpa = new AplicacionJpaController();
    EspecializacionJpaController espJpa = new EspecializacionJpaController();
    ClienteJpaController cliJpa = new ClienteJpaController();
    TecnicoJpaController tecJpa = new TecnicoJpaController();
    OperadorJpaController operJpa = new OperadorJpaController();
    IncidenteJpaController inciJpa = new IncidenteJpaController();
    
    // --------------- CLIENTE -----------------
    public void crearCliente(Cliente cli){
        try {
            cliJpa.create(cli);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarCliente(int id) {
        try {
            cliJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarCliente(Cliente cli) {
        try {
            cliJpa.edit(cli);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cliente traerCliente(int id) {
        return cliJpa.findCliente(id);
    }

    public ArrayList<Cliente> traerListaClientes() {
        List<Cliente> lis = cliJpa.findClienteEntities();
        ArrayList<Cliente> li = new ArrayList<>(lis);
        return li;
    }
    
    // --------------- APLICACION -----------------
    public void crearAplicacion(Aplicacion ap){
        apJpa.create(ap);
    }
    public void eliminarAplicacion(int id) {
        try {
            apJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarAplicacion(Aplicacion ap) {
        try {
            apJpa.edit(ap);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Aplicacion traerAplicacion(int id) {
        return apJpa.findAplicacion(id);
    }

    public ArrayList<Aplicacion> traerListaAplicaciones() {
        List<Aplicacion> lis = apJpa.findAplicacionEntities();
        ArrayList<Aplicacion> li = new ArrayList<>(lis);
        return li;
    }

    // --------------- ESPECIALIZACION ------------
    public void crearEspecializacion(Especializacion esp) {
        espJpa.create(esp);
    }

    public void eliminarEspecializacion(int id) {
        try {
            espJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarEspecializacion(Especializacion esp) {
        try {
            espJpa.edit(esp);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Especializacion traerEspecializacion(int id) {
        return espJpa.findEspecializacion(id);
    }

    public ArrayList<Especializacion> traerListaEspecializacion() {
        List<Especializacion> lis = espJpa.findEspecializacionEntities();
        ArrayList<Especializacion> li = new ArrayList<>(lis);
        return li;
    }
    
    // -----------------  TECNICO ----------------------------
    public void crearTecnico(Tecnico tec) {
        try {
            tecJpa.create(tec);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarTecnico(int id) {
        try {
            tecJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarTecnico(Tecnico tec) {
        try {
            tecJpa.edit(tec);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Tecnico traerTecnico(int id) {
        return tecJpa.findTecnico(id);
    }

    public ArrayList<Tecnico> traerListaTecnico() {
        List<Tecnico> li = tecJpa.findTecnicoEntities();
        ArrayList<Tecnico> lis = new ArrayList<>(li);
        return lis;
    }

    // -------------------- OPERADOR ------------------
    public void crearOperador(Operador op) {
        try {
            operJpa.create(op);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarOperador(int id) {
        try {
            operJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarOperador(Operador op) {
        try {
            operJpa.edit(op);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Operador traerOperador(int id) {
        return operJpa.findOperador(id);
    }

    public ArrayList<Operador> traerListaOperador() {
        List<Operador> li = operJpa.findOperadorEntities();
        ArrayList<Operador> lis = new ArrayList<>(li);
        return lis;
    }
    
    // -------------------- INCIDENTE  ------------------

    public void crearIncidente(Incidente inci) {
        try {
            inciJpa.create(inci);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarIncidente(int id) {
        try {
            inciJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarIncidente(Incidente inci) {
        try {
            inciJpa.edit(inci);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Incidente traerIncidente(int id) {
        return inciJpa.findIncidente(id);
    }

    public ArrayList<Incidente> traerListaIncidente() {
        List<Incidente> li = inciJpa.findIncidenteEntities();
        ArrayList<Incidente> lis = new ArrayList<>(li);
        return lis;
    }

    
}
