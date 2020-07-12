package util;

import capteur.Capteur;
import capteur.CapteurExterieur;
import capteur.CapteurInterieur;
import capteurtype.CapteurExterieurType;
import capteurtype.CapteurInterieurType;
import custom.EnergyNode;
import custom.EnergyTree;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class VisualisationClient {

  private Socket socket;
  private PrintWriter outputStream;
  private BufferedInputStream inputStream;

  private int serverPort;
  private String serverIp;
  private boolean connected;
  private String identifiant;
  private Timer timerSocket;

  private EnergyTree tree;

  private TreeSet<CapteurExterieur> sensorsExt = new TreeSet<>();
  private TreeSet<CapteurInterieur> sensorsInt = new TreeSet<>();

  private JTable tableFollow;

  public VisualisationClient(EnergyTree tree, JTable tableFollow) {
    this.tree = tree;
    this.tableFollow = tableFollow;
    serverIp = "0.0.0.0";
    serverPort = 0;
    connected = false;
  }

  public int getServerPort() { return serverPort; }

  public void setServerPort(int serverPort) { this.serverPort = serverPort; }

  public String getServerIp() { return serverIp; }

  public void setServerIp(String serverIp) { this.serverIp = serverIp; }

  public boolean isConnected() { return connected; }

  public TreeSet<CapteurExterieur> getSensorsExt() { return sensorsExt; }

  public TreeSet<CapteurInterieur> getSensorsInt() { return sensorsInt; }

  public boolean connectToServer(String identifiant) {
    try {
      socket = new Socket(serverIp, serverPort);

      outputStream = new PrintWriter(socket.getOutputStream());
      inputStream = new BufferedInputStream(socket.getInputStream());

      outputStream.println("ConnexionVisu;" + identifiant);
      outputStream.flush();

      if (recevoirAccuseConnexion()) {
        connected = true;
        this.identifiant = identifiant;
        this.timerSocket = new Timer();
        timerSocket.scheduleAtFixedRate(new TimerTask() {
          public void run() {
            try {
              while (inputStream.available() != 0) {
                String messageSocket = readSocket();

                String[] messages = messageSocket.split("\r\n");

                for (int i = 0; i < messages.length; ++i) {
                  String[] message_split = messages[i].split(";");

                  if (message_split[0].equals("CapteurPresent")) {
                    DateTimeFormatter dtf =
                        DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate localDate = LocalDate.now();
                    if (message_split.length == 7) { /* Intérieur */
                      CapteurInterieurType type =
                          CapteurInterieurType.getTypeByName(message_split[2]);
                      CapteurInterieur c = new CapteurInterieur(
                          message_split[1], dtf.format(localDate),
                          type.getDefaultMin(), type.getDefaultMax(), type,
                          message_split[3], Integer.parseInt(message_split[4]),
                          message_split[5], message_split[6]);
                      sensorsInt.add(c);
                      tree.setSensorsInt(sensorsInt);
                    } else if (message_split.length == 5) { /* Extérieur */
                      CapteurExterieurType type =
                          CapteurExterieurType.getTypeByName(message_split[2]);
                      CapteurExterieur c = new CapteurExterieur(
                          message_split[1], dtf.format(localDate),
                          type.getDefaultMin(), type.getDefaultMax(), type,
                          Float.parseFloat(message_split[3]),
                          Float.parseFloat(message_split[4]));
                      sensorsExt.add(c);
                      tree.setSensorsExt(sensorsExt);
                    }
                    EnergyNode root = tree.buildTree();
                    tree.setRoot(root);
                  } else if (message_split[0].equals("CapteurDeco")) {
                    String capteurId = message_split[1];
                    CapteurExterieur toRemoveExt = null;
                    CapteurInterieur toRemoveInt = null;
                    for (CapteurExterieur c : sensorsExt)
                      if (c.getIdentifiant().equals(capteurId))
                        toRemoveExt = c;
                    for (CapteurInterieur c : sensorsInt)
                      if (c.getIdentifiant().equals(capteurId))
                        toRemoveInt = c;
                    if (toRemoveExt != null) {
                      sensorsExt.remove(toRemoveExt);
                      tree.getFollowSet().remove(toRemoveExt.getIdentifiant());
                      updateFollowTable();
                      tree.refreshTable();
                    }
                    if (toRemoveInt != null) {
                      sensorsInt.remove(toRemoveInt);
                      tree.getFollowSet().remove(toRemoveInt.getIdentifiant());
                      updateFollowTable();
                      tree.refreshTable();
                    }
                    tree.setSensorsExt(sensorsExt);
                    tree.setSensorsInt(sensorsInt);
                    EnergyNode root = tree.buildTree();
                    tree.setRoot(root);
                  } else if (message_split[0].equals("ValeurCapteur")) {
                    String capteurId = message_split[1];
                    float value = Float.parseFloat(message_split[2]);
                    Capteur c = tree.getCapteurExterieurFromId(capteurId);
                    if (c == null)
                      c = tree.getCapteurInterieurFromId(capteurId);
                    c.setValue(value);
                    updateFollowTable();
                  } else if (message_split[0].equals("InscriptionKO")) {
                    String errMsg =
                        "Erreur lors de l'inscription aux capteurs : ";
                    for (int j = 1; j < message_split.length; ++j)
                      errMsg.concat(message_split[j] + ", ");
                    SimulationError sim = new SimulationError(errMsg);
                    sim.show();
                  } else if (message_split[0].equals("DesinscriptionKO")) {
                    String errMsg =
                        "Erreur lors de la désinscription aux capteurs : ";
                    for (int j = 1; j < message_split.length; ++j)
                      errMsg.concat(message_split[j] + ", ");
                    SimulationError sim = new SimulationError(errMsg);
                    sim.show();
                  }
                }
              }
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }, 0, 100);
      }
    } catch (IOException e) {
      SimulationError sim =
          new SimulationError("Connexion au serveur échouée !");
      sim.show();
    }

    return connected;
  }

  private void updateFollowTable() {
    DefaultTableModel modelFollow = (DefaultTableModel)tableFollow.getModel();

    for (int i = modelFollow.getRowCount() - 1; i >= 0; --i)
      modelFollow.removeRow(i);

    for (String s : tree.getFollowSet()) {
      Capteur c = tree.getCapteurExterieurFromId(s);
      if (c == null)
        c = tree.getCapteurInterieurFromId(s);
      modelFollow.addRow(new Object[] {c.getIdentifiant(),
                                       c.getType().toString(),
                                       Float.toString(c.getCurrentValue()),
                                       Long.toString(c.getFrequence())});
    }
  }

  public void sendInscriptionCapteur(String identifiant) {
    outputStream.println("InscriptionCapteur;" + identifiant);
    outputStream.flush();
  }

  public void sendDesinscriptionCapteur(String identifiant) {
    outputStream.println("DesinscriptionCapteur;" + identifiant);
    outputStream.flush();
  }

  public void disconnectFromServer() {
    this.timerSocket.cancel();
    connected = false;
    outputStream.println("DeconnexionVisu;" + identifiant);
    outputStream.flush();
    if (!recevoirAccuseDeconnexion()) {
      SimulationError sim =
          new SimulationError("La déconnexion s'est mal passée !");
      sim.show();
    }
  }

  private boolean recevoirAccuseConnexion() {
    String response = "";
    try {
      response = this.readSocket();
    } catch (IOException e) {
      SimulationError sim =
          new SimulationError("Impossible d'envoyer des données au serveur !");
      sim.show();
    }
    return response.contains("ConnexionOK");
  }

  private boolean recevoirAccuseDeconnexion() {
    String response = "";
    try {
      response = this.readSocket();
    } catch (IOException e) {
      SimulationError sim =
          new SimulationError("Impossible d'envoyer des données au serveur !");
      sim.show();
    }
    return response.contains("DeconnexionOK");
  }

  private String readSocket() throws IOException {
    byte[] rawMessage = new byte[512];
    int rawMessageSize = this.inputStream.read(rawMessage);
    String socketMessage = new String(rawMessage, 0, rawMessageSize);
    return socketMessage;
  }
}
