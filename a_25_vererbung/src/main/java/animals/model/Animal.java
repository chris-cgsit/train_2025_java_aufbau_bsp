/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

package animals.model;

/**
 * Klasse für ein (Zoo-)Tier mit mehreren Eigenschaften wie Name, Lateinischer
 * Name oder Gewicht
 * 
 * @author Michaela
 *
 */
public class Animal {
	private String name, latinName;

	private int weight;

	private boolean herbivore;

	public Animal(String name, String lateinischerName, int gewicht, boolean pflanzenfresser) {
		this.name = name;
		this.latinName = lateinischerName;
		this.weight = gewicht;
		this.herbivore = pflanzenfresser;
	}

	public String getName() {
		return name;
	}

	public String getLatinName() {
		return latinName;
	}

	public int getWeight() {
		return weight;
	}

	public boolean isHerbivore() {
		return herbivore;
	}

	@Override
	public String toString() {
		return String.format("%s, %s - %d kg - Vegetarier: %s",
				name, latinName, weight, herbivore ? "ja" : "nein");
	}

}
