/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import entidades.Cliente;
import entidades.Controladora;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import vistas.PantallaPrincipal;
import vistas.VistaClientes;

/**
 *
 * @author Dario
 */
public class ControladorVistaClientes implements ActionListener, ListSelectionListener {
    private VistaClientes vista;
    private PantallaPrincipal menu;
    private Controladora data;
    MyModelo modelo = new MyModelo();


    public ControladorVistaClientes(VistaClientes vista, PantallaPrincipal menu, Controladora data) {
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
        modelo.addColumn("Razon Social");
        modelo.addColumn("CUIT/CUIL");
        modelo.addColumn("Alta");
        modelo.addColumn("Direccion");
        vista.jTabla.setModel(modelo);
    }

    public void cargaTabla() {
        ArrayList<Cliente> lista = data.traerListaClientes();
        modelo.setRowCount(0);
        for (Cliente ope : lista) {
            modelo.addRow(new Object[]{ope.getId(),ope.getNombre(),ope.getRz(),ope.getCuit(),ope.getAlta(),ope.getDireccion()});
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
            vista.txCuit.setText("");
            vista.txDireccion.setText("");
            vista.txRz.setText("");
        }
        if (e.getSource() == vista.btGuardar){
            Cliente esp = new Cliente();
            //esp.setId(Integer.parseInt(vista.txId.getText()));
            esp.setNombre(vista.txNombre.getText());
            esp.setNombre(vista.txNombre.getText());
            esp.setCuit(vista.txCuit.getText());
            esp.setDireccion(vista.txDireccion.getText());
            esp.setRz(vista.txRz.getText());
            esp.setAlta(vista.dcFecha.getDate());
            if (Integer.parseInt(vista.txId.getText()) == -1){
                data.crearCliente(esp);
            }else{
                esp.setId(Integer.parseInt(vista.txId.getText()));
                data.editarCliente(esp);
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
                vista.txId.setText(vista.jTabla.getValueAt(fila, 0).toString());
                vista.txNombre .setText(vista.jTabla.getValueAt(fila, 1).toString());
                vista.txRz.setText(vista.jTabla.getValueAt(fila, 2).toString());
                vista.txCuit.setText(vista.jTabla.getValueAt(fila, 3).toString());
                vista.dcFecha.setDate((Date) vista.jTabla.getValueAt(fila, 4));
                vista.txDireccion.setText(vista.jTabla.getValueAt(fila, 5).toString());
                
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
