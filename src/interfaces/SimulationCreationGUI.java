package interfaces;

import capteur.Capteur;
import capteur.CapteurExterieur;
import capteur.CapteurInterieur;
import capteurtype.CapteurExterieurType;
import capteurtype.CapteurInterieurType;
import capteurtype.CapteurType;
import config.Config;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import util.SimulationError;


public class SimulationCreationGUI extends JFrame {

  private static final long serialVersionUID = 3733745462920407044L;

  private Font openSans11, openSans12, openSans14, openSans18;

  private JButton guiButtonExteriorClear, guiButtonExteriorSubmit,
      guiButtonInteriorClear, guiButtonInteriorSubmit;
  private JComboBox<String> guiComboboxExteriorType, guiComboboxInteriorBat,
      guiComboboxInteriorEtage, guiComboboxInteriorSalle,
      guiComboboxInteriorType;
  private JTextField guiInputExteriorId, guiInputExteriorLatitude,
      guiInputExteriorLongitude, guiInputExteriorMax, guiInputExteriorMin,
      guiInputInteriorId, guiInputInteriorMax, guiInputInteriorMin;
  private JLabel guiLabelExteriorId, guiLabelExteriorLatitude,
      guiLabelExteriorLongitude, guiLabelExteriorMax, guiLabelExteriorMin,
      guiLabelExteriorTitle, guiLabelExteriorType, guiLabelInteriorBat,
      guiLabelInteriorEtage, guiLabelInteriorId, guiLabelInteriorMax,
      guiLabelInteriorMin, guiLabelInteriorRelative, guiLabelInteriorSalle,
      guiLabelInteriorTitle, guiLabelInteriorType;
  private JPanel guiPanelExterior, guiPanelInterior;
  private JScrollPane guiScrollpaneInteriorRelative;
  private JSeparator guiSeparatorExteriorButtons, guiSeparatorExteriorTitle,
      guiSeparatorInteriorButtons, guiSeparatorInteriorTitle;
  private JTabbedPane guiTabs;
  private JTextArea guiTextareaInteriorRelative;

  private Calendar calendar =
      Calendar.getInstance(TimeZone.getTimeZone("GMT+1"));
  private Capteur sensor;
  private String configFilename = "config.txt";

  public SimulationCreationGUI() { initializeSimulationCreateGUI(); }

  private void initializeComponents() {
    guiTabs = new JTabbedPane();
    guiPanelInterior = new JPanel();
    guiSeparatorInteriorTitle = new JSeparator();
    guiLabelInteriorTitle = new JLabel();
    guiLabelInteriorId = new JLabel();
    guiLabelInteriorType = new JLabel();
    guiLabelInteriorBat = new JLabel();
    guiLabelInteriorEtage = new JLabel();
    guiLabelInteriorSalle = new JLabel();
    guiLabelInteriorRelative = new JLabel();
    guiInputInteriorId = new JTextField();
    guiScrollpaneInteriorRelative = new JScrollPane();
    guiTextareaInteriorRelative = new JTextArea();
    guiComboboxInteriorType = new JComboBox<>();
    guiComboboxInteriorBat = new JComboBox<>();
    guiComboboxInteriorEtage = new JComboBox<>();
    guiComboboxInteriorSalle = new JComboBox<>();
    guiLabelInteriorMin = new JLabel();
    guiLabelInteriorMax = new JLabel();
    guiButtonInteriorSubmit = new JButton();
    guiButtonInteriorClear = new JButton();
    guiSeparatorInteriorButtons = new JSeparator();
    guiInputInteriorMin = new JTextField();
    guiInputInteriorMax = new JTextField();
    guiPanelExterior = new JPanel();
    guiLabelExteriorTitle = new JLabel();
    guiSeparatorExteriorTitle = new JSeparator();
    guiLabelExteriorId = new JLabel();
    guiInputExteriorId = new JTextField();
    guiLabelExteriorType = new JLabel();
    guiComboboxExteriorType = new JComboBox<>();
    guiLabelExteriorLatitude = new JLabel();
    guiLabelExteriorLongitude = new JLabel();
    guiLabelExteriorMin = new JLabel();
    guiLabelExteriorMax = new JLabel();
    guiInputExteriorLatitude = new JTextField();
    guiInputExteriorLongitude = new JTextField();
    guiInputExteriorMin = new JTextField();
    guiInputExteriorMax = new JTextField();
    guiButtonExteriorSubmit = new JButton();
    guiButtonExteriorClear = new JButton();
    guiSeparatorExteriorButtons = new JSeparator();
  }

  private void initializeWindow() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Energy Control - Simulation Client");
    setMinimumSize(new Dimension(640, 480));
    setResizable(false);
  }

  private void initializeInteriorTheme() {
    guiTabs.setFont(new Font("Open Sans", 0, 11)); // NOI18N

    guiLabelInteriorTitle.setFont(new Font("Open Sans", 0, 18)); // NOI18N

    guiLabelInteriorId.setFont(new Font("Open Sans", 0, 14)); // NOI18N

    guiLabelInteriorType.setFont(new Font("Open Sans", 0, 14)); // NOI18N

    guiLabelInteriorBat.setFont(new Font("Open Sans", 0, 14)); // NOI18N

    guiLabelInteriorEtage.setFont(new Font("Open Sans", 0, 14)); // NOI18N

    guiLabelInteriorSalle.setFont(new Font("Open Sans", 0, 14)); // NOI18N

    guiLabelInteriorRelative.setFont(new Font("Open Sans", 0, 14)); // NOI18N

    guiInputInteriorId.setFont(new Font("Open Sans", 0, 11)); // NOI18N

    guiTextareaInteriorRelative.setFont(new Font("Open Sans", 0, 11)); // NOI18N

    guiComboboxInteriorType.setFont(new Font("Open Sans", 0, 11)); // NOI18N

    guiComboboxInteriorBat.setFont(new Font("Open Sans", 0, 11)); // NOI18N

    guiComboboxInteriorEtage.setFont(new Font("Open Sans", 0, 11)); // NOI18N

    guiComboboxInteriorSalle.setFont(new Font("Open Sans", 0, 11)); // NOI18N

    guiLabelInteriorMin.setFont(new Font("Open Sans", 0, 14)); // NOI18N

    guiLabelInteriorMax.setFont(new Font("Open Sans", 0, 14)); // NOI18N

    guiButtonInteriorSubmit.setFont(new Font("Open Sans", 0, 12)); // NOI18N

    guiButtonInteriorClear.setFont(new Font("Open Sans", 0, 12)); // NOI18N

    guiInputInteriorMin.setFont(new Font("Open Sans", 0, 11)); // NOI18N

    guiInputInteriorMax.setFont(new Font("Open Sans", 0, 11)); // NOI18N
  }

  private void initializeInteriorWidgets() {
    guiTabs.setFocusable(false);

    guiLabelInteriorTitle.setText("Ajout d'un capteur intérieur");

    guiLabelInteriorId.setText("Identifiant");

    guiLabelInteriorType.setText("Type de données");

    guiLabelInteriorBat.setText("Bâtiment");

    guiLabelInteriorEtage.setText("Étage");

    guiLabelInteriorSalle.setText("Salle");

    guiLabelInteriorRelative.setText("Position relative");

    guiTextareaInteriorRelative.setColumns(20);
    guiTextareaInteriorRelative.setRows(5);
    guiScrollpaneInteriorRelative.setViewportView(guiTextareaInteriorRelative);

    guiComboboxInteriorType.setModel(
        new DefaultComboBoxModel<>(CapteurInterieurType.getValues()));
    guiComboboxInteriorType.setFocusable(false);
    guiComboboxInteriorType.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        guiComboboxInteriorTypeActionPerformed(evt);
      }
    });

    guiComboboxInteriorBat.setModel(
        new DefaultComboBoxModel<>(this.getBatiments()));
    guiComboboxInteriorBat.setFocusable(false);
    guiComboboxInteriorBat.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        guiComboboxInteriorBatActionPerformed(evt);
      }
    });

    guiComboboxInteriorEtage.setModel(
        new DefaultComboBoxModel<>(this.getEtages()));
    guiComboboxInteriorEtage.setFocusable(false);
    guiComboboxInteriorEtage.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        guiComboboxInteriorEtageActionPerformed(evt);
      }
    });

    guiComboboxInteriorSalle.setModel(
        new DefaultComboBoxModel<>(this.getSalles()));
    guiComboboxInteriorSalle.setFocusable(false);

    guiLabelInteriorMin.setText("Valeur minimale");

    guiLabelInteriorMax.setText("Valeur maximale");

    guiButtonInteriorSubmit.setText("Valider");
    guiButtonInteriorSubmit.setFocusable(false);
    guiButtonInteriorSubmit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        guiButtonInteriorSubmitActionPerformed(evt);
      }
    });

    guiButtonInteriorClear.setText("RÀZ");
    guiButtonInteriorClear.setFocusable(false);
    guiButtonInteriorClear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        initializeInteriorWidgets();
        guiInputInteriorId.setText("");
        guiTextareaInteriorRelative.setText("");
      }
    });

    guiInputInteriorMin.setText(
        getMinValueFromType(this.guiComboboxInteriorType, true));

    guiInputInteriorMax.setText(
        getMaxValueFromType(this.guiComboboxInteriorType, true));
  }

  private void initializeInteriorLayout() {
    GroupLayout guiPanelInteriorLayout = new GroupLayout(guiPanelInterior);
    guiPanelInterior.setLayout(guiPanelInteriorLayout);
    guiPanelInteriorLayout.setHorizontalGroup(
        guiPanelInteriorLayout
            .createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(
                guiPanelInteriorLayout.createSequentialGroup()
                    .addGap(226, 226, 226)
                    .addComponent(guiButtonInteriorSubmit)
                    .addGap(18, 18, 18)
                    .addComponent(guiButtonInteriorClear)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(
                guiPanelInteriorLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(
                        guiPanelInteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(guiSeparatorInteriorTitle)
                            .addGroup(
                                guiPanelInteriorLayout.createSequentialGroup()
                                    .addGroup(
                                        guiPanelInteriorLayout
                                            .createParallelGroup(
                                                GroupLayout.Alignment.LEADING)
                                            .addComponent(guiLabelInteriorType)
                                            .addComponent(guiLabelInteriorId))
                                    .addGap(59, 59, 59)
                                    .addGroup(
                                        guiPanelInteriorLayout
                                            .createParallelGroup(
                                                GroupLayout.Alignment.LEADING)
                                            .addComponent(
                                                guiComboboxInteriorType, 0,
                                                GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                            .addComponent(guiInputInteriorId)))
                            .addGroup(
                                GroupLayout.Alignment.TRAILING,
                                guiPanelInteriorLayout.createSequentialGroup()
                                    .addGroup(
                                        guiPanelInteriorLayout
                                            .createParallelGroup(
                                                GroupLayout.Alignment.LEADING)
                                            .addComponent(guiLabelInteriorSalle)
                                            .addComponent(
                                                guiLabelInteriorRelative))
                                    .addGap(67, 67, 67)
                                    .addGroup(
                                        guiPanelInteriorLayout
                                            .createParallelGroup(
                                                GroupLayout.Alignment.LEADING)
                                            .addComponent(
                                                guiComboboxInteriorSalle, 0,
                                                GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                            .addComponent(
                                                guiScrollpaneInteriorRelative)))
                            .addGroup(
                                guiPanelInteriorLayout.createSequentialGroup()
                                    .addComponent(guiLabelInteriorBat)
                                    .addGap(113, 113, 113)
                                    .addComponent(guiComboboxInteriorBat, 0,
                                                  GroupLayout.DEFAULT_SIZE,
                                                  Short.MAX_VALUE))
                            .addGroup(
                                guiPanelInteriorLayout.createSequentialGroup()
                                    .addComponent(guiLabelInteriorEtage)
                                    .addGap(137, 137, 137)
                                    .addComponent(guiComboboxInteriorEtage, 0,
                                                  GroupLayout.DEFAULT_SIZE,
                                                  Short.MAX_VALUE))
                            .addComponent(guiSeparatorInteriorButtons)
                            .addGroup(
                                guiPanelInteriorLayout.createSequentialGroup()
                                    .addComponent(guiLabelInteriorTitle)
                                    .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(
                                GroupLayout.Alignment.TRAILING,
                                guiPanelInteriorLayout.createSequentialGroup()
                                    .addGroup(
                                        guiPanelInteriorLayout
                                            .createParallelGroup(
                                                GroupLayout.Alignment.LEADING)
                                            .addComponent(guiLabelInteriorMin)
                                            .addComponent(guiLabelInteriorMax))
                                    .addPreferredGap(
                                        LayoutStyle.ComponentPlacement.RELATED,
                                        GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                    .addGroup(
                                        guiPanelInteriorLayout
                                            .createParallelGroup(
                                                GroupLayout.Alignment.LEADING)
                                            .addComponent(
                                                guiInputInteriorMax,
                                                GroupLayout.PREFERRED_SIZE, 104,
                                                GroupLayout.PREFERRED_SIZE)
                                            .addComponent(
                                                guiInputInteriorMin,
                                                GroupLayout.PREFERRED_SIZE, 104,
                                                GroupLayout.PREFERRED_SIZE))
                                    .addGap(317, 317, 317)))
                    .addContainerGap()));
    guiPanelInteriorLayout.setVerticalGroup(
        guiPanelInteriorLayout
            .createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(
                guiPanelInteriorLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(guiLabelInteriorTitle)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(
                        guiSeparatorInteriorTitle, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(
                        guiPanelInteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(guiLabelInteriorId)
                            .addComponent(guiInputInteriorId,
                                          GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.DEFAULT_SIZE,
                                          GroupLayout.PREFERRED_SIZE))
                    .addGap(7, 7, 7)
                    .addGroup(
                        guiPanelInteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(guiLabelInteriorType)
                            .addComponent(guiComboboxInteriorType,
                                          GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.DEFAULT_SIZE,
                                          GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(
                        guiPanelInteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(guiLabelInteriorBat)
                            .addComponent(guiComboboxInteriorBat,
                                          GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.DEFAULT_SIZE,
                                          GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(
                        guiPanelInteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(guiLabelInteriorEtage)
                            .addComponent(guiComboboxInteriorEtage,
                                          GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.DEFAULT_SIZE,
                                          GroupLayout.PREFERRED_SIZE))
                    .addGap(8, 8, 8)
                    .addGroup(
                        guiPanelInteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(guiLabelInteriorSalle)
                            .addComponent(guiComboboxInteriorSalle,
                                          GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.DEFAULT_SIZE,
                                          GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(
                        guiPanelInteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(guiLabelInteriorRelative)
                            .addComponent(guiScrollpaneInteriorRelative,
                                          GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.DEFAULT_SIZE,
                                          GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(
                        guiPanelInteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(guiLabelInteriorMin)
                            .addComponent(guiInputInteriorMin,
                                          GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.DEFAULT_SIZE,
                                          GroupLayout.PREFERRED_SIZE))
                    .addGroup(
                        guiPanelInteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(
                                guiPanelInteriorLayout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(guiLabelInteriorMax))
                            .addGroup(
                                guiPanelInteriorLayout.createSequentialGroup()
                                    .addPreferredGap(
                                        LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(guiInputInteriorMax,
                                                  GroupLayout.PREFERRED_SIZE,
                                                  GroupLayout.DEFAULT_SIZE,
                                                  GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 39,
                                     Short.MAX_VALUE)
                    .addComponent(guiSeparatorInteriorButtons,
                                  GroupLayout.PREFERRED_SIZE, 10,
                                  GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(
                        guiPanelInteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(guiButtonInteriorSubmit)
                            .addComponent(guiButtonInteriorClear))
                    .addContainerGap()));
    guiTabs.addTab("Intérieur", guiPanelInterior);
  }

  private void initializeExteriorTheme() {
    guiLabelExteriorTitle.setFont(openSans18);     // NOI18N
    guiLabelExteriorId.setFont(openSans14);        // NOI18N
    guiInputExteriorId.setFont(openSans11);        // NOI18N
    guiLabelExteriorType.setFont(openSans14);      // NOI18N
    guiComboboxExteriorType.setFont(openSans11);   // NOI18N
    guiLabelExteriorLatitude.setFont(openSans14);  // NOI18N
    guiLabelExteriorLongitude.setFont(openSans14); // NOI18N
    guiLabelExteriorMin.setFont(openSans14);       // NOI18N
    guiLabelExteriorMax.setFont(openSans14);       // NOI18N
    guiInputExteriorLatitude.setFont(openSans11);  // NOI18N
    guiInputExteriorLongitude.setFont(openSans11); // NOI18N
    guiInputExteriorMin.setFont(openSans11);       // NOI18N
    guiInputExteriorMax.setFont(openSans11);       // NOI18N
    guiButtonExteriorSubmit.setFont(openSans12);   // NOI18N
    guiButtonExteriorClear.setFont(openSans12);    // NOI18N
  }

  private void initializeExteriorWidgets() {
    guiLabelExteriorTitle.setText("Ajout d'un capteur extérieur");

    guiLabelExteriorId.setText("Identifiant");

    guiLabelExteriorType.setText("Type de données");

    guiComboboxExteriorType.setModel(
        new DefaultComboBoxModel<>(CapteurExterieurType.getValues()));
    guiComboboxExteriorType.setFocusable(false);
    guiComboboxExteriorType.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        guiComboboxExteriorTypeActionPerformed(evt);
      }
    });

    guiLabelExteriorLatitude.setText("Latitude");

    guiLabelExteriorLongitude.setText("Longitude");

    guiLabelExteriorMin.setText("Valeur minimale");

    guiLabelExteriorMax.setText("Valeur maximale");

    guiInputExteriorLatitude.setText("0.0");

    guiInputExteriorLongitude.setText("0.0");

    guiInputExteriorMin.setText(
        this.getMinValueFromType(this.guiComboboxExteriorType, false));

    guiInputExteriorMax.setText(
        this.getMaxValueFromType(this.guiComboboxExteriorType, false));

    guiButtonExteriorSubmit.setText("Valider");
    guiButtonExteriorSubmit.setFocusable(false);
    guiButtonExteriorSubmit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        guiButtonExteriorSubmitActionPerformed(evt);
      }
    });

    guiButtonExteriorClear.setText("RÀZ");
    guiButtonExteriorClear.setFocusable(false);
    guiButtonExteriorClear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        initializeExteriorWidgets();
        guiInputExteriorId.setText("");
      }
    });
  }

  private void initializeExteriorLayout() {
    GroupLayout guiPanelExteriorLayout = new GroupLayout(guiPanelExterior);
    guiPanelExterior.setLayout(guiPanelExteriorLayout);
    guiPanelExteriorLayout.setHorizontalGroup(
        guiPanelExteriorLayout
            .createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(
                guiPanelExteriorLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(
                        guiPanelExteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(guiSeparatorExteriorTitle)
                            .addGroup(
                                guiPanelExteriorLayout.createSequentialGroup()
                                    .addGroup(
                                        guiPanelExteriorLayout
                                            .createParallelGroup(
                                                GroupLayout.Alignment.LEADING)
                                            .addComponent(guiLabelExteriorTitle)
                                            .addComponent(guiLabelExteriorMin))
                                    .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(
                                guiPanelExteriorLayout.createSequentialGroup()
                                    .addGroup(
                                        guiPanelExteriorLayout
                                            .createParallelGroup(
                                                GroupLayout.Alignment.LEADING)
                                            .addComponent(guiLabelExteriorType)
                                            .addComponent(guiLabelExteriorId)
                                            .addComponent(
                                                guiLabelExteriorLatitude)
                                            .addComponent(
                                                guiLabelExteriorLongitude)
                                            .addComponent(guiLabelExteriorMax))
                                    .addGap(59, 59, 59)
                                    .addGroup(
                                        guiPanelExteriorLayout
                                            .createParallelGroup(
                                                GroupLayout.Alignment.LEADING)
                                            .addComponent(
                                                guiComboboxExteriorType, 0, 431,
                                                Short.MAX_VALUE)
                                            .addComponent(guiInputExteriorId)
                                            .addGroup(
                                                guiPanelExteriorLayout
                                                    .createSequentialGroup()
                                                    .addGroup(
                                                        guiPanelExteriorLayout
                                                            .createParallelGroup(
                                                                GroupLayout
                                                                    .Alignment
                                                                    .TRAILING,
                                                                false)
                                                            .addComponent(
                                                                guiInputExteriorMax,
                                                                GroupLayout
                                                                    .Alignment
                                                                    .LEADING)
                                                            .addComponent(
                                                                guiInputExteriorLongitude,
                                                                GroupLayout
                                                                    .Alignment
                                                                    .LEADING)
                                                            .addComponent(
                                                                guiInputExteriorLatitude,
                                                                GroupLayout
                                                                    .Alignment
                                                                    .LEADING)
                                                            .addComponent(
                                                                guiInputExteriorMin,
                                                                GroupLayout
                                                                    .DEFAULT_SIZE,
                                                                104,
                                                                Short
                                                                    .MAX_VALUE))
                                                    .addGap(0, 0,
                                                            Short.MAX_VALUE))))
                            .addGroup(
                                guiPanelExteriorLayout.createSequentialGroup()
                                    .addGap(216, 216, 216)
                                    .addComponent(guiButtonExteriorSubmit)
                                    .addGap(18, 18, 18)
                                    .addComponent(guiButtonExteriorClear)
                                    .addPreferredGap(
                                        LayoutStyle.ComponentPlacement.RELATED,
                                        235, GroupLayout.PREFERRED_SIZE))
                            .addComponent(guiSeparatorExteriorButtons))
                    .addContainerGap()));
    guiPanelExteriorLayout.setVerticalGroup(
        guiPanelExteriorLayout
            .createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(
                guiPanelExteriorLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(guiLabelExteriorTitle)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(
                        guiSeparatorExteriorTitle, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(
                        guiPanelExteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(guiLabelExteriorId)
                            .addComponent(guiInputExteriorId,
                                          GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.DEFAULT_SIZE,
                                          GroupLayout.PREFERRED_SIZE))
                    .addGap(7, 7, 7)
                    .addGroup(
                        guiPanelExteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(guiLabelExteriorType)
                            .addComponent(guiComboboxExteriorType,
                                          GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.DEFAULT_SIZE,
                                          GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(
                        guiPanelExteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(guiLabelExteriorLatitude)
                            .addComponent(guiInputExteriorLatitude,
                                          GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.DEFAULT_SIZE,
                                          GroupLayout.PREFERRED_SIZE))
                    .addGap(7, 7, 7)
                    .addGroup(
                        guiPanelExteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(guiLabelExteriorLongitude)
                            .addComponent(guiInputExteriorLongitude,
                                          GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.DEFAULT_SIZE,
                                          GroupLayout.PREFERRED_SIZE))
                    .addGap(6, 6, 6)
                    .addGroup(
                        guiPanelExteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(guiLabelExteriorMin)
                            .addComponent(guiInputExteriorMin,
                                          GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.DEFAULT_SIZE,
                                          GroupLayout.PREFERRED_SIZE))
                    .addGap(6, 6, 6)
                    .addGroup(
                        guiPanelExteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(guiLabelExteriorMax)
                            .addComponent(guiInputExteriorMax,
                                          GroupLayout.PREFERRED_SIZE,
                                          GroupLayout.DEFAULT_SIZE,
                                          GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                     164, Short.MAX_VALUE)
                    .addComponent(guiSeparatorExteriorButtons,
                                  GroupLayout.PREFERRED_SIZE, 10,
                                  GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(
                        guiPanelExteriorLayout
                            .createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(guiButtonExteriorSubmit)
                            .addComponent(guiButtonExteriorClear))
                    .addContainerGap()));
    guiTabs.addTab("Extérieur", guiPanelExterior);
  }

  private void initializeLayout() {
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                          .addContainerGap()
                          .addComponent(guiTabs)
                          .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING,
                      layout.createSequentialGroup()
                          .addContainerGap()
                          .addComponent(guiTabs)
                          .addContainerGap()));

    pack();
  }

  private void initializeSimulationCreateGUI() {
    try {
      this.openSans11 =
          Font.createFont(Font.TRUETYPE_FONT, new File("OpenSans.ttf"))
              .deriveFont(11f);
      this.openSans12 =
          Font.createFont(Font.TRUETYPE_FONT, new File("OpenSans.ttf"))
              .deriveFont(12f);
      this.openSans14 =
          Font.createFont(Font.TRUETYPE_FONT, new File("OpenSans.ttf"))
              .deriveFont(14f);
      this.openSans18 =
          Font.createFont(Font.TRUETYPE_FONT, new File("OpenSans.ttf"))
              .deriveFont(18f);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }

    this.initializeComponents();
    this.initializeWindow();

    this.initializeInteriorWidgets();
    this.initializeInteriorTheme();
    this.initializeInteriorLayout();

    this.initializeExteriorWidgets();
    this.initializeExteriorTheme();
    this.initializeExteriorLayout();

    this.initializeLayout();
  }

  private void guiButtonInteriorSubmitActionPerformed(ActionEvent evt) {
    if (!this.isValidIdentifiant(true)) {
      SimulationError error =
          new SimulationError("Entrez un identifiant valide !");
      error.show();
    } else if (!this.isValidMinMaxValues(true)) {
      SimulationError error =
          new SimulationError("La valeur min doit être <= à la valeur max !");
      error.show();
    } else if (!this.isValidRelativePosition()) {
      SimulationError error =
          new SimulationError("Veuillez renseigner une position relative !");
      error.show();
    } else {
      this.createInteriorSensor();
      this.launchSimulationManageGUI();
    }
  }

  private void guiButtonExteriorSubmitActionPerformed(ActionEvent evt) {
    if (!this.isValidIdentifiant(false)) {
      SimulationError error =
          new SimulationError("Entrez un identifiant valide !");
      error.show();
    } else if (!this.isValidMinMaxValues(false)) {
      SimulationError error =
          new SimulationError("La valeur min doit être <= à la valeur max !");
      error.show();
    } else if (!this.isValidLatitude()) {
      SimulationError error = new SimulationError("-90.0 <= LATITUDE <= 90.0");
      error.show();
    } else if (!this.isValidLongitude()) {
      SimulationError error =
          new SimulationError("-180.0 <= LONGITUDE <= 180.0");
      error.show();
    } else {
      this.createExteriorSensor();
      this.launchSimulationManageGUI();
    }
  }

  private void guiComboboxInteriorBatActionPerformed(ActionEvent evt) {
    this.guiComboboxInteriorEtage.setModel(
        new DefaultComboBoxModel<>(this.getEtages()));
    this.guiComboboxInteriorSalle.setModel(
        new DefaultComboBoxModel<>(this.getSalles()));
  }

  private void guiComboboxInteriorEtageActionPerformed(ActionEvent evt) {
    this.guiComboboxInteriorSalle.setModel(
        new DefaultComboBoxModel<>(this.getSalles()));
  }

  private void guiComboboxInteriorTypeActionPerformed(ActionEvent evt) {
    this.guiInputInteriorMin.setText(
        this.getMinValueFromType(this.guiComboboxInteriorType, true));
    this.guiInputInteriorMax.setText(
        this.getMaxValueFromType(this.guiComboboxInteriorType, true));
  }

  private void guiComboboxExteriorTypeActionPerformed(ActionEvent evt) {
    this.guiInputExteriorMin.setText(
        this.getMinValueFromType(this.guiComboboxExteriorType, false));
    this.guiInputExteriorMax.setText(
        this.getMaxValueFromType(this.guiComboboxExteriorType, false));
  }

  private void createInteriorSensor() {
    String identifiant = this.guiInputInteriorId.getText();
    String date = getCurrentDate();
    float minValue = Float.parseFloat(this.guiInputInteriorMin.getText());
    float maxValue = Float.parseFloat(this.guiInputInteriorMax.getText());
    CapteurInterieurType type = CapteurInterieurType.getTypeByName(
        (String)this.guiComboboxInteriorType.getModel().getSelectedItem());
    String batiment = (String)this.guiComboboxInteriorBat.getSelectedItem();
    int etage = Integer.parseInt(
        (String)this.guiComboboxInteriorEtage.getSelectedItem());
    String salle = (String)this.guiComboboxInteriorSalle.getSelectedItem();
    String positionRelative = this.guiTextareaInteriorRelative.getText();

    this.sensor =
        new CapteurInterieur(identifiant, date, minValue, maxValue, type,
                             batiment, etage, salle, positionRelative);
  }

  private void createExteriorSensor() {
    String identifiant = this.guiInputExteriorId.getText();
    String date = getCurrentDate();
    float minValue = Float.parseFloat(this.guiInputExteriorMin.getText());
    float maxValue = Float.parseFloat(this.guiInputExteriorMax.getText());
    CapteurExterieurType type = CapteurExterieurType.getTypeByName(
        (String)this.guiComboboxExteriorType.getModel().getSelectedItem());
    float latitude = Float.parseFloat(this.guiInputExteriorLatitude.getText());
    float longitude =
        Float.parseFloat(this.guiInputExteriorLongitude.getText());

    this.sensor = new CapteurExterieur(identifiant, date, minValue, maxValue,
                                       type, latitude, longitude);
  }

  private void launchSimulationManageGUI() {
    SimulationGestionGUI sim = new SimulationGestionGUI(this.sensor);
    sim.setVisible(true);
    sim.setLocationRelativeTo(null);
    this.dispose();
  }

  private String getCurrentDate() {
    String day = Integer.toString(this.calendar.get(Calendar.DAY_OF_MONTH));
    String month = Integer.toString(this.calendar.get(Calendar.MONTH) + 1);
    String year = Integer.toString(this.calendar.get(Calendar.YEAR));
    String hour = Integer.toString(this.calendar.get(Calendar.HOUR_OF_DAY));
    String minutes = Integer.toString(this.calendar.get(Calendar.MINUTE));
    String seconds = Integer.toString(this.calendar.get(Calendar.SECOND));

    return day + "/" + month + "/" + year + " " + hour + ":" + minutes + ":" +
        seconds;
  }

  private String[] getBatiments() {
    return Config.getBatimentsFromConfig(configFilename);
  }

  private String[] getEtages() {
    int batiment = this.guiComboboxInteriorBat.getSelectedIndex();
    return Config.getEtagesFromConfig(configFilename, batiment);
  }

  private String[] getSalles() {
    int batiment = this.guiComboboxInteriorBat.getSelectedIndex();
    int etage = this.guiComboboxInteriorEtage.getSelectedIndex();
    return Config.getSallesFromConfig(configFilename, batiment, etage);
  }

  private boolean isValidIdentifiant(boolean interior) {
    if (interior)
      return !this.guiInputInteriorId.getText().equals("");
    else
      return !this.guiInputExteriorId.getText().equals("");
  }

  private boolean isValidMinMaxValues(boolean interior) {
    if (interior)
      return Float.parseFloat(this.guiInputInteriorMin.getText()) <
          Float.parseFloat(this.guiInputInteriorMax.getText());
    else
      return Float.parseFloat(this.guiInputExteriorMin.getText()) <
          Float.parseFloat(this.guiInputExteriorMax.getText());
  }

  private boolean isValidLatitude() {
    return Float.parseFloat(this.guiInputExteriorLatitude.getText()) >= -90f &&
        Float.parseFloat(this.guiInputExteriorLatitude.getText()) <= 90f;
  }

  private boolean isValidLongitude() {
    return Float.parseFloat(this.guiInputExteriorLongitude.getText()) >=
        -180f &&
        Float.parseFloat(this.guiInputExteriorLongitude.getText()) <= 180f;
  }

  private boolean isValidRelativePosition() {
    return !guiTextareaInteriorRelative.getText().equals("");
  }

  private String getMinValueFromType(JComboBox<String> comboBox,
                                     boolean interior) {
    String typeName = (String)comboBox.getSelectedItem();
    CapteurType type;
    if (interior)
      type = CapteurInterieurType.getTypeByName(typeName);
    else
      type = CapteurExterieurType.getTypeByName(typeName);
    String minValue = Float.toString(type.getDefaultMin());
    return minValue;
  }

  private String getMaxValueFromType(JComboBox<String> comboBox,
                                     boolean interior) {
    String typeName = (String)comboBox.getSelectedItem();
    CapteurType type;
    if (interior)
      type = CapteurInterieurType.getTypeByName(typeName);
    else
      type = CapteurExterieurType.getTypeByName(typeName);
    String maxValue = Float.toString(type.getDefaultMax());
    return maxValue;
  }

  public static void main(String args[]) {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    try {
      ge.registerFont(
          Font.createFont(Font.TRUETYPE_FONT, new File("OpenSans.ttf")));
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }

    SimulationCreationGUI sim = new SimulationCreationGUI();
    sim.setVisible(true);
    sim.setLocationRelativeTo(null);
  }
}
