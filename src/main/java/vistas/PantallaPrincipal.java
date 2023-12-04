/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

/**
 *
 * @author Dario
 */
public class PantallaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form PantallaPrincipal
     */
    public PantallaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pVistas = new javax.swing.JDesktopPane();
        pBotones = new javax.swing.JDesktopPane();
        btSalir = new javax.swing.JButton();
        btAplicacion = new javax.swing.JButton();
        btEspecialidad = new javax.swing.JButton();
        btEmpleado = new javax.swing.JButton();
        btCliente = new javax.swing.JButton();
        btIncidencia = new javax.swing.JButton();
        btEspTec = new javax.swing.JButton();
        btTecEsp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyecto FInal UTN");

        pVistas.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout pVistasLayout = new javax.swing.GroupLayout(pVistas);
        pVistas.setLayout(pVistasLayout);
        pVistasLayout.setHorizontalGroup(
            pVistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1161, Short.MAX_VALUE)
        );
        pVistasLayout.setVerticalGroup(
            pVistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pBotones.setBackground(new java.awt.Color(255, 204, 102));

        btSalir.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btSalir.setText("Salir");

        btAplicacion.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btAplicacion.setText("Cargar Aplicacion");

        btEspecialidad.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btEspecialidad.setText("Cargar Especializacion");

        btEmpleado.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btEmpleado.setText("Cargar Empleado");

        btCliente.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btCliente.setText("Cargar Cliente");

        btIncidencia.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btIncidencia.setText("Incidencia");

        btEspTec.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btEspTec.setText("Asignar Esp a Tecnicos");

        btTecEsp.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btTecEsp.setText("Asignar Tecnicos a Esp");

        pBotones.setLayer(btSalir, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pBotones.setLayer(btAplicacion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pBotones.setLayer(btEspecialidad, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pBotones.setLayer(btEmpleado, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pBotones.setLayer(btCliente, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pBotones.setLayer(btIncidencia, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pBotones.setLayer(btEspTec, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pBotones.setLayer(btTecEsp, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pBotonesLayout = new javax.swing.GroupLayout(pBotones);
        pBotones.setLayout(pBotonesLayout);
        pBotonesLayout.setHorizontalGroup(
            pBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btAplicacion, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
            .addComponent(btEspecialidad, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
            .addComponent(btSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
            .addComponent(btEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
            .addComponent(btCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
            .addComponent(btIncidencia, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
            .addComponent(btEspTec, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
            .addComponent(btTecEsp, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
        );
        pBotonesLayout.setVerticalGroup(
            pBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btEmpleado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEspecialidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btAplicacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btEspTec)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btIncidencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 234, Short.MAX_VALUE)
                .addComponent(btTecEsp)
                .addGap(156, 156, 156)
                .addComponent(btSalir)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pVistas))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pVistas)
            .addComponent(pBotones)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btAplicacion;
    public javax.swing.JButton btCliente;
    public javax.swing.JButton btEmpleado;
    public javax.swing.JButton btEspTec;
    public javax.swing.JButton btEspecialidad;
    public javax.swing.JButton btIncidencia;
    public javax.swing.JButton btSalir;
    public javax.swing.JButton btTecEsp;
    public javax.swing.JDesktopPane pBotones;
    public javax.swing.JDesktopPane pVistas;
    // End of variables declaration//GEN-END:variables
}