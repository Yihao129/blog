package com.ascending.model;

import com.ascending.model.view.ModelView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @JsonView({ModelView.AuthorEager.class,ModelView.PostEager.class,ModelView.PostLazy.class,ModelView.CommentEager.class})
    private int id;

    @Column(name="author")
    @JsonView({ModelView.AuthorEager.class,ModelView.PostEager.class,ModelView.PostLazy.class,ModelView.CommentEager.class})
    private String author;

    @Column(name="content")
    @JsonView({ModelView.AuthorEager.class,ModelView.PostEager.class,ModelView.PostLazy.class,ModelView.CommentEager.class})
    private String content;

    @Column(name="date")
    @JsonView({ModelView.AuthorEager.class, ModelView.PostEager.class,ModelView.PostLazy.class,ModelView.CommentEager.class})
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonView({ModelView.PostEager.class})
    private Author writer;

    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonView({ModelView.PostEager.class})
    private List<Comment> comments;

    public Post(){};

    public Post(int id, String author, String content, LocalDateTime date, Author writer, List<Comment> comments) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.date = date;
        this.writer = writer;
        this.comments = comments;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Author getWriter() {
        return writer;
    }

    public void setWriter(Author writer) {
        this.writer = writer;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", writer=" + writer +
                ", comments=" + comments.hashCode() +
                '}';
    }
}
