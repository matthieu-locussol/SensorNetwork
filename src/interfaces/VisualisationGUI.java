package interfaces;

import capteur.Capteur;
import capteurtype.CapteurExterieurType;
import capteurtype.CapteurInterieurType;
import capteurtype.CapteurType;
import custom.EnergyTree;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import util.Alert;
import util.GraphPanel;
import util.SimulationConnexion;
import util.SimulationError;
import util.VisualisationClient;


public class VisualisationGUI extends javax.swing.JFrame {

  private static final long serialVersionUID = 3202495613180723843L;

  private VisualisationClient client;

  private javax.swing.JButton buttonAddAlert, buttonConnect, buttonDisconnect,
      buttonRename;
  private javax.swing.JComboBox<String> comboboxTypeDonnees;
  private javax.swing.JTextField inputValMax, inputValMin, inputRename;
  private javax.swing.JLabel labelAddAlert, labelName, labelServerIP,
      labelTypeDonnees, labelUnite1, labelUnite2, labelValMax, labelValMin,
      labelVisualisation, labelInfo1, labelInfo2;
  private javax.swing.JPanel panelAlerts, panelFollow, panelSensors,
      panelHistoric;
  private javax.swing.JScrollPane scrollpaneTableAlerts, scrollpaneTableFollow,
      scrollpaneTableTree, scrollpaneTree, scrollpaneTableHistoric;
  private javax.swing.JSeparator sepAddAlert, sepVertical, sepVerticalAlert;
  private javax.swing.JTabbedPane tabbedPane;
  private javax.swing.JTable tableAlerts, tableFollow, tableTree, tableHistoric;
  private EnergyTree tree;

  private TreeSet<String> followSet = new TreeSet<>();
  private TreeSet<String> setTypes;

  private TreeSet<Alert> alertsSet = new TreeSet<>();

  private HashMap<String, GraphPanel> mapHistoric = new HashMap<>();

  public VisualisationGUI() {
    tableTree = new javax.swing.JTable();
    tableFollow = new javax.swing.JTable();
    tree =
        new EnergyTree(tableTree, followSet, new TreeSet<>(), new TreeSet<>());
    this.client = new VisualisationClient(tree, tableFollow);
    initializeVisualisationGUI();
  }

  private void initializeVisualisationGUI() {
    labelVisualisation = new javax.swing.JLabel();
    labelName = new javax.swing.JLabel();
    labelServerIP = new javax.swing.JLabel();
    tabbedPane = new javax.swing.JTabbedPane();
    panelSensors = new javax.swing.JPanel();
    scrollpaneTree = new javax.swing.JScrollPane();
    sepVertical = new javax.swing.JSeparator();
    scrollpaneTableTree = new javax.swing.JScrollPane();
    panelFollow = new javax.swing.JPanel();
    scrollpaneTableFollow = new javax.swing.JScrollPane();
    panelAlerts = new javax.swing.JPanel();
    sepVerticalAlert = new javax.swing.JSeparator();
    labelTypeDonnees = new javax.swing.JLabel();
    comboboxTypeDonnees = new javax.swing.JComboBox<>();
    labelAddAlert = new javax.swing.JLabel();
    sepAddAlert = new javax.swing.JSeparator();
    labelValMin = new javax.swing.JLabel();
    inputValMin = new javax.swing.JTextField();
    labelValMax = new javax.swing.JLabel();
    inputValMax = new javax.swing.JTextField();
    labelUnite1 = new javax.swing.JLabel();
    labelUnite2 = new javax.swing.JLabel();
    scrollpaneTableAlerts = new javax.swing.JScrollPane();
    tableAlerts = new javax.swing.JTable();
    buttonAddAlert = new javax.swing.JButton();
    buttonConnect = new javax.swing.JButton();
    buttonDisconnect = new javax.swing.JButton();
    inputRename = new javax.swing.JTextField();
    buttonRename = new javax.swing.JButton();
    labelInfo1 = new javax.swing.JLabel();
    labelInfo2 = new javax.swing.JLabel();
    panelHistoric = new javax.swing.JPanel();
    scrollpaneTableHistoric = new javax.swing.JScrollPane();
    tableHistoric = new javax.swing.JTable();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setMinimumSize(new java.awt.Dimension(1024, 768));
    setPreferredSize(new java.awt.Dimension(1024, 768));
    setSize(new java.awt.Dimension(1024, 768));

    labelVisualisation.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
    labelVisualisation.setText("Interface de Visualisation");

    labelName.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
    labelName.setForeground(new java.awt.Color(58, 84, 135));
    labelName.setText("\"MaVisu1\"");

    labelServerIP.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
    labelServerIP.setForeground(new java.awt.Color(195, 83, 68));
    labelServerIP.setText("Déconnecté (0.0.0.0:0)");

    tabbedPane.setFocusable(false);
    tabbedPane.setFont(new java.awt.Font("Open Sans", 1, 12));

    tree.getTree().setFont(new java.awt.Font("Open Sans", 0, 12));
    tree.getTree().setBorder(
        javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
    tree.getTree().setFocusable(false);
    scrollpaneTree.setViewportView(tree.getTree());

    sepVertical.setOrientation(javax.swing.SwingConstants.VERTICAL);

    tableTree.setFocusable(false);
    tableTree.setRowSelectionAllowed(false);
    scrollpaneTableTree.setViewportView(tableTree);

    javax.swing.GroupLayout panelSensorsLayout =
        new javax.swing.GroupLayout(panelSensors);
    panelSensors.setLayout(panelSensorsLayout);
    panelSensorsLayout.setHorizontalGroup(
        panelSensorsLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                panelSensorsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollpaneTree,
                                  javax.swing.GroupLayout.PREFERRED_SIZE, 267,
                                  javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(sepVertical,
                                  javax.swing.GroupLayout.PREFERRED_SIZE, 15,
                                  javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollpaneTableTree,
                                  javax.swing.GroupLayout.DEFAULT_SIZE, 674,
                                  Short.MAX_VALUE)
                    .addContainerGap()));
    panelSensorsLayout.setVerticalGroup(
        panelSensorsLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                panelSensorsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(
                        panelSensorsLayout
                            .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollpaneTableTree,
                                          javax.swing.GroupLayout.DEFAULT_SIZE,
                                          620, Short.MAX_VALUE)
                            .addComponent(
                                sepVertical,
                                javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(
                                scrollpaneTree,
                                javax.swing.GroupLayout.Alignment.LEADING))
                    .addContainerGap()));

    tabbedPane.addTab("Capteurs présents sur le réseau", panelSensors);

    tableFollow.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {},
        new String[] {"Identifiant", "Type de données", "Valeur"}));
    tableFollow.setFocusable(false);
    tableFollow.setRowSelectionAllowed(false);
    scrollpaneTableFollow.setViewportView(tableFollow);

    javax.swing.GroupLayout panelFollowLayout =
        new javax.swing.GroupLayout(panelFollow);
    panelFollow.setLayout(panelFollowLayout);
    panelFollowLayout.setHorizontalGroup(
        panelFollowLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFollowLayout.createSequentialGroup()
                          .addContainerGap()
                          .addComponent(scrollpaneTableFollow,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        981, Short.MAX_VALUE)
                          .addContainerGap()));
    panelFollowLayout.setVerticalGroup(
        panelFollowLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFollowLayout.createSequentialGroup()
                          .addContainerGap()
                          .addComponent(scrollpaneTableFollow,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        620, Short.MAX_VALUE)
                          .addContainerGap()));

    tabbedPane.addTab("Capteurs suivis", panelFollow);

    sepVerticalAlert.setOrientation(javax.swing.SwingConstants.VERTICAL);

    labelTypeDonnees.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
    labelTypeDonnees.setText("Type de données");

    setTypes = new TreeSet<>();
    for (String s : CapteurExterieurType.getValues())
      setTypes.add(s);
    for (String s : CapteurInterieurType.getValues())
      setTypes.add(s);

    comboboxTypeDonnees.setModel(new javax.swing.DefaultComboBoxModel<>(
        setTypes.toArray(new String[setTypes.size()])));
    comboboxTypeDonnees.setFocusable(false);

    labelAddAlert.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
    labelAddAlert.setText("Ajout d'une alerte");

    labelValMin.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
    labelValMin.setText("Valeur minimale");

    inputValMin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    inputValMin.setText("0.0");
    inputValMin.setPreferredSize(new java.awt.Dimension(56, 20));

    labelValMax.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
    labelValMax.setText("Valeur maximale");

    inputValMax.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    inputValMax.setText("0.0");
    inputValMax.setPreferredSize(new java.awt.Dimension(56, 20));

    labelUnite1.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
    labelUnite1.setForeground(new java.awt.Color(58, 84, 135));
    labelUnite1.setText("(W)");

    labelUnite2.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
    labelUnite2.setForeground(new java.awt.Color(58, 84, 135));
    labelUnite2.setText("(W)");

    tableAlerts.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {}, new String[] {"Type de l'alerte", "Valeur minimale",
                                         "Valeur maximale"}));
    tableAlerts.setFocusable(false);
    tableAlerts.setRowSelectionAllowed(false);
    scrollpaneTableAlerts.setViewportView(tableAlerts);

    buttonAddAlert.setText("Ajouter");
    buttonAddAlert.setFocusable(false);

    labelInfo1.setFont(new java.awt.Font("Open Sans", 2, 13)); // NOI18N
    labelInfo1.setText("(Double-cliquez sur une alerte pour la");

    labelInfo2.setFont(new java.awt.Font("Open Sans", 2, 13)); // NOI18N
    labelInfo2.setText("supprimer rapidement)");

    javax.swing.GroupLayout panelAlertsLayout =
        new javax.swing.GroupLayout(panelAlerts);
    panelAlerts.setLayout(panelAlertsLayout);
    panelAlertsLayout.setHorizontalGroup(
        panelAlertsLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                panelAlertsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(
                        panelAlertsLayout
                            .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                panelAlertsLayout
                                    .createParallelGroup(javax.swing.GroupLayout
                                                             .Alignment.LEADING,
                                                         false)
                                    .addComponent(labelTypeDonnees)
                                    .addComponent(labelAddAlert)
                                    .addComponent(
                                        sepAddAlert,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        279, Short.MAX_VALUE)
                                    .addComponent(labelValMin)
                                    .addComponent(comboboxTypeDonnees, 0, 279,
                                                  Short.MAX_VALUE)
                                    .addComponent(labelValMax)
                                    .addGroup(
                                        panelAlertsLayout
                                            .createSequentialGroup()
                                            .addGroup(
                                                panelAlertsLayout
                                                    .createParallelGroup(
                                                        javax.swing.GroupLayout
                                                            .Alignment.TRAILING)
                                                    .addComponent(
                                                        buttonAddAlert,
                                                        javax.swing.GroupLayout
                                                            .PREFERRED_SIZE,
                                                        175,
                                                        javax.swing.GroupLayout
                                                            .PREFERRED_SIZE)
                                                    .addGroup(
                                                        panelAlertsLayout
                                                            .createParallelGroup(
                                                                javax.swing
                                                                    .GroupLayout
                                                                    .Alignment
                                                                    .TRAILING,
                                                                false)
                                                            .addComponent(
                                                                inputValMax,
                                                                javax.swing
                                                                    .GroupLayout
                                                                    .Alignment
                                                                    .LEADING,
                                                                javax.swing
                                                                    .GroupLayout
                                                                    .DEFAULT_SIZE,
                                                                230,
                                                                Short.MAX_VALUE)
                                                            .addComponent(
                                                                inputValMin,
                                                                javax.swing
                                                                    .GroupLayout
                                                                    .Alignment
                                                                    .LEADING,
                                                                javax.swing
                                                                    .GroupLayout
                                                                    .DEFAULT_SIZE,
                                                                javax.swing
                                                                    .GroupLayout
                                                                    .DEFAULT_SIZE,
                                                                Short
                                                                    .MAX_VALUE)))
                                            .addPreferredGap(
                                                javax.swing.LayoutStyle
                                                    .ComponentPlacement
                                                    .UNRELATED)
                                            .addGroup(
                                                panelAlertsLayout
                                                    .createParallelGroup(
                                                        javax.swing.GroupLayout
                                                            .Alignment.LEADING)
                                                    .addComponent(labelUnite1)
                                                    .addComponent(
                                                        labelUnite2))))
                            .addComponent(labelInfo1)
                            .addComponent(labelInfo2))
                    .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(sepVerticalAlert,
                                  javax.swing.GroupLayout.PREFERRED_SIZE, 15,
                                  javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollpaneTableAlerts,
                                  javax.swing.GroupLayout.DEFAULT_SIZE, 673,
                                  Short.MAX_VALUE)
                    .addContainerGap()));
    panelAlertsLayout.setVerticalGroup(
        panelAlertsLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                panelAlertsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(
                        panelAlertsLayout
                            .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollpaneTableAlerts)
                            .addComponent(
                                sepVerticalAlert,
                                javax.swing.GroupLayout.Alignment.LEADING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, 620,
                                Short.MAX_VALUE)
                            .addGroup(
                                javax.swing.GroupLayout.Alignment.LEADING,
                                panelAlertsLayout.createSequentialGroup()
                                    .addComponent(labelAddAlert)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle
                                            .ComponentPlacement.RELATED)
                                    .addComponent(
                                        sepAddAlert,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle
                                            .ComponentPlacement.RELATED)
                                    .addComponent(labelTypeDonnees)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle
                                            .ComponentPlacement.RELATED)
                                    .addComponent(
                                        comboboxTypeDonnees,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        29,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle
                                            .ComponentPlacement.UNRELATED)
                                    .addComponent(labelValMin)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle
                                            .ComponentPlacement.RELATED)
                                    .addGroup(panelAlertsLayout
                                                  .createParallelGroup(
                                                      javax.swing.GroupLayout
                                                          .Alignment.BASELINE)
                                                  .addComponent(
                                                      inputValMin,
                                                      javax.swing.GroupLayout
                                                          .PREFERRED_SIZE,
                                                      29,
                                                      javax.swing.GroupLayout
                                                          .PREFERRED_SIZE)
                                                  .addComponent(labelUnite1))
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle
                                            .ComponentPlacement.UNRELATED)
                                    .addComponent(labelValMax)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle
                                            .ComponentPlacement.RELATED)
                                    .addGroup(panelAlertsLayout
                                                  .createParallelGroup(
                                                      javax.swing.GroupLayout
                                                          .Alignment.BASELINE)
                                                  .addComponent(
                                                      inputValMax,
                                                      javax.swing.GroupLayout
                                                          .PREFERRED_SIZE,
                                                      29,
                                                      javax.swing.GroupLayout
                                                          .PREFERRED_SIZE)
                                                  .addComponent(labelUnite2))
                                    .addGap(18, 18, 18)
                                    .addComponent(
                                        buttonAddAlert,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        37,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle
                                            .ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                    .addComponent(labelInfo1)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle
                                            .ComponentPlacement.RELATED)
                                    .addComponent(labelInfo2)))
                    .addContainerGap()));

    tabbedPane.addTab("Alertes", panelAlerts);

    tableHistoric.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {},
        new String[] {"Nom du capteur", "Localisation", "Type de données"}));
    scrollpaneTableHistoric.setViewportView(tableHistoric);

    javax.swing.GroupLayout panelHistoricLayout =
        new javax.swing.GroupLayout(panelHistoric);
    panelHistoric.setLayout(panelHistoricLayout);
    panelHistoricLayout.setHorizontalGroup(
        panelHistoricLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHistoricLayout.createSequentialGroup()
                          .addContainerGap()
                          .addComponent(scrollpaneTableHistoric,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        981, Short.MAX_VALUE)
                          .addContainerGap()));
    panelHistoricLayout.setVerticalGroup(
        panelHistoricLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHistoricLayout.createSequentialGroup()
                          .addContainerGap()
                          .addComponent(scrollpaneTableHistoric,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        605, Short.MAX_VALUE)
                          .addContainerGap()));

    tabbedPane.addTab("Historique", panelHistoric);

    buttonConnect.setFont(new java.awt.Font("Open Sans", 1, 14));
    buttonConnect.setText("Connexion");
    buttonConnect.setFocusable(false);
    buttonConnect.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonConnectActionPerformed(evt);
      }
    });

    buttonDisconnect.setFont(new java.awt.Font("Open Sans", 1, 14));
    buttonDisconnect.setText("Déconnexion");
    buttonDisconnect.setEnabled(false);
    buttonDisconnect.setFocusable(false);
    buttonDisconnect.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonDisconnectActionPerformed(evt);
      }
    });

    tableAlerts.getTableHeader().setFont(new java.awt.Font("Open Sans", 1, 12));
    tableAlerts.setFont(new java.awt.Font("Open Sans", 0, 12));
    tableFollow.getTableHeader().setFont(new java.awt.Font("Open Sans", 1, 12));
    tableFollow.setFont(new java.awt.Font("Open Sans", 0, 12));
    tableHistoric.getTableHeader().setFont(
        new java.awt.Font("Open Sans", 1, 12));
    tableHistoric.setFont(new java.awt.Font("Open Sans", 0, 12));
    tableTree.getTableHeader().setFont(new java.awt.Font("Open Sans", 1, 12));
    tableTree.setFont(new java.awt.Font("Open Sans", 0, 12));

    inputRename.setText("MaVisu1");

    buttonRename.setText("Renommer");
    buttonRename.setFocusable(false);

    javax.swing.GroupLayout layout =
        new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(
                        layout
                            .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(
                                tabbedPane,
                                javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(
                                layout.createSequentialGroup()
                                    .addComponent(labelVisualisation)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle
                                            .ComponentPlacement.RELATED)
                                    .addComponent(labelName)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle
                                            .ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                    .addComponent(labelServerIP))
                            .addGroup(
                                javax.swing.GroupLayout.Alignment.TRAILING,
                                layout.createSequentialGroup()
                                    .addComponent(
                                        inputRename,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        146,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle
                                            .ComponentPlacement.RELATED)
                                    .addComponent(buttonRename)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle
                                            .ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                    .addComponent(
                                        buttonConnect,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        175,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle
                                            .ComponentPlacement.UNRELATED)
                                    .addComponent(
                                        buttonDisconnect,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        175,
                                        javax.swing.GroupLayout
                                            .PREFERRED_SIZE)))
                    .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(
                        layout
                            .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelVisualisation)
                            .addComponent(labelName)
                            .addComponent(labelServerIP))
                    .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(
                        layout
                            .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                layout
                                    .createParallelGroup(
                                        javax.swing.GroupLayout.Alignment
                                            .BASELINE)
                                    .addComponent(
                                        buttonDisconnect,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        37,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(
                                        buttonConnect,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        37,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(
                                layout
                                    .createParallelGroup(
                                        javax.swing.GroupLayout.Alignment
                                            .BASELINE)
                                    .addComponent(
                                        inputRename,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonRename)))
                    .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(tabbedPane)
                    .addContainerGap()));

    this.tableTree.setEnabled(false);
    this.tableTree.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent me) {
        JTable table = (JTable)me.getSource();
        Point p = me.getPoint();
        int row = table.rowAtPoint(p);
        if (me.getClickCount() == 2 && row != -1) {
          DefaultTableModel model = (DefaultTableModel)table.getModel();
          if (!followSet.contains(model.getValueAt(row, 0))) {
            if (tree.getSensorType() == EnergyTree.EXTERIOR)
              model.setValueAt("Oui", row, 4);
            else if (tree.getSensorType() == EnergyTree.INTERIOR)
              model.setValueAt("Oui", row, 6);
            followSet.add((String)model.getValueAt(row, 0));
            client.sendInscriptionCapteur((String)model.getValueAt(row, 0));
          } else {
            if (tree.getSensorType() == EnergyTree.EXTERIOR)
              model.setValueAt("Non", row, 4);
            else if (tree.getSensorType() == EnergyTree.INTERIOR)
              model.setValueAt("Non", row, 6);
            followSet.remove(model.getValueAt(row, 0));
            client.sendDesinscriptionCapteur((String)model.getValueAt(row, 0));
          }
          updateFollowTable();
        }
      }
    });

    this.tableFollow.setEnabled(false);

    this.tableAlerts.setEnabled(false);

    this.tableAlerts.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent me) {
        JTable table = (JTable)me.getSource();
        Point p = me.getPoint();
        int row = table.rowAtPoint(p);
        if (me.getClickCount() == 2 && row != -1) {
          DefaultTableModel model = (DefaultTableModel)table.getModel();
          CapteurType type = (CapteurType)model.getValueAt(row, 0);
          Alert degenerateAlert = new Alert(type, 0.0f, 0.0f);
          alertsSet.remove(degenerateAlert);
          updateAlertsTable();
        }
      }
    });

    this.buttonRename.setEnabled(false);

    this.inputRename.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent e) { buttonRename.setEnabled(true); }
    });

    this.buttonRename.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { renameIdentifiant(); }
    });

    this.buttonAddAlert.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (Float.parseFloat(inputValMin.getText()) >
            Float.parseFloat(inputValMax.getText())) {
          SimulationError sim = new SimulationError(
              "La valeur min doit être <= à la valeur max !");
          sim.show();
        } else {
          Float minValue = Float.parseFloat(inputValMin.getText());
          Float maxValue = Float.parseFloat(inputValMax.getText());
          CapteurType type = CapteurExterieurType.getTypeByName(
              (String)comboboxTypeDonnees.getSelectedItem());
          if (type == null)
            type = CapteurInterieurType.getTypeByName(
                (String)comboboxTypeDonnees.getSelectedItem());

          Alert newAlert = new Alert(type, minValue, maxValue);
          if (alertsSet.contains(newAlert)) {
            SimulationError sim = new SimulationError(
                "Une alerte pour ce type est déjà définie !");
            sim.show();
          } else
            alertsSet.add(newAlert);
          updateAlertsTable();
        }
      }
    });

    this.comboboxTypeDonnees.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        CapteurType type = CapteurInterieurType.getTypeByName(
            (String)comboboxTypeDonnees.getSelectedItem());
        if (type == null)
          type = CapteurExterieurType.getTypeByName(
              (String)comboboxTypeDonnees.getSelectedItem());
        labelUnite1.setText("(" + type.getUnite() + ")");
        labelUnite2.setText("(" + type.getUnite() + ")");
      }
    });

    this.tableFollow.setDefaultRenderer(
        Object.class, new DefaultTableCellRenderer() {
          private static final long serialVersionUID = 5444464302866117265L;

          public Component getTableCellRendererComponent(
              JTable table, Object value, boolean isSelected, boolean hasFocus,
              int row, int column) {
            final Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
            Color cellColor = Color.WHITE;
            int style = 0;

            DefaultTableModel model = (DefaultTableModel)tableFollow.getModel();
            CapteurType type = CapteurExterieurType.getTypeByName(
                (String)model.getValueAt(row, 1));
            if (type == null)
              type = CapteurInterieurType.getTypeByName(
                  (String)model.getValueAt(row, 1));

            Alert alt = null;
            for (Alert a : alertsSet)
              if (a.getType() == type)
                alt = a;

            if (alt != null) {
              float sensorValue =
                  Float.parseFloat((String)model.getValueAt(row, 2));
              if (sensorValue < alt.getMinValue() ||
                  sensorValue > alt.getMaxValue()) {
                cellColor = new Color(195, 83, 68);
                style = Font.BOLD;
              }
            }

            c.setBackground(cellColor);
            c.setFont(c.getFont().deriveFont(style));
            return c;
          }
        });

    this.tableTree.setDefaultRenderer(
        Object.class, new DefaultTableCellRenderer() {
          private static final long serialVersionUID = 2954364891235495808L;

          public Component getTableCellRendererComponent(
              JTable table, Object value, boolean isSelected, boolean hasFocus,
              int row, int column) {
            final Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
            int style = 0;

            DefaultTableModel model = (DefaultTableModel)tableTree.getModel();
            if (model.getValueAt(row, model.getColumnCount() - 1).equals("Oui"))
              style = Font.BOLD;

            c.setFont(c.getFont().deriveFont(style));
            model.fireTableCellUpdated(row, column);
            return c;
          }
        });

    initializeTableHistoric();

    this.tableHistoric.setEnabled(false);
    this.tableHistoric.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent me) {
        JTable table = (JTable)me.getSource();
        Point p = me.getPoint();
        int row = table.rowAtPoint(p);
        if (me.getClickCount() == 2 && row != -1) {
          DefaultTableModel model = (DefaultTableModel)table.getModel();
          String sensorName = (String)model.getValueAt(row, 0);
          GraphPanel chart = mapHistoric.get(sensorName);
          chart.show();
        }
      }
    });

    pack();
  }

  private void initializeTableHistoric() {
    try {
      DefaultTableModel model = (DefaultTableModel)tableHistoric.getModel();

      LineNumberReader fileHistoric =
          new LineNumberReader(new FileReader("historique.txt"));

      while (fileHistoric.ready()) {
        String line = fileHistoric.readLine();
        String[] split_line = line.split(";");
        if (split_line.length == 4) {
          String sensorName = split_line[0];
          String localisation = split_line[1] + ";" + split_line[2];
          CapteurExterieurType type =
              CapteurExterieurType.getTypeByName(split_line[3]);

          model.addRow(new Object[] {sensorName, localisation, type});

          List<Float> listeValeurs = new ArrayList<Float>();

          line = fileHistoric.readLine();
          split_line = line.split(";");
          int nbJours = Integer.parseInt(split_line[0]);
          int valeursParJours = Integer.parseInt(split_line[1]);

          for (int i = 0; i < nbJours * valeursParJours; ++i) {
            line = fileHistoric.readLine();
            listeValeurs.add(Float.parseFloat(line));
          }

          GraphPanel chartTest = new GraphPanel(listeValeurs, sensorName);

          mapHistoric.put(sensorName, chartTest);
        } else if (split_line.length == 6) {
          String sensorName = split_line[0];
          String localisation = "Bât." + split_line[1] + ", Étage " +
                                split_line[2] + ", Salle " + split_line[3] +
                                " (" + split_line[4] + ")";
          CapteurInterieurType type =
              CapteurInterieurType.getTypeByName(split_line[5]);

          model.addRow(new Object[] {sensorName, localisation, type});

          List<Float> listeValeurs = new ArrayList<Float>();

          line = fileHistoric.readLine();
          split_line = line.split(";");
          int nbJours = Integer.parseInt(split_line[0]);
          int valeursParJours = Integer.parseInt(split_line[1]);

          for (int i = 0; i < nbJours * valeursParJours; ++i) {
            line = fileHistoric.readLine();
            listeValeurs.add(Float.parseFloat(line));
          }

          GraphPanel chartTest = new GraphPanel(listeValeurs, sensorName);

          mapHistoric.put(sensorName, chartTest);
        }
      }

      fileHistoric.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void buttonConnectActionPerformed(java.awt.event.ActionEvent evt) {
    SimulationConnexion sim = new SimulationConnexion("0.0.0.0", 0);
    sim.show();

    if (sim.isAddressValid()) {
      client.setServerIp(sim.getIp());
      client.setServerPort(sim.getPort());

      if (client.connectToServer(getIdentifiant())) {
        buttonDisconnect.setEnabled(true);
        buttonConnect.setEnabled(false);
        labelServerIP.setText("Connecté (" + client.getServerIp() + ":" +
                              client.getServerPort() + ")");
        labelServerIP.setForeground(new java.awt.Color(48, 134, 90));
      } else {
        labelServerIP.setText("Déconnecté (" + client.getServerIp() + ":" +
                              client.getServerPort() + ")");
      }
    }
  }

  private void buttonDisconnectActionPerformed(java.awt.event.ActionEvent evt) {
    buttonDisconnect.setEnabled(false);
    buttonConnect.setEnabled(true);
    labelServerIP.setText("Déconnecté (0.0.0.0:0)");
    labelServerIP.setForeground(new java.awt.Color(195, 83, 68));
  }

  private void renameIdentifiant() {
    buttonRename.setEnabled(false);
    labelName.setText("\"" + inputRename.getText() + "\"");
  }

  private String getIdentifiant() { return labelName.getText(); }

  private void updateFollowTable() {
    DefaultTableModel modelFollow = (DefaultTableModel)tableFollow.getModel();

    for (int i = modelFollow.getRowCount() - 1; i >= 0; --i)
      modelFollow.removeRow(i);

    for (String s : followSet) {
      Capteur c = tree.getCapteurExterieurFromId(s);
      if (c == null)
        c = tree.getCapteurInterieurFromId(s);
      modelFollow.addRow(new Object[] {c.getIdentifiant(),
                                       c.getType().toString(),
                                       Float.toString(c.getCurrentValue()),
                                       Long.toString(c.getFrequence())});
    }
  }

  private void updateAlertsTable() {
    DefaultTableModel modelAlerts = (DefaultTableModel)tableAlerts.getModel();

    for (int i = modelAlerts.getRowCount() - 1; i >= 0; --i)
      modelAlerts.removeRow(i);

    for (Alert alt : alertsSet)
      modelAlerts.addRow(
          new Object[] {alt.getType(), alt.getMinValue(), alt.getMaxValue()});
  }

  public static void main(String args[]) {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    try {
      ge.registerFont(
          Font.createFont(Font.TRUETYPE_FONT, new File("OpenSans.ttf")));
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }

    VisualisationGUI visual = new VisualisationGUI();
    visual.setVisible(true);
    visual.setLocationRelativeTo(null);
  }
}
