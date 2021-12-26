/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame.mahasiswa;

import connection.Koneksi;
import frame.admin.MenuUtama;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import model.Dosen;
import model.Jadwal;
import model.Kelas;
import model.Krs;
import model.Makul;
import model.Mhs;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Fadhilah
 */
public class KrsMhsFrame extends javax.swing.JFrame {
    
    private Krs krs;
    private Mhs mhs;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    private String qry;
    private String idKrs;
    
    public ArrayList<Krs> getKrsList(String keyword) {
        ArrayList<Makul> makulList = getMakulList();
        ArrayList<Dosen> dosenList = getDosenList();
        ArrayList<Kelas> kelasList = getKelasList();
        ArrayList<Krs> krsList = new ArrayList<>();
        
        String kodeMakul, nip, kodeKelas;
        String makul = null; 
        String semester = null; 
        String dosen = null; 
        String kelas = null;
        int sks = 0;

        Connection con = Koneksi.getKoneksi();
        Statement st;
        ResultSet rs;
        String query = "SELECT * FROM krs JOIN mhs ON krs.id_mhs = mhs.id_mhs "
                + "JOIN jadwal ON krs.kode_jadwal = jadwal.kode_jadwal JOIN "
                + "makul ON jadwal.kode_makul = makul.kode_makul JOIN dosen ON "
                + "jadwal.nip_dosen = dosen.nip JOIN kelas ON jadwal.kode_kelas "
                + "= kelas.kode_kelas Where krs.id_mhs = " + mhs.getIdMhs();
            
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {        
                krs = new Krs(rs.getString("id_krs"),
                                rs.getInt("mhs.id_mhs"),
                                rs.getString("mhs.npm"),
                                rs.getString("mhs.nama"),
                                rs.getString("mhs.prog_studi"),
                                rs.getString("jadwal.kode_jadwal"),
                                rs.getString("makul.kode_makul"),
                                rs.getString("makul.makul"),
                                rs.getInt("makul.sks"),
                                rs.getString("makul.semester"),
                                rs.getString("kelas.kode_kelas"),
                                rs.getString("kelas.nama_kelas"),
                                rs.getString("dosen.nip"),
                                rs.getString("dosen.nama_dosen"),
                                rs.getString("tahun_ajar"));
                krsList.add(krs);
            }
        } catch (SQLException e) {
            System.err.println("Error getJadwalList : " + e);
        }
        return krsList;
    }
    
    public void selectKrs(String keyword) {
        ArrayList<Krs> list = getKrsList(keyword);
        DefaultTableModel model = (DefaultTableModel) tKrsMhs.getModel();
        Object[] row = new Object[12];
        int no = 1;
        
        for (int i = 0; i < list.size(); i++) {
            row[0] = no++;
            row[1] = list.get(i).getIdKrs();
            row[2] = list.get(i).getMhs().getNpm();
            row[3] = list.get(i).getMhs().getNama();
            row[4] = list.get(i).getMhs().getProgStudi();
            row[5] = list.get(i).getKodeJadwal();
            row[6] = list.get(i).getDosen().getNama();
            row[7] = list.get(i).getMakul().getMakul();
            row[8] = list.get(i).getMakul().getSks();
            row[9] = list.get(i).getMakul().getSemester();
            row[10] = list.get(i).getKelas().getKelas();
            row[11] = list.get(i).getTahunAjar();
            model.addRow(row);
        }
    }
    
    public final void resetTable(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tKrsMhs.getModel();
        model.setRowCount(0);
        selectKrs(keyword);
    }

    public void setTableList() {
        Jadwal jadwal;
        ArrayList<Jadwal> jadwalList = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) tList.getModel();
        Object[] row = new Object[5];
        int no = 1;
        Connection con = Koneksi.getKoneksi();
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT jadwal.*, makul.*, dosen.*, kelas.* FROM jadwal "
                + "INNER JOIN makul ON jadwal.kode_makul = makul.kode_makul "
                + "JOIN dosen ON jadwal.nip_dosen = dosen.nip "
                + "JOIN kelas ON jadwal.kode_kelas = kelas.kode_kelas WHERE "
                + "jadwal.semester_jadwal LIKE ? ORDER BY jadwal.kode_jadwal";
        
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + cbSemester.getSelectedItem().toString() + "%");

            rs = ps.executeQuery();
            while (rs.next()) {      
                jadwal = new Jadwal(
                        rs.getString("kode_jadwal"), 
                        rs.getString("makul.kode_makul"), 
                        rs.getString("makul.makul"),
                        rs.getString("dosen.nip"), 
                        rs.getString("dosen.nama_dosen"), 
                        rs.getString("kelas.kode_kelas"), 
                        rs.getString("kelas.nama_kelas"),
                        rs.getString("semester_jadwal"),
                        rs.getString("jam"), 
                        rs.getString("hari"));
                jadwalList.add(jadwal);
            }
        } catch (SQLException e) {
            System.err.println("Error getJadwalList : " + e);
        }
        
        for (int i = 0; i < jadwalList.size(); i++) {
            row[0] = no++;
            row[1] = jadwalList.get(i).getKodeJadwal();
            row[2] = jadwalList.get(i).getMakul().getMakul();
            row[3] = jadwalList.get(i).getDosen().getNama();
            row[4] = jadwalList.get(i).getKelas().getKelas();
            model.addRow(row);
        }
    }
    
    public final void resetTableList() {
        DefaultTableModel model = (DefaultTableModel) tList.getModel();
        model.setRowCount(0);
    }
    
    public ArrayList<Makul> getMakulList() {
        ArrayList<Makul> makulList = new ArrayList<>();
        Makul makul;
        
        try {
        con = Koneksi.getKoneksi();
        Statement st = con.createStatement();
            
            // query makul
            ResultSet rs = st.executeQuery("SELECT * FROM makul");
            
            while (rs.next()){
                makul = new Makul(rs.getString("kode_makul"), rs.getString("makul"), 
                                    rs.getInt("sks"), rs.getString("semester"));
                makulList.add(makul);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return makulList;
    }
    
    public ArrayList<Dosen> getDosenList() {
        ArrayList<Dosen> dosenList = new ArrayList<>();
        Dosen dosen;
        
        try {
        con = Koneksi.getKoneksi();
        Statement st = con.createStatement();
            
            // query dosen
            ResultSet rs = st.executeQuery("SELECT * FROM dosen");
            
            while (rs.next()){
                dosen = new Dosen(rs.getString("nip"), rs.getString("nama_dosen"));
                dosenList.add(dosen);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return dosenList;
    }
    
    public ArrayList<Kelas> getKelasList() {
        ArrayList<Kelas> kelasList = new ArrayList<>();
        Kelas kelas;
        
        try {
        con = Koneksi.getKoneksi();
        Statement st = con.createStatement();
            
            // query kelas
            ResultSet rs = st.executeQuery("SELECT * FROM kelas");
            
            while (rs.next()){
                kelas = new Kelas(rs.getString("kode_kelas"), rs.getString("nama_kelas"));
                kelasList.add(kelas);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return kelasList;
    }
    
    public ArrayList<String> getListKrsMhs(int id) {
        ArrayList<String> listKrsMhs = new ArrayList<>();
        String krsMhs;
        
        try {
            con = Koneksi.getKoneksi();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM krs WHERE id_mhs = " + id);

            while (rs.next()){
                krsMhs = rs.getString("kode_jadwal");
                listKrsMhs.add(krsMhs);
            }
        } catch (SQLException e) {
            System.err.println("Error getListKrsMhs(): " + e.getMessage());
        }
        return listKrsMhs;
    }
    
    public String makeId() {
        String kode = null;
        String lastKode = null;
        kode = "KS0001";
        try {
            Connection con = Koneksi.getKoneksi();
            String query = "SELECT id_krs FROM krs WHERE id_krs LIKE ? ORDER BY id_krs DESC";
            ps = con.prepareStatement(query);
            ps.setString(1, "KS%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                lastKode = rs.getString(1);
                break;
            }
        } catch (SQLException e) {
            System.err.println("Error makeId() : " + e);
        }
        
        if (lastKode != null) {
            int angka = Integer.parseInt(lastKode.substring(2));
            angka++;
            kode = "KS" + String.format("%04d", angka);
        }
        return kode;
    }
    
    public void setTahunAjar() {
        String year, tahunAjar = "";
        int nextYear;
        int angka = 0;
        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        year = df.format(now);
        
        nextYear = Integer.parseInt(year);
        tahunAjar = year + "/" + ++nextYear;
        eTahun.setText(tahunAjar);
    }
    
    public void setLabelDaftar() {
        lblDaftar.setText("Daftar Matakuliah " + cbSemester.getSelectedItem().toString());
    }
    
    public void lebarKolom(){ 
        TableColumn column;
        tList.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF); 
        
        // tabel krs
        column = tKrsMhs.getColumnModel().getColumn(0); 
        column.setPreferredWidth(10);
        column = tKrsMhs.getColumnModel().getColumn(1); 
        column.setPreferredWidth(20);
        column = tKrsMhs.getColumnModel().getColumn(2); 
        column.setPreferredWidth(50);
        column = tKrsMhs.getColumnModel().getColumn(3); 
        column.setPreferredWidth(200);
        column = tKrsMhs.getColumnModel().getColumn(4); 
        column.setPreferredWidth(85);
        column = tKrsMhs.getColumnModel().getColumn(5); 
        column.setPreferredWidth(45);
        column = tKrsMhs.getColumnModel().getColumn(6); 
        column.setPreferredWidth(240);
        column = tKrsMhs.getColumnModel().getColumn(7); 
        column.setPreferredWidth(160);
        column = tKrsMhs.getColumnModel().getColumn(8); 
        column.setPreferredWidth(10);
        column = tKrsMhs.getColumnModel().getColumn(9); 
        column.setPreferredWidth(50);
        column = tKrsMhs.getColumnModel().getColumn(10); 
        column.setPreferredWidth(10);
        column = tKrsMhs.getColumnModel().getColumn(11); 
        column.setPreferredWidth(50);
        
        // tabel list
        column = tList.getColumnModel().getColumn(0); 
        column.setPreferredWidth(30);
        column = tList.getColumnModel().getColumn(1); 
        column.setPreferredWidth(100); 
        column = tList.getColumnModel().getColumn(2); 
        column.setPreferredWidth(244); 
        column = tList.getColumnModel().getColumn(3); 
        column.setPreferredWidth(340);
        column = tList.getColumnModel().getColumn(4); 
        column.setPreferredWidth(50);
    }
    
    public KrsMhsFrame() {
        initComponents();
        this.setBackground(new Color(0,0,0,0));
        this.setLocationRelativeTo(null);
        lebarKolom();
        setTahunAjar();
        setTableList(); 
        setLabelDaftar();
        resetTable("");
    }
    
    public KrsMhsFrame(Mhs mhs) {
        initComponents();
        this.mhs = mhs;
        this.setBackground(new Color(0,0,0,0));
        this.setLocationRelativeTo(null);
        lebarKolom();
        setTahunAjar();
        setTableList(); 
        setLabelDaftar();
        resetTable("");
        eNpm.setText(mhs.getNpm());
        eNama.setText(mhs.getNama());
        eId.setText(Integer.toString(mhs.getIdMhs()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        bKembali = new javax.swing.JLabel();
        bLogout = new javax.swing.JLabel();
        bExit = new javax.swing.JLabel();
        lblDaftar1 = new javax.swing.JLabel();
        lblDaftar = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbSemester = new javax.swing.JComboBox<>();
        eId = new javax.swing.JTextField();
        eNpm = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        eKdJadwal = new javax.swing.JTextField();
        eMakul = new javax.swing.JTextField();
        eDosen = new javax.swing.JTextField();
        bCetak = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tList = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        eTahun = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tKrsMhs = new javax.swing.JTable();
        eNama = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        bSimpan = new javax.swing.JButton();
        bHapus = new javax.swing.JButton();
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
        getContentPane().add(bKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 10, 70, 90));

        bLogout.setIcon(new javax.swing.ImageIcon("D:\\PBO5P\\KRS_JAVA_APP\\src\\asset\\button\\logout.png")); // NOI18N
        bLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bLogoutMouseClicked(evt);
            }
        });
        getContentPane().add(bLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 10, 70, 90));

        bExit.setIcon(new javax.swing.ImageIcon("D:\\PBO5P\\KRS_JAVA_APP\\src\\asset\\button\\exit.png")); // NOI18N
        bExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bExitMouseClicked(evt);
            }
        });
        getContentPane().add(bExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 10, 90, 90));

        lblDaftar1.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        lblDaftar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDaftar1.setText("Daftar Matakuliah Di Ambil ");
        getContentPane().add(lblDaftar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 642, 1430, -1));

        lblDaftar.setFont(new java.awt.Font("Berlin Sans FB", 0, 21)); // NOI18N
        lblDaftar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDaftar.setText("Daftar Matakuliah ");
        getContentPane().add(lblDaftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 770, -1));

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 21)); // NOI18N
        jLabel3.setText("ID");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, -1, -1));

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB", 0, 21)); // NOI18N
        jLabel6.setText("Matakuliah");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, -1, -1));

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB", 0, 21)); // NOI18N
        jLabel7.setText("Kode Jadwal");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, -1, -1));

        jLabel8.setFont(new java.awt.Font("Berlin Sans FB", 0, 21)); // NOI18N
        jLabel8.setText("Semester");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, -1, -1));

        cbSemester.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        cbSemester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6", "Semester 7", "Semester 8" }));
        cbSemester.setPreferredSize(new java.awt.Dimension(64, 18));
        cbSemester.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbSemesterItemStateChanged(evt);
            }
        });
        getContentPane().add(cbSemester, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 300, 30));

        eId.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        eId.setEnabled(false);
        getContentPane().add(eId, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 300, -1));

        eNpm.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        eNpm.setEnabled(false);
        getContentPane().add(eNpm, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 300, -1));

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB", 0, 21)); // NOI18N
        jLabel5.setText("Dosen");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 520, -1, -1));

        eKdJadwal.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        eKdJadwal.setEnabled(false);
        getContentPane().add(eKdJadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, 300, -1));

        eMakul.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        eMakul.setEnabled(false);
        getContentPane().add(eMakul, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 470, 300, -1));

        eDosen.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        eDosen.setEnabled(false);
        getContentPane().add(eDosen, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 520, 300, -1));

        bCetak.setBackground(new java.awt.Color(198, 210, 211));
        bCetak.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        bCetak.setForeground(new java.awt.Color(59, 59, 59));
        bCetak.setIcon(new javax.swing.ImageIcon("D:\\PBO5P\\KRS_JAVA_APP\\src\\asset\\button\\icon_cetak.png")); // NOI18N
        bCetak.setText("Cetak");
        bCetak.setToolTipText("");
        bCetak.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 176, 174), 2, true), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(198, 210, 211), 2)));
        bCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCetakActionPerformed(evt);
            }
        });
        getContentPane().add(bCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 780, 100, 30));

        tList.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        tList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Kode", "Matakuliah", "Dosen", "Kelas"
            }
        ));
        tList.setGridColor(new java.awt.Color(0, 0, 0));
        tList.setMinimumSize(new java.awt.Dimension(100, 0));
        tList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 250, 770, 170));

        jLabel4.setFont(new java.awt.Font("Berlin Sans FB", 0, 21)); // NOI18N
        jLabel4.setText("Tahun Ajaran");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, -1, -1));

        eTahun.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        eTahun.setEnabled(false);
        getContentPane().add(eTahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, 300, -1));

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB", 0, 21)); // NOI18N
        jLabel9.setText("NPM");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, -1, -1));

        tKrsMhs.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        tKrsMhs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "ID KRS", "NPM", "Nama", "Program Studi", "Kode Jadwal", "Dosen", "Matakuliah", "SKS", "Semester", "Kelas", "Tahun Ajaran"
            }
        ));
        tKrsMhs.setGridColor(new java.awt.Color(0, 0, 0));
        tKrsMhs.setMinimumSize(new java.awt.Dimension(100, 0));
        tKrsMhs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tKrsMhsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tKrsMhs);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 670, 1430, 150));

        eNama.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        eNama.setEnabled(false);
        eNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eNamaActionPerformed(evt);
            }
        });
        getContentPane().add(eNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 300, -1));

        jLabel10.setFont(new java.awt.Font("Berlin Sans FB", 0, 21)); // NOI18N
        jLabel10.setText("Nama");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, -1, -1));

        bSimpan.setBackground(new java.awt.Color(198, 210, 211));
        bSimpan.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bSimpan.setForeground(new java.awt.Color(59, 59, 59));
        bSimpan.setIcon(new javax.swing.ImageIcon("D:\\PBO5P\\KRS_JAVA_APP\\src\\asset\\button\\icon_simpan.png")); // NOI18N
        bSimpan.setText("SIMPAN");
        bSimpan.setToolTipText("");
        bSimpan.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 176, 174), 2, true), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(198, 210, 211), 2)));
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(bSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 460, 180, 40));

        bHapus.setBackground(new java.awt.Color(198, 210, 211));
        bHapus.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bHapus.setForeground(new java.awt.Color(59, 59, 59));
        bHapus.setIcon(new javax.swing.ImageIcon("D:\\PBO5P\\KRS_JAVA_APP\\src\\asset\\button\\icon_hapus.png")); // NOI18N
        bHapus.setText("HAPUS");
        bHapus.setToolTipText("");
        bHapus.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 176, 174), 2, true), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(198, 210, 211), 2)));
        bHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusActionPerformed(evt);
            }
        });
        getContentPane().add(bHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 460, 180, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\PBO5P\\KRS_JAVA_APP\\src\\asset\\form\\krs_mhs_form.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, -1));

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
        new frame.login.Login().setVisible(true);
        dispose();
    }//GEN-LAST:event_bLogoutMouseClicked

    private void cbSemesterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSemesterItemStateChanged
        // TODO add your handling code here:
        resetTableList();
        setTableList();
        setLabelDaftar();
    }//GEN-LAST:event_cbSemesterItemStateChanged

    private void tListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tListMouseClicked
        // TODO add your handling code here:
        eKdJadwal.setText(tList.getValueAt(tList.getSelectedRow(), 1).toString());    
        eMakul.setText(tList.getValueAt(tList.getSelectedRow(), 2).toString());
        eDosen.setText(tList.getValueAt(tList.getSelectedRow(), 3).toString());
    }//GEN-LAST:event_tListMouseClicked

    private void tKrsMhsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tKrsMhsMouseClicked
        // TODO add your handling code here:
        idKrs = tKrsMhs.getValueAt(tKrsMhs.getSelectedRow(), 1).toString();
    }//GEN-LAST:event_tKrsMhsMouseClicked

    private void eNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eNamaActionPerformed

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        // TODO add your handling code here:
        krs = new Krs();
        krs.setIdKrs(makeId());
        krs.setIdMhs(Integer.parseInt(eId.getText()));
        krs.setKodeJadwal(eKdJadwal.getText());
        krs.setTahunAjar(eTahun.getText());
        
        if (eNpm.getText().equals("") || eKdJadwal.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Lengkapi data");
        } else {   
            ArrayList<String> list = getListKrsMhs(krs.getIdMhs());
            qry = "INSERT INTO krs VALUES (?, ?, ?, ?)";
            int listSize = list.size();
            int gagal = 0;
            
            try { 
                for (int i = 0; i < listSize; i++) {
                    if (list.get(i).equals(krs.getKodeJadwal()))
                        gagal = 1;
                }
                
                if (gagal == 0) {
                    ps = con.prepareStatement(qry);
                    ps.setString(1, krs.getIdKrs());
                    ps.setInt(2, krs.getIdMhs());
                    ps.setString(3, krs.getKodeJadwal());
                    ps.setString(4, krs.getTahunAjar());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Berhasil menyimpan data", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
                    resetTable("");
                }else 
                   JOptionPane.showMessageDialog(null, "Gagal input, matakuliah ini sudah di input ke krs!", "Gagal input KRS", JOptionPane.ERROR_MESSAGE);  
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal menyimpan data: " + e.getMessage(), "Pemberitahuan", JOptionPane.ERROR_MESSAGE);
                System.err.println("gagal simpan data: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_bSimpanActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        // TODO add your handling code here:
        int pilihan = JOptionPane.showConfirmDialog(
                        null, 
                        "Yakin mau hapus", 
                        "Konfirmasi hapus", 
                        JOptionPane.YES_NO_OPTION);
        if (pilihan == 0) {
            if (idKrs == null) {
                JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus");
            }else {
               try {
                    con = Koneksi.getKoneksi();
                    qry = "DELETE FROM krs WHERE id_krs = ?";
                    PreparedStatement ps = con.prepareStatement(qry);
                    ps.setString(1, idKrs);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Berhasil menghapus data", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
                    resetTable("");
                } catch (SQLException e) {
                   JOptionPane.showMessageDialog(null, "Gagal menghapus data: " + e.getMessage(), "Pemberitahuan", JOptionPane.ERROR_MESSAGE);
                   System.err.println("gagal hapus data: " + e.getMessage());
                } 
            }
        }
    }//GEN-LAST:event_bHapusActionPerformed

    private void bCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCetakActionPerformed
        // TODO add your handling code here:
        try{
            String NamaFile = "../../report/krsReport.jasper";
            con = Koneksi.getKoneksi();
            HashMap param = new HashMap();
            //Mengambil parameter
            param.put("idMhs", mhs.getIdMhs());
                  
            JasperPrint JPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("../../report/krsReport.jasper"), param, con);
            JasperViewer.viewReport(JPrint, false);
        }catch(Exception ex){
            System.out.println(ex);
        }
    }//GEN-LAST:event_bCetakActionPerformed

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
            java.util.logging.Logger.getLogger(KrsMhsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KrsMhsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KrsMhsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KrsMhsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new KrsMhsFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCetak;
    private javax.swing.JLabel bExit;
    private javax.swing.JButton bHapus;
    private javax.swing.JLabel bKembali;
    private javax.swing.JLabel bLogout;
    private javax.swing.JButton bSimpan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbSemester;
    private javax.swing.JTextField eDosen;
    private javax.swing.JTextField eId;
    private javax.swing.JTextField eKdJadwal;
    private javax.swing.JTextField eMakul;
    private javax.swing.JTextField eNama;
    private javax.swing.JTextField eNpm;
    private javax.swing.JTextField eTahun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDaftar;
    private javax.swing.JLabel lblDaftar1;
    private javax.swing.JTable tKrsMhs;
    private javax.swing.JTable tList;
    // End of variables declaration//GEN-END:variables
}
