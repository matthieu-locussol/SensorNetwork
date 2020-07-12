package capteurtype;

public enum CapteurExterieurType implements CapteurType {

  TEMPERATURE("Température", "°C", "#.0", 0.2f, 60000, -10f, 50f),
  HUMIDITE("Humidité", "%", "#", 0f, 90000, 0f, 100f),
  LUMINOSITE("Luminosité", "lum", "#.00", 0.01f, 5000, 0f, 1000f),
  VITESSEVENT("Vitesse du vent", "km/h", "#.0", 0.3f, 20000, 0f, 30f),
  PRESSION("Pression atmosphérique", "hPa", "#.0", 0f, 5000, 1000f, 1100f);

  private String type;
  private String unite;
  private String precision;
  private float marge;
  private long frequence;
  private float min;
  private float max;

  private CapteurExterieurType(String type, String unite, String precision,
                               float marge, long frequence, float min,
                               float max) {
    this.type = type;
    this.unite = unite;
    this.precision = precision;
    this.marge = marge;
    this.frequence = frequence;
    this.min = min;
    this.max = max;
  }

  public String toString() { return this.type.toString(); }

  public String getUnite() { return this.unite; }

  public String getPrecision() { return precision; }

  public float getMarge() { return marge; }

  public long getDefaultFrequence() { return frequence; }

  public float getDefaultMin() { return min; }

  public float getDefaultMax() { return max; }

  public static CapteurExterieurType getTypeByName(String name) {
    for (CapteurExterieurType t : CapteurExterieurType.values())
      if (t.toString().equals(name))
        return t;
    return null;
  }

  public static String[] getValues() {
    CapteurExterieurType[] enumValues = CapteurExterieurType.values();
    String[] values = new String[enumValues.length];
    for (int i = 0; i < values.length; ++i)
      values[i] = enumValues[i].toString();
    return values;
  }
}
