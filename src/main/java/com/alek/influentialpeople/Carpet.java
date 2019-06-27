package com.alek.influentialpeople;

import com.fasterxml.jackson.annotation.JsonView;

public class Carpet {
	
	@JsonView(Views.Public.class)
	private int value;
	@JsonView(Views.Public.class)
	private String material;
	@JsonView(Views.Public.class)
	private int lengthInCm;
	private int widthInCm;
	
	public Carpet() {
		super();
	}

	public Carpet(int value, String material, int lengthInCm, int widthInCm) {
		super(); 
		this.value = value;
		this.material = material;
		this.lengthInCm = lengthInCm;
		this.widthInCm = widthInCm;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public int getLengthInCm() {
		return lengthInCm;
	}

	public void setLengthInCm(int lengthInCm) {
		this.lengthInCm = lengthInCm;
	}

	public int getWidthInCm() {
		return widthInCm;
	}

	public void setWidthInCm(int widthInCm) {
		this.widthInCm = widthInCm;
	}

	@Override
	public String toString() {
		return String.format("Carpet [value=%s, material=%s, lengthInCm=%s, widthInCm=%s]", value, material, lengthInCm,
				widthInCm);
	}
	
}
