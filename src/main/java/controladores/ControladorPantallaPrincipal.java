package controladores;

import entidades.Controladora;
import entidades.Incidente;
import entidades.Tecnico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            // Obtener el tecnico con mas Intervenciones
            Controladora data = new Controladora();
            ArrayList<Incidente> incidentes = data.traerListaIncidente();
            ArrayList<Acumulador> cuenta = new ArrayList();
            // Iteras sobre la lista de incidentes
            for (Incidente incidente : incidentes) {
                // Buscas el técnico en la lista de acumuladores
                Acumulador acumulador = buscarAcumuladorPorTecnico(cuenta, incidente.getTecnico());

                // Si el técnico no existe en la lista, lo creas
                if (acumulador == null) {
                    acumulador = new Acumulador(incidente.getTecnico(), 0);
                    cuenta.add(acumulador);
                }

                // Incrementas el contador
                acumulador.setContador(acumulador.getContador() + 1);
            }

            // Ahora la lista 'cuenta' tiene la información que necesitas
            // Puedes imprimir o hacer lo que necesites con la lista de acumuladores
            //for (Acumulador acumulador : cuenta) {
            //    System.out.println("Técnico: " + acumulador.getTec().getNombre() + ", Contador: " + acumulador.getContador());
            //}
            // Buscando el Maximo  y minimo 
            // Encuentra el máximo usando un comparador
            Acumulador tecnicoMax = Collections.max(cuenta, Comparator.comparingInt(Acumulador::getContador));

            // Encuentra el mínimo usando un comparador
            Acumulador tecnicoMin = Collections.min(cuenta, Comparator.comparingInt(Acumulador::getContador));

            // Imprime o haz lo que necesites con el técnico con el contador más alto
            System.out.println("Técnico con + Reparaciones: " + tecnicoMax.getTec().getNombre() + ", Cantidad: " + tecnicoMax.getContador());

            // Imprime o haz lo que necesites con el técnico con el contador más bajo
            System.out.println("Técnico con - Reparaciones: " + tecnicoMin.getTec().getNombre() + ", Cantidad: " + tecnicoMin.getContador());

            //JOptionPane.showMessageDialog(menu, "El Tecncico Con mas Incidencias es: " + tec.getNombre());
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
