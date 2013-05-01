package presentation;

import domain.Controller;
import domain.DelOrdre;
import domain.Kunde;
import domain.Odetaljer;
import domain.Ordre;
import domain.Del;
import domain.Vare;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public final class GUI extends javax.swing.JFrame {

    private DefaultListModel list1 = new DefaultListModel();
    private DefaultListModel list2 = new DefaultListModel();
    private DefaultListModel list3 = new DefaultListModel();
    private DefaultListModel Vareliste = new DefaultListModel();
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
        jLabelErrorOrdre.setText("");
        jLabelOpretRedigerVare.setText("");
        update();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelOrdre = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButtonOrdreTilføj = new javax.swing.JButton();
        jButtonOrdreFjern = new javax.swing.JButton();
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
        jButtonOrdreGennemførOrdre = new javax.swing.JButton();
        jRadioButtonAfhentning = new javax.swing.JRadioButton();
        jRadioButtonLevering = new javax.swing.JRadioButton();
        jButtonOrdreRediger = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jButtonOrdreFaktura = new javax.swing.JButton();
        jButtonOrdreTilbudPDF = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxKunder = new javax.swing.JComboBox();
        jLabelErrorOrdre = new javax.swing.JLabel();
        jTextFieldAntal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldRabat = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextFieldTotalPris = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jButtonOrdrePakkeliste = new javax.swing.JButton();
        jButtonOrdreStatus = new javax.swing.JButton();
        jButtonOrdreCheckDato = new javax.swing.JButton();
        jComboBoxLevering = new javax.swing.JComboBox();
        jButtonOrdreOrdrePDF = new javax.swing.JButton();
        jComboBoxAfhentning = new javax.swing.JComboBox();
        jPanelKunder = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jListKundeliste = new javax.swing.JList();
        jButtonKundeRediger = new javax.swing.JButton();
        jButtonKundeGem = new javax.swing.JButton();
        jLabelOpretRedigerKunde = new javax.swing.JLabel();
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
        jLabel33 = new javax.swing.JLabel();
        jPanelLager = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListVareliste = new javax.swing.JList();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldVareAntal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldVareNavn = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldVarePris = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        jListLagerDele = new javax.swing.JList();
        jTextFieldPartNavn = new javax.swing.JTextField();
        jTextFieldPartAntal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButtonLagerFjern = new javax.swing.JButton();
        jButtonLagerTilføj = new javax.swing.JButton();
        jButtonLagerRedigerDel = new javax.swing.JButton();
        jRadioAktiv = new javax.swing.JRadioButton();
        jRadioIkkeAktiv = new javax.swing.JRadioButton();
        jButtonLagerRedigerVare = new javax.swing.JButton();
        jButtonLagerGem = new javax.swing.JButton();
        jLabelVarenummer1 = new javax.swing.JLabel();
        jLabelVarenummer2 = new javax.swing.JLabel();
        jTextFieldLagerSøg = new javax.swing.JTextField();
        jButtonLagerSøg = new javax.swing.JButton();
        jLabelErrorLager = new javax.swing.JLabel();
        jLabelOpretRedigerVare = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanelHistorik = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jListHistorik = new javax.swing.JList();
        jButtonHistorikAfslut = new javax.swing.JButton();
        jButtonHistorikFaktura = new javax.swing.JButton();
        jButtonHistorikSlet = new javax.swing.JButton();
        jTextFieldHistorikSøg = new javax.swing.JTextField();
        jButtonHistorikSøg = new javax.swing.JButton();
        jButtonHistorikTilbud = new javax.swing.JButton();
        jButtonHistorikOrdre = new javax.swing.JButton();
        jButtonTilbud1 = new javax.swing.JButton();
        jButtonOrdre1 = new javax.swing.JButton();
        jButtonTilbud3 = new javax.swing.JButton();
        jButtonOrdre3 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jPanelRetur = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListReturDele = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        jListOrdrer = new javax.swing.JList();
        jButtonReturAfslutOrdre = new javax.swing.JButton();
        jButtonReturHjemme = new javax.swing.JButton();
        jButtonReturLevrér = new javax.swing.JButton();
        jButtonReturSøg = new javax.swing.JButton();
        jTextFieldReturSøg = new javax.swing.JTextField();
        jButtonReturUde = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 48, -1, -1));

        jPanelOrdre.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Varer:");
        jPanelOrdre.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 160, 20));

        jScrollPane1.setViewportView(jList2);

        jPanelOrdre.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 163, 250));

        jButtonOrdreTilføj.setText("Tilføj >");
        jButtonOrdreTilføj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrdreTilføjActionPerformed(evt);
            }
        });
        jPanelOrdre.add(jButtonOrdreTilføj, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 80, -1));

        jButtonOrdreFjern.setText("< Fjern");
        jButtonOrdreFjern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrdreFjernActionPerformed(evt);
            }
        });
        jPanelOrdre.add(jButtonOrdreFjern, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 80, -1));

        jScrollPane2.setViewportView(jList1);

        jPanelOrdre.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 163, 280));

        jLabel3.setText(" Dato for afhentning/levering:");
        jPanelOrdre.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 20));
        jPanelOrdre.add(jTextFieldMånedInd, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 30, -1));
        jPanelOrdre.add(jTextFieldDagUd, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 30, -1));
        jPanelOrdre.add(jTextFieldMånedUd, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 30, -1));
        jPanelOrdre.add(jTextFieldÅrInd, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 50, -1));

        jLabel4.setText(" Dato for returnering:");
        jPanelOrdre.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, 20));
        jPanelOrdre.add(jTextFieldDagInd, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 30, -1));
        jPanelOrdre.add(jTextFieldÅrUd, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 50, -1));

        jButtonOrdreGennemførOrdre.setText("Gennemfør ordre >");
        jButtonOrdreGennemførOrdre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrdreGennemførOrdreActionPerformed(evt);
            }
        });
        jPanelOrdre.add(jButtonOrdreGennemførOrdre, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, -1, -1));

        buttonGroup1.add(jRadioButtonAfhentning);
        jRadioButtonAfhentning.setText("Afhentning");
        jRadioButtonAfhentning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAfhentningActionPerformed(evt);
            }
        });
        jPanelOrdre.add(jRadioButtonAfhentning, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, -1, -1));

        buttonGroup1.add(jRadioButtonLevering);
        jRadioButtonLevering.setText("Levering");
        jRadioButtonLevering.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonLeveringActionPerformed(evt);
            }
        });
        jPanelOrdre.add(jRadioButtonLevering, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, -1, -1));

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
        jPanelOrdre.add(jButtonOrdreRediger, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, -1, -1));

        jScrollPane3.setViewportView(jList3);

        jPanelOrdre.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 160, 280));

        jButtonOrdreFaktura.setText("Faktura PDF");
        jButtonOrdreFaktura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrdreFakturaActionPerformed(evt);
            }
        });
        jPanelOrdre.add(jButtonOrdreFaktura, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 110, 130, -1));

        jButtonOrdreTilbudPDF.setText("Tilbud PDF");
        jButtonOrdreTilbudPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrdreTilbudPDFActionPerformed(evt);
            }
        });
        jPanelOrdre.add(jButtonOrdreTilbudPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, 130, -1));

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Aktive Ordrer:");
        jPanelOrdre.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, 160, 20));

        jComboBoxKunder.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kunder" }));
        jPanelOrdre.add(jComboBoxKunder, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 160, -1));

        jLabelErrorOrdre.setText("jLabelErrorOrdre");
        jPanelOrdre.add(jLabelErrorOrdre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 670, 20));
        jPanelOrdre.add(jTextFieldAntal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 40, -1));

        jLabel17.setText("Antal:");
        jPanelOrdre.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, -1, 20));

        jTextFieldRabat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRabatActionPerformed(evt);
            }
        });
        jPanelOrdre.add(jTextFieldRabat, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 60, -1));

        jLabel27.setText(" Rabat:");
        jPanelOrdre.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, -1));

        jLabel28.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Ordre:");
        jPanelOrdre.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 160, 20));

        jTextFieldTotalPris.setEditable(false);
        jPanelOrdre.add(jTextFieldTotalPris, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, 60, -1));

        jLabel30.setText(" Pris før rabat:");
        jPanelOrdre.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, -1, -1));

        jButtonOrdrePakkeliste.setText("Pakkeliste");
        jButtonOrdrePakkeliste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrdrePakkelisteActionPerformed(evt);
            }
        });
        jPanelOrdre.add(jButtonOrdrePakkeliste, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 200, 130, -1));

        jButtonOrdreStatus.setText("Status");
        jButtonOrdreStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrdreStatusActionPerformed(evt);
            }
        });
        jPanelOrdre.add(jButtonOrdreStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 230, 130, -1));

        jButtonOrdreCheckDato.setText("Check Dato");
        jButtonOrdreCheckDato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrdreCheckDatoActionPerformed(evt);
            }
        });
        jPanelOrdre.add(jButtonOrdreCheckDato, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, -1, -1));

        jComboBoxLevering.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Levering", "08:00", "10:00", "12:00", "14:00", "16:00", "Stor Order" }));
        jPanelOrdre.add(jComboBoxLevering, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, -1, -1));

        jButtonOrdreOrdrePDF.setText("Ordre PDF");
        jButtonOrdreOrdrePDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrdreOrdrePDFActionPerformed(evt);
            }
        });
        jPanelOrdre.add(jButtonOrdreOrdrePDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 140, 130, -1));

        jComboBoxAfhentning.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Afhentning", "08:00", "10:00", "12:00", "14:00", "16:00", "Stor Order" }));
        jPanelOrdre.add(jComboBoxAfhentning, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 40, -1, -1));

        jTabbedPane1.addTab("Ordre", jPanelOrdre);

        jPanelKunder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane10.setViewportView(jListKundeliste);

        jPanelKunder.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 220, 320));

        jButtonKundeRediger.setText("   Rediger >");
        jButtonKundeRediger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKundeRedigerActionPerformed(evt);
            }
        });
        jPanelKunder.add(jButtonKundeRediger, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 90, 23));

        jButtonKundeGem.setText("< Gem   ");
        jButtonKundeGem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKundeGemActionPerformed(evt);
            }
        });
        jPanelKunder.add(jButtonKundeGem, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 90, 23));

        jLabelOpretRedigerKunde.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabelOpretRedigerKunde.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOpretRedigerKunde.setText("Opret kunde:");
        jPanelKunder.add(jLabelOpretRedigerKunde, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 300, 40));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setText("Firmanavn");
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 80, 20));
        jPanel7.add(jTextFieldAdresse, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 180, -1));

        jLabel23.setText("Adresse");
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 60, -1));
        jPanel7.add(jTextFieldFirmaNavn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 180, -1));

        jLabel24.setText("Fulde navn");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 90, -1));
        jPanel7.add(jTextFieldFuldeNavn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 180, -1));

        jLabel25.setText("Telefonnummer");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 100, -1));

        jLabel26.setText("Postnummer");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));
        jPanel7.add(jTextFieldPostnummer, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 180, -1));
        jPanel7.add(jTextFieldTelefonnummer, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 180, -1));

        jPanelKunder.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 300, 320));

        jButtonKundeSøg.setText("Søg");
        jButtonKundeSøg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKundeSøgActionPerformed(evt);
            }
        });
        jPanelKunder.add(jButtonKundeSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, 20));
        jPanelKunder.add(jTextFieldKundeSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, 20));

        jLabelKundenummer1.setText("jLabelKundenummer1");
        jPanelKunder.add(jLabelKundenummer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 120, 30));

        jLabelKundenummer2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelKundenummer2.setText("jLabelKundenummer2");
        jPanelKunder.add(jLabelKundenummer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 160, 30));

        jLabelErrorKunder.setText("jLabelErrorKunder");
        jPanelKunder.add(jLabelErrorKunder, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 670, 20));

        jLabel33.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Kundeliste:");
        jPanelKunder.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 220, 40));

        jTabbedPane1.addTab("Kunder", jPanelKunder);

        jPanelLager.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setViewportView(jListVareliste);

        jPanelLager.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 220, 320));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setText("Navn");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 20));
        jPanel5.add(jTextFieldVareAntal, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 120, -1));

        jLabel11.setText("Antal");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 60, -1));
        jPanel5.add(jTextFieldVareNavn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 120, -1));

        jLabel12.setText("Pris");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 50, -1));
        jPanel5.add(jTextFieldVarePris, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 120, -1));

        jListLagerDele.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane11.setViewportView(jListLagerDele);

        jPanel5.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 180, 90));
        jPanel5.add(jTextFieldPartNavn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 120, -1));
        jPanel5.add(jTextFieldPartAntal, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 120, -1));

        jLabel7.setText("Navn");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel8.setText("Antal");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jButtonLagerFjern.setText("Fjern");
        jButtonLagerFjern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLagerFjernActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonLagerFjern, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 80, -1));

        jButtonLagerTilføj.setText("Tilføj");
        jButtonLagerTilføj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLagerTilføjActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonLagerTilføj, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 60, 50));

        jButtonLagerRedigerDel.setText("Rediger");
        jButtonLagerRedigerDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLagerRedigerDelActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonLagerRedigerDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 80, -1));

        buttonGroup2.add(jRadioAktiv);
        jRadioAktiv.setText("Aktiv");
        jPanel5.add(jRadioAktiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));

        buttonGroup2.add(jRadioIkkeAktiv);
        jRadioIkkeAktiv.setText("Ikke Aktiv");
        jPanel5.add(jRadioIkkeAktiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

        jPanelLager.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 300, 320));

        jButtonLagerRedigerVare.setText("   Rediger >");
        jButtonLagerRedigerVare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLagerRedigerVareActionPerformed(evt);
            }
        });
        jPanelLager.add(jButtonLagerRedigerVare, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 90, 23));

        jButtonLagerGem.setText("< Gem   ");
        jButtonLagerGem.setMaximumSize(new java.awt.Dimension(105, 29));
        jButtonLagerGem.setMinimumSize(new java.awt.Dimension(105, 29));
        jButtonLagerGem.setPreferredSize(new java.awt.Dimension(105, 29));
        jButtonLagerGem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLagerGemActionPerformed(evt);
            }
        });
        jPanelLager.add(jButtonLagerGem, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 90, 23));

        jLabelVarenummer1.setText("jLabelVarenummer1");
        jPanelLager.add(jLabelVarenummer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 100, 30));

        jLabelVarenummer2.setText("jLabelVarenummer2");
        jPanelLager.add(jLabelVarenummer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 120, 30));

        jTextFieldLagerSøg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLagerSøgActionPerformed(evt);
            }
        });
        jPanelLager.add(jTextFieldLagerSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, 20));

        jButtonLagerSøg.setText("Søg");
        jButtonLagerSøg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLagerSøgActionPerformed(evt);
            }
        });
        jPanelLager.add(jButtonLagerSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, 20));

        jLabelErrorLager.setText("jLabelErrorLager");
        jPanelLager.add(jLabelErrorLager, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 740, 20));

        jLabelOpretRedigerVare.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabelOpretRedigerVare.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOpretRedigerVare.setText("Opret vare:");
        jPanelLager.add(jLabelOpretRedigerVare, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 300, 40));

        jLabel31.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Vareliste:");
        jPanelLager.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 220, 40));

        jTabbedPane1.addTab("Lager", jPanelLager);

        jPanelHistorik.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jListHistorik.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane9.setViewportView(jListHistorik);

        jPanelHistorik.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 220, 320));

        jButtonHistorikAfslut.setText("Afslut");
        jButtonHistorikAfslut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistorikAfslutActionPerformed(evt);
            }
        });
        jPanelHistorik.add(jButtonHistorikAfslut, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 80, -1));

        jButtonHistorikFaktura.setText("Faktura");
        jButtonHistorikFaktura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistorikFakturaActionPerformed(evt);
            }
        });
        jPanelHistorik.add(jButtonHistorikFaktura, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 80, -1));

        jButtonHistorikSlet.setText("Slet");
        jButtonHistorikSlet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistorikSletActionPerformed(evt);
            }
        });
        jPanelHistorik.add(jButtonHistorikSlet, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 80, -1));
        jPanelHistorik.add(jTextFieldHistorikSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, 20));

        jButtonHistorikSøg.setText("Søg");
        jButtonHistorikSøg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistorikSøgActionPerformed(evt);
            }
        });
        jPanelHistorik.add(jButtonHistorikSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, 20));

        jButtonHistorikTilbud.setText("Tilbud");
        jButtonHistorikTilbud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistorikTilbudActionPerformed(evt);
            }
        });
        jPanelHistorik.add(jButtonHistorikTilbud, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 80, -1));

        jButtonHistorikOrdre.setText("Ordre");
        jButtonHistorikOrdre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistorikOrdreActionPerformed(evt);
            }
        });
        jPanelHistorik.add(jButtonHistorikOrdre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 80, -1));

        jButtonTilbud1.setText("Tilbud");
        jButtonTilbud1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistorikTilbudActionPerformed(evt);
            }
        });
        jPanelHistorik.add(jButtonTilbud1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 80, -1));

        jButtonOrdre1.setText("Ordre");
        jButtonOrdre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistorikOrdreActionPerformed(evt);
            }
        });
        jPanelHistorik.add(jButtonOrdre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 80, -1));

        jButtonTilbud3.setText("Tilbud");
        jButtonTilbud3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistorikTilbudActionPerformed(evt);
            }
        });
        jPanelHistorik.add(jButtonTilbud3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 80, -1));

        jButtonOrdre3.setText("Ordre");
        jButtonOrdre3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistorikOrdreActionPerformed(evt);
            }
        });
        jPanelHistorik.add(jButtonOrdre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 80, -1));

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Kundeliste:");
        jPanelHistorik.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 220, 40));

        jTabbedPane1.addTab("Historik", jPanelHistorik);

        jPanelRetur.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setViewportView(jListReturDele);

        jPanelRetur.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 220, 320));

        jScrollPane6.setViewportView(jListOrdrer);

        jPanelRetur.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 220, 320));

        jButtonReturAfslutOrdre.setText("Afslut ordre");
        jButtonReturAfslutOrdre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturAfslutOrdreActionPerformed(evt);
            }
        });
        jPanelRetur.add(jButtonReturAfslutOrdre, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, -1, -1));

        jButtonReturHjemme.setText("Hjemme");
        jButtonReturHjemme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturHjemmeActionPerformed(evt);
            }
        });
        jPanelRetur.add(jButtonReturHjemme, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 80, -1));

        jButtonReturLevrér.setText("Levér >");
        jButtonReturLevrér.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturLevrérActionPerformed(evt);
            }
        });
        jPanelRetur.add(jButtonReturLevrér, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 213, 90, 23));

        jButtonReturSøg.setText("Søg");
        jButtonReturSøg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturSøgActionPerformed(evt);
            }
        });
        jPanelRetur.add(jButtonReturSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, 20));
        jPanelRetur.add(jTextFieldReturSøg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, 20));

        jButtonReturUde.setText("Ude");
        jButtonReturUde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturUdeActionPerformed(evt);
            }
        });
        jPanelRetur.add(jButtonReturUde, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, 80, -1));

        jLabel32.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Retur:");
        jPanelRetur.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 220, 40));

        jLabel21.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Ordre:");
        jPanelRetur.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 220, 40));

        jTabbedPane1.addTab("Retur", jPanelRetur);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLagerSøgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLagerSøgActionPerformed
        jLabelErrorLager.setText("");
        if (!jTextFieldLagerSøg.getText().equals("")) {
            int counter = 0;
            ArrayList<Vare> vl = new ArrayList<>();
            for (int i = 0; i < Vareliste.size(); i++) {
                vl.add((Vare) Vareliste.getElementAt(i));
            }
            for (int i = 0; i < vl.size(); i++) {
                if (controller.lagerSøg(vl.get(i), jTextFieldLagerSøg.getText())) {
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
    }//GEN-LAST:event_jButtonLagerSøgActionPerformed

    private void jTextFieldLagerSøgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLagerSøgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLagerSøgActionPerformed

    private void jButtonLagerGemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLagerGemActionPerformed
        jLabelErrorLager.setText("");
        int vnummer = 0;
        int aktiv = 1;
        if (!jTextFieldVareNavn.getText().isEmpty() && !jTextFieldVarePris.getText().isEmpty() && !jTextFieldVareAntal.getText().isEmpty()) {
            if (!jLabelVarenummer2.getText().isEmpty()) {
                vnummer = Integer.parseInt(jLabelVarenummer2.getText());
            }
            String vnavn = jTextFieldVareNavn.getText();
            double pris = Double.parseDouble(jTextFieldVarePris.getText());
            ArrayList<Del> vare = new ArrayList();
            int qty = Integer.parseInt(jTextFieldVareAntal.getText());
            if (jRadioAktiv.isSelected()) {
                aktiv = 1;
            }
            if (jRadioIkkeAktiv.isSelected()) {
                aktiv = 0;
            }
            for (int i = 0; i < listLagerDele.size(); i++) {
                vare.add((Del) listLagerDele.getElementAt(i));
            }
            controller.gemLager(vnummer, vnavn, qty, pris, aktiv, vare);
            update();
            controller.redigerFalse();
        } else {
            jLabelErrorLager.setText("Du skal udfylde alle tekstfelterne for at gemme en vare.");
        }

    }//GEN-LAST:event_jButtonLagerGemActionPerformed

    private void jButtonLagerRedigerVareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLagerRedigerVareActionPerformed
        jLabelErrorLager.setText("");
        Vare selected = (Vare) jListVareliste.getSelectedValue();
        if (selected != null) {
            if (controller.getRediger() == false) {
                listReturDele.removeElement(selected);
                listLagerDele.clear();
                jLabelOpretRedigerVare.setText("Redigerer i vare:");
                jLabelVarenummer1.setText("Varenummer:");
                jLabelVarenummer2.setText(selected.getVnummer() + "");
                jTextFieldVareNavn.setText(selected.getVnavn());
                jTextFieldVarePris.setText(selected.getPris() + "");
                jTextFieldVareAntal.setText(selected.getQty() + "");
                if (selected.getAktiv() == 1) {
                    jRadioAktiv.setSelected(true);
                } else {
                    jRadioIkkeAktiv.setSelected(false);
                }
                for (int i = 0; i < selected.getDel().size(); i++) {
                    listLagerDele.addElement(selected.getDel().get(i));
                }
                controller.redigerTrue();
            } else {
                jLabelErrorLager.setText("Du skal redigere varen færdig før du redigerer en ny.");
            }
        }
    }//GEN-LAST:event_jButtonLagerRedigerVareActionPerformed

    private void jButtonLagerRedigerDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLagerRedigerDelActionPerformed
        jLabelErrorLager.setText("");
        Object selected = (Del) jListLagerDele.getSelectedValue();
        if (selected != null) {
            if (!jTextFieldPartNavn.getText().isEmpty() || !jTextFieldPartAntal.getText().isEmpty()) {
                Del del = (Del) jListLagerDele.getSelectedValue();
                listLagerDele.removeElementAt(jListLagerDele.getSelectedIndex());
                jTextFieldPartNavn.setText(del.getTitel());
                jTextFieldPartAntal.setText("" + del.getAntal());
            }
            jLabelErrorLager.setText("Du skal redigere delen færdig før du redigerer en ny.");
        }
    }//GEN-LAST:event_jButtonLagerRedigerDelActionPerformed

    private void jButtonLagerTilføjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLagerTilføjActionPerformed
        jLabelErrorLager.setText("");
        if (!jTextFieldPartNavn.getText().isEmpty() && !jTextFieldPartAntal.getText().isEmpty()) {
            int vnummer = controller.getNextVnummer();
            String delnavn = jTextFieldPartNavn.getText();
            int delantal = Integer.parseInt(jTextFieldPartAntal.getText());
            listLagerDele.addElement(new Del(vnummer, delnavn, delantal));
            jTextFieldPartNavn.setText("");
            jTextFieldPartAntal.setText("");
        }
    }//GEN-LAST:event_jButtonLagerTilføjActionPerformed

    private void jButtonLagerFjernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLagerFjernActionPerformed
        jLabelErrorLager.setText("");
        Object selected = (Del) jListLagerDele.getSelectedValue();
        if (selected != null) {
            listLagerDele.removeElement(selected);
        }
    }//GEN-LAST:event_jButtonLagerFjernActionPerformed

    private void jButtonHistorikOrdreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistorikOrdreActionPerformed
        Ordre selected = (Ordre) jListHistorik.getSelectedValue();
        if (selected != null) {
            controller.bekraeftOrdre(selected.getOnummer());
            update();
        }
    }//GEN-LAST:event_jButtonHistorikOrdreActionPerformed

    private void jButtonHistorikTilbudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistorikTilbudActionPerformed
        Ordre selected = (Ordre) jListHistorik.getSelectedValue();
        if (selected != null) {
            controller.ordreTilbud(selected.getOnummer());
            update();
        }
    }//GEN-LAST:event_jButtonHistorikTilbudActionPerformed

    private void jButtonHistorikSøgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistorikSøgActionPerformed
        if (!jTextFieldHistorikSøg.getText().equals("")) {
            int counter = 0;
            ArrayList<Ordre> ol = new ArrayList<>();
            for (int i = 0; i < listHistorik.size(); i++) {
                ol.add((Ordre) listHistorik.getElementAt(i));
            }
            for (int i = 0; i < ol.size(); i++) {
                if (controller.historikSøg(ol.get(i), jTextFieldHistorikSøg.getText())) {
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

    private void jButtonHistorikSletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistorikSletActionPerformed
        Ordre selected = (Ordre) jListHistorik.getSelectedValue();
        if (selected != null) {
            controller.deleteOrder(selected);
            update();
        }
    }//GEN-LAST:event_jButtonHistorikSletActionPerformed

    private void jButtonHistorikFakturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistorikFakturaActionPerformed
        Ordre selected = (Ordre) jListHistorik.getSelectedValue();
        if (selected != null) {
            controller.ordreFaktureret(selected.getOnummer());
            update();
        }
    }//GEN-LAST:event_jButtonHistorikFakturaActionPerformed

    private void jButtonHistorikAfslutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistorikAfslutActionPerformed
        Ordre selected = (Ordre) jListHistorik.getSelectedValue();
        if (selected != null) {
            controller.ordreAfslut(selected.getKnummer());
            update();
        }
    }//GEN-LAST:event_jButtonHistorikAfslutActionPerformed

    private void jButtonReturUdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturUdeActionPerformed
        DelOrdre selectedDelOrdre = (DelOrdre) jListReturDele.getSelectedValue();
        int index = jListOrdrer.getSelectedIndex();
        if (selectedDelOrdre != null) {
            selectedDelOrdre.setStatus(0);
            controller.updateDelOrdre(selectedDelOrdre);
            update();
            jListOrdrer.setSelectedIndex(index);
            listReturDele.clear();
            boolean status = true;
            Ordre selectedOrdre = (Ordre) jListOrdrer.getSelectedValue();
            for (int i = 0; i < selectedOrdre.getDelo().size(); i++) {
                listReturDele.addElement(selectedOrdre.getDelo().get(i));
                status = status && selectedOrdre.getDelo().get(i).getStatus() == 1;
            }
            if (status) {
                jButtonReturAfslutOrdre.setEnabled(true);
            } else {
                jButtonReturAfslutOrdre.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jButtonReturUdeActionPerformed

    private void jButtonReturSøgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturSøgActionPerformed
        if (!jTextFieldReturSøg.getText().equals("")) {
            int counter = 0;
            ArrayList<Ordre> ol = new ArrayList<>();
            for (int i = 0; i < listOrdrer.size(); i++) {
                ol.add((Ordre) listOrdrer.getElementAt(i));
            }
            for (int i = 0; i < ol.size(); i++) {
                if (controller.returSøg(ol.get(i), jTextFieldReturSøg.getText())) {
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

    private void jButtonReturHjemmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturHjemmeActionPerformed
        DelOrdre selectedDelOrdre = (DelOrdre) jListReturDele.getSelectedValue();
        int index = jListOrdrer.getSelectedIndex();
        if (selectedDelOrdre != null) {
            selectedDelOrdre.setStatus(1);
            controller.updateDelOrdre(selectedDelOrdre);
            update();
            jListOrdrer.setSelectedIndex(index);
            listReturDele.clear();
            boolean status = true;
            Ordre selectedOrdre = (Ordre) jListOrdrer.getSelectedValue();
            for (int i = 0; i < selectedOrdre.getDelo().size(); i++) {
                listReturDele.addElement(selectedOrdre.getDelo().get(i));
                status = status && selectedOrdre.getDelo().get(i).getStatus() == 1;
            }
            if (status) {
                jButtonReturAfslutOrdre.setEnabled(true);
            } else {
                jButtonReturAfslutOrdre.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jButtonReturHjemmeActionPerformed

    private void jButtonReturAfslutOrdreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturAfslutOrdreActionPerformed
        Ordre selected = (Ordre) jListOrdrer.getSelectedValue();
        if (selected != null) {
            int index = jListOrdrer.getSelectedIndex();
            boolean status = true;
            for (int i = 0; i < listReturDele.size(); i++) {
                DelOrdre delordre = (DelOrdre) listReturDele.get(i);
                status = status && delordre.getStatus() == 1;
            }
            if (status) {
                controller.ordreAfslut(selected.getOnummer());
                listReturDele.clear();
            }
            update();
            jListOrdrer.setSelectedIndex(index);
        }
    }//GEN-LAST:event_jButtonReturAfslutOrdreActionPerformed

    private void jButtonKundeSøgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKundeSøgActionPerformed
        jLabelErrorKunder.setText("");
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

    private void jButtonKundeGemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKundeGemActionPerformed
        jLabelErrorKunder.setText("");
        String firmanavn = jTextFieldFirmaNavn.getText();
        String navn = jTextFieldFuldeNavn.getText();
        String adresse = jTextFieldAdresse.getText();
        String postnummer = jTextFieldPostnummer.getText();
        String telefonnummer = jTextFieldTelefonnummer.getText();
        int knummer;

        if (jLabelKundenummer2.getText().isEmpty()) {
            knummer = 0;
        } else {
            knummer = Integer.parseInt(jLabelKundenummer2.getText());
        }
        if (!navn.isEmpty() || !adresse.isEmpty() || !postnummer.isEmpty() || !telefonnummer.isEmpty()) {
            controller.gemKunde(firmanavn, navn, adresse, Integer.parseInt(postnummer), Integer.parseInt(telefonnummer), knummer);
            update();
        } else {
            jLabelErrorKunder.setText("Mangler navn / adresse / postnummer / telefonnummer");
        }
    }//GEN-LAST:event_jButtonKundeGemActionPerformed

    private void jButtonKundeRedigerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKundeRedigerActionPerformed
        jLabelErrorKunder.setText("");
        Kunde selected = (Kunde) jListKundeliste.getSelectedValue();
        if (selected != null) {
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
                jLabelErrorKunder.setText("Du skal redigere kunden færdig før du redigerer en ny.");
            }
        }
    }//GEN-LAST:event_jButtonKundeRedigerActionPerformed

    private void jButtonOrdreOrdrePDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrdreOrdrePDFActionPerformed
        jLabelErrorOrdre.setText("");
        Ordre selected = (Ordre) jList3.getSelectedValue();
        if (jList3.getSelectedValue() != null) {
            int selectedIndex = jList3.getSelectedIndex();
            controller.bekraeftOrdre(selected.getOnummer());
            controller.setCurrentOrder(selected);
            update();
            selected = (Ordre) list3.getElementAt(selectedIndex);
            controller.setCurrentOrder(selected);
            controller.pdfOrdre();
        }
    }//GEN-LAST:event_jButtonOrdreOrdrePDFActionPerformed

    private void jButtonOrdreCheckDatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrdreCheckDatoActionPerformed
        jLabelErrorOrdre.setText("");
        if (jTextFieldDagUd.getText().isEmpty() || jTextFieldMånedUd.getText().isEmpty() || jTextFieldÅrUd.getText().isEmpty() || jTextFieldDagInd.getText().isEmpty() || jTextFieldMånedInd.getText().isEmpty() || jTextFieldÅrInd.getText().isEmpty()) {
            jLabelErrorOrdre.setText("Datoer mangler.");
        } else {
            update();
        }
    }//GEN-LAST:event_jButtonOrdreCheckDatoActionPerformed

    private void jButtonOrdreStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrdreStatusActionPerformed
        jLabelErrorOrdre.setText("");
        Ordre selected = (Ordre) jList3.getSelectedValue();
        if (selected != null) {
            JOptionPane.showMessageDialog(rootPane, selected.getStatus(), "Status for ordre: " + selected.getOnummer(), WIDTH);
        }
    }//GEN-LAST:event_jButtonOrdreStatusActionPerformed

    private void jButtonOrdrePakkelisteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrdrePakkelisteActionPerformed
        jLabelErrorOrdre.setText("");
        int selectedIndex = jList3.getSelectedIndex();
        if (jList3.getSelectedValue() != null) {
            controller.pakkeliste((Ordre) jList3.getSelectedValue());
            update();
            controller.setCurrentOrder((Ordre) list3.getElementAt(selectedIndex));
            controller.PDFPakkeliste();
        }
    }//GEN-LAST:event_jButtonOrdrePakkelisteActionPerformed

    private void jTextFieldRabatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRabatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRabatActionPerformed

    private void jButtonOrdreTilbudPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrdreTilbudPDFActionPerformed
        jLabelErrorOrdre.setText("");
        int selectedIndex = jList3.getSelectedIndex();
        if (jList3.getSelectedValue() != null) {
            controller.tilbud((Ordre) jList3.getSelectedValue());
            update();
            controller.setCurrentOrder((Ordre) list3.getElementAt(selectedIndex));
            controller.pdfTilbud();
        }
    }//GEN-LAST:event_jButtonOrdreTilbudPDFActionPerformed

    private void jButtonOrdreFakturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrdreFakturaActionPerformed
        jLabelErrorOrdre.setText("");
        int selectedIndex = jList3.getSelectedIndex();
        if (jList3.getSelectedValue() != null) {
            controller.faktura((Ordre) jList3.getSelectedValue());
            update();
            controller.setCurrentOrder((Ordre) list3.getElementAt(selectedIndex));
            controller.pdfFaktura();
        }
    }//GEN-LAST:event_jButtonOrdreFakturaActionPerformed

    private void jButtonOrdreRedigerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrdreRedigerActionPerformed
        jLabelErrorOrdre.setText("");
        Ordre selected = (Ordre) jList3.getSelectedValue();
        if (selected != null) {
            if (list2.isEmpty() == false) {
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
                update();
                Kunde kunde = controller.getKunde(selected.getKnummer());
                for (int i = 0; i < jComboBoxKunder.getItemCount(); i++) {
                    if (jComboBoxKunder.getItemAt(i).equals(kunde.getNavn())) {
                        jComboBoxKunder.setSelectedIndex(i);
                    }
                }
            } else {
                jLabelErrorOrdre.setText("Du skal redigere ordren færdig før du redigerer en ny.");
            }
        }
    }//GEN-LAST:event_jButtonOrdreRedigerActionPerformed

    private void jRadioButtonLeveringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonLeveringActionPerformed
        jComboBoxLevering.setEnabled(true);
        jComboBoxAfhentning.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonLeveringActionPerformed

    private void jRadioButtonAfhentningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAfhentningActionPerformed
        jComboBoxLevering.setEnabled(false);
        jComboBoxAfhentning.setEnabled(false);
    }//GEN-LAST:event_jRadioButtonAfhentningActionPerformed

    private void jButtonOrdreGennemførOrdreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrdreGennemførOrdreActionPerformed
        jLabelErrorOrdre.setText("");
        ArrayList<Odetaljer> odetaljer = new ArrayList<>();
        ArrayList<DelOrdre> delordre = new ArrayList<>();
        if (!list2.isEmpty()) {
            if (jComboBoxKunder.getSelectedItem() != "Kunder") {
                if (list2.isEmpty() == false) {
                    if (jRadioButtonAfhentning.isSelected() || jRadioButtonLevering.isSelected()) {
                        for (int i = 0; i < list2.size(); i++) {
                            Vare vare = (Vare) list2.getElementAt(i);
                            odetaljer.add(new Odetaljer(0, vare.getVnummer(), vare.getQty()));
                            for (int j = 0; j < vare.getDel().size(); j++) {
                                delordre.add(new DelOrdre(vare.getDel().get(j).getTitel(), vare.getVnummer(), 0, 1, vare.getDel().get(j).getAntal()));
                            }
                        }
                        String leveringDate = Integer.parseInt(jTextFieldDagUd.getText()) + "-" + Integer.parseInt(jTextFieldMånedUd.getText()) + "-" + Integer.parseInt(jTextFieldÅrUd.getText());
                        String returneringDate = Integer.parseInt(jTextFieldDagInd.getText()) + "-" + Integer.parseInt(jTextFieldMånedInd.getText()) + "-" + Integer.parseInt(jTextFieldÅrInd.getText());
                        controller.gennemførOrdrer(jRadioButtonAfhentning.isSelected(), "" + jComboBoxKunder.getSelectedItem(), Double.parseDouble(jTextFieldTotalPris.getText()), Double.parseDouble(jTextFieldRabat.getText()), Double.parseDouble(jTextFieldTotalPris.getText()) * 0.25, "" + jComboBoxLevering.getSelectedItem(), "" + jComboBoxAfhentning.getSelectedItem(), leveringDate, returneringDate, odetaljer, "", delordre);
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

                    } else {
                        jLabelErrorOrdre.setText("Vælg afhentning eller levering.");
                    }
                }
            } else {
                jLabelErrorOrdre.setText("Vælg en kunde.");
            }
            controller.redigerFalse();
            update();
        }
    }//GEN-LAST:event_jButtonOrdreGennemførOrdreActionPerformed

    private void jButtonOrdreFjernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrdreFjernActionPerformed
        jLabelErrorOrdre.setText("");
        Vare selected = (Vare) jList2.getSelectedValue();
        if (selected != null) {
            if (selected.getQty() >= Integer.parseInt(jTextFieldAntal.getText())) {
                list2.removeElement(selected);
                selected.setQty(selected.getQty() - (Integer.parseInt(jTextFieldAntal.getText())));
                list2.addElement(selected);
            } else {
                jLabelErrorOrdre.setText("Du kan ikke fjerne flere varer fra ordren end den indeholder.");
            }
            if (selected.getQty() == 0) {
                list2.removeElement(selected);
            }
            update();
        }
    }//GEN-LAST:event_jButtonOrdreFjernActionPerformed
    private void jButtonOrdreTilføjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrdreTilføjActionPerformed
        boolean check = false;
        Vare selected = (Vare) jList1.getSelectedValue();
        if (selected != null) {
            selected.setQty(Integer.parseInt(jTextFieldAntal.getText()));
            if (controller.checkQty(selected.getVnummer(), Integer.parseInt(jTextFieldAntal.getText()))) {
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
                jLabelErrorOrdre.setText("");
            } else {
                jLabelErrorOrdre.setText("Der er ikke nok varer på lager til ordren.");
            }
            update();
        }
    }//GEN-LAST:event_jButtonOrdreTilføjActionPerformed

    private void jButtonReturLevrérActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturLevrérActionPerformed
        listReturDele.clear();
        Ordre selected = (Ordre) jListOrdrer.getSelectedValue();
        if (selected != null) {
            for (int i = 0; i < selected.getDelo().size(); i++) {
                listReturDele.addElement(selected.getDelo().get(i));
            }
            boolean status = true;
            for (int i = 0; i < listReturDele.size(); i++) {
                DelOrdre delordre = (DelOrdre) listReturDele.get(i);
                status = status && delordre.getStatus() == 1;
            }
            if (status) {
                jButtonReturAfslutOrdre.setEnabled(true);
            } else {
                jButtonReturAfslutOrdre.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jButtonReturLevrérActionPerformed

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
                DelOrdre delordre = (DelOrdre) value;  // Using value we are getting the object in JList
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setText(delordre.toString());  // Setting the text
                return label;
            }
        });
    }

    public void update() {
        list1.clear();
        list3.clear();

        listHistorik.clear();
        listOrdrer.clear();
        jComboBoxKunder.removeAllItems();
        jComboBoxKunder.addItem("Kunder");

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
        ArrayList<Kunde> kl = controller.getAllCustomers();
        Kunde[] ka = new Kunde[kl.size()];
        for (int i = 0; i < kl.size(); i++) {
            ka[i] = kl.get(i);
        }
        controller.quickSortKunde(ka, 0, ka.length - 1);
        for (int i = 0; i < ka.length; i++) {
            jComboBoxKunder.addItem(ka[i].getNavn());
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
        jComboBoxLevering.removeAllItems();
        jComboBoxLevering.addItem("Levering");
        jComboBoxLevering.addItem("08:00");
        jComboBoxLevering.addItem("10:00");
        jComboBoxLevering.addItem("12:00");
        jComboBoxLevering.addItem("14:00");
        jComboBoxLevering.addItem("16:00");
        jComboBoxLevering.addItem("Stor Order");
        jComboBoxAfhentning.removeAllItems();
        jComboBoxAfhentning.addItem("Afhentning");
        jComboBoxAfhentning.addItem("08:00");
        jComboBoxAfhentning.addItem("10:00");
        jComboBoxAfhentning.addItem("12:00");
        jComboBoxAfhentning.addItem("14:00");
        jComboBoxAfhentning.addItem("16:00");
        jComboBoxAfhentning.addItem("Stor Order");
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
                            for (int l = 1; l < jComboBoxLevering.getItemCount(); l++) {
                                if (o.getTidLev().equals("" + jComboBoxLevering.getItemAt(l))) {
                                    tidLev[l - 1]++;
                                    if (tidLev[l - 1] >= 2) {
                                        jComboBoxLevering.removeItemAt(l);
                                    }
                                    try {
                                        jComboBoxLevering.removeItemAt(6);
                                    } catch (Exception ex) {
                                    }
                                } else if (o.getTidLev().equals("Stor Order")) {
                                    jComboBoxLevering.removeAllItems();
                                    jComboBoxLevering.addItem("Levering");
                                }
                            }
                        }
                        if ((retDay == oRetDay && retMonth == oRetMonth && retYear == oRetYear) || (retDay == oLevDay && retMonth == oLevMonth && retYear == oLevYear)) {
                            for (int l = 1; l < jComboBoxAfhentning.getItemCount(); l++) {
                                if (o.getTidRet().equals("" + jComboBoxAfhentning.getItemAt(l))) {
                                    tidRet[l - 1]++;
                                    if (tidRet[l - 1] >= 2) {
                                        jComboBoxAfhentning.removeItemAt(l);
                                    }
                                    try {
                                        jComboBoxAfhentning.removeItemAt(6);
                                    } catch (Exception ex) {
                                    }
                                } else if (o.getTidRet().equals("Stor Order")) {
                                    jComboBoxAfhentning.removeAllItems();
                                    jComboBoxAfhentning.addItem("Afhentning");
                                }
                            }
                        }
                    }
                } else {
                    if (o.getOnummer() != controller.getCurrentOrder().getOnummer()) {
                        if (o.getAfhentning().equalsIgnoreCase("Leveres af os")) {
                            if ((levDay == oLevDay && levMonth == oLevMonth && levYear == oLevYear) || (levDay == oRetDay && levMonth == oRetMonth && levYear == oRetYear)) {
                                for (int l = 1; l < jComboBoxLevering.getItemCount(); l++) {
                                    if (o.getTidLev().equals("" + jComboBoxLevering.getItemAt(l))) {
                                        tidLev[l - 1]++;
                                        if (tidLev[l - 1] >= 2) {
                                            jComboBoxLevering.removeItemAt(l);
                                        }
                                        try {
                                            jComboBoxLevering.removeItemAt(6);
                                        } catch (Exception ex) {
                                        }
                                    } else if (o.getTidLev().equals("Stor Order")) {
                                        jComboBoxLevering.removeAllItems();
                                        jComboBoxLevering.addItem("Levering");
                                    }
                                }
                            }
                            if ((retDay == oRetDay && retMonth == oRetMonth && retYear == oRetYear) || (retDay == oLevDay && retMonth == oLevMonth && retYear == oLevYear)) {
                                for (int l = 1; l < jComboBoxAfhentning.getItemCount(); l++) {
                                    if (o.getTidRet().equals("" + jComboBoxAfhentning.getItemAt(l))) {
                                        tidRet[l - 1]++;
                                        if (tidRet[l - 1] >= 2) {
                                            jComboBoxAfhentning.removeItemAt(l);
                                        }
                                        try {
                                            jComboBoxAfhentning.removeItemAt(6);
                                        } catch (Exception ex) {
                                        }
                                    } else if (o.getTidRet().equals("Stor Order")) {
                                        jComboBoxAfhentning.removeAllItems();
                                        jComboBoxAfhentning.addItem("Afhentning");
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
            jTextFieldVareNavn.setText("");
            jTextFieldVarePris.setText("");
            jTextFieldVareAntal.setText("");
            jTextFieldPartNavn.setText("");
            jTextFieldPartAntal.setText("");
            listLagerDele.clear();
        }
        // Kundeliste 
        ArrayList<Kunde> kl9 = controller.getAllCustomers();
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
    private javax.swing.JButton jButtonHistorikAfslut;
    private javax.swing.JButton jButtonHistorikFaktura;
    private javax.swing.JButton jButtonHistorikOrdre;
    private javax.swing.JButton jButtonHistorikSlet;
    private javax.swing.JButton jButtonHistorikSøg;
    private javax.swing.JButton jButtonHistorikTilbud;
    private javax.swing.JButton jButtonKundeGem;
    private javax.swing.JButton jButtonKundeRediger;
    private javax.swing.JButton jButtonKundeSøg;
    private javax.swing.JButton jButtonLagerFjern;
    private javax.swing.JButton jButtonLagerGem;
    private javax.swing.JButton jButtonLagerRedigerDel;
    private javax.swing.JButton jButtonLagerRedigerVare;
    private javax.swing.JButton jButtonLagerSøg;
    private javax.swing.JButton jButtonLagerTilføj;
    private javax.swing.JButton jButtonOrdre1;
    private javax.swing.JButton jButtonOrdre3;
    private javax.swing.JButton jButtonOrdreCheckDato;
    private javax.swing.JButton jButtonOrdreFaktura;
    private javax.swing.JButton jButtonOrdreFjern;
    private javax.swing.JButton jButtonOrdreGennemførOrdre;
    private javax.swing.JButton jButtonOrdreOrdrePDF;
    private javax.swing.JButton jButtonOrdrePakkeliste;
    private javax.swing.JButton jButtonOrdreRediger;
    private javax.swing.JButton jButtonOrdreStatus;
    private javax.swing.JButton jButtonOrdreTilbudPDF;
    private javax.swing.JButton jButtonOrdreTilføj;
    private javax.swing.JButton jButtonReturAfslutOrdre;
    private javax.swing.JButton jButtonReturHjemme;
    private javax.swing.JButton jButtonReturLevrér;
    private javax.swing.JButton jButtonReturSøg;
    private javax.swing.JButton jButtonReturUde;
    private javax.swing.JButton jButtonTilbud1;
    private javax.swing.JButton jButtonTilbud3;
    private javax.swing.JComboBox jComboBoxAfhentning;
    private javax.swing.JComboBox jComboBoxKunder;
    private javax.swing.JComboBox jComboBoxLevering;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelErrorKunder;
    private javax.swing.JLabel jLabelErrorLager;
    private javax.swing.JLabel jLabelErrorOrdre;
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
    private javax.swing.JList jListVareliste;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelHistorik;
    private javax.swing.JPanel jPanelKunder;
    private javax.swing.JPanel jPanelLager;
    private static javax.swing.JPanel jPanelOrdre;
    private javax.swing.JPanel jPanelRetur;
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
    private javax.swing.JScrollPane jScrollPane9;
    private static javax.swing.JTabbedPane jTabbedPane1;
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
    private javax.swing.JTextField jTextFieldPartAntal;
    private javax.swing.JTextField jTextFieldPartNavn;
    private javax.swing.JTextField jTextFieldPostnummer;
    private javax.swing.JTextField jTextFieldRabat;
    private javax.swing.JTextField jTextFieldReturSøg;
    private javax.swing.JTextField jTextFieldTelefonnummer;
    private javax.swing.JTextField jTextFieldTotalPris;
    private javax.swing.JTextField jTextFieldVareAntal;
    private javax.swing.JTextField jTextFieldVareNavn;
    private javax.swing.JTextField jTextFieldVarePris;
    private javax.swing.JTextField jTextFieldÅrInd;
    private javax.swing.JTextField jTextFieldÅrUd;
    // End of variables declaration//GEN-END:variables
}
