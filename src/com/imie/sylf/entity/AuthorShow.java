package com.imie.sylf.entity;

import java.io.Serializable;

/**
 * Entity which map a show with their authro
 * 
 * @author Jean
 *
 */
public class AuthorShow implements Serializable {
    
    /**
     * Author_Show id
     */
    private int id;
    /**
     * Author id
     */
    private int id_author;
    /**
     * Show id
     */
    private int id_show;
    
    /**
     * Getter for the id of the relation
     * @return
     */
    public int getId() {
        return id;
    }
    /**
     * Setter for the id of the relation
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Getter for the author id 
     */
    public int getId_author() {
        return id_author;
    }
    /**
     * Setter for the author id
     * @param id_author
     */
    public void setId_author(int id_author) {
        this.id_author = id_author;
    }
    /**
     * Getter for the show id
     * @return
     */
    public int getId_show() {
        return id_show;
    }
    /**
     * Setter for the id show
     * @param id_show
     */
    public void setId_show(int id_show) {
        this.id_show = id_show;
    }
    /**
     * Construcot with all the attributes of the relation
     * 
     * @param id_author
     * @param id_show
     */
    public AuthorShow(int id_author, int id_show) {
        super();
        this.id_author = id_author;
        this.id_show = id_show;
    }
    
    /**
     * Empty constructor
     */
    public AuthorShow(){
        
    }
    
}
