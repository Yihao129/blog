package com.ascending.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resource")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "s3key")
    private String s3key;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    public Resource(User user, String fileName, String s3key, LocalDateTime creationTime) {
        this.user = user;
        this.fileName = fileName;
        this.s3key = s3key;
        this.creationTime = creationTime;
    }

    public Resource(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getS3key() {
        return s3key;
    }

    public void setS3key(String s3key) {
        this.s3key = s3key;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", fileName='" + fileName + '\'' +
                ", s3key='" + s3key + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
