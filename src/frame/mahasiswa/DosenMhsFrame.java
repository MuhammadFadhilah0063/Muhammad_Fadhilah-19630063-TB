/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame.mahasiswa;

import connection.Koneksi;
import frame.admin.*;
import frame.login.Login;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import model.Dosen;
import model.Mhs;

/**
 *
 * @author Fadhilah
 */
public class DosenMhsFrame extends javax.swing.JFrame {
    private Mhs mhs;
    private Dosen dosen;
    private Connection con;
    private String qry;
    
    public ArrayList<Dosen> getDosenList(String keyword) {
        ArrayList<Dosen> dosenList = new ArrayList<>();
        con = Koneksi.getKoneksi();
        ResultSet rs;
        Statement st;
        qry = "SELECT * FROM dosen";
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(qry);
            while (rs.next()) {                
                dosen = new Dosen(
                        rs.getString("nip"), 
                        rs.getString("nama_dosen"),
                        rs.getString("telepon"),
                        rs.getString("alamat"));
                dosenList.add(dosen);
            }
        } catch (SQLException | NullPointerException ex) {
            System.err.println("Koneksi Null Gagal");
        }
        return dosenList;
    }
    
    public void selectDosen(String keyword) {
        ArrayList<Dosen> list = getDosenList(keyword);
        DefaultTableModel model = (DefaultTableModel) tDosen.getModel();
        Object[] row = new Object[5];
        int no = 1;
        
        for (int i = 0; i < list.size(); i++) {
            row[0] = no++;
            row[1] = list.get(i).getNip();
            row[2] = list.get(i).getNama();
            row[3] = list.get(i).getTelepon();
            row[4] = list.get(i).getAlamat();
            model.addRow(row);
        }
    }
    
    public final void resetTable(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tDosen.getModel();
        model.setRowCount(0);
        selectDosen(keyword);
    }

    public void lebarKolom(){ 
        TableColumn column;
        tDosen.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF); 
        column = tDosen.getColumnModel().getColumn(0); 
        column.setPreferredWidth(30);
        column = tDosen.getColumnModel().getColumn(1); 
        column.setPreferredWidth(180);
        column = tDosen.getColumnModel().getColumn(2); 
        column.setPreferredWidth(290); 
        column = tDosen.getColumnModel().getColumn(3); 
        column.setPreferredWidth(175); 
        column = tDosen.getColumnModel().getColumn(4); 
        column.setPreferredWidth(1000); 
    }
    
    public DosenMhsFrame() {
        initComponents();
        this.setBackground(new Color(0,0,0,0));
        this.setLocationRelativeTo(null);
        lebarKolom();
        resetTable("");
    }
    
    public DosenMhsFrame(Mhs mhs) {
        initComponents();
        this.mhs = mhs;
        this.setBackground(new Color(0,0,0,0));
        this.setLocationRelativeTo(null);
        lebarKolom();
        resetTable("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bKembali = new javax.swing.JLabel();
        bLogout = new javax.swing.JLabel();
        bExit = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tDosen = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bKembali.setIcon(new javax.swing.ImageIcon("D:\\PBO5P\\KRS_JAVA_APP\\src\\asset\\button\\back.png")); // NOI18N
        bKembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bKembaliMouseClicked(evt);
            }
        });
        getContentPane().add(bKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 10, 70, 90));

        bLogout.setIcon(new javax.swing.ImageIcon("D:\\PBO5P\\KRS_JAVA_APP\\src\\asset\\button\\logout.png")); // NOI18N
        bLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bLogoutMouseClicked(evt);
            }
        });
        getContentPane().add(bLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, 70, 90));

        bExit.setIcon(new javax.swing.ImageIcon("D:\\PBO5P\\KRS_JAVA_APP\\src\\asset\\button\\exit.png")); // NOI18N
        bExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bExitMouseClicked(evt);
            }
        });
        getContentPane().add(bExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 10, 90, 90));

        tDosen.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        tDosen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "NIP", "Nama Dosen", "Nomor Telepon", "Alamat"
            }
        ));
        tDosen.setGridColor(new java.awt.Color(0, 0, 0));
        tDosen.setMinimumSize(new java.awt.Dimension(100, 0));
        jScrollPane1.setViewportView(tDosen);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 1030, 460));

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\PBO5P\\KRS_JAVA_APP\\src\\asset\\form\\form_lihat_dosen.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bExitMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_bExitMouseClicked

    private void bKembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bKembaliMouseClicked
        // TODO add your handling code here:
        new MenuUtamaMhs(mhs).setVisible(true);
        dispose();
    }//GEN-LAST:event_bKembaliMouseClicked

    private void bLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bLogoutMouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
        dispose();
    }//GEN-LAST:event_bLogoutMouseClicked

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
            java.util.logging.Logger.getLogger(DosenMhsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DosenMhsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DosenMhsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DosenMhsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DosenMhsFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bExit;
    private javax.swing.JLabel bKembali;
    private javax.swing.JLabel bLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tDosen;
    // End of variables declaration//GEN-END:variables
}