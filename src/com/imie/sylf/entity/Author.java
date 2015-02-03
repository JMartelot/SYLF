package com.imie.sylf.entity;

import java.io.Serializable;

public class Author implements Serializable {

    private int id;
    
    private String name;
    
    private String profil_picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfil_picture() {
        return profil_picture;
    }

    public void setProfil_picture(String profil_picture) {
        this.profil_picture = profil_picture;
    }

    public Author(int id, String name, String profil_picture) {
        super();
        this.id = id;
        this.name = name;
        this.profil_picture = profil_picture;
    }
    
    public Author(){
        
    }
    
}
