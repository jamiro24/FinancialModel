/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financialmodel;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author s130228
 */
public class GUI extends javax.swing.JFrame {

    HashMap<String, Drone> drones;

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();

        drones = new HashMap<>();
        ArrayList<Drone> ds = Drone.getDrones();

        for (Drone d : ds) {
            drones.put(d.name, d);
            mDrones.addItem(d.name);
        }

        mDrones.setSelectedIndex(0);

        mHeight.setPaintLabels(true);
        mHeight.setMajorTickSpacing(5);
        mHeight.setMinorTickSpacing(1);
        mHeight.setPaintTicks(true);

        mAngle.setPaintLabels(true);
        mAngle.setMajorTickSpacing(15);
        mAngle.setMinorTickSpacing(1);
        mAngle.setPaintTicks(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        mHeight = new javax.swing.JSlider();
        jLabel5 = new javax.swing.JLabel();
        mSpeed = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        mTimeFrame = new javax.swing.JTextField();
        mCalculate = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        mDrones = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        mCampusArea = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        mViewDistance = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        mAngle = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        mCameraCost = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        mAmountCameras = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        mSalary = new javax.swing.JTextField();
        mEmployeesReplaced = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        mSoftwareCosts = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        jLabel15 = new javax.swing.JLabel();
        mAvgDistance = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        mCameraAmount = new javax.swing.JTextField();
        xdlabel = new javax.swing.JLabel();
        mEmployeeAmount = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        mCameraWattage = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        output.setEditable(false);
        output.setColumns(20);
        output.setRows(5);
        jScrollPane1.setViewportView(output);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jLabel1.setText("Height (m)");

        mHeight.setMaximum(30);
        mHeight.setMinimum(5);
        mHeight.setValue(15);

        jLabel5.setText("Speed (km/h)");

        mSpeed.setText("5");

        jLabel6.setText("Time frame (minutes)");

        mTimeFrame.setText("15");
        mTimeFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mTimeFrameActionPerformed(evt);
            }
        });

        mCalculate.setText("Calculate");
        mCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCalculateActionPerformed(evt);
            }
        });

        jLabel8.setText("Drone preset");

        jLabel10.setText("Campus area (km^2)");

        mCampusArea.setText("0.74");
        mCampusArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCampusAreaActionPerformed(evt);
            }
        });

        jLabel11.setText("View distance (m)");

        mViewDistance.setText("150");

        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Tilt angle (degrees)");

        mAngle.setMaximum(45);
        mAngle.setValue(20);
        mAngle.setAutoscrolls(true);

        jLabel3.setText("Camera cost (euros)");

        mCameraCost.setText("3500");

        jLabel4.setText("Amount of cameras replaced");

        mAmountCameras.setText("5");
        mAmountCameras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mAmountCamerasActionPerformed(evt);
            }
        });

        jLabel7.setText("Yearly salary (euros)");

        mSalary.setText("50000");

        mEmployeesReplaced.setText("0");

        jLabel12.setText("Employees replaced");

        jLabel13.setText("Software costs (euros)");

        mSoftwareCosts.setText("0");

        jLabel14.setText("Output");

        jLabel15.setText("Average distance to charge station (km)");

        mAvgDistance.setText("0.4");

        jLabel16.setText("Amount of cameras");

        mCameraAmount.setText("50");
        mCameraAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCameraAmountActionPerformed(evt);
            }
        });

        xdlabel.setText("Amount of employees (at the same time)");

        mEmployeeAmount.setText("3");

        jLabel17.setText("Camera wattage (w)");

        mCameraWattage.setText("20");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mViewDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel8))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(mCampusArea, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                                    .addComponent(mDrones, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(mAvgDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(xdlabel)
                    .addComponent(jLabel17)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(mCameraWattage, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mCalculate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mEmployeeAmount, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mCameraAmount, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mEmployeesReplaced, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mHeight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                        .addComponent(mSpeed, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mTimeFrame, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mSoftwareCosts, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mSalary, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mAmountCameras, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mCameraCost, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mAngle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(mDrones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jLabel14))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mCampusArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(mViewDistance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mAvgDistance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mTimeFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mAngle, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mCameraCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mCameraAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mAmountCameras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mCameraWattage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(xdlabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(mEmployeeAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mEmployeesReplaced, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mSoftwareCosts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mCalculate)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addGap(44, 44, 44))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(335, 335, 335))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mTimeFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mTimeFrameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mTimeFrameActionPerformed

    private void mCampusAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCampusAreaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mCampusAreaActionPerformed

    private void mAmountCamerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mAmountCamerasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mAmountCamerasActionPerformed

    private void mCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCalculateActionPerformed

        if (Double.parseDouble(mEmployeeAmount.getText()) < Double.parseDouble(mEmployeesReplaced.getText())) {
            JOptionPane.showMessageDialog(this, "You can't replace more employees then you have");
            return;
        }
        
        if (Double.parseDouble(mCameraAmount.getText()) < Double.parseDouble(mAmountCameras.getText())) {
            JOptionPane.showMessageDialog(this, "You can't replace more cameras then you have");
            return;
        }
        output.setText("");

        Drone d = drones.get(mDrones.getSelectedItem());
        
        d.printSpecs(output);
        Calc.DISTANCELIMIT = Double.parseDouble(mViewDistance.getText());
        Calc.AVGDISTANCE = Double.parseDouble(mAvgDistance.getText());
        Calc.nEmployees = Double.parseDouble(mEmployeeAmount.getText());
        Calc.nCameras = Double.parseDouble(mCameraAmount.getText());
        double coverage = Calc.coverage(output,
                mHeight.getValue(),
                d.fov,
                mAngle.getValue(),
                d.aspectRatio,
                Double.parseDouble(mSpeed.getText()),
                Double.parseDouble(mTimeFrame.getText()));

        Calc.upTime(output, d);

        Calc.cost(output,
                d,
                Double.parseDouble(mCameraCost.getText()),
                Double.parseDouble(mAmountCameras.getText()),
                Double.parseDouble(mSalary.getText()),
                Double.parseDouble(mEmployeesReplaced.getText()),
                Double.parseDouble(mSoftwareCosts.getText()),
                coverage,
                Double.parseDouble(mCameraWattage.getText()));
    }//GEN-LAST:event_mCalculateActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new AddDrone(new AddResult() {
            @Override
            public void done() {
                mDrones.removeAllItems();
                drones = new HashMap<>();

                ArrayList<Drone> ds = Drone.getDrones();

                for (Drone d : ds) {
                    drones.put(d.name, d);
                    mDrones.addItem(d.name);
                }

                mDrones.setSelectedIndex(0);

                mHeight.setPaintLabels(true);
                mHeight.setMajorTickSpacing(5);
                mHeight.setMinorTickSpacing(1);
                mHeight.setPaintTicks(true);

                mAngle.setPaintLabels(true);
                mAngle.setMajorTickSpacing(15);
                mAngle.setMinorTickSpacing(3);
                mAngle.setPaintTicks(true);
            }

        }).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void mCameraAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCameraAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mCameraAmountActionPerformed

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
                /* if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }*/
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mAmountCameras;
    private javax.swing.JSlider mAngle;
    private javax.swing.JTextField mAvgDistance;
    private javax.swing.JButton mCalculate;
    private javax.swing.JTextField mCameraAmount;
    private javax.swing.JTextField mCameraCost;
    private javax.swing.JTextField mCameraWattage;
    private javax.swing.JTextField mCampusArea;
    private javax.swing.JComboBox<String> mDrones;
    private javax.swing.JTextField mEmployeeAmount;
    private javax.swing.JTextField mEmployeesReplaced;
    private javax.swing.JSlider mHeight;
    private javax.swing.JTextField mSalary;
    private javax.swing.JTextField mSoftwareCosts;
    private javax.swing.JTextField mSpeed;
    private javax.swing.JTextField mTimeFrame;
    private javax.swing.JTextField mViewDistance;
    private javax.swing.JTextArea output;
    private javax.swing.JLabel xdlabel;
    // End of variables declaration//GEN-END:variables
}
