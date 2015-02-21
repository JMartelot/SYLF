package com.imie.sylf.entity;

import java.io.Serializable;

/**
 * Entity Author
 * 
 * @author Quentin
 * 
 */
public class Author implements Serializable {

	private int id;

	private String name;

	private String profil_picture;

	/**
	 * get Id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * set id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * get name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get profil
	 * @return
	 */
	public String getProfil_picture() {
		return profil_picture;
	}
	
	/**
	 * set profil picture
	 * @param profil_picture
	 */
	public void setProfil_picture(String profil_picture) {
		this.profil_picture = profil_picture;
	}

	/**
	 * Constructor Author
	 */
	public Author(int id, String name, String profil_picture) {
		super();
		this.id = id;
		this.name = name;
		this.profil_picture = profil_picture;
	}

	/**
	 * Default constructor Author
	 */
	public Author() {

	}

}
