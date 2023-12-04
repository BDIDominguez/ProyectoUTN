
package controladores;

import entidades.Aplicacion;
import entidades.Controladora;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import vistas.vistaAplicacion;
import vistas.PantallaPrincipal;

public class ControladorAplicacion implements ActionListener, ListSelectionListener{
    private vistaAplicacion vista;
    private PantallaPrincipal menu;
    private Controladora data;
    MyModelo modelo = new MyModelo();

    public ControladorAplicacion(vistaAplicacion vista, PantallaPrincipal menu, Controladora data) {
        this.vista = vista;
        this.menu = menu;
        this.data = data;
        
        vista.btEliminar.addActionListener(this);
        vista.btGuardar.addActionListener(this);
        vista.btNuevo.addActionListener(this);
        vista.btSalir.addActionListener(this);
        
        vista.tAplicaciones.getSelectionModel().addListSelectionListener(this);
        
        
        
    }

    public void iniciar(){
        menu.pVistas.add(vista);
        vista.setVisible(true);
        menu.pVistas.moveToFront(vista);
        vista.requestFocus();
        modelarTabla();
        CargaTabla();
        
    }
    
    
    
    public void CargaTabla(){
        ArrayList<Aplicacion> lista = data.traerListaAplicacion();
        modelo.setRowCount(0);
        for (Aplicacion apli : lista) {
            modelo.addRow(new Object[]{apli.getId(), apli.getNombre(), apli.getCaduca(), apli.getObs()});
          
        }
        vista.tAplicaciones.setModel(modelo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == vista.btSalir){
           vista.dispose();
       }
       if (e.getSource() == vista.btEliminar){
           int id =Integer.parseInt(vista.txId.getText());
           if (id > 0){
               data.eliminarAplicacion(id);
               JOptionPane.showMessageDialog(vista, "Se Elimino Correctamente");
           }else{
               JOptionPane.showMessageDialog(vista, "No se puede Eliminar");
           }
           CargaTabla();
       }
       if (e.getSource() == vista.btNuevo){
           vista.txId.setText("-1");
           vista.txNombre.setText("");
           vista.txObs.setText("");
           vista.dcCaduca.setDate(new Date());
       }
       if (e.getSource() == vista.btGuardar){
           Aplicacion ap = new Aplicacion();
           //ap.setId(Integer.parseInt(vista.txId.getText()));
           ap.setNombre(vista.txNombre.getText());
           ap.setCaduca(vista.dcCaduca.getDate());
           ap.setObs(vista.txObs.getText());
           if (vista.txId.getText().equals("-1")){
               data.crearAplicacion(ap);
               JOptionPane.showMessageDialog(vista, "Se creo la Aplicacion");
           }else{
               data.editarAplicacion(ap);
               JOptionPane.showMessageDialog(vista, "Se Modifico la Aplicacion");
           }
           CargaTabla();
       }
       
       
       
       
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int fila = vista.tAplicaciones.getSelectedRow();
            if (fila != -1) {
                int id = (int) vista.tAplicaciones.getValueAt(fila, 0);
                Aplicacion ope = data.traerAplicacion(id);
                vista.txId.setText(vista.tAplicaciones.getValueAt(fila, 0).toString());
                vista.txNombre.setText(vista.tAplicaciones.getValueAt(fila, 1).toString());
                vista.dcCaduca.setDate( (Date)  vista.tAplicaciones.getValueAt(fila, 2));
                vista.txObs.setText(vista.tAplicaciones.getValueAt(fila, 3).toString());
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
    private void modelarTabla() {
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Caducidad");
        modelo.addColumn("Observaciones");
        vista.tAplicaciones.setModel(modelo);
        
    }
    
}
