package com.animalsinzoo;

public class Animal {
	
	private int animalID = 0;
	private String animalSpecies = null;
	private String animalName = null;
	private int animalAge = 0;
	private Double animalWeight = 0.0;
	
	public Animal() {
		super();
	}
	
	public int getAnimalID() {
		return animalID;
	}

	public void setAnimalID(int animalID) {
		this.animalID = animalID;
	}

	public String getAnimalSpecies() {
		return animalSpecies;
	}

	public void setAnimalSpecies(String animalSpecies) {
		this.animalSpecies = animalSpecies;
	}

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public int getAnimalAge() {
		return animalAge;
	}

	public void setAnimalAge(int animalAge) {
		this.animalAge = animalAge;
	}

	public Double getAnimalWeight() {
		return animalWeight;
	}

	public void setAnimalWeight(Double animalWeight) {
		this.animalWeight = animalWeight;
	}

	@Override
	public String toString() {
		return "animal [animalID=" + animalID + ", animalSpecies=" + animalSpecies + ", animalName=" + animalName
				+ ", animalAge=" + animalAge + ", animalWeight=" + animalWeight + "]";
	}
	
	

} // class
