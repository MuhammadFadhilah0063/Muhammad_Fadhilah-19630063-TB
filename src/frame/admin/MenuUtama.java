
package frame.admin;

import frame.mahasiswa.KrsMhsFrame;
import java.awt.Color;
import util.FrameSetting;

/**
 *
 * @author Fadhilah
 */
public class MenuUtama extends javax.swing.JFrame {

    /**
     * Creates new form MenuUtama
     */
    public MenuUtama() {
        initComponents();
        FrameSetting.setFrame(this);
        jPanel2.setBackground(new Color(0,0,0,0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        bKelas = new javax.swing.JLabel();
        bJadwal = new javax.swing.JLabel();
        bKrs = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        bLogout = new javax.swing.JLabel();
        bExit = new javax.swing.JLabel();
        bMakul = new javax.swing.JLabel();
        bMhs = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bDosen = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bKelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/button/btn_kelas.png"))); // NOI18N
        bKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bKelasMouseClicked(evt);
            }
        });
        jPanel2.add(bKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 430, 290, 230));

        bJadwal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/button/btn_jadwal.png"))); // NOI18N
        bJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bJadwalMouseClicked(evt);
            }
        });
        jPanel2.add(bJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 430, 280, -1));

        bKrs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/button/btn_krs.png"))); // NOI18N
        bKrs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bKrsMouseClicked(evt);
            }
        });
        jPanel2.add(bKrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 430, 280, -1));

        jLabel9.setBackground(new java.awt.Color(27, 178, 115));
        jLabel9.setFont(new java.awt.Font("Berlin Sans FB", 0, 22)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(246, 248, 246));
        jLabel9.setText("Admin");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 66, 370, 20));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/button/user.png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 70, 90));

        bLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/button/logout.png"))); // NOI18N
        bLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bLogoutMouseClicked(evt);
            }
        });
        jPanel2.add(bLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 30, 70, 90));

        bExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/button/exit.png"))); // NOI18N
        bExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bExitMouseClicked(evt);
            }
        });
        jPanel2.add(bExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 30, 90, 90));

        bMakul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/button/btn_makul.png"))); // NOI18N
        bMakul.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bMakulMouseClicked(evt);
            }
        });
        jPanel2.add(bMakul, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, -1, -1));

        bMhs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/button/btn_mhs.png"))); // NOI18N
        bMhs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bMhsMouseClicked(evt);
            }
        });
        jPanel2.add(bMhs, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 200, 280, -1));
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 474, 280, 140));

        bDosen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bDosen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/button/btn_dosen.png"))); // NOI18N
        bDosen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDosenMouseClicked(evt);
            }
        });
        jPanel2.add(bDosen, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 200, 270, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/form/form_menu_admin.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1080, 780));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bExitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_bExitMouseClicked

    private void bLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bLogoutMouseClicked
        // TODO add your handling code here:
        new frame.login.Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bLogoutMouseClicked

    private void bMhsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMhsMouseClicked
        // TODO add your handling code here:
        new MahasiswaFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bMhsMouseClicked

    private void bDosenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDosenMouseClicked
        // TODO add your handling code here:
        new DosenFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDosenMouseClicked

    private void bMakulMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMakulMouseClicked
        // TODO add your handling code here:
        new MakulFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bMakulMouseClicked

    private void bKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bKelasMouseClicked
        // TODO add your handling code here:
        new KelasFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bKelasMouseClicked

    private void bJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bJadwalMouseClicked
        // TODO add your handling code here:
        new JadwalFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bJadwalMouseClicked

    private void bKrsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bKrsMouseClicked
        // TODO add your handling code here:
        new KrsFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bKrsMouseClicked

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
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bDosen;
    private javax.swing.JLabel bExit;
    private javax.swing.JLabel bJadwal;
    private javax.swing.JLabel bKelas;
    private javax.swing.JLabel bKrs;
    private javax.swing.JLabel bLogout;
    private javax.swing.JLabel bMakul;
    private javax.swing.JLabel bMhs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
