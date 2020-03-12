package com.assending.model;

import java.util.Date;

public class Author {
    private int id;
    private String name;
    private String email;
    private Date register_date;

    public Author(){};

    public Author(int id, String name, String email, Date register_date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.register_date = register_date;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", register_date=" + register_date +
                '}';
    }
}
