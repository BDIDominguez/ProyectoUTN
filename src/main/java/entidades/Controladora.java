package entidades;

import java.util.ArrayList;
import persistencias.ControladoraPersistencia;

public class Controladora {
    //CRUD
    ControladoraPersistencia ctrl= new ControladoraPersistencia();
    
    // ------------- CLIENTE  --------------------
    public void crearCliente(Cliente cli){
        ctrl.crearCliente(cli);
    }
    public void eliminarCliente(int id){
        ctrl.eliminarCliente(id);
    }
    public void editarCliente(Cliente cli){
        ctrl.editarCliente(cli);
    }
    public Cliente traerCliente(int id){
        return ctrl.traerCliente(id);
    }
    public ArrayList<Cliente> traerListaClientes(){
        return ctrl.traerListaClientes();
    }
            
    // ------------- APLICACION --------------------
    public void crearAplicacion(Aplicacion ap){
        ctrl.crearAplicacion(ap);
    }
    public void eliminarAplicacion(int id){
        ctrl.eliminarAplicacion(id);
    }
    public void editarAplicacion(Aplicacion ap){
        ctrl.editarAplicacion(ap);
    }
    public Aplicacion traerAplicacion(int id){
        return ctrl.traerAplicacion(id);
    }
    public ArrayList<Aplicacion> traerListaAplicacion(){
        return ctrl.traerListaAplicaciones();
    }
    
    // ------------- ESPECIALIZACION ---------------
    public void crearEspecializacion(Especializacion esp){
        ctrl.crearEspecializacion(esp);
    }
    public void eliminarEspecializacion(int id){
        ctrl.eliminarEspecializacion(id);
    }
    public void editarEspecializacion(Especializacion esp){
        ctrl.editarEspecializacion(esp);
    }
    public Especializacion traerEspecializacion(int id){
        return ctrl.traerEspecializacion(id);
    }
    public ArrayList<Especializacion> traerListaEspecializacion(){
        return ctrl.traerListaEspecializacion();
    }
    // ------------- TECNICO ---------------
    public void crearTecnico(Tecnico tec){
        ctrl.crearTecnico(tec);
    }
    public void eliminarTecnico(int id){
        ctrl.eliminarTecnico(id);
    }
    public void editarTecnico(Tecnico tec){
        ctrl.editarTecnico(tec);
    }
    public Tecnico traerTecnico(int id){
        return ctrl.traerTecnico(id);
    }
    public ArrayList<Tecnico> traerListaTecnico(){
        return ctrl.traerListaTecnico();
    }
    // ------------- OPERADOR ---------------
    public void crearOperador(Operador op){
        ctrl.crearOperador(op);
    }
    public void eliminarOperador(int id){
        ctrl.eliminarOperador(id);
    }
    public void editarOperador(Operador op){
        ctrl.editarOperador(op);
    }
    public Operador traerOperador(int id){
        return ctrl.traerOperador(id);
    }
    public ArrayList<Operador> traerListaOperador(){
        return ctrl.traerListaOperador();
    }
    // ------------- INCIDENTE ---------------
    public void crearIncidente(Incidente inci){
        ctrl.crearIncidente(inci);
    }
    public void eliminarIncidente(int id){
        ctrl.eliminarIncidente(id);
    }
    public void editarIncidente(Incidente inci){
        ctrl.editarIncidente(inci);
    }
    public Incidente traerIncidente(int id){
        return ctrl.traerIncidente(id);
    }
    public ArrayList<Incidente> traerListaIncidente(){
        return ctrl.traerListaIncidente();
    }
    
}
