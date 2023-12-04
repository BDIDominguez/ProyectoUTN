
package controladores;

import entidades.Aplicacion;
import entidades.Cliente;
import entidades.Controladora;
import entidades.EstadoIncidente;
import entidades.Incidente;
import entidades.Tecnico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import vistas.PantallaPrincipal;
import vistas.VistaIncidente;

/**
 *
 * @author Dario
 */
public class ControladorVistaIncidente implements ActionListener, ListSelectionListener {

    private VistaIncidente vista;
    private PantallaPrincipal menu;
    private Controladora data;
    MyModelo modelo = new MyModelo();

    public ControladorVistaIncidente(VistaIncidente vista, PantallaPrincipal menu, Controladora data) {
        this.vista = vista;
        this.menu = menu;
        this.data = data;

        vista.btEliminar.addActionListener(this);
        vista.btGuardar.addActionListener(this);
        vista.btNuevo.addActionListener(this);
        vista.btSalir.addActionListener(this);
        vista.cbAplicacion.addActionListener(this);
        vista.cbCliente.addActionListener(this);
        vista.cbEstado.addActionListener(this);
        vista.cbTecnico.addActionListener(this);
        vista.txID.addActionListener(this);

        vista.jTabla.getSelectionModel().addListSelectionListener(this);

    }
    
    public void iniciar() {
        menu.pVistas.add(vista);
        vista.setVisible(true);
        menu.pVistas.moveToFront(vista);
        vista.requestFocus();
        modelarTabla();
        cargarTabla();
        cargarClientes();
        cargarAplicacion();
        cargarTecnico();
        cargarEnum();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == vista.btSalir){
           vista.dispose();
       }
       if (e.getSource() == vista.btNuevo){
           vista.txID.setText("-1");
           vista.dcFinalizado.setDate(new Date());
           vista.dcInicio.setDate(new Date());
           vista.dcTiempo.setDate(new Date());
           vista.cbEstado.setSelectedItem("CARGADO");
       }
       if (e.getSource() == vista.btGuardar){
           Incidente inci = new Incidente();
           Cliente cli = data.traerCliente(extraerIdCliente());
           Aplicacion apli = data.traerAplicacion(extraerIdAplicacion());
           Tecnico tec = data.traerTecnico(extraerIdTecnico());
           inci.setCliente(cli);
           inci.setAplicacion(apli);
           inci.setTecnico(tec);
           inci.setInicio(vista.dcInicio.getDate());
           inci.setTiempoRep(vista.dcTiempo.getDate());
           inci.setFinalizado(vista.dcFinalizado.getDate());
           inci.setEstado(EstadoIncidente.valueOf(vista.cbEstado.getSelectedItem().toString()));
           if (!vista.txID.getText().equals("-1")){
               inci.setId(Integer.parseInt(vista.txID.getText()));
               data.editarIncidente(inci);
               JOptionPane.showMessageDialog(vista, "Se Modifico Correctamente");
           }else{
               data.crearIncidente(inci);
               JOptionPane.showMessageDialog(vista, "Se Creo Correctamente");
           }
           cargarTabla();
       }
       if (e.getSource() == vista.btEliminar){
           if (!vista.txID.getText().equals("-1")){
               data.eliminarIncidente(Integer.parseInt(vista.txID.getText()));
               JOptionPane.showMessageDialog(vista, "Se Elimino Correctamente");
               cargarTabla();
               vista.btEliminar.setEnabled(false);
           }
       }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
       if (!e.getValueIsAdjusting()) {
            int fila = vista.jTabla.getSelectedRow();
            if (fila != -1) {
                //vista.btEliminar.setEnabled(true);
                int id = (int) vista.jTabla.getValueAt(fila, 0);
                Incidente inci = data.traerIncidente(id);
                vista.txID.setText(inci.getId() + "");
                vista.cbCliente.setSelectedItem(inci.getCliente().getId() + " - " + inci.getCliente().getNombre());
                vista.cbAplicacion.setSelectedItem(inci.getAplicacion().getId() + " - " + inci.getAplicacion().getNombre());
                vista.cbTecnico.setSelectedItem(inci.getTecnico().getLegajo() + " - " + inci.getTecnico().getNombre());
                vista.dcInicio.setDate(inci.getInicio());
                vista.dcTiempo.setDate(inci.getTiempoRep());
                vista.dcFinalizado.setDate(inci.getFinalizado());
                vista.cbEstado.setSelectedItem(inci.getEstado());
                vista.btEliminar.setEnabled(true);
            }
        }
    }
    
    private void cargarClientes() {
        ArrayList<Cliente> lista = data.traerListaClientes();
        for (Cliente cliente : lista) {
            vista.cbCliente.addItem(cliente.getId() + " - " +cliente.getNombre());
        }
    }
    private int extraerIdCliente() {
        int id = -1;
        try {
            String combobox = vista.cbCliente.getSelectedItem().toString();
            String partes[] = combobox.split("-");
            id = Integer.parseInt(partes[0].trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "A ocurrido un error al cargar los indices en el combobox, revices la posicion del idMateria");
        }
        return id;
    }
    private void cargarAplicacion() {
        ArrayList<Aplicacion> lista = data.traerListaAplicacion();
        for (Aplicacion cliente : lista) {
            vista.cbAplicacion.addItem(cliente.getId() + " - " +cliente.getNombre());
        }
    }
    private int extraerIdAplicacion() {
        int id = -1;
        try {
            String combobox = vista.cbAplicacion.getSelectedItem().toString();
            String partes[] = combobox.split("-");
            id = Integer.parseInt(partes[0].trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "A ocurrido un error al cargar los indices en el combobox, revices la posicion del idMateria");
        }
        return id;
    }
    private void cargarTecnico() {
        ArrayList<Tecnico> lista = data.traerListaTecnico();
        for (Tecnico cliente : lista) {
            vista.cbTecnico.addItem(cliente.getLegajo() + " - " +cliente.getNombre());
        }
    }
    private int extraerIdTecnico() {
        int id = -1;
        try {
            String combobox = vista.cbTecnico.getSelectedItem().toString();
            String partes[] = combobox.split("-");
            id = Integer.parseInt(partes[0].trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "A ocurrido un error al cargar los indices en el combobox, revices la posicion del idMateria");
        }
        return id;
    }
    private void cargarEnum() {
        vista.cbEstado.removeAllItems();
        EstadoIncidente[] estados = EstadoIncidente.values();
        for (EstadoIncidente estado : estados) {
            vista.cbEstado.addItem(estado.toString());
        }
    }

    private class MyModelo extends DefaultTableModel {

        //para evitar las edicion de los campos de la tabla pero que se puedan seleccionar
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    private void modelarTabla() {
        modelo.addColumn("ID");
        modelo.addColumn("Cliente");
        modelo.addColumn("Aplicacion");
        modelo.addColumn("Tecnico");
        modelo.addColumn("Inicio");
        modelo.addColumn("Tiempo Rep");
        modelo.addColumn("Finalizado");
        modelo.addColumn("Estado");
        vista.jTabla.setModel(modelo);
    }
    private void cargarTabla(){
        ArrayList<Incidente> lista = data.traerListaIncidente();
        modelo.setRowCount(0);
        for (Incidente inci : lista) {
            String cliente = inci.getCliente().getId() + " - " + inci.getCliente().getNombre();
            String aplicacion = inci.getAplicacion().getId() + " - " + inci.getAplicacion().getNombre();
            String tecnico = inci.getTecnico().getLegajo() + " - " + inci.getTecnico().getNombre();
            modelo.addRow(new Object[]{inci.getId(), cliente, aplicacion, tecnico, inci.getInicio(), inci.getTiempoRep(),inci.getFinalizado(), inci.getEstado() });
            
        }
        vista.jTabla.setModel(modelo);
    }
}
