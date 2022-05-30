package com.labs.day10;

public class Employee {
	int id;
	/**
	 * @return the id
	 */
	private int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	private void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	private String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	private void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the designation
	 */
	private String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	private void setDesignation(String designation) {
		this.designation = designation;
	}

	String name;
	String designation;

	public static void main(String[] args) {
	
	}

}
