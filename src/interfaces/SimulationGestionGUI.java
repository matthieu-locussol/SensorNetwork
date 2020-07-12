package interfaces;

import capteur.Capteur;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
import util.SimulationConnexion;
import util.SimulationError;


public class SimulationGestionGUI extends JFrame {

  private static final long serialVersionUID = 1936897248934861714L;

  private JButton guiButtonConnect, guiButtonDisconnect, guiButtonManual,
      guiButtonPeriod, guiButtonRandom, guiButtonValue;
  private JTextField guiInputPeriod, guiInputValue;
  private JLabel guiLabelConnected, guiLabelGeneration, guiLabelId,
      guiLabelPeriod, guiLabelPeriodUpdate, guiLabelRealGeneration,
      guiLabelRealId, guiLabelRealType, guiLabelRealValues, guiLabelType,
      guiLabelValue, guiLabelValueUpdate, guiLabelValues;
  private JSeparator guiSeparatorButtons, guiSeparatorInfos,
      guiSeparatorInterface;

  private Capteur sensor;

  public SimulationGestionGUI(Capteur sensor) {
    this.sensor = sensor;
    initializeSimulationManagerGUI();
  }

  private void initializeComponents() {
    guiLabelId = new JLabel();
    guiSeparatorInfos = new JSeparator();
    guiLabelType = new JLabel();
    guiLabelRealType = new JLabel();
    guiLabelValues = new JLabel();
    guiLabelRealValues = new JLabel();
    guiLabelRealId = new JLabel();
    guiLabelConnected = new JLabel();
    guiSeparatorInterface = new JSeparator();
    guiButtonConnect = new JButton();
    guiButtonDisconnect = new JButton();
    guiSeparatorButtons = new JSeparator();
    guiLabelGeneration = new JLabel();
    guiLabelRealGeneration = new JLabel();
    guiButtonRandom = new JButton();
    guiButtonManual = new JButton();
    guiInputValue = new JTextField();
    guiLabelValue = new JLabel();
    guiButtonValue = new JButton();
    guiLabelValueUpdate = new JLabel();
    guiInputPeriod = new JTextField();
    guiLabelPeriod = new JLabel();
    guiButtonPeriod = new JButton();
    guiLabelPeriodUpdate = new JLabel();
  }

  private void initializeWindow() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Energy Control - Simulation Client");
    setMinimumSize(new Dimension(640, 480));
    setResizable(false);
  }

  private void initializeTheme() {
    guiLabelId.setFont(new Font("Open Sans", 0, 18)); // NOI18N

    guiLabelType.setFont(new Font("Open Sans", 0, 12)); // NOI18N

    guiLabelRealType.setFont(new Font("Open Sans", 1, 12)); // NOI18N
    guiLabelRealType.setForeground(new Color(58, 84, 135));

    guiLabelValues.setFont(new Font("Open Sans", 0, 12)); // NOI18N

    guiLabelRealValues.setFont(new Font("Open Sans", 1, 12)); // NOI18N
    guiLabelRealValues.setForeground(new Color(58, 84, 135));

    guiLabelRealId.setFont(new Font("Open Sans", 1, 18)); // NOI18N
    guiLabelRealId.setForeground(new Color(58, 84, 135));

    guiLabelConnected.setFont(new Font("Open Sans", 1, 18)); // NOI18N
    guiLabelConnected.setForeground(new Color(195, 83, 68));

    guiLabelGeneration.setFont(new Font("Open Sans", 0, 18)); // NOI18N

    guiLabelRealGeneration.setFont(new Font("Open Sans", 1, 18)); // NOI18N
    guiLabelRealGeneration.setForeground(new Color(58, 84, 135));

    guiInputValue.setFont(new Font("Open Sans", 0, 14)); // NOI18N

    guiLabelValue.setFont(new Font("Open Sans", 0, 14)); // NOI18N

    guiButtonValue.setFont(new Font("Open Sans", 0, 12)); // NOI18N

    guiLabelValueUpdate.setFont(new Font("Open Sans", 2, 12)); // NOI18N
    guiLabelValueUpdate.setForeground(new Color(48, 134, 90));

    guiInputPeriod.setFont(new Font("Open Sans", 0, 14)); // NOI18N

    guiLabelPeriod.setFont(new Font("Open Sans", 0, 14)); // NOI18N

    guiButtonPeriod.setFont(new Font("Open Sans", 0, 12)); // NOI18N

    guiLabelPeriodUpdate.setFont(new Font("Open Sans", 2, 12)); // NOI18N
    guiLabelPeriodUpdate.setForeground(new Color(48, 134, 90));
  }

  private void initializeWidgets() {
    guiLabelId.setText("Capteur");

    guiLabelType.setText("Ce capteur permet de gérer la :");

    guiLabelRealType.setText(this.sensor.getType().toString());

    guiLabelValues.setText("Intervalle de température acceptée :");

    guiLabelRealValues.setText(this.sensor.getMinValue() + " à " +
                               this.sensor.getMaxValue() + " " +
                               this.sensor.getType().getUnite());

    guiLabelRealId.setText("\"" + this.sensor.getIdentifiant() + "\"");

    guiLabelConnected.setText("Déconnecté (0.0.0.0:0)");

    guiButtonConnect.setText("Connexion");
    guiButtonConnect.setFocusable(false);
    guiButtonConnect.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        guiButtonConnectActionPerformed(evt);
      }
    });

    guiButtonDisconnect.setText("Déconnexion");
    guiButtonDisconnect.setEnabled(false);
    guiButtonDisconnect.setFocusable(false);
    guiButtonDisconnect.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        guiButtonDisconnectActionPerformed(evt);
      }
    });

    guiLabelGeneration.setText("Valeurs générées :");

    guiLabelRealGeneration.setText("Aléatoirement");

    guiButtonRandom.setText("Aléatoire");
    guiButtonRandom.setEnabled(false);
    guiButtonRandom.setFocusable(false);
    guiButtonRandom.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        guiButtonRandomActionPerformed(evt);
      }
    });

    guiButtonManual.setText("Manuel");
    guiButtonManual.setFocusable(false);
    guiButtonManual.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        guiButtonManualActionPerformed(evt);
      }
    });

    guiInputValue.setHorizontalAlignment(JTextField.CENTER);
    guiInputValue.setText(this.setDefaultValue());
    guiInputValue.setEnabled(false);
    guiInputValue.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) { guiInputValueKeyTyped(evt); }
    });

    guiLabelValue.setText("Valeur du capteur");
    guiLabelValue.setEnabled(false);

    guiButtonValue.setText("Mettre à jour");
    guiButtonValue.setEnabled(false);
    guiButtonValue.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        guiButtonValueActionPerformed(evt);
      }
    });

    guiLabelValueUpdate.setText("Valeur du capteur à jour");
    guiLabelValueUpdate.setEnabled(false);

    guiInputPeriod.setHorizontalAlignment(JTextField.CENTER);
    guiInputPeriod.setText(
        Long.toString(this.sensor.getType().getDefaultFrequence()));
    guiInputPeriod.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) { guiInputPeriodKeyTyped(evt); }
    });

    guiLabelPeriod.setText("Période du capteur (min: 100ms)");

    guiButtonPeriod.setText("Mettre à jour");
    guiButtonPeriod.setEnabled(false);
    guiButtonPeriod.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        guiButtonPeriodActionPerformed(evt);
      }
    });

    guiLabelPeriodUpdate.setText("Période du capteur à jour");
  }

  private void initializeLayout() {
    org.jdesktop.layout.GroupLayout layout =
        new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(
                layout.createSequentialGroup()
                    .addContainerGap()
                    .add(
                        layout
                            .createParallelGroup(
                                org.jdesktop.layout.GroupLayout.LEADING)
                            .add(guiSeparatorInfos)
                            .add(
                                layout.createSequentialGroup()
                                    .add(guiLabelId)
                                    .addPreferredGap(
                                        org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(guiLabelRealId)
                                    .addPreferredGap(
                                        org.jdesktop.layout.LayoutStyle.RELATED,
                                        org.jdesktop.layout.GroupLayout
                                            .DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                    .add(guiLabelConnected))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING,
                                 guiSeparatorInterface)
                            .add(layout.createSequentialGroup()
                                     .add(guiButtonConnect,
                                          org.jdesktop.layout.GroupLayout
                                              .DEFAULT_SIZE,
                                          org.jdesktop.layout.GroupLayout
                                              .DEFAULT_SIZE,
                                          Short.MAX_VALUE)
                                     .addPreferredGap(org.jdesktop.layout
                                                          .LayoutStyle.RELATED)
                                     .add(guiButtonDisconnect,
                                          org.jdesktop.layout.GroupLayout
                                              .PREFERRED_SIZE,
                                          305,
                                          org.jdesktop.layout.GroupLayout
                                              .PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING,
                                 guiSeparatorButtons)
                            .add(
                                layout.createSequentialGroup()
                                    .add(layout
                                             .createParallelGroup(
                                                 org.jdesktop.layout.GroupLayout
                                                     .LEADING)
                                             .add(layout.createSequentialGroup()
                                                      .add(guiLabelType)
                                                      .addPreferredGap(
                                                          org.jdesktop.layout
                                                              .LayoutStyle
                                                              .RELATED)
                                                      .add(guiLabelRealType))
                                             .add(layout.createSequentialGroup()
                                                      .add(guiLabelValues)
                                                      .addPreferredGap(
                                                          org.jdesktop.layout
                                                              .LayoutStyle
                                                              .RELATED)
                                                      .add(guiLabelRealValues)))
                                    .add(0, 0, Short.MAX_VALUE))
                            .add(
                                layout.createSequentialGroup()
                                    .add(guiLabelGeneration)
                                    .addPreferredGap(
                                        org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(guiLabelRealGeneration)
                                    .addPreferredGap(
                                        org.jdesktop.layout.LayoutStyle.RELATED,
                                        org.jdesktop.layout.GroupLayout
                                            .DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                    .add(guiButtonManual)
                                    .addPreferredGap(
                                        org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(guiButtonRandom))
                            .add(
                                org.jdesktop.layout.GroupLayout.TRAILING,
                                layout.createSequentialGroup()
                                    .add(layout
                                             .createParallelGroup(
                                                 org.jdesktop.layout.GroupLayout
                                                     .LEADING)
                                             .add(layout.createSequentialGroup()
                                                      .add(guiInputPeriod,
                                                           org.jdesktop.layout
                                                               .GroupLayout
                                                               .PREFERRED_SIZE,
                                                           181,
                                                           org.jdesktop.layout
                                                               .GroupLayout
                                                               .PREFERRED_SIZE)
                                                      .addPreferredGap(
                                                          org.jdesktop.layout
                                                              .LayoutStyle
                                                              .RELATED)
                                                      .add(guiButtonPeriod))
                                             .add(guiLabelPeriod)
                                             .add(guiLabelPeriodUpdate))
                                    .addPreferredGap(
                                        org.jdesktop.layout.LayoutStyle.RELATED,
                                        48, Short.MAX_VALUE)
                                    .add(layout
                                             .createParallelGroup(
                                                 org.jdesktop.layout.GroupLayout
                                                     .LEADING)
                                             .add(guiLabelValue)
                                             .add(layout.createSequentialGroup()
                                                      .add(guiInputValue,
                                                           org.jdesktop.layout
                                                               .GroupLayout
                                                               .PREFERRED_SIZE,
                                                           181,
                                                           org.jdesktop.layout
                                                               .GroupLayout
                                                               .PREFERRED_SIZE)
                                                      .addPreferredGap(
                                                          org.jdesktop.layout
                                                              .LayoutStyle
                                                              .RELATED)
                                                      .add(guiButtonValue))
                                             .add(guiLabelValueUpdate))))
                    .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(
                layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout
                             .createParallelGroup(
                                 org.jdesktop.layout.GroupLayout.BASELINE)
                             .add(guiLabelId)
                             .add(guiLabelRealId)
                             .add(guiLabelConnected))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(guiSeparatorInfos,
                         org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10,
                         org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout
                             .createParallelGroup(
                                 org.jdesktop.layout.GroupLayout.BASELINE)
                             .add(guiLabelType)
                             .add(guiLabelRealType))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout
                             .createParallelGroup(
                                 org.jdesktop.layout.GroupLayout.BASELINE)
                             .add(guiLabelValues)
                             .add(guiLabelRealValues))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                    .add(guiSeparatorInterface,
                         org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10,
                         org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout
                             .createParallelGroup(
                                 org.jdesktop.layout.GroupLayout.BASELINE)
                             .add(guiLabelGeneration)
                             .add(guiLabelRealGeneration)
                             .add(guiButtonRandom)
                             .add(guiButtonManual))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED,
                                     101, Short.MAX_VALUE)
                    .add(layout
                             .createParallelGroup(
                                 org.jdesktop.layout.GroupLayout.BASELINE)
                             .add(guiLabelValue)
                             .add(guiLabelPeriod))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(
                        layout
                            .createParallelGroup(
                                org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(guiInputValue,
                                 org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                 45,
                                 org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(guiButtonValue,
                                 org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                 45,
                                 org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(guiInputPeriod,
                                 org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                 45,
                                 org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(
                                guiButtonPeriod,
                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                45,
                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout
                             .createParallelGroup(
                                 org.jdesktop.layout.GroupLayout.BASELINE)
                             .add(guiLabelValueUpdate)
                             .add(guiLabelPeriodUpdate))
                    .add(67, 67, 67)
                    .add(guiSeparatorButtons,
                         org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10,
                         org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(
                        layout
                            .createParallelGroup(
                                org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(guiButtonConnect,
                                 org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                 36,
                                 org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(
                                guiButtonDisconnect,
                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                36,
                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()));

    pack();
  }

  private void initializeSimulationManagerGUI() {

    this.initializeComponents();
    this.initializeWindow();

    this.initializeWidgets();
    this.initializeTheme();
    this.initializeLayout();
  }

  private void guiButtonDisconnectActionPerformed(ActionEvent evt) {
    this.sensor.envoyerDeconnexionCapteur();

    this.guiButtonConnect.setEnabled(true);
    this.guiButtonDisconnect.setEnabled(false);
    this.guiLabelConnected.setText("Déconnecté (" + this.sensor.getIp() + ":" +
                                   this.sensor.getPort() + ")");
    this.guiLabelConnected.setForeground(new Color(195, 83, 68));
  }

  private void guiButtonConnectActionPerformed(ActionEvent evt) {
    SimulationConnexion sim =
        new SimulationConnexion(this.sensor.getIp(), this.sensor.getPort());
    sim.show();

    if (sim.isAddressValid()) {
      this.sensor.setIp(sim.getIp());
      this.sensor.setPort(sim.getPort());

      this.sensor.envoyerConnexionCapteur();

      if (sensor.isConnected()) {
        this.guiButtonDisconnect.setEnabled(true);
        this.guiButtonConnect.setEnabled(false);
        this.guiLabelConnected.setText("Connecté (" + this.sensor.getIp() +
                                       ":" + this.sensor.getPort() + ")");
        this.guiLabelConnected.setForeground(new Color(48, 134, 90));
      } else {
        this.guiLabelConnected.setText("Déconnecté (" + this.sensor.getIp() +
                                       ":" + this.sensor.getPort() + ")");
        this.guiLabelConnected.setForeground(new Color(195, 83, 68));
      }
    }
  }

  private void guiButtonManualActionPerformed(ActionEvent evt) {
    this.sensor.setRandomValue(false);
    Float newValue = Float.parseFloat(this.guiInputValue.getText());
    this.sensor.setValue(newValue);

    this.guiButtonManual.setEnabled(false);
    this.guiButtonRandom.setEnabled(true);
    this.guiLabelRealGeneration.setText("Manuellement");
    this.guiLabelValue.setEnabled(true);
    this.guiLabelValueUpdate.setEnabled(true);
    this.guiInputValue.setEnabled(true);
  }

  private void guiButtonRandomActionPerformed(ActionEvent evt) {
    this.sensor.setRandomValue(true);

    this.guiButtonRandom.setEnabled(false);
    this.guiButtonManual.setEnabled(true);
    this.guiLabelRealGeneration.setText("Aléatoirement");
    this.guiButtonValue.setEnabled(false);
    this.guiLabelValue.setEnabled(false);
    this.guiLabelValueUpdate.setEnabled(false);
    this.guiInputValue.setEnabled(false);
  }

  private void guiButtonValueActionPerformed(ActionEvent evt) {
    DecimalFormat df = new DecimalFormat(this.sensor.getType().getPrecision());
    float inputValue = Float.parseFloat(this.guiInputValue.getText());
    String textValue = df.format(inputValue).replace(',', '.');
    float value = Float.parseFloat(textValue);

    if (isValidValue(value)) {
      this.sensor.setValue(value);
      this.guiButtonValue.setEnabled(false);
      this.guiLabelValueUpdate.setText("Valeur du capteur à jour");
      this.guiLabelValueUpdate.setForeground(new Color(48, 134, 90));
      this.guiInputValue.setText(textValue);
    } else {
      SimulationError sim = new SimulationError(
          "La valeur entrée est hors des bornes du capteur !");
      sim.show();
    }
  }

  private void guiInputValueKeyTyped(KeyEvent evt) {
    this.guiButtonValue.setEnabled(true);
    this.guiLabelValueUpdate.setText("Valeur du capteur non mise à jour");
    this.guiLabelValueUpdate.setForeground(new Color(195, 83, 68));
  }

  private void guiInputPeriodKeyTyped(KeyEvent evt) {
    this.guiButtonPeriod.setEnabled(true);
    this.guiLabelPeriodUpdate.setText("Période du capteur non mise à jour");
    this.guiLabelPeriodUpdate.setForeground(new Color(195, 83, 68));
  }

  private void guiButtonPeriodActionPerformed(ActionEvent evt) {
    try {
      Long period = Long.parseLong(this.guiInputPeriod.getText());

      if (isValidPeriod(period)) {
        this.sensor.setFrequence(period);
        this.guiButtonPeriod.setEnabled(false);
        this.guiLabelPeriodUpdate.setText("Période du capteur à jour");
        this.guiLabelPeriodUpdate.setForeground(new Color(48, 134, 90));
      } else {
        SimulationError sim = new SimulationError(
            "La période doit être supérieure ou égale à 100ms !");
        sim.show();
      }
    } catch (NumberFormatException e) {
      SimulationError sim =
          new SimulationError("La période entrée est invalide (trop grande) !");
      sim.show();
    }
  }

  private boolean isValidValue(Float value) {
    return this.sensor.isValidValue(value);
  }

  private boolean isValidPeriod(Long period) {
    return period >= 100 && period <= Long.MAX_VALUE;
  }

  private String setDefaultValue() {
    DecimalFormat df = new DecimalFormat(this.sensor.getType().getPrecision());
    float inputValue =
        (this.sensor.getMinValue() + this.sensor.getMaxValue()) / 2f;
    String value = df.format(inputValue).replace(',', '.');
    return value;
  }
}
