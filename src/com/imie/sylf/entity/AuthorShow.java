package com.imie.sylf.entity;

import java.io.Serializable;

public class AuthorShow implements Serializable {

    private int id;
    
    private int id_author;
    
    private int id_show;
    
    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public int getId_author() {
        return id_author;
    }



    public void setId_author(int id_author) {
        this.id_author = id_author;
    }



    public int getId_show() {
        return id_show;
    }



    public void setId_show(int id_show) {
        this.id_show = id_show;
    }

    public AuthorShow(int id_author, int id_show) {
        super();
        this.id_author = id_author;
        this.id_show = id_show;
    }

    public AuthorShow(){
        
    }
    
}
