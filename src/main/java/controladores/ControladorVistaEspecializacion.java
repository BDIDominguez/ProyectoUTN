
package controladores;

import entidades.Controladora;
import entidades.Especializacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import vistas.PantallaPrincipal;
import vistas.VistaEmpleado;
import vistas.vistaEspecializacion;

public class ControladorVistaEspecializacion implements ActionListener, ListSelectionListener {

    private vistaEspecializacion vista;
    private PantallaPrincipal menu;
    private Controladora data;
    MyModelo modelo = new MyModelo();


    public ControladorVistaEspecializacion(vistaEspecializacion vista, PantallaPrincipal menu, Controladora data) {
        this.vista = vista;
        this.menu = menu;
        this.data = data;

        vista.btEliminar.addActionListener(this);
        vista.btGuardar.addActionListener(this);
        vista.btNuevo.addActionListener(this);
        vista.btSalir.addActionListener(this);

        vista.jTabla.getSelectionModel().addListSelectionListener(this);

    }

    public void iniciar() {
        menu.pVistas.add(vista);
        vista.setVisible(true);
        menu.pVistas.moveToFront(vista);
        vista.requestFocus();
        modelarTabla();
        cargaTabla();
    }
    
    
    
    
    private void modelarTabla() {
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Explicacion");
        vista.jTabla.setModel(modelo);
    }

    public void cargaTabla() {
        ArrayList<Especializacion> lista = data.traerListaEspecializacion();
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
        if (e.getSource() == vista.btNuevo){
            vista.txId.setText("-1");
            vista.txNombre.setText("");
            vista.txExpli.setText("");
        }
        if (e.getSource() == vista.btGuardar){
            Especializacion esp = new Especializacion();
            //esp.setId(Integer.parseInt(vista.txId.getText()));
            esp.setNombre(vista.txNombre.getText());
            esp.setExp(vista.txExpli.getText());
            if (Integer.parseInt(vista.txId.getText()) == -1){
                data.crearEspecializacion(esp);
            }else{
                data.editarEspecializacion(esp);
            }
            cargaTabla();
        }
        if (e.getSource() == vista.btEliminar){
            
            int id = Integer.parseInt(vista.txId.getText().toString());
            data.eliminarEspecializacion(id);
            JOptionPane.showMessageDialog(vista, "Se Elimino Correctamente");
            cargaTabla();
        }
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int fila = vista.jTabla.getSelectedRow();
            if (fila != -1) {
                int id = (int) vista.jTabla.getValueAt(fila, 0);
                vista.txId.setText(vista.jTabla.getValueAt(fila, 0).toString());
                vista.txNombre .setText(vista.jTabla.getValueAt(fila, 1).toString());
                vista.txExpli.setText(vista.jTabla.getValueAt(fila, 2).toString());
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
