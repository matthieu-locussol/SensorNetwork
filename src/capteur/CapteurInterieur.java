package capteur;

import capteurtype.CapteurInterieurType;
import util.SimulationError;

public class CapteurInterieur
    extends Capteur implements Comparable<CapteurInterieur> {

  private String batiment;
  private int etage;
  private String salle;
  private String positionRelative;

  public CapteurInterieur(String identifiant, String date, float minValue,
                          float maxValue, CapteurInterieurType type,
                          String batiment, int etage, String salle,
                          String positionRelative) {
    super(identifiant, date, minValue, maxValue, type);
    this.batiment = batiment;
    this.etage = etage;
    this.salle = salle;
    this.positionRelative = positionRelative;
  }

  public String getBatiment() { return batiment; }

  public int getEtage() { return etage; }

  public String getSalle() { return salle; }

  public String getPositionRelative() { return positionRelative; }

  public void envoyerConnexionCapteur() {
    super.envoyerConnexionCapteur();
    if (super.readytosend) {
      super.outputStream.println("ConnexionCapteur;" + super.identifiant + ";" +
                                 super.type + ";" + this.batiment + ";" +
                                 this.etage + ";" + this.salle + ";" +
                                 this.positionRelative);
      super.outputStream.flush();

      if (super.recevoirAccuseConnexion()) {
        super.connected = true;
      } else {
        SimulationError sim =
            new SimulationError("La connexion au serveur a échouée !");
        sim.show();
      }
    }
  }

  @Override
  public int compareTo(CapteurInterieur o) {
    int value = 0;
    String bat = o.getBatiment();
    value = batiment.compareTo(bat);
    if (value == 0) {
      Integer eta = o.getEtage();
      value = Integer.valueOf(etage).compareTo(eta);
      if (value == 0) {
        String sal = o.getSalle();
        value = salle.compareTo(sal);
        if (value == 0) {
          String id = o.getIdentifiant();
          value = identifiant.compareTo(id);
        }
      }
    }
    return value;
  }

  public boolean equals(Object o) {
    boolean isEquals = false;
    if (o instanceof CapteurInterieur)
      isEquals = this.compareTo((CapteurInterieur)o) == 0;
    return isEquals;
  }
}
