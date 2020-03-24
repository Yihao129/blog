package com.ascending.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="register_date")
    private LocalDateTime register_date;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Post> posts;

    public Author(){};

    public Author(int id, String name, String email, LocalDateTime register_date, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.register_date = register_date;
        this.posts = posts;
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

    public LocalDateTime getRegister_date() {
        return register_date;
    }

    public void setRegister_date(LocalDateTime register_date) {
        this.register_date = register_date;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", register_date=" + register_date +
                ", posts=" + posts.hashCode() +
                '}';
    }
}
