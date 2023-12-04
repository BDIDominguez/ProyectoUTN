package controladores;

import entidades.Controladora;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.vistaAplicacion;
import vistas.PantallaPrincipal;
import vistas.VistaAsignarTecnico;
import vistas.VistaClientes;
import vistas.VistaEmpleado;
import vistas.VistaIncidente;
import vistas.vistaEspecializacion;

public class ControladorPantallaPrincipal implements ActionListener {
    private PantallaPrincipal menu;

    public ControladorPantallaPrincipal(PantallaPrincipal menu) {
        this.menu = menu;
        menu.btEspecialidad.addActionListener(this);
        menu.btSalir.addActionListener(this);
        menu.btAplicacion.addActionListener(this);
        menu.btEmpleado.addActionListener(this);
        menu.btCliente.addActionListener(this);
        menu.btIncidencia.addActionListener(this);
        menu.btEspTec.addActionListener(this);
        menu.btTecEsp.addActionListener(this);
        
        
    }
    
    public void iniciar(){
        menu.setVisible(true);
        menu.btTecEsp.setVisible(false);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.btSalir){
            menu.dispose();
        }
        
        if (e.getSource() == menu.btAplicacion){
            vistaAplicacion vista = new vistaAplicacion();
            Controladora data = new Controladora();
            ControladorAplicacion ctrl = new ControladorAplicacion(vista, menu, data);
            ctrl.iniciar();
            
        }
        if (e.getSource() == menu.btEmpleado){
            VistaEmpleado vista = new VistaEmpleado();
            Controladora data = new Controladora();
            ControladorVistaEmpleado ctrl = new ControladorVistaEmpleado(vista, menu, data);
            ctrl.iniciar();
            
        }
        if (e.getSource() == menu.btEspecialidad){
            vistaEspecializacion vista = new vistaEspecializacion();
            Controladora data = new Controladora();
            ControladorVistaEspecializacion ctrl = new ControladorVistaEspecializacion(vista, menu, data);
            ctrl.iniciar();
        }
        if (e.getSource() == menu.btCliente){
            VistaClientes vista = new VistaClientes();
            Controladora data = new Controladora();
            ControladorVistaClientes ctrl = new ControladorVistaClientes(vista, menu, data);
            ctrl.iniciar();
        }
        if (e.getSource() == menu.btEspTec){
            VistaAsignarTecnico vista = new VistaAsignarTecnico();
            Controladora data = new Controladora();
            ControladorVistaAsignarTecnico ctrl = new ControladorVistaAsignarTecnico(vista, menu, data);
            ctrl.iniciar();
        }
        if (e.getSource() == menu.btIncidencia){
            VistaIncidente vista = new VistaIncidente();
            Controladora data = new Controladora();
            ControladorVistaIncidente ctrl = new ControladorVistaIncidente(vista, menu, data);
            ctrl.iniciar();
        }
    }
    
    
}
