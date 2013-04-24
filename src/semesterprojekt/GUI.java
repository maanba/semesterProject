package semesterprojekt;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class GUI extends javax.swing.JFrame {

    private DefaultListModel list1 = new DefaultListModel();
    private DefaultListModel list2 = new DefaultListModel();
    private DefaultListModel list3 = new DefaultListModel();
    private DefaultListModel Vareliste = new DefaultListModel();
    private DefaultListModel listTilbudVarer = new DefaultListModel();
    private DefaultListModel listKundeliste = new DefaultListModel();
    private DefaultListModel listHistorik = new DefaultListModel();
    private DefaultListModel listOrdrer = new DefaultListModel();
    private DefaultListModel listReturDele = new DefaultListModel();
    private DefaultListModel listLagerDele = new DefaultListModel();
    private Controller controller = new Controller();

    public GUI() {
        initComponents();
        cellRenderer();

        setTitle("Semesterprojekt");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                controller.releaseConnection();
            }
        });

        jList1.setModel(list1);
        jList2.setModel(list2);
        jList3.setModel(list3);
        jListVareliste.setModel(Vareliste);
        jListKundeliste.setModel(listKundeliste);
        jListHistorik.setModel(listHistorik);
        jListOrdrer.setModel(listOrdrer);
        jListReturDele.setModel(listReturDele);
        jListLagerDele.setModel(listLagerDele);

        jLabelErrorLager.setText("");
        jLabelErrorOrdre.setText("");
        jLabelErrorKunder.setText("");
        jLabelOpretRedigerVare.setText("");


        jLabelErrorOrdre.setText(null);
        update();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButtonTilføj = new javax.swing.JButton();
        jButtonFjern = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldMånedInd = new javax.swing.JTextField();
        jTextFieldDagUd = new javax.swing.JTextField();
        jTextFieldMånedUd = new javax.swing.JTextField();
        jTextFieldÅrInd = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldDagInd = new javax.swing.JTextField();
        jTextFieldÅrUd = new javax.swing.JTextField();
        jButtonGennemførOrdre = new javax.swing.JButton();
        jRadioButtonAfhentning = new javax.swing.JRadioButton();
        jRadioButtonLevering = new javax.swing.JRadioButton();
        jButtonOrdreRediger = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jButtonDepositum = new javax.swing.JButton();
        jButtonFaktura = new javax.swing.JButton();
        jButtonTilbudPdf = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabelErrorOrdre = new javax.swing.JLabel();
        jTextFieldAntal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldRabat = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextFieldTotalPris = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jButtonPakkeliste = new javax.swing.JButton();
        jButtonMontoer = new javax.swing.JButton();
        jButtonStatus = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jButtonOrdrePdf = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jListKundeliste = new javax.swing.JList();
        jButtonRedigerKunde = new javax.swing.JButton();
        jButtonGemKunde = new javax.swing.JButton();
        jLabelOpretRedigerKunde = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldAdresse = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextFieldFirmaNavn = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldFuldeNavn = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldPostnummer = new javax.swing.JTextField();
        jTextFieldTelefonnummer = new javax.swing.JTextField();
        jButtonKundeSøg = new javax.swing.JButton();
        jTextFieldKundeSøg = new javax.swing.JTextField();
        jLabelKundenummer1 = new javax.swing.JLabel();
        jLabelKundenummer2 = new javax.swing.JLabel();
        jLabelErrorKunder = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jListTilbudVarer = new javax.swing.JList();
        jTextField10 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton14 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldTilbudSøg = new javax.swing.JTextField();
        jButtonTilbudSøg = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListReturDele = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        jListOrdrer = new javax.swing.JList();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButtonAfslutOrdre = new javax.swing.JButton();
        jButtonHjemme = new javax.swing.JButton();
        jButtonLevér = new javax.swing.JButton();
        jButtonReturSøg = new javax.swing.JButton();
        jTextFieldReturSøg = new javax.swing.JTextField();
        jLabelErrorRetur = new javax.swing.JLabel();
        jButtonUde = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jListHistorik = new javax.swing.JList();
        jButtonAfslut = new javax.swing.JButton();
        jButtonHistorikFaktura = new javax.swing.JButton();
        jButtonSletOrdre = new javax.swing.JButton();
        jTextFieldHistorikSøg = new javax.swing.JTextField();
        jButtonHistorikSøg = new javax.swing.JButton();
        jButtonTilbud = new javax.swing.JButton();
        jButtonOrdre = new javax.swing.JButton();
        jButtonTilbud1 = new javax.swing.JButton();
        jButtonOrdre1 = new javax.swing.JButton();
        jButtonTilbud3 = new javax.swing.JButton();
        jButtonOrdre3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListVareliste = new javax.swing.JList();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldVareQty = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldNavn = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldVarePris = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        jListLagerDele = new javax.swing.JList();
        jTextFieldPartNavn = new javax.swing.JTextField();
        jTextFieldPartAntal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButtonFjernPart = new javax.swing.JButton();
        jButtonTilføjPart = new javax.swing.JButton();
        jButtonRedigerPart = new javax.swing.JButton();
        jRadioAktiv = new javax.swing.JRadioButton();
        jRadioIkkeAktiv = new javax.swing.JRadioButton();
        jLabelOpretRedigerVare = new javax.swing.JLabel();
        jButtonLagerRediger = new javax.swing.JButton();
        jButtonLagerGem = new javax.swing.JButton();
        jButtonSletVare = new javax.swing.JButton();
        jLabelVarenummer1 = new javax.swing.JLabel();
        jLabelVarenummer2 = new javax.swing.JLabel();
        jTextFieldLagerSøg = new javax.swing.JTextField();
        jButtonLagerSøg = new javax.swing.JButton();
        jLabelErrorLager = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 48, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Varer:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 160, 20));

        jScrollPane1.setViewportView(jList2);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 163, 230));

        jButtonTilføj.setText("Tilføj >");
        jButtonTilføj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTilføjActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonTilføj, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 80, -1));

        jButtonFjern.setText("< Fjern");
        jButtonFjern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFjernActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonFjern, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 80, -1));

        jScrollPane2.setViewportView(jList1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 163, 260));

        jLabel3.setText(" Dato for afhentning/levering:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 20));
        jPanel1.add(jTextFieldMånedInd, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 30, -1));
        jPanel1.add(jTextFieldDagUd, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 30, -1));
        jPanel1.add(jTextFieldMånedUd, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 30, -1));
        jPanel1.add(jTextFieldÅrInd, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 50, -1));

        jLabel4.setText(" Dato for returnering:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, 20));
        jPanel1.add(jTextFieldDagInd, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 30, -1));
        jPanel1.add(jTextFieldÅrUd, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 50, -1));

        jButtonGennemførOrdre.setText("Gennemfør ordre >");
        jButtonGennemførOrdre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGennemførOrdreActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonGennemførOrdre, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, -1, -1));

        buttonGroup1.add(jRadioButtonAfhentning);
        jRadioButtonAfhentning.setText("Afhentning");
        jRadioButtonAfhentning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAfhentningActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButtonAfhentning, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, -1, -1));

        buttonGroup1.add(jRadioButtonLevering);
        jRadioButtonLevering.setText("Levering");
        jRadioButtonLevering.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonLeveringActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButtonLevering, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, -1, -1));

        jButtonOrdreRediger.setText("< Rediger");
        jButtonOrdreRediger.setToolTipText("");
        jButtonOrdreRediger.setMaximumSize(new java.awt.Dimension(125, 23));
        jButtonOrdreRediger.setMinimumSize(new java.awt.Dimension(125, 23));
        jButtonOrdreRediger.setPreferredSize(new java.awt.Dimension(125, 23));
        jButtonOrdreRediger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrdreRedigerActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonOrdreRediger, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 260, -1, -1));

        jScrollPane3.setViewportView(jList3);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, 160, 260));

        jButtonDepositum.setText("Depositum");
        jButtonDepositum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDepositumActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDepositum, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 270, 130, -1));

        jButtonFaktura.setText("Faktura PDF");
        jButtonFaktura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFakturaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonFaktura, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 120, 130, -1));

        jButtonTilbudPdf.setText("Tilbud PDF");
        jButtonTilbudPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTilbudPdfActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonTilbudPdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 180, 130, -1));

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Aktive Ordrer:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, 160, 20));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kunder" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 160, -1));

        jLabelErrorOrdre.setText("jLabelErrorOrdre");
        jPanel1.add(jLabelErrorOrdre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 810, 20));
        jPanel1.add(jTextFieldAntal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 40, -1));

        jLabel17.setText("Antal:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, -1, 20));

        jTextFieldRabat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRabatActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldRabat, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 60, -1));

        jLabel27.setText(" Rabat:");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, -1, -1));

        jLabel28.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Ordre:");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 160, 20));

        jTextFieldTotalPris.setEditable(false);
        jPanel1.add(jTextFieldTotalPris, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 60, -1));

        jLabel30.setText(" Pris før rabat:");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

        jButtonPakkeliste.setText("Pakkeliste");
        jButtonPakkeliste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPakkelisteActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonPakkeliste, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 210, 130, -1));

        jButtonMontoer.setText("Tilføj Montør");
        jButtonMontoer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMontoerActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonMontoer, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 240, 130, -1));

        jButtonStatus.setText("Status");
        jButtonStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStatusActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 300, 130, -1));

        jButton1.setText("Check Dato");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Levering", "08:00", "10:00", "12:00", "14:00", "16:00", "Stor Order" }));
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, -1, -1));

        jButtonOrdrePdf.setText("Ordre PDF");
        jButtonOrdrePdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrdrePdfActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonOrdrePdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 150, 130, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Afhentning", "08:00", "10:00", "12:00", "14:00", "16:00", "Stor Order" }));
        jPanel1.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, -1, -1));

        jTabbedPane1.addTab("Ordre", jPanel1);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane10.setViewportView(jListKundeliste);

        jPanel6.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 180, 360));

        jButtonRedigerKunde.setText("Rediger >");
        jButtonRedigerKunde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRedigerKundeActionPerformed(evt);
            }
        });
        jPanel6.add(jButtonRedigerKunde, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 90, 23));

        jButtonGemKunde.setText("< Gem");
        jButtonGemKunde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGemKundeActionPerformed(evt);
            }
        });
        jPanel6.add(jButtonGemKunde, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 90, 23));

        jLabelOpretRedigerKunde.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabelOpretRedigerKunde.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOpretRedigerKunde.setText("Opret kunde:");
        jPanel6.add(jLabelOpretRedigerKunde, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 350, 20));

        jLabel19.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Kundeliste:");
        jPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 180, 20));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setText("Firmanavn");
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 80, 20));
        jPanel7.add(jTextFieldAdresse, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 190, -1));

        jLabel23.setText("Adresse");
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 60, -1));
        jPanel7.add(jTextFieldFirmaNavn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 190, -1));

        jLabel24.setText("Fulde navn");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 90, -1));
        jPanel7.add(jTextFieldFuldeNavn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 190, -1));

        jLabel25.setText("Telefonnummer");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 100, -1));

        jLabel26.setText("Postnummer");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));
        jPanel7.add(jTextFieldPostnummer, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 190, -1));
        jPanel7.add(jTextFieldTelefonnummer, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 190, -1));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 350, 360));

        jButtonKundeSøg.setText("Søg");
        jButtonKundeSøg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKundeSøgActionPerformed(evt);
            }
        });
        jPanel6.add(jButtonKundeSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, 22));
        jPanel6.add(jTextFieldKundeSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, -1));

        jLabelKundenummer1.setText("jLabelKundenummer1");
        jPanel6.add(jLabelKundenummer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 120, 30));

        jLabelKundenummer2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelKundenummer2.setText("jLabelKundenummer2");
        jPanel6.add(jLabelKundenummer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 160, 30));

        jLabelErrorKunder.setText("jLabelErrorKunder");
        jPanel6.add(jLabelErrorKunder, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, -1, -1));

        jTabbedPane1.addTab("Kunder", jPanel6);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane7.setViewportView(jListTilbudVarer);

        jPanel4.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 210, 360));
        jPanel4.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 80, -1));

        jLabel15.setText("Kroner ");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, -1, 20));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("\nKære kunde... ");
        jScrollPane8.setViewportView(jTextArea1);

        jPanel4.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 210, 360));

        jButton14.setText("PDF");
        jPanel4.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, -1, -1));

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Kommentar:");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 210, -1));

        jLabel29.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Varer:");
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 210, -1));
        jPanel4.add(jTextFieldTilbudSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, -1));

        jButtonTilbudSøg.setText("Søg");
        jButtonTilbudSøg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTilbudSøgActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonTilbudSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, 22));

        jTabbedPane1.addTab("Tilbud", jPanel4);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setViewportView(jListReturDele);

        jPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 170, 330));

        jScrollPane6.setViewportView(jListOrdrer);

        jPanel3.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 170, 330));

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Dele:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 170, -1));

        jLabel14.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Ordrer:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 170, -1));

        jButtonAfslutOrdre.setText("Afslut ordre");
        jButtonAfslutOrdre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAfslutOrdreActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonAfslutOrdre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, -1, -1));

        jButtonHjemme.setText("Hjemme");
        jButtonHjemme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHjemmeActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonHjemme, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 70, -1));

        jButtonLevér.setText("Levér >");
        jButtonLevér.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLevérActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonLevér, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, -1, -1));

        jButtonReturSøg.setText("Søg");
        jButtonReturSøg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturSøgActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonReturSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));
        jPanel3.add(jTextFieldReturSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, -1));
        jPanel3.add(jLabelErrorRetur, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 810, 20));

        jButtonUde.setText("Ude");
        jButtonUde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUdeActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonUde, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 70, -1));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 160, -1, -1));

        jButton3.setText("UDE!");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 280, -1, -1));

        jTabbedPane1.addTab("Retur", jPanel3);
        jTabbedPane1.addTab("Kalender", jPanel8);

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jListHistorik.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane9.setViewportView(jListHistorik);

        jPanel9.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 330, 230));

        jButtonAfslut.setText("Afslut");
        jButtonAfslut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAfslutActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonAfslut, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 80, -1));

        jButtonHistorikFaktura.setText("Faktura");
        jButtonHistorikFaktura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistorikFakturaActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonHistorikFaktura, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 80, -1));

        jButtonSletOrdre.setText("Slet");
        jButtonSletOrdre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSletOrdreActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonSletOrdre, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 80, -1));
        jPanel9.add(jTextFieldHistorikSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, -1));

        jButtonHistorikSøg.setText("Søg");
        jButtonHistorikSøg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistorikSøgActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonHistorikSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, 22));

        jButtonTilbud.setText("Tilbud");
        jButtonTilbud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTilbudActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonTilbud, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 80, -1));

        jButtonOrdre.setText("Ordre");
        jButtonOrdre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrdreActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonOrdre, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 80, -1));

        jButtonTilbud1.setText("Tilbud");
        jButtonTilbud1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTilbudActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonTilbud1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 80, -1));

        jButtonOrdre1.setText("Ordre");
        jButtonOrdre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrdreActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonOrdre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 80, -1));

        jButtonTilbud3.setText("Tilbud");
        jButtonTilbud3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTilbudActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonTilbud3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 80, -1));

        jButtonOrdre3.setText("Ordre");
        jButtonOrdre3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrdreActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonOrdre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 80, -1));

        jTabbedPane1.addTab("Historik", jPanel9);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Vareliste:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 220, 20));

        jScrollPane5.setViewportView(jListVareliste);

        jPanel2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 222, 360));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setText("Navn");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 20));
        jPanel5.add(jTextFieldVareQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 120, -1));

        jLabel11.setText("Antal");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 60, -1));
        jPanel5.add(jTextFieldNavn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 120, -1));

        jLabel12.setText("Pris");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 50, -1));
        jPanel5.add(jTextFieldVarePris, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 120, -1));

        jListLagerDele.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane11.setViewportView(jListLagerDele);

        jPanel5.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 180, 130));
        jPanel5.add(jTextFieldPartNavn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 120, -1));
        jPanel5.add(jTextFieldPartAntal, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 120, -1));

        jLabel7.setText("Navn");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel8.setText("Antal");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jButtonFjernPart.setText("Fjern");
        jButtonFjernPart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFjernPartActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonFjernPart, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 70, -1));

        jButtonTilføjPart.setText("Tilføj");
        jButtonTilføjPart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTilføjPartActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonTilføjPart, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 60, 50));

        jButtonRedigerPart.setText("Rediger");
        jButtonRedigerPart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRedigerPartActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonRedigerPart, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 70, -1));

        buttonGroup2.add(jRadioAktiv);
        jRadioAktiv.setText("Aktiv");
        jPanel5.add(jRadioAktiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        buttonGroup2.add(jRadioIkkeAktiv);
        jRadioIkkeAktiv.setText("Ikke Aktiv");
        jPanel5.add(jRadioIkkeAktiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 290, 360));

        jLabelOpretRedigerVare.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabelOpretRedigerVare.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOpretRedigerVare.setText("Opret vare:");
        jPanel2.add(jLabelOpretRedigerVare, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 290, -1));

        jButtonLagerRediger.setText("Rediger >");
        jButtonLagerRediger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLagerRedigerActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonLagerRediger, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 90, 23));

        jButtonLagerGem.setText("< Gem");
        jButtonLagerGem.setMaximumSize(new java.awt.Dimension(105, 29));
        jButtonLagerGem.setMinimumSize(new java.awt.Dimension(105, 29));
        jButtonLagerGem.setPreferredSize(new java.awt.Dimension(105, 29));
        jButtonLagerGem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLagerGemActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonLagerGem, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 90, 23));

        jButtonSletVare.setText("Slet");
        jButtonSletVare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSletVareActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonSletVare, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 90, 23));

        jLabelVarenummer1.setText("jLabelVarenummer1");
        jPanel2.add(jLabelVarenummer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 100, 30));

        jLabelVarenummer2.setText("jLabelVarenummer2");
        jPanel2.add(jLabelVarenummer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 120, 30));

        jTextFieldLagerSøg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLagerSøgActionPerformed(evt);
            }
        });
        jPanel2.add(jTextFieldLagerSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, -1));

        jButtonLagerSøg.setText("Søg");
        jButtonLagerSøg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLagerSøgActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonLagerSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, 22));

        jLabelErrorLager.setText("jLabelErrorLager");
        jPanel2.add(jLabelErrorLager, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 740, 20));

        jTabbedPane1.addTab("Lager", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        Del del = (Del) jListLagerDele.getSelectedValue();
        int si = jListLagerDele.getSelectedIndex();
        listLagerDele.removeElementAt(si);
        jTextFieldPartNavn.setText(del.getTitel());
        jTextFieldPartAntal.setText("" + del.getAntal());
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButtonLagerSøgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLagerSøgActionPerformed
        if (!jTextFieldLagerSøg.getText().equals("")) {
            int counter = 0;
            ArrayList<Vare> vl = new ArrayList<>();
            for (int i = 0; i < Vareliste.size(); i++) {
                vl.add((Vare) Vareliste.getElementAt(i));
            }
            for (int i = 0; i < vl.size(); i++) {
                String vnummer = vl.get(i).getVnummer() + "";
                String vnavn = vl.get(i).getVnavn().toLowerCase();
                if (vnavn.contains(jTextFieldLagerSøg.getText().toLowerCase())) {
                    jListVareliste.setSelectedIndex(i);
                } else if (vnummer.contains(jTextFieldLagerSøg.getText())) {
                    jListVareliste.setSelectedIndex(i);
                } else {
                    Vareliste.removeElementAt(i - counter);
                    counter++;
                }
            }
            if (Vareliste.isEmpty() == true) {
                update();
            }
        } else {
            update();
        }
        jLabelErrorLager.setText("");
    }//GEN-LAST:event_jButtonLagerSøgActionPerformed

    private void jTextFieldLagerSøgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLagerSøgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLagerSøgActionPerformed

    private void jButtonSletVareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSletVareActionPerformed
        Vare selected = (Vare) jListVareliste.getSelectedValue();
        if (selected != null) {
            controller.deleteVare(selected);
            update();
        }
        jLabelErrorLager.setText("");
    }//GEN-LAST:event_jButtonSletVareActionPerformed

    private void jButtonLagerGemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLagerGemActionPerformed
        int vnummer;
        int aktiv;
        String vnavn = jTextFieldNavn.getText();
        double pris = Double.parseDouble(jTextFieldVarePris.getText());
        int qty = Integer.parseInt(jTextFieldVareQty.getText());

        if (controller.getRediger() == true) {
            vnummer = Integer.parseInt(jLabelVarenummer2.getText());
            if (jRadioAktiv.isSelected()) {
                aktiv = 1;
            } else {
                aktiv = 0;
            }
            Vare vare = new Vare(vnummer, vnavn, qty, pris, aktiv);
            for (int i = 0; i < listLagerDele.size(); i++) {
                vare.addDel((Del) listLagerDele.getElementAt(i));
            }
            controller.updateVare(vare);
            jLabelErrorLager.setText("");
        } else {
            vnummer = controller.getNextVnummer();
            if (jRadioAktiv.isSelected()) {
                aktiv = 1;
            } else {
                aktiv = 0;
            }
            Vare vare = new Vare(vnummer, vnavn, qty, pris, aktiv);
            for (int i = 0; i < listLagerDele.size(); i++) {
                vare.addDel((Del) listLagerDele.getElementAt(i));
            }
            controller.addVare(vare);
            jLabelErrorLager.setText("");
        }
        update();
        controller.redigerFalse();
    }//GEN-LAST:event_jButtonLagerGemActionPerformed

    private void jButtonLagerRedigerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLagerRedigerActionPerformed
        Vare selected = (Vare) jListVareliste.getSelectedValue();
        if (controller.getRediger() == true) {
            jLabelErrorLager.setText("You need to finish editing your stock before editing a new one.");
        }
        if (controller.getRediger() == false && selected != null) {

            listReturDele.removeElement(selected);
            listLagerDele.clear();
            jLabelOpretRedigerVare.setText("Redigerer i vare:");
            jLabelVarenummer1.setText("Varenummer:");
            jLabelVarenummer2.setText(selected.getVnummer() + "");
            jTextFieldNavn.setText(selected.getVnavn());
            jTextFieldVarePris.setText(selected.getPris() + "");
            jTextFieldVareQty.setText(selected.getQty() + "");
            if (selected.getAktiv() == 1) {
                jRadioAktiv.setSelected(true);
            } else {
                jRadioIkkeAktiv.setSelected(false);
            }
            for (int i = 0; i < selected.getDel().size(); i++) {
                listLagerDele.addElement(selected.getDel().get(i));
            }
            controller.redigerTrue();
            jLabelErrorLager.setText("");
        }
    }//GEN-LAST:event_jButtonLagerRedigerActionPerformed

    private void jButtonRedigerPartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRedigerPartActionPerformed
        Object selected = (Del) jListLagerDele.getSelectedValue();
        if (!jTextFieldPartNavn.getText().isEmpty() || !jTextFieldPartAntal.getText().isEmpty()) {
            jLabelErrorLager.setText("You need to finish editing a part before editing a new one.");
        } else if (selected != null) {
            Del del = (Del) jListLagerDele.getSelectedValue();
            listLagerDele.removeElementAt(jListLagerDele.getSelectedIndex());
            jTextFieldPartNavn.setText(del.getTitel());
            jTextFieldPartAntal.setText("" + del.getAntal());
        }
    }//GEN-LAST:event_jButtonRedigerPartActionPerformed

    private void jButtonTilføjPartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTilføjPartActionPerformed
        if (!jTextFieldPartNavn.getText().isEmpty() && !jTextFieldPartAntal.getText().isEmpty()) {
            int vnummer = controller.getNextVnummer();
            String delnavn = jTextFieldPartNavn.getText();
            int delantal = Integer.parseInt(jTextFieldPartAntal.getText());
            listLagerDele.addElement(new Del(vnummer, delnavn, delantal));
            jTextFieldPartNavn.setText("");
            jTextFieldPartAntal.setText("");
        }
    }//GEN-LAST:event_jButtonTilføjPartActionPerformed

    private void jButtonFjernPartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFjernPartActionPerformed
        Object selected = (Del) jListLagerDele.getSelectedValue();
        listLagerDele.removeElement(selected);
        jLabelErrorLager.setText("");
    }//GEN-LAST:event_jButtonFjernPartActionPerformed

    private void jButtonOrdreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrdreActionPerformed
        Ordre selected = (Ordre) jListHistorik.getSelectedValue();
        controller.bekraeftOrdre(selected.getOnummer());
        update();
    }//GEN-LAST:event_jButtonOrdreActionPerformed

    private void jButtonTilbudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTilbudActionPerformed
        Ordre selected = (Ordre) jListHistorik.getSelectedValue();
        controller.ordreTilbud(selected.getOnummer());
        update();
    }//GEN-LAST:event_jButtonTilbudActionPerformed

    private void jButtonHistorikSøgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistorikSøgActionPerformed
        if (!jTextFieldHistorikSøg.getText().equals("")) {
            int counter = 0;
            ArrayList<Ordre> ol = new ArrayList<>();
            for (int i = 0; i < listHistorik.size(); i++) {
                ol.add((Ordre) listHistorik.getElementAt(i));
            }
            for (int i = 0; i < ol.size(); i++) {
                String onummer = ol.get(i).getOnummer() + "";
                String knummer = ol.get(i).getKnummer() + "";
                String status = ol.get(i).getStatus().toLowerCase();
                Kunde kunde = controller.getKunde(ol.get(i).getKnummer());
                String knavn = kunde.getNavn().toLowerCase();
                if (onummer.contains(jTextFieldHistorikSøg.getText())) {
                    jListHistorik.setSelectedIndex(i);
                } else if (status.contains(jTextFieldHistorikSøg.getText().toLowerCase())) {
                    jListHistorik.setSelectedIndex(i);
                } else if (knummer.contains(jTextFieldHistorikSøg.getText())) {
                    jListHistorik.setSelectedIndex(i);
                } else if (knavn.contains(jTextFieldHistorikSøg.getText().toLowerCase())) {
                    jListHistorik.setSelectedIndex(i);
                } else {
                    listHistorik.removeElementAt(i - counter);
                    counter++;
                }
            }
            if (listHistorik.isEmpty() == true) {
                update();
            }
        } else {
            update();
        }
    }//GEN-LAST:event_jButtonHistorikSøgActionPerformed

    private void jButtonSletOrdreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSletOrdreActionPerformed
        if (!jListHistorik.isSelectionEmpty()) {
            Ordre ordre = (Ordre) jListHistorik.getSelectedValue();
            controller.deleteOrder(ordre);
            update();
        }
    }//GEN-LAST:event_jButtonSletOrdreActionPerformed

    private void jButtonHistorikFakturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistorikFakturaActionPerformed
        Ordre selected = (Ordre) jListHistorik.getSelectedValue();
        controller.ordreFaktureret(selected.getOnummer());
        update();
    }//GEN-LAST:event_jButtonHistorikFakturaActionPerformed

    private void jButtonAfslutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAfslutActionPerformed
        Ordre selected = (Ordre) jListHistorik.getSelectedValue();
        controller.ordreAfslut(selected.getKnummer());
        update();
    }//GEN-LAST:event_jButtonAfslutActionPerformed

    private void jButtonReturSøgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturSøgActionPerformed
        if (!jTextFieldReturSøg.getText().equals("")) {
            int counter = 0;
            ArrayList<Ordre> ol = new ArrayList<>();

            for (int i = 0; i < listOrdrer.size(); i++) {
                ol.add((Ordre) listOrdrer.getElementAt(i));
            }
            for (int i = 0; i < ol.size(); i++) {
                String onummer = ol.get(i).getOnummer() + "";
                String knummer = ol.get(i).getKnummer() + "";
                String status = ol.get(i).getStatus().toLowerCase();
                Kunde kunde = controller.getKunde(ol.get(i).getKnummer());
                String firma = null;
                String knavn = null;
                if (kunde.getFirma() != null) {
                    firma = kunde.getFirma().toLowerCase();
                }
                if (kunde.getNavn() != null) {
                    knavn = kunde.getNavn().toLowerCase();
                }
                if (onummer.contains(jTextFieldReturSøg.getText())) {
                    jListOrdrer.setSelectedIndex(i);
                } else if (knummer.contains(jTextFieldReturSøg.getText())) {
                    jListOrdrer.setSelectedIndex(i);
                } else if (status.contains(jTextFieldReturSøg.getText().toLowerCase())) {
                    jListOrdrer.setSelectedIndex(i);
                } else if (knavn != null && knavn.contains(jTextFieldReturSøg.getText().toLowerCase())) {
                    jListOrdrer.setSelectedIndex(i);
                } else if (firma != null && firma.contains(jTextFieldReturSøg.getText().toLowerCase())) {
                    jListOrdrer.setSelectedIndex(i);
                } else {
                    listOrdrer.removeElementAt(i - counter);
                    counter++;
                }
            }
            if (listOrdrer.isEmpty() == true) {
                update();
            }
        } else {
            update();
        }
    }//GEN-LAST:event_jButtonReturSøgActionPerformed

    private void jButtonLevérActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLevérActionPerformed
        listReturDele.clear();
        Ordre selected = (Ordre) jListOrdrer.getSelectedValue();
        ArrayList<Odetaljer> od = selected.getOd();
        for (int i = 0; i < od.size(); i++) {
            Vare vare = controller.getVare(od.get(i).getVnummer());
            for (int j = 0; j < vare.getDel().size(); j++) {
                listReturDele.addElement((Del) vare.getDel().get(j));
            }
        }
    }//GEN-LAST:event_jButtonLevérActionPerformed

    private void jButtonHjemmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHjemmeActionPerformed
        Del selectedDel = (Del) jListReturDele.getSelectedValue();
        Ordre selectedOrdre = (Ordre) jListOrdrer.getSelectedValue();
        selectedDel.getVnummer();
      
        
        
        listReturDele.clear();
        Ordre selected = (Ordre) jListOrdrer.getSelectedValue();
        ArrayList<Odetaljer> od = selected.getOd();
        for (int i = 0; i < od.size(); i++) {
            Vare vare = controller.getVare(od.get(i).getVnummer());
            for (int j = 0; j < vare.getDel().size(); j++) {
                listReturDele.addElement((Del) vare.getDel().get(j));
            }
        }
    }//GEN-LAST:event_jButtonHjemmeActionPerformed

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        Del selected = (Del) jListLagerDele.getSelectedValue();

    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jButtonAfslutOrdreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAfslutOrdreActionPerformed
        Ordre selected = (Ordre) jListOrdrer.getSelectedValue();
        controller.ordreAfslut(selected.getKnummer());
        update();
    }//GEN-LAST:event_jButtonAfslutOrdreActionPerformed

    private void jButtonTilbudSøgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTilbudSøgActionPerformed
        if (!jTextFieldTilbudSøg.getText().equals("")) {
            int counter = 0;
            ArrayList<Vare> vl = new ArrayList<>();

            for (int i = 0; i < listTilbudVarer.size(); i++) {
                vl.add((Vare) listTilbudVarer.getElementAt(i));
            }
            for (int i = 0; i < vl.size(); i++) {
                String vnavn = vl.get(i).getVnavn().toLowerCase();
                String vnummer = vl.get(i).getVnummer() + "";
                if (vnavn.contains(jTextFieldTilbudSøg.getText().toLowerCase())) {
                    jListTilbudVarer.setSelectedIndex(i);
                } else if (vnummer.contains(jTextFieldTilbudSøg.getText())) {
                    jListTilbudVarer.setSelectedIndex(i);
                } else {
                    listTilbudVarer.removeElementAt(i - counter);
                    counter++;
                }
            }
            if (listTilbudVarer.isEmpty() == true) {
                update();
            }
        } else {
            update();
        }
    }//GEN-LAST:event_jButtonTilbudSøgActionPerformed

    private void jButtonKundeSøgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKundeSøgActionPerformed
        if (!jTextFieldKundeSøg.getText().equals("")) {
            ArrayList<Kunde> kl = new ArrayList<>();
            int counter = 0;
            for (int i = 0; i < listKundeliste.size(); i++) {
                kl.add((Kunde) listKundeliste.getElementAt(i));
            }
            for (int i = 0; i < kl.size(); i++) {
                if (controller.kundeSøg(kl.get(i), jTextFieldKundeSøg.getText()) == true) {
                    jListKundeliste.setSelectedIndex(i);
                } else {
                    listKundeliste.removeElementAt(i - counter);
                    counter++;
                }
            }
            if (listKundeliste.isEmpty() == true) {
                update();
            }
        } else {
            update();
        }
    }//GEN-LAST:event_jButtonKundeSøgActionPerformed
    private void jButtonGemKundeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGemKundeActionPerformed
        String firma = jTextFieldFirmaNavn.getText();
        String navn = jTextFieldFuldeNavn.getText();
        String adresse = jTextFieldAdresse.getText();
        int postnummer = Integer.parseInt(jTextFieldPostnummer.getText());
        int telefonnummer = Integer.parseInt(jTextFieldTelefonnummer.getText());

        if (jTextFieldFuldeNavn.getText().isEmpty() || jTextFieldAdresse.getText().isEmpty()
                || jTextFieldPostnummer.getText().isEmpty() || jTextFieldTelefonnummer.getText().isEmpty()) {
            jLabelErrorKunder.setText("You need to fill in all the fields before adding a new customer.");
        } else if (controller.getRediger() == false) {
            controller.addNewKunde(firma, navn, adresse, postnummer, telefonnummer);
            jLabelErrorKunder.setText("");
            update();
        } else if (controller.getRediger() == true) {
            int knummer = Integer.parseInt(jLabelKundenummer2.getText());
            Kunde kunde = new Kunde(knummer, firma, navn, adresse, postnummer, telefonnummer);
            controller.updateKunde(kunde);
            jLabelErrorKunder.setText("");
            update();
            controller.redigerFalse();
        }
    }//GEN-LAST:event_jButtonGemKundeActionPerformed

    private void jButtonRedigerKundeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRedigerKundeActionPerformed
        Kunde selected = (Kunde) jListKundeliste.getSelectedValue();

        if (controller.getRediger() == false) {
            jLabelOpretRedigerKunde.setText("Redigerer i kunde:");
            jLabelKundenummer1.setText("Kundenummer:");
            jLabelKundenummer2.setText(selected.getKnummer() + "");
            if (jTextFieldFirmaNavn == null) {
                jTextFieldFirmaNavn.setText("");
            } else {
                jTextFieldFirmaNavn.setText(selected.getFirma());
            }
            jTextFieldFuldeNavn.setText(selected.getNavn());
            jTextFieldAdresse.setText(selected.getAdresse() + "");
            jTextFieldPostnummer.setText(selected.getPostnummer() + "");
            jTextFieldTelefonnummer.setText(selected.getTelefonnummer() + "");
            controller.redigerTrue();
        } else {
            jLabelErrorKunder.setText("You need to finish editing the current customer before editing a new one.");
        }
    }//GEN-LAST:event_jButtonRedigerKundeActionPerformed

    private void jButtonOrdrePdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrdrePdfActionPerformed
        Ordre selected = (Ordre) jList3.getSelectedValue();
        int selectedIndex = jList3.getSelectedIndex();
        controller.bekraeftOrdre(selected.getOnummer());
        controller.setSelectedOrdre(selected);
        update();
        selected = (Ordre) list3.getElementAt(selectedIndex);
        controller.setCurrentOrder(selected);
        controller.pdfOrdre();
    }//GEN-LAST:event_jButtonOrdrePdfActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTextFieldDagUd.getText().isEmpty() || jTextFieldMånedUd.getText().isEmpty() || jTextFieldÅrUd.getText().isEmpty() || jTextFieldDagInd.getText().isEmpty() || jTextFieldMånedInd.getText().isEmpty() || jTextFieldÅrInd.getText().isEmpty()) {
            jLabelErrorOrdre.setText("Fejl. Indtast datoer korrekt");
        } else {
            update();
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStatusActionPerformed
        Ordre selected = (Ordre) jList3.getSelectedValue();
        if (selected == null) {
            jLabelErrorOrdre.setText("Vælg en ordre før du trykker status!");
        } else {
            JOptionPane.showMessageDialog(rootPane, selected.getStatus(), "Status for ordre: " + selected.getOnummer(), WIDTH);
        }
    }//GEN-LAST:event_jButtonStatusActionPerformed

    private void jButtonMontoerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMontoerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonMontoerActionPerformed

    private void jButtonPakkelisteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPakkelisteActionPerformed
        Ordre selected = (Ordre) jList3.getSelectedValue();
        int selectedIndex = jList3.getSelectedIndex();
        controller.setSelectedOrdre(selected);
        if (selected.getFnummer() == 0) {
            controller.addOrderFakturaNummer(selected);
        }
        controller.ordreFaktureret(selected.getOnummer());
        update();
        selected = (Ordre) list3.getElementAt(selectedIndex);
        controller.setCurrentOrder(selected);
        controller.pdfPakkeliste();
    }//GEN-LAST:event_jButtonPakkelisteActionPerformed

    private void jTextFieldRabatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRabatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRabatActionPerformed

    private void jButtonTilbudPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTilbudPdfActionPerformed
        Ordre selected = (Ordre) jList3.getSelectedValue();
        int selectedIndex = jList3.getSelectedIndex();
        controller.ordreTilbud(selected.getOnummer());
        controller.setSelectedOrdre(selected);
        update();
        selected = (Ordre) list3.getElementAt(selectedIndex);
        controller.setCurrentOrder(selected);
        controller.pdfTilbud();
    }//GEN-LAST:event_jButtonTilbudPdfActionPerformed

    private void jButtonFakturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFakturaActionPerformed
        Ordre selected = (Ordre) jList3.getSelectedValue();
        int selectedIndex = jList3.getSelectedIndex();
        controller.setSelectedOrdre(selected);
        if (selected.getFnummer() == 0) {
            controller.addOrderFakturaNummer(selected);
        }
        controller.ordreFaktureret(selected.getOnummer());
        update();
        selected = (Ordre) list3.getElementAt(selectedIndex);
        controller.setCurrentOrder(selected);
        controller.pdfFaktura();
    }//GEN-LAST:event_jButtonFakturaActionPerformed

    private void jButtonDepositumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDepositumActionPerformed
        Ordre selected = (Ordre) jList3.getSelectedValue();
        double depositum = Double.parseDouble(JOptionPane.showInputDialog("Her kan Erling indtaste depositum: "));
        selected.setDepositum(depositum);
        System.out.println("Depositum: " + selected.getDepositum());
        controller.setCurrentOrder(selected);
        controller.updateOrder(selected.getKnummer(), selected.getPris(), selected.getRabat(), selected.getDepositum(), selected.getTidLev(), selected.getTidRet(), selected.getAfhentning(), selected.getStatus(), selected.getLevering(), selected.getReturnering(), selected.getOd());
    }//GEN-LAST:event_jButtonDepositumActionPerformed

    private void jButtonOrdreRedigerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrdreRedigerActionPerformed
        Ordre selected = (Ordre) jList3.getSelectedValue();
        if (list2.isEmpty() == false) {
            jLabelErrorOrdre.setText("You need to finish editing the current group before editing a new one.");
        }
        if (selected != null && list2.isEmpty() == true) {
            ArrayList<Odetaljer> od = selected.getOd();

            for (int i = 0; i < od.size(); i++) {
                Vare vare = controller.getVare(od.get(i).getVnummer());
                vare.setQty(od.get(i).getMaengde());
                list2.addElement(vare);
            }
            jTextFieldDagUd.setText(selected.getLevering().substring(0, 2));
            jTextFieldMånedUd.setText(selected.getLevering().substring(3, 5));
            jTextFieldÅrUd.setText(selected.getLevering().substring(6, 10));
            jTextFieldDagInd.setText(selected.getReturnering().substring(0, 2));
            jTextFieldMånedInd.setText(selected.getReturnering().substring(3, 5));
            jTextFieldÅrInd.setText(selected.getReturnering().substring(6, 10));
            jTextFieldRabat.setText(selected.getRabat() + "");

            if ("Leveres af os".equals(selected.getAfhentning())) {
                jRadioButtonLevering.setSelected(true);
            } else {
                jRadioButtonAfhentning.setSelected(true);
            }

            jTextFieldTotalPris.setText(selected.getPris() + "");
            controller.setCurrentOrder(selected);
            controller.redigerTrue();
        }
        update();

        Kunde kunde = controller.getKunde(selected.getKnummer());

        for (int i = 0; i < jComboBox1.getItemCount(); i++) {
            if (jComboBox1.getItemAt(i).equals(kunde.getNavn())) {
                jComboBox1.setSelectedIndex(i);
            }
        }
    }//GEN-LAST:event_jButtonOrdreRedigerActionPerformed

    private void jRadioButtonLeveringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonLeveringActionPerformed
        jComboBox2.setEnabled(true);
        jComboBox3.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonLeveringActionPerformed

    private void jRadioButtonAfhentningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAfhentningActionPerformed
        jComboBox2.setEnabled(false);
        jComboBox3.setEnabled(false);
    }//GEN-LAST:event_jRadioButtonAfhentningActionPerformed

    private void jButtonGennemførOrdreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGennemførOrdreActionPerformed
        ArrayList<Odetaljer> odetaljer = new ArrayList<>();
        if (jComboBox1.getSelectedItem() != "Kunder") {
            if (list2.isEmpty() == false) {
                if (jRadioButtonAfhentning.isSelected() || jRadioButtonLevering.isSelected()) {
                    for (int i = 0; i < list2.size(); i++) {
                        Vare vare = (Vare) list2.getElementAt(i);
                        odetaljer.add(new Odetaljer(0, vare.getVnummer(), vare.getQty(), 1));
                    }
                    String leveringDate = Integer.parseInt(jTextFieldDagUd.getText()) + "-" + Integer.parseInt(jTextFieldMånedUd.getText()) + "-" + Integer.parseInt(jTextFieldÅrUd.getText());
                    String returneringDate = Integer.parseInt(jTextFieldDagInd.getText()) + "-" + Integer.parseInt(jTextFieldMånedInd.getText()) + "-" + Integer.parseInt(jTextFieldÅrInd.getText());
                    controller.gennemførOrdrer(jRadioButtonAfhentning.isSelected(), "" + jComboBox1.getSelectedItem(), Double.parseDouble(jTextFieldTotalPris.getText()), Double.parseDouble(jTextFieldRabat.getText()), Double.parseDouble(jTextFieldTotalPris.getText()) * 0.25, "" + jComboBox2.getSelectedItem(), "" + jComboBox3.getSelectedItem(), leveringDate, returneringDate, odetaljer);
                    list2.clear();
                    list3.clear();
                    jLabelErrorOrdre.setText("");
                    jTextFieldTotalPris.setText("");
                    jTextFieldRabat.setText("");
                    jTextFieldDagInd.setText("");
                    jTextFieldDagUd.setText("");
                    jTextFieldMånedInd.setText("");
                    jTextFieldMånedUd.setText("");
                    jTextFieldÅrInd.setText("");
                    jTextFieldÅrUd.setText("");
                    controller.redigerFalse();
                }
            }
        } else {
            jLabelErrorOrdre.setText("Vælg en Kunde");
        }
        controller.redigerFalse();
        update();
    }//GEN-LAST:event_jButtonGennemførOrdreActionPerformed

    private void jButtonFjernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFjernActionPerformed
        Vare selected = (Vare) jList2.getSelectedValue();
        if (selected != null && selected.getQty() >= Integer.parseInt(jTextFieldAntal.getText())) {
            list2.removeElement(selected);
            //controller.undoQty(selected.getVnavn(), Integer.parseInt(jTextFieldAntal.getText()));
            selected.setQty(selected.getQty() - (Integer.parseInt(jTextFieldAntal.getText())));
            list2.addElement(selected);
            if (selected.getQty() == 0) {
                list2.removeElement(selected);
            }
        } else {
            jLabelErrorOrdre.setText("FEJL!");
        }
        update();
    }//GEN-LAST:event_jButtonFjernActionPerformed

    private void jButtonTilføjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTilføjActionPerformed
        boolean check = false;
        Vare selected = (Vare) jList1.getSelectedValue();
        selected.setQty(Integer.parseInt(jTextFieldAntal.getText()));
        if (selected != null && controller.checkQty(selected.getVnummer(), Integer.parseInt(jTextFieldAntal.getText()))) {
            for (int i = 0; i < list2.size(); i++) {
                Vare vare = (Vare) list2.getElementAt(i);
                if (vare.getVnummer() == selected.getVnummer()) {
                    vare.setQty(vare.getQty() + Integer.parseInt(jTextFieldAntal.getText()));
                    list2.addElement(vare);
                    list2.removeElementAt(i);
                    check = true;
                    break;
                }
            }
            if (check == false) {
                list2.addElement(selected);
            }
        } else {
            jLabelErrorOrdre.setText("FEJL!");
        }
        update();
    }//GEN-LAST:event_jButtonTilføjActionPerformed

    private void jButtonUdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUdeActionPerformed
        Del selectedDel = (Del) jListReturDele.getSelectedValue();

        
        
        listReturDele.clear();
        Ordre selected = (Ordre) jListOrdrer.getSelectedValue();
        ArrayList<Odetaljer> od = selected.getOd();
        for (int i = 0; i < od.size(); i++) {
            Vare vare = controller.getVare(od.get(i).getVnummer());
            for (int j = 0; j < vare.getDel().size(); j++) {
                listReturDele.addElement((Del) vare.getDel().get(j));
            }
        }
    }//GEN-LAST:event_jButtonUdeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                Ordre selectedOrdre = (Ordre) jListOrdrer.getSelectedValue();
                for (int i = 0; i < selectedOrdre.getOd().size(); i++) {
            System.out.println(selectedOrdre.getOd().get(i));
        }
        
        
       
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                        Del selectedDel = (Del) jListReturDele.getSelectedValue();
                   
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonLeverActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel. For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;




                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    public void cellRenderer() {
        jList1.setCellRenderer(new DefaultListCellRenderer() { // Setting the DefaultListCellRenderer
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                Vare vare = (Vare) value;  // Using value we are getting the object in JList
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setText(vare.toString());  // Setting the text
                return label;
            }
        });
        jList2.setCellRenderer(new DefaultListCellRenderer() { // Setting the DefaultListCellRenderer
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                Vare vare = (Vare) value;  // Using value we are getting the object in JList
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setText(vare.toString());  // Setting the text
                return label;
            }
        });
        jList3.setCellRenderer(new DefaultListCellRenderer() { // Setting the DefaultListCellRenderer
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                Ordre ordre = (Ordre) value;  // Using value we are getting the object in JList
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setText("" + ordre.getOnummer());  // Setting the text
                return label;
            }
        });
        jListReturDele.setCellRenderer(new DefaultListCellRenderer() { // Setting the DefaultListCellRenderer
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                Del del = (Del) value;  // Using value we are getting the object in JList
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setText(del.toString());  // Setting the text
                return label;
            }
        });
    }

    public void update() {
        list1.clear();
        list3.clear();

        listHistorik.clear();
        listOrdrer.clear();
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Kunder");

        // list3 & listHistorik & listOrdrer:
        ArrayList<Ordre> ol = controller.getAllOrdres();
        Ordre[] oa = new Ordre[ol.size()];
        for (int i = 0; i < ol.size(); i++) {
            oa[i] = ol.get(i);
        }
        controller.quickSortOrdre(oa, 0, oa.length - 1);
        // controller.quickSortOrdre(oa, 0, oa.length - 1);
        for (int i = 0; i < oa.length; i++) {

            if (!"Afsluttet".equals(oa[i].getStatus())) {
                list3.addElement(oa[i]);
                listHistorik.addElement(oa[i]);
                listOrdrer.addElement(oa[i]);
            } else if ("Afsluttet".equals(oa[i].getStatus())) {
                listHistorik.addElement(oa[i]);
            }
        }

        // jComboBox1:
        ArrayList<Kunde> kl = controller.getAllCostumers();
        Kunde[] ka = new Kunde[kl.size()];
        for (int i = 0; i < kl.size(); i++) {
            ka[i] = kl.get(i);
        }
        controller.quickSortKunde(ka, 0, ka.length - 1);

        for (int i = 0; i < ka.length; i++) {
            jComboBox1.addItem(ka[i].getNavn());
        }

        // list2:
        ArrayList<Vare> vl2 = new ArrayList<>();
        if (list2.isEmpty() == false) {
            for (int i = 0; i < list2.size(); i++) {
                vl2.add((Vare) list2.get(i));
            }
            list2.clear();
            Vare[] va1 = new Vare[vl2.size()];
            for (int i = 0; i < vl2.size(); i++) {
                va1[i] = vl2.get(i);
            }
            controller.quickSortVare(va1, 0, va1.length - 1);

            for (int i = 0; i < va1.length; i++) {
                list2.addElement(va1[i]);
            }
            if (list2.isEmpty() == false) {
                double totalpris = 0;
                for (int i = 0; i < list2.size(); i++) {
                    Vare vare = (Vare) list2.getElementAt(i);
                    totalpris += vare.getPris() * vare.getQty();
                }
                jTextFieldTotalPris.setText(totalpris + "");
            }
        }

        // list1:
        if (!"".equals(jTextFieldÅrUd.getText()) && !"".equals(jTextFieldDagUd.getText()) && !"".equals(jTextFieldMånedUd.getText()) && !"".equals(jTextFieldÅrInd.getText()) && !"".equals(jTextFieldDagInd.getText()) && !"".equals(jTextFieldMånedInd.getText())) {
            Vare[] va;
            ArrayList<Ordre> l3 = new ArrayList();
            ArrayList<Vare> l2 = new ArrayList();
            for (int i = 0; i < list3.size(); i++) {
                l3.add((Ordre) list3.getElementAt(i));
            }
            for (int i = 0; i < list2.size(); i++) {
                l2.add((Vare) list2.getElementAt(i));
            }
            va = controller.updateList1(Integer.parseInt(jTextFieldÅrUd.getText()), Integer.parseInt(jTextFieldMånedUd.getText()), Integer.parseInt(jTextFieldDagUd.getText()), Integer.parseInt(jTextFieldÅrInd.getText()), Integer.parseInt(jTextFieldMånedInd.getText()), Integer.parseInt(jTextFieldDagInd.getText()), l3, l2);
            controller.quickSortVare(va, 0, va.length - 1);

            for (int i = 0; i < va.length; i++) {
                if (va[i].getAktiv() == 1) {
                    list1.addElement(va[i]);
                }
            }
        }

        //jComboBox2 & jComboBox3
        int[] tidLev = new int[5];
        int[] tidRet = new int[5];
        jComboBox2.removeAllItems();
        jComboBox2.addItem("Levering");
        jComboBox2.addItem("08:00");
        jComboBox2.addItem("10:00");
        jComboBox2.addItem("12:00");
        jComboBox2.addItem("14:00");
        jComboBox2.addItem("16:00");
        jComboBox2.addItem("Stor Order");
        jComboBox3.removeAllItems();
        jComboBox3.addItem("Afhentning");
        jComboBox3.addItem("08:00");
        jComboBox3.addItem("10:00");
        jComboBox3.addItem("12:00");
        jComboBox3.addItem("14:00");
        jComboBox3.addItem("16:00");
        jComboBox3.addItem("Stor Order");
        if (!"".equals(jTextFieldÅrUd.getText()) && !"".equals(jTextFieldDagUd.getText()) && !"".equals(jTextFieldMånedUd.getText()) && !"".equals(jTextFieldÅrInd.getText()) && !"".equals(jTextFieldDagInd.getText()) && !"".equals(jTextFieldMånedInd.getText())) {
            int levYear = Integer.parseInt(jTextFieldÅrUd.getText());
            int levMonth = Integer.parseInt(jTextFieldMånedUd.getText());
            int levDay = Integer.parseInt(jTextFieldDagUd.getText());
            int retYear = Integer.parseInt(jTextFieldÅrInd.getText());
            int retMonth = Integer.parseInt(jTextFieldMånedInd.getText());
            int retDay = Integer.parseInt(jTextFieldDagInd.getText());
            for (int j = 0; j < list3.size(); j++) {
                Ordre o = (Ordre) list3.getElementAt(j);
                int oLevYear = Integer.parseInt(o.getLevering().substring(6, 10));
                int oLevMonth = Integer.parseInt(o.getLevering().substring(3, 5));
                int oLevDay = Integer.parseInt(o.getLevering().substring(0, 2));
                int oRetYear = Integer.parseInt(o.getReturnering().substring(6, 10));
                int oRetMonth = Integer.parseInt(o.getReturnering().substring(3, 5));
                int oRetDay = Integer.parseInt(o.getReturnering().substring(0, 2));
                if (!controller.getRediger()) {
                    if (o.getAfhentning().equalsIgnoreCase("Leveres af os")) {
                        if ((levDay == oLevDay && levMonth == oLevMonth && levYear == oLevYear) || (levDay == oRetDay && levMonth == oRetMonth && levYear == oRetYear)) {
                            for (int l = 1; l < jComboBox2.getItemCount(); l++) {
                                if (o.getTidLev().equals("" + jComboBox2.getItemAt(l))) {
                                    tidLev[l - 1]++;
                                    if (tidLev[l - 1] >= 2) {
                                        jComboBox2.removeItemAt(l);
                                    }
                                    try {
                                        jComboBox2.removeItemAt(6);
                                    } catch (Exception ex) {
                                    }
                                } else if (o.getTidLev().equals("Stor Order")) {
                                    jComboBox2.removeAllItems();
                                    jComboBox2.addItem("Levering");
                                }
                            }
                        }
                        if ((retDay == oRetDay && retMonth == oRetMonth && retYear == oRetYear) || (retDay == oLevDay && retMonth == oLevMonth && retYear == oLevYear)) {
                            for (int l = 1; l < jComboBox3.getItemCount(); l++) {
                                if (o.getTidRet().equals("" + jComboBox3.getItemAt(l))) {
                                    tidRet[l - 1]++;
                                    if (tidRet[l - 1] >= 2) {
                                        jComboBox3.removeItemAt(l);
                                    }
                                    try {
                                        jComboBox3.removeItemAt(6);
                                    } catch (Exception ex) {
                                    }
                                } else if (o.getTidRet().equals("Stor Order")) {
                                    jComboBox3.removeAllItems();
                                    jComboBox3.addItem("Afhentning");
                                }

                            }
                        }
                    }
                } else {
                    if (o.getOnummer() != controller.getCurrentOrder().getOnummer()) {
                        if (o.getAfhentning().equalsIgnoreCase("Leveres af os")) {
                            if ((levDay == oLevDay && levMonth == oLevMonth && levYear == oLevYear) || (levDay == oRetDay && levMonth == oRetMonth && levYear == oRetYear)) {
                                for (int l = 1; l < jComboBox2.getItemCount(); l++) {
                                    if (o.getTidLev().equals("" + jComboBox2.getItemAt(l))) {
                                        tidLev[l - 1]++;
                                        if (tidLev[l - 1] >= 2) {
                                            jComboBox2.removeItemAt(l);
                                        }
                                        try {
                                            jComboBox2.removeItemAt(6);
                                        } catch (Exception ex) {
                                        }
                                    } else if (o.getTidLev().equals("Stor Order")) {
                                        jComboBox2.removeAllItems();
                                        jComboBox2.addItem("Levering");
                                    }
                                }
                            }
                            if ((retDay == oRetDay && retMonth == oRetMonth && retYear == oRetYear) || (retDay == oLevDay && retMonth == oLevMonth && retYear == oLevYear)) {
                                for (int l = 1; l < jComboBox3.getItemCount(); l++) {
                                    if (o.getTidRet().equals("" + jComboBox3.getItemAt(l))) {
                                        tidRet[l - 1]++;
                                        if (tidRet[l - 1] >= 2) {
                                            jComboBox3.removeItemAt(l);
                                        }
                                        try {
                                            jComboBox3.removeItemAt(6);
                                        } catch (Exception ex) {
                                        }
                                    } else if (o.getTidRet().equals("Stor Order")) {
                                        jComboBox3.removeAllItems();
                                        jComboBox3.addItem("Afhentning");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Vareliste
        ArrayList<Vare> vl5 = controller.getAllRessources();
        Vare[] va5 = new Vare[vl5.size()];
        for (int i = 0; i < vl5.size(); i++) {
            va5[i] = vl5.get(i);
        }
        for (int i = 0; i < vl5.size(); i++) {
            controller.quickSortVare(va5, 0, va5.length - 1);
            Vareliste.clear();
            for (int j = 0; j < va5.length; j++) {
                Vareliste.addElement(va5[j]);
            }


            jLabelOpretRedigerVare.setText("Opret vare:");
            jLabelKundenummer1.setText("");
            jLabelKundenummer2.setText("");
            jLabelVarenummer1.setText("");
            jLabelVarenummer2.setText("");
            jTextFieldNavn.setText("");
            jTextFieldVarePris.setText("");
            jTextFieldVareQty.setText("");
            jTextFieldPartNavn.setText("");
            jTextFieldPartAntal.setText("");
            listLagerDele.clear();

        }
        // Kundeliste 
        ArrayList<Kunde> kl9 = controller.getAllCostumers();
        Kunde[] ka9 = new Kunde[kl9.size()];
        for (int i = 0; i < kl9.size(); i++) {
            ka9[i] = kl9.get(i);
        }
        for (int i = 0; i < kl9.size(); i++) {

            controller.quickSortKunde(ka9, 0, ka9.length - 1);
            listKundeliste.clear();
            for (int j = 0; j < ka9.length; j++) {
                listKundeliste.addElement(ka9[j]);
            }

            jLabelOpretRedigerKunde.setText("Opret kunde:");
            jLabelKundenummer1.setText("");
            jLabelKundenummer2.setText("");
            jTextFieldFirmaNavn.setText("");
            jTextFieldFuldeNavn.setText("");
            jTextFieldAdresse.setText("");
            jTextFieldPostnummer.setText("");
            jTextFieldTelefonnummer.setText("");


        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAfslut;
    private javax.swing.JButton jButtonAfslutOrdre;
    private javax.swing.JButton jButtonDepositum;
    private javax.swing.JButton jButtonFaktura;
    private javax.swing.JButton jButtonFjern;
    private javax.swing.JButton jButtonFjernPart;
    private javax.swing.JButton jButtonGemKunde;
    private javax.swing.JButton jButtonGennemførOrdre;
    private javax.swing.JButton jButtonHistorikFaktura;
    private javax.swing.JButton jButtonHistorikSøg;
    private javax.swing.JButton jButtonHjemme;
    private javax.swing.JButton jButtonKundeSøg;
    private javax.swing.JButton jButtonLagerGem;
    private javax.swing.JButton jButtonLagerRediger;
    private javax.swing.JButton jButtonLagerSøg;
    private javax.swing.JButton jButtonLevér;
    private javax.swing.JButton jButtonMontoer;
    private javax.swing.JButton jButtonOrdre;
    private javax.swing.JButton jButtonOrdre1;
    private javax.swing.JButton jButtonOrdre3;
    private javax.swing.JButton jButtonOrdrePdf;
    private javax.swing.JButton jButtonOrdreRediger;
    private javax.swing.JButton jButtonPakkeliste;
    private javax.swing.JButton jButtonRedigerKunde;
    private javax.swing.JButton jButtonRedigerPart;
    private javax.swing.JButton jButtonReturSøg;
    private javax.swing.JButton jButtonSletOrdre;
    private javax.swing.JButton jButtonSletVare;
    private javax.swing.JButton jButtonStatus;
    private javax.swing.JButton jButtonTilbud;
    private javax.swing.JButton jButtonTilbud1;
    private javax.swing.JButton jButtonTilbud3;
    private javax.swing.JButton jButtonTilbudPdf;
    private javax.swing.JButton jButtonTilbudSøg;
    private javax.swing.JButton jButtonTilføj;
    private javax.swing.JButton jButtonTilføjPart;
    private javax.swing.JButton jButtonUde;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelErrorKunder;
    private javax.swing.JLabel jLabelErrorLager;
    private javax.swing.JLabel jLabelErrorOrdre;
    private javax.swing.JLabel jLabelErrorRetur;
    private javax.swing.JLabel jLabelKundenummer1;
    private javax.swing.JLabel jLabelKundenummer2;
    private javax.swing.JLabel jLabelOpretRedigerKunde;
    private javax.swing.JLabel jLabelOpretRedigerVare;
    private javax.swing.JLabel jLabelVarenummer1;
    private javax.swing.JLabel jLabelVarenummer2;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
    private javax.swing.JList jListHistorik;
    private javax.swing.JList jListKundeliste;
    private javax.swing.JList jListLagerDele;
    private javax.swing.JList jListOrdrer;
    private javax.swing.JList jListReturDele;
    private javax.swing.JList jListTilbudVarer;
    private javax.swing.JList jListVareliste;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioAktiv;
    private javax.swing.JRadioButton jRadioButtonAfhentning;
    private javax.swing.JRadioButton jRadioButtonLevering;
    private javax.swing.JRadioButton jRadioIkkeAktiv;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextFieldAdresse;
    private javax.swing.JTextField jTextFieldAntal;
    private javax.swing.JTextField jTextFieldDagInd;
    private javax.swing.JTextField jTextFieldDagUd;
    private javax.swing.JTextField jTextFieldFirmaNavn;
    private javax.swing.JTextField jTextFieldFuldeNavn;
    private javax.swing.JTextField jTextFieldHistorikSøg;
    private javax.swing.JTextField jTextFieldKundeSøg;
    private javax.swing.JTextField jTextFieldLagerSøg;
    private javax.swing.JTextField jTextFieldMånedInd;
    private javax.swing.JTextField jTextFieldMånedUd;
    private javax.swing.JTextField jTextFieldNavn;
    private javax.swing.JTextField jTextFieldPartAntal;
    private javax.swing.JTextField jTextFieldPartNavn;
    private javax.swing.JTextField jTextFieldPostnummer;
    private javax.swing.JTextField jTextFieldRabat;
    private javax.swing.JTextField jTextFieldReturSøg;
    private javax.swing.JTextField jTextFieldTelefonnummer;
    private javax.swing.JTextField jTextFieldTilbudSøg;
    private javax.swing.JTextField jTextFieldTotalPris;
    private javax.swing.JTextField jTextFieldVarePris;
    private javax.swing.JTextField jTextFieldVareQty;
    private javax.swing.JTextField jTextFieldÅrInd;
    private javax.swing.JTextField jTextFieldÅrUd;
    // End of variables declaration//GEN-END:variables
}
