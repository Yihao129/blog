package com.assending.model;

import java.sql.Date;

public class Post {

    private int id;
    private String author;
    private String content;
    private Date date;
    private int author_id;

    public Post(){};

    public Post(int id, String author, String content, Date date, int author_id) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.date = date;
        this.author_id = author_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", author_id=" + author_id +
                '}';
    }
}
