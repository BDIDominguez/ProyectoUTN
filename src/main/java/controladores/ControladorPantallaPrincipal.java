package controladores;

import entidades.Controladora;
import entidades.Incidente;
import entidades.Tecnico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
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
        menu.btEstadisticas.addActionListener(this);

    }

    public void iniciar() {
        menu.setVisible(true);
        menu.btTecEsp.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.btSalir) {
            menu.dispose();
        }

        if (e.getSource() == menu.btAplicacion) {
            vistaAplicacion vista = new vistaAplicacion();
            Controladora data = new Controladora();
            ControladorAplicacion ctrl = new ControladorAplicacion(vista, menu, data);
            ctrl.iniciar();

        }
        if (e.getSource() == menu.btEmpleado) {
            VistaEmpleado vista = new VistaEmpleado();
            Controladora data = new Controladora();
            ControladorVistaEmpleado ctrl = new ControladorVistaEmpleado(vista, menu, data);
            ctrl.iniciar();

        }
        if (e.getSource() == menu.btEspecialidad) {
            vistaEspecializacion vista = new vistaEspecializacion();
            Controladora data = new Controladora();
            ControladorVistaEspecializacion ctrl = new ControladorVistaEspecializacion(vista, menu, data);
            ctrl.iniciar();
        }
        if (e.getSource() == menu.btCliente) {
            VistaClientes vista = new VistaClientes();
            Controladora data = new Controladora();
            ControladorVistaClientes ctrl = new ControladorVistaClientes(vista, menu, data);
            ctrl.iniciar();
        }
        if (e.getSource() == menu.btEspTec) {
            VistaAsignarTecnico vista = new VistaAsignarTecnico();
            Controladora data = new Controladora();
            ControladorVistaAsignarTecnico ctrl = new ControladorVistaAsignarTecnico(vista, menu, data);
            ctrl.iniciar();
        }
        if (e.getSource() == menu.btIncidencia) {
            VistaIncidente vista = new VistaIncidente();
            Controladora data = new Controladora();
            ControladorVistaIncidente ctrl = new ControladorVistaIncidente(vista, menu, data);
            ctrl.iniciar();
        }
        if (e.getSource() == menu.btEstadisticas) {
            // Obtener el tecnico con mas Intervenciones y menos incidencias
            Controladora data = new Controladora();
            ArrayList<Incidente> incidentes = data.traerListaIncidente();
            ArrayList<Acumulador> cuenta = new ArrayList();
            for (Incidente incidente : incidentes) {
                Acumulador acumulador = buscarAcumuladorPorTecnico(cuenta, incidente.getTecnico());
                if (acumulador == null) {
                    acumulador = new Acumulador(incidente.getTecnico(), 0);
                    cuenta.add(acumulador);
                }
                acumulador.setContador(acumulador.getContador() + 1);
            }
            Acumulador tecnicoMax = Collections.max(cuenta, Comparator.comparingInt(Acumulador::getContador));
            Acumulador tecnicoMin = Collections.min(cuenta, Comparator.comparingInt(Acumulador::getContador));
            System.out.println("Técnico con + Reparaciones: " + tecnicoMax.getTec().getNombre() + ", Cantidad: " + tecnicoMax.getContador());
            System.out.println("Técnico con - Reparaciones: " + tecnicoMin.getTec().getNombre() + ", Cantidad: " + tecnicoMin.getContador());
            // Obtener el Tecnico con el menor tiempo de reparacion
            Duration durac = Duration.ZERO;
            Boolean asignada = false;
            Tecnico tec = new Tecnico();
            
            for (Incidente incidente : incidentes) {
                if (incidente.getEstado().toString().equals("TERMINADO")){
                    LocalDateTime fechaInicio = incidente.getInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    LocalDateTime fechaFin = incidente.getFinalizado().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    if (!asignada){
                        durac = Duration.between(fechaInicio, fechaFin);
                        tec = incidente.getTecnico();
                        asignada = true;
                    }else{
                        Duration nuevo = Duration.between(fechaInicio, fechaFin);
                        if (durac.compareTo(nuevo) < 0 ){
                            durac = nuevo;
                            tec = incidente.getTecnico();
                        }
                    }
                }
            }
            System.out.println("El tecnico con la resolucion + rapida fue " + tec.getNombre() + " en un tiempo de " + durac);
            
            
        }

    }

    public class Acumulador {
        private Tecnico tec;
        private int contador;
        public Acumulador(Tecnico tec, int contador) {
            this.tec = tec;
            this.contador = contador;
        }
        public Tecnico getTec() {
            return tec;
        }
        public void setTec(Tecnico tec) {
            this.tec = tec;
        }
        public int getContador() {
            return contador;
        }
        public void setContador(int contador) {
            this.contador = contador;
        }
    }
    private static Acumulador buscarAcumuladorPorTecnico(ArrayList<Acumulador> cuenta, Tecnico tecnico) {
        for (Acumulador acumulador : cuenta) {
            if (acumulador.getTec().equals(tecnico)) {
                return acumulador;
            }
        }
        return null;
    }
   
    
}
