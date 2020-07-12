package util;

import capteurtype.CapteurType;

public class Alert implements Comparable<Alert> {
	
	private CapteurType type;
	private float minValue;
	private float maxValue;
	
	public Alert(CapteurType type, float minValue, float maxValue) {
		this.type = type;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public CapteurType getType() {
		return type;
	}

	public float getMinValue() {
		return minValue;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public int compareTo(Alert o) {
		return type.toString().compareTo(o.getType().toString());
	}

}
