package com.labs.day5;

// Encapsulation is achieved through access modifiers
class Encapsulation {

	private String id;
	private String name;
	private int rollNo;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rollNo
	 */
	public int getRollNo() {
		return rollNo;
	}

	/**
	 * @param rollNo the rollNo to set
	 */
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

}

public class TestEncapsulation {

	public static void main(String[] args) {
		Encapsulation obj1 = new Encapsulation();
		// setting the values
		obj1.setName("Stud1");
		obj1.setRollNo(5);
		obj1.setId("A");

		// Displaying values of the variables
		System.out.println(" name: " + obj1.getName());
		System.out.println(" id: " + obj1.getId());
		System.out.println(" roll: " + obj1.getRollNo());

		// Direct access of rollNo is not possible
		// due to encapsulation
		// System.out.println(" roll: " +
		// obj1.rollNo);
	}

}
