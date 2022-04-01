package com.skilldistillery.filmquery.entities;

import java.util.Objects;

public class Actor {

	private int iD;
	private String firstName;
	private String lastName;

	public Actor() {
		super();
	}

	public Actor(int iD, String firstName, String lastName) {
		super();
		this.iD = iD;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return iD;
	}

	public void setId(int id) {
		this.iD = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirst_name(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLast_name(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Actor id: " + iD + ", First Name: " + firstName + ", Last Name: " + lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(iD);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return iD == other.iD;
	}

}
