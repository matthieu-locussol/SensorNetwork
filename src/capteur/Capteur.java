package capteur;

import capteurtype.CapteurType;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import util.SimulationError;


public abstract class Capteur {

  private Socket socket;
  protected PrintWriter outputStream;
  protected BufferedInputStream inputStream;

  protected String ip;
  protected int port;
  protected String identifiant;
  private String date;
  private float currentValue;
  private float minValue;
  private float maxValue;
  private long frequence;
  protected CapteurType type;
  protected boolean connected;
  protected boolean readytosend;
  private boolean randomValue;

  private Timer timer;

  public Capteur(String identifiant, String date, float minValue,
                 float maxValue, CapteurType type) {
    this.ip = "0.0.0.0";
    this.port = 0;
    this.identifiant = identifiant;
    this.date = date;
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.type = type;
    this.frequence = this.type.getDefaultFrequence();
    this.connected = false;
    this.readytosend = false;
    this.randomValue = true;
    this.timer = new Timer();
    this.initializeTimerTask();
  }

  public CapteurType getType() { return type; }

  public String getIdentifiant() { return identifiant; }

  public String getDate() { return date; }

  public float getCurrentValue() { return currentValue; }

  public float getMinValue() { return minValue; }

  public float getMaxValue() { return maxValue; }

  public long getFrequence() { return frequence; }

  public boolean isConnected() { return connected; }

  public String getIp() { return ip; }

  public void setIp(String ip) { this.ip = ip; }

  public int getPort() { return port; }

  public void setPort(int port) { this.port = port; }

  public void setValue(float value) { this.currentValue = value; }

  public void setFrequence(long frequence) {
    this.frequence = frequence;
    this.initializeTimerTask();
  }

  public void setConnected(boolean connected) { this.connected = connected; }

  public boolean isRandomValue() { return randomValue; }

  public void setRandomValue(boolean randomValue) {
    this.randomValue = randomValue;
  }

  public void envoyerConnexionCapteur() {
    try {
      this.socket = new Socket(this.ip, this.port);

      try {
        this.outputStream = new PrintWriter(this.socket.getOutputStream());
        this.inputStream =
            new BufferedInputStream(this.socket.getInputStream());
        this.readytosend = true;
      } catch (IOException e) {
        this.readytosend = false;
        SimulationError sim = new SimulationError(
            "Impossible d'envoyer des données au serveur !");
        sim.show();
      }

    } catch (UnknownHostException e) {
      SimulationError sim = new SimulationError(
          "Impossible de se connecter à " + ip + ":" + port + " !");
      sim.show();
    } catch (IOException e) {
      SimulationError sim =
          new SimulationError("Impossible d'envoyer des données au serveur !");
      sim.show();
    }
  }

  public void envoyerValeurCapteur() {
    if (this.connected) {

      if (this.randomValue) {
        DecimalFormat df = new DecimalFormat(this.getType().getPrecision());
        float randomValue =
            (float)(Math.random() * (this.maxValue - this.minValue)) +
            this.minValue;
        String textValue = df.format(randomValue).replace(',', '.');
        this.currentValue = Float.parseFloat(textValue);
      }

      this.outputStream.println("ValeurCapteur;" + this.currentValue);
      this.outputStream.flush();
    }
  }

  public void envoyerDeconnexionCapteur() {
    this.readytosend = false;
    this.connected = false;
    this.outputStream.println("DeconnexionCapteur;" + identifiant);
    this.outputStream.flush();
    if (!this.recevoirAccuseDeconnexion()) {
      SimulationError sim =
          new SimulationError("La déconnexion au serveur s'est mal déroulée !");
      sim.show();
    }
  }

  public String toString() { return identifiant; }

  protected boolean recevoirAccuseConnexion() {
    String response = "";
    try {
      response = this.readSocket();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return response.contains("ConnexionOK");
  }

  protected boolean recevoirAccuseDeconnexion() {
    String response = "";
    try {
      response = this.readSocket();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return response.contains("DeconnexionOK");
  }

  protected String readSocket() throws IOException {
    byte[] rawMessage = new byte[512];
    int rawMessageSize = this.inputStream.read(rawMessage);
    String socketMessage = new String(rawMessage, 0, rawMessageSize);
    return socketMessage;
  }

  public boolean isValidValue(Float value) {
    return value >= this.minValue && value <= this.maxValue;
  }

  private void initializeTimerTask() {
    this.timer.cancel();
    this.timer = new Timer();
    this.timer.schedule(new TimerTask() {
      public void run() { envoyerValeurCapteur(); }
    }, this.frequence, this.frequence);
  }
}
