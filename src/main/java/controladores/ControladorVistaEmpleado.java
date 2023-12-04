package controladores;

import entidades.Controladora;
import entidades.Empleado;
import entidades.Operador;
import entidades.Tecnico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import vistas.PantallaPrincipal;
import vistas.VistaEmpleado;

/**
 * @author Dario
 */
public class ControladorVistaEmpleado implements ActionListener, ListSelectionListener {

    private VistaEmpleado vista;
    private PantallaPrincipal menu;
    private Controladora data;
    MyModelo modelo = new MyModelo();

    public ControladorVistaEmpleado(VistaEmpleado vista, PantallaPrincipal menu, Controladora data) {
        this.vista = vista;
        this.menu = menu;
        this.data = data;

        vista.btEliminar.addActionListener(this);
        vista.btGuardar.addActionListener(this);
        vista.btNuevo.addActionListener(this);
        vista.btSalir.addActionListener(this);

        vista.tbEmpleados.getSelectionModel().addListSelectionListener(this);

    }

    public void iniciar() {
        menu.pVistas.add(vista);
        vista.setVisible(true);
        menu.pVistas.moveToFront(vista);
        vista.requestFocus();
        modelarTabla();
        cargaTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btSalir) {
            vista.dispose();
        }
        if (e.getSource() == vista.btNuevo) {
            vista.txID.setText("-1");
            vista.txNombre.setText("");
            vista.txDni.setText("0");
            vista.txCorreo.setText("");
            vista.txCelular.setText("");
            vista.txTipo.setText("Operador/Tecnico");
        }
        if (e.getSource() == vista.btGuardar) {
            if (vista.txID.getText().equals("-1")) {
                if (vista.txTipo.getText().toLowerCase().equals("tecnico")) {
                    Tecnico tec = new Tecnico();
                    tec.setDni(Integer.parseInt(vista.txDni.getText()));
                    tec.setNombre(vista.txNombre.getText());
                    tec.setCelular(vista.txCelular.getText());
                    tec.setCorreo(vista.txCorreo.getText());
                    data.crearTecnico(tec);
                    JOptionPane.showMessageDialog(vista, "Se Cargo el Tecnico Correctamente");
                } else {
                    Operador oper = new Operador();
                    oper.setDni(Integer.parseInt(vista.txDni.getText()));
                    oper.setNombre(vista.txNombre.getText());
                    oper.setCelular(vista.txCelular.getText());
                    oper.setCorreo(vista.txCorreo.getText());
                    data.crearOperador(oper);
                    JOptionPane.showMessageDialog(vista, "Se Cargo el Operador Correctamente");
                }
            } else {
                if (vista.txTipo.getText().toLowerCase().equals("tecnico")) {
                    Tecnico tec = new Tecnico();
                    tec.setLegajo(Integer.parseInt(vista.txID.getText()));
                    tec.setDni(Integer.parseInt(vista.txDni.getText()));
                    tec.setNombre(vista.txNombre.getText());
                    tec.setCelular(vista.txCelular.getText());
                    tec.setCorreo(vista.txCorreo.getText());
                    data.editarTecnico(tec);
                    JOptionPane.showMessageDialog(vista, "Se Modifico el Tecnico Correctamente");
                } else {
                    Operador oper = new Operador();
                    oper.setLegajo(Integer.parseInt(vista.txID.getText()));
                    oper.setDni(Integer.parseInt(vista.txDni.getText()));
                    oper.setNombre(vista.txNombre.getText());
                    oper.setCelular(vista.txCelular.getText());
                    oper.setCorreo(vista.txCorreo.getText());
                    data.editarOperador(oper);
                    JOptionPane.showMessageDialog(vista, "Se Modifico el Operador Correctamente");
                }
            }
            cargaTabla();
        }
        if (e.getSource() == vista.btEliminar) {
            int vRespuesta = JOptionPane.showConfirmDialog(menu, "Seguro de Eliminar?", "Advertencia", JOptionPane.YES_NO_OPTION);
            if (vRespuesta == 0) {
                int id = Integer.parseInt(vista.txID.getText());
                if (vista.txTipo.getText().toLowerCase().equals("tecnico")) {
                    data.eliminarTecnico(id);
                } else {
                    data.eliminarOperador(id);
                }
                JOptionPane.showMessageDialog(vista, "Se Elimino Correctamente");
            }
            cargaTabla();
        }

    } // Fin ActionPerformed

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int fila = vista.tbEmpleados.getSelectedRow();
            if (fila != -1) {
                int id = (int) vista.tbEmpleados.getValueAt(fila, 0);
                Empleado ope = data.traerOperador(id);
                Empleado tec = data.traerTecnico(id);
                if (ope == null) {
                    vista.txTipo.setText("Tecnico");
                } else {
                    vista.txTipo.setText("Operador");
                }
                vista.txID.setText(vista.tbEmpleados.getValueAt(fila, 0).toString());
                vista.txNombre.setText(vista.tbEmpleados.getValueAt(fila, 1).toString());
                vista.txDni.setText(vista.tbEmpleados.getValueAt(fila, 2).toString());
                vista.txCorreo.setText(vista.tbEmpleados.getValueAt(fila, 3).toString());
                vista.txCelular.setText(vista.tbEmpleados.getValueAt(fila, 4).toString());

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
        modelo.addColumn("Legajo");
        modelo.addColumn("Nombre");
        modelo.addColumn("DNI");
        modelo.addColumn("Correo");
        modelo.addColumn("Celular");
        vista.tbEmpleados.setModel(modelo);
    }

    public void cargaTabla() {
        ArrayList<Operador> lista = data.traerListaOperador();
        ArrayList<Tecnico> tec = data.traerListaTecnico();
        modelo.setRowCount(0);
        for (Operador ope : lista) {
            modelo.addRow(new Object[]{ope.getLegajo(), ope.getNombre(), ope.getDni(), ope.getCorreo(), ope.getCelular()});
        }
        for (Tecnico ope : tec) {
            modelo.addRow(new Object[]{ope.getLegajo(), ope.getNombre(), ope.getDni(), ope.getCorreo(), ope.getCelular()});
        }
        vista.tbEmpleados.setModel(modelo);
    }

}
