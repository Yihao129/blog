package com.ascending.model;

import com.ascending.model.view.ModelView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ModelView.CommentLazy.class,ModelView.CommentEager.class,ModelView.PostEager.class})
    private int id;

    @Column(name = "content")
    @JsonView({ModelView.CommentLazy.class,ModelView.CommentEager.class,ModelView.PostEager.class})
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonView({ModelView.CommentEager.class})
    private Post post;

    public Comment(){};

    public Comment(int id, String content, Post post) {
        this.id = id;
        this.content = content;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}

