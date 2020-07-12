package capteur;

import capteurtype.CapteurExterieurType;
import util.SimulationError;

public class CapteurExterieur
    extends Capteur implements Comparable<CapteurExterieur> {

  private float latitude;
  private float longitude;

  public CapteurExterieur(String identifiant, String date, float minValue,
                          float maxValue, CapteurExterieurType type,
                          float latitude, float longitude) {
    super(identifiant, date, minValue, maxValue, type);
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public float getLatitude() { return latitude; }

  public float getLongitude() { return longitude; }

  public void envoyerConnexionCapteur() {
    super.envoyerConnexionCapteur();
    if (super.readytosend) {
      super.outputStream.println("ConnexionCapteur;" + super.identifiant + ";" +
                                 super.type + ";" + this.latitude + ";" +
                                 this.longitude);
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

  public int compareTo(CapteurExterieur o) {
    int value = 0;
    Float lat = o.getLatitude();
    value = Float.valueOf(latitude).compareTo(lat);
    if (value == 0) {
      Float longi = o.getLongitude();
      value = Float.valueOf(longitude).compareTo(longi);
    }
    return value;
  }

  public boolean equals(Object o) {
    int isEquals = -1;
    if (o instanceof CapteurExterieur)
      isEquals = identifiant.compareTo(((CapteurExterieur)o).getIdentifiant());
    return isEquals == 0;
  }
}
