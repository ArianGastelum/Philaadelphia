/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.philaadelphia.ui;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mx.itson.philaadelphia.entidades.Conductor;
import mx.itson.philaadelphia.persistencia.ConductorDAO;

/**
 *
 * @author Arian Gastelum
 */
public class TablaConductor extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public TablaConductor() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblConductores = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuBarOpciones = new javax.swing.JMenu();
        jMenuAgregar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblConductores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Número de licencia", "Fecha de alta"
            }
        ));
        jScrollPane1.setViewportView(tblConductores);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        MenuBarOpciones.setText("Opciones");
        MenuBarOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuBarOpcionesActionPerformed(evt);
            }
        });

        jMenuAgregar.setText("Agregar");
        jMenuAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAgregarActionPerformed(evt);
            }
        });
        MenuBarOpciones.add(jMenuAgregar);

        jMenuBar1.add(MenuBarOpciones);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(btnEliminar)
                .addGap(74, 74, 74)
                .addComponent(btnEditar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnEditar))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        cargarTabla();
    }
        /**
     * Sirve para imprimir la tabla de la base de datos a la tabla que vera el usuiario
     */
    private void cargarTabla(){
        
        try{
            
            DefaultTableModel modelo = (DefaultTableModel) tblConductores.getModel();
            modelo.setRowCount(0);
            ConductorDAO con = new ConductorDAO();

            List<Conductor> cond = con.obtenerTodos();
            
            for(Conductor d : cond){

                modelo.addRow(new Object[] {d.getId(), d.getNombre(), d.getNumlicencia(), d.getFechaAlta()});

            }
            
        }catch(Exception ex){
            
            System.err.println("Ocurrio un error: " + ex.getMessage());
            
        }
    }//GEN-LAST:event_formWindowOpened

    private void MenuBarOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuBarOpcionesActionPerformed
         
    }//GEN-LAST:event_MenuBarOpcionesActionPerformed

    private void jMenuAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAgregarActionPerformed
         AgregarConductor form = new AgregarConductor();
    form.setVisible(true);
    }//GEN-LAST:event_jMenuAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
      int filaSeleccionada = tblConductores.getSelectedRow();
    if (filaSeleccionada >= 0) {
        int id = (int) tblConductores.getValueAt(filaSeleccionada, 0);
        
        // Mostrar cuadro de diálogo de confirmación
        int opcion = JOptionPane.showConfirmDialog(TablaConductor.this, "¿Está seguro de eliminar el conductor seleccionado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            ConductorDAO conductorDAO = new ConductorDAO();
            conductorDAO.eliminarConductor(id);
            // Volver a cargar la tabla para reflejar los cambios
            cargarTabla();
        }
    } else {
        JOptionPane.showMessageDialog(TablaConductor.this, "Debe seleccionar un conductor para eliminar.");
    }
    
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

     /**   int filaSeleccionada = tblConductores.getSelectedRow();
    if (filaSeleccionada >= 0) {
        int id = (int) tblConductores.getValueAt(filaSeleccionada, 0);
        
        ConductorDAO dao = new ConductorDAO();
        Conductor conductor = dao.obtenerConductorPorId(id);
        
     //   EditarConductor form = new EditarConductor(conductor.getId());
        form.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un registro para editarlo.");
    }*/
    }//GEN-LAST:event_btnEditarActionPerformed

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
            java.util.logging.Logger.getLogger(TablaConductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TablaConductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TablaConductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TablaConductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TablaConductor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuBarOpciones;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JMenuItem jMenuAgregar;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblConductores;
    // End of variables declaration//GEN-END:variables
}
