package capteurtype;

public enum CapteurInterieurType implements CapteurType {

  TEMPERATURE("Température", "°C", "#.0", 0.2f, 60000, -10f, 50f),
  HUMIDITE("Humidité", "%", "#", 0f, 90000, 0f, 100f),
  LUMINOSITE("Luminosité", "lum", "#.00", 0.01f, 5000, 0f, 1000f),
  VOLUMESONORE("Volume sonore", "dB", "#.0", 0.1f, 10000, 0f, 120f),
  CONSOMMATIONECLAIRAGE("Consommation éclairage", "W", "#", 1f, 30000, 0f,
                        3000f),
  EAUFROIDE("Eau froide", "l", "#.0", 0f, 600000, 0f, 100f),
  EAUCHAUDE("Eau chaude", "l", "#.0", 0f, 60000, 0f, 1000f);

  private String type;
  private String unite;
  private String precision;
  private float marge;
  private long frequence;
  private float min;
  private float max;

  private CapteurInterieurType(String type, String unite, String precision,
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

  public static CapteurInterieurType getTypeByName(String name) {
    for (CapteurInterieurType t : CapteurInterieurType.values())
      if (t.toString().equals(name))
        return t;
    return null;
  }

  public static String[] getValues() {
    CapteurInterieurType[] enumValues = CapteurInterieurType.values();
    String[] values = new String[enumValues.length];
    for (int i = 0; i < values.length; ++i)
      values[i] = enumValues[i].toString();
    return values;
  }
}
