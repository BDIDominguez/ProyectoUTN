package controladores;

import entidades.Controladora;
import entidades.Especializacion;
import entidades.Tecnico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import vistas.PantallaPrincipal;
import vistas.VistaAsignarTecnico;

/**
 *
 * @author Dario
 */
public class ControladorVistaAsignarTecnico implements ActionListener, ListSelectionListener  {
    private VistaAsignarTecnico vista;
    private PantallaPrincipal menu;
    private Controladora data;
    MyModelo modelo = new MyModelo();
    Set<Especializacion> lista = new TreeSet<>(Comparator.comparing(Especializacion::getNombre));
    


    public ControladorVistaAsignarTecnico(VistaAsignarTecnico vista, PantallaPrincipal menu, Controladora data) {
        this.vista = vista;
        this.menu = menu;
        this.data = data;

        vista.btEliminar.addActionListener(this);
        vista.btAgregar.addActionListener(this);
        vista.btSalir.addActionListener(this);
        vista.cbEspe.addActionListener(this);
        vista.cbTecnico.addActionListener(this);

        vista.jTabla.getSelectionModel().addListSelectionListener(this);
         
        

    }

    public void iniciar() {
        menu.pVistas.add(vista);
        vista.setVisible(true);
        menu.pVistas.moveToFront(vista);
        vista.requestFocus();
        modelarTabla();
        cargarCbTecnico();
    }
    
    private void cargarCbTecnico(){
        ArrayList<Tecnico> tecnicos = data.traerListaTecnico();
         vista.cbTecnico.removeAllItems();
         for (Tecnico tecnico : tecnicos) {
            String cadena = tecnico.getNombre() + " - " + tecnico.getLegajo();
            vista.cbTecnico.addItem(cadena);
        }
    }
    private int extraerIdTecnico() {
        int id = -1;
        try {
            String combobox = vista.cbTecnico.getSelectedItem().toString();
            String partes[] = combobox.split("-");
            id = Integer.parseInt(partes[1].trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "A ocurrido un error al cargar los indices en el combobox, revices la posicion del idMateria");
        }
        return id;
    }
    private int extraerIdEspe() {
        int id = -1;
        try {
            String combobox = vista.cbEspe.getSelectedItem().toString();
            String partes[] = combobox.split("-");
            id = Integer.parseInt(partes[1].trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "A ocurrido un error al cargar los indices en el combobox, revices la posicion del idMateria");
        }
        return id;
    }
    
    private void cargarCbEspe(){
        ArrayList<Especializacion> espes = data.traerListaEspecializacion();
         vista.cbEspe.removeAllItems();
         for (Especializacion espe : espes) {
            String cadena = espe.getNombre() + " - " + espe.getId();
            vista.cbEspe.addItem(cadena);
        }
    }
    private void cargarListaExistente(){
        int id = extraerIdTecnico();
        Tecnico tec = data.traerTecnico(id);
        lista.clear();
        lista.addAll(tec.getEspecializaciones());
    }
    
    private void modelarTabla() {
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Explicacion");
        vista.jTabla.setModel(modelo);
    }

    public void cargaTabla() {
        //ArrayList<Especializacion> lista = data.traerListaEspecializacion();
        modelo.setRowCount(0);
        for (Especializacion ope : lista) {
            modelo.addRow(new Object[]{ope.getId(),ope.getNombre(),ope.getExp()});
        }
        vista.jTabla.setModel(modelo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btSalir){
            vista.dispose();
        }
        if (e.getSource() == vista.cbTecnico){
            int id = extraerIdTecnico();
            Tecnico tec = data.traerTecnico(id);
            List<Especializacion> li = tec.getEspecializaciones();
            this.lista.addAll(li);
            cargarListaExistente();
            cargarCbEspe();
            cargaTabla();
        }
        
        if (e.getSource() == vista.btAgregar){
            // Carga Visual en la Tabla y en la Collection para posterior uso
            int id = extraerIdEspe();
            Especializacion esp = data.traerEspecializacion(id);
            lista.add(esp);
            cargaTabla();
            // Se asigna el TreeSet al Tecnico para que se alamacene en la BD
            id = extraerIdTecnico();
            Tecnico tec = data.traerTecnico(id);
            List<Especializacion> li = new ArrayList<>(lista);
            tec.setEspecializaciones(li);
            data.editarTecnico(tec);
        }
        
        if (e.getSource() == vista.btEliminar){
            int fila = vista.jTabla.getSelectedRow();
            int id = (int) vista.jTabla.getValueAt(fila, 0);
            modelo.removeRow(fila);
            Especializacion esp = data.traerEspecializacion(id);
            lista.remove(esp);
            id = extraerIdTecnico();
            Tecnico tec = data.traerTecnico(id);
            List<Especializacion> li = new ArrayList<>(lista);
            tec.setEspecializaciones(li);
            data.editarTecnico(tec);
            JOptionPane.showMessageDialog(vista, "Se Elimino Correctamente");
            
        }
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int fila = vista.jTabla.getSelectedRow();
            if (fila != -1) {
                vista.btEliminar.setEnabled(true);
            }
        }
    }
    private class MyModelo extends DefaultTableModel {

        //para evitar las edicion de los campos de la tabla pero que se puedan seleccionar
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
    
    
}
