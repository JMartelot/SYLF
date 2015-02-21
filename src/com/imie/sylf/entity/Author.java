package com.imie.sylf.entity;

import java.io.Serializable;

/**
 * Entity Author
 * 
 * @author Jean
 *
 */
public class Author implements Serializable {
    
    /**
     * Author identifiant
     */
    private int id;
    
    /**
     * Author name
     */
    private String name;
    
    /**
     * Profil picture of the author
     */
    private String profil_picture;

    /**
     * Getter for the author id
     * @return id 
     */
    public int getId() {
        return id;
    }
    /**
     * Setter for the author id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Getter for the author name
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     * Setter for the author name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter for the profil picture author
     * @return
     */
    public String getProfil_picture() {
        return profil_picture;
    }

    /**
     * Setter for the profil picture author
     * @param profil_picture
     */
    public void setProfil_picture(String profil_picture) {
        this.profil_picture = profil_picture;
    }
    
    /**
     * Constructor with all the attributes
     * 
     * @param id
     * @param name
     * @param profil_picture
     */
    public Author(int id, String name, String profil_picture) {
        super();
        this.id = id;
        this.name = name;
        this.profil_picture = profil_picture;
    }
    
    /**
     * Empty constructor
     */
    public Author(){
        
    }
    
}
