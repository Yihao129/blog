package com.assending.model;

public class Comment {
    private int id;
    private String content;
    private int post_id;

    public Comment(){};

    public Comment(int id, String content, int post_id) {
        this.id = id;
        this.content = content;
        this.post_id = post_id;
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

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    @Override
    public String toString() {
        return "comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", post_id=" + post_id +
                '}';
    }
}

