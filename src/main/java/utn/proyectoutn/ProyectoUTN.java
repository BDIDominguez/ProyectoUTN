package utn.proyectoutn;

import controladores.ControladorPantallaPrincipal;
import entidades.Aplicacion;
import entidades.Cliente;
import entidades.Controladora;
import entidades.Especializacion;
import entidades.EstadoIncidente;
import entidades.Incidente;
import entidades.Operador;
import entidades.Tecnico;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import persistencias.ControladoraPersistencia;
import vistas.PantallaPrincipal;


/**
 *
 * @author Dario
 */
public class ProyectoUTN {

    public static void main(String[] args) {
        //ControladoraPersistencia controlPersis = new ControladoraPersistencia();
        
        //Controladora ctrl = new Controladora();
        
        /*
        Aplicacion ap1 = new Aplicacion(1, "Sistema Operativo XP", "OS windows XP", new Date(), null);
        ctrl.crearAplicacion(ap1);
        //Aplicacion ap1 = ctrl.traerAplicacion(1);
        Especializacion esp1 = new Especializacion(1, "Tecnico OS Windows", "Sistema operativo", ap1,null);   
        Especializacion esp2 = new Especializacion(2, "Tecnico Hardware Impresoras", "Impresoras", ap1,null);   
        Especializacion esp3 = new Especializacion(3, "Administrador Base Datos", "Cientifico de Datos", ap1,null);   
        
        ctrl.crearEspecializacion(esp1);
        ctrl.crearEspecializacion(esp2);
        ctrl.crearEspecializacion(esp3);
        
        List<Especializacion> lista = new ArrayList();
        lista.add(esp1);
        lista.add(esp2);
        lista.add(esp3);
        
        
        
        Cliente cli = new Cliente(1, "Distribuidora Santy", "20-30541575-9", "La providencia nro 62", "Dario Dominguez", new Date());
        ctrl.crearCliente(cli);

        
        
        // --- Crear Empleados Operador y Tecnico!!!
        Operador oper = new Operador(1, "Ines Soledad", 30150502, "3885273827", "soledadvasualdo@gmail.com");
        ctrl.crearOperador(oper);
        
        Tecnico tec = new Tecnico(lista, 2, "Dario Dominguez", 305415575, "3885273263", "bdidominguez@gmail.com");
        ctrl.crearTecnico(tec);
        
        Tecnico tecnico = ctrl.traerTecnico(2);
        Date inicio = new Date();
        Date finalizado = new Date();
        Aplicacion aplicacion = ctrl.traerAplicacion(1);
        Cliente cliente = ctrl.traerCliente(1);
        
        Incidente inci = new Incidente(1, tecnico, inicio, finalizado, aplicacion, cliente, new Date(), EstadoIncidente.CARGADO);
        ctrl.crearIncidente(inci);
                
        System.out.println("Primera Corrida OK");
        */
        
        //Tecnico tec = new Tecnico( );
        
        
        
        
        PantallaPrincipal vista = new PantallaPrincipal();
        ControladorPantallaPrincipal prog = new ControladorPantallaPrincipal(vista);
        prog.iniciar();
    }
}
