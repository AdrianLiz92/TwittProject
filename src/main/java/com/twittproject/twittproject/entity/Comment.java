package com.twittproject.twittproject.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Comment {
    @Id
    private Long id;
    private Date create_date;
    private String text;
    private Timestamp modify_date;
    private Timestamp delete_date;
    @ManyToOne
    private User user_id;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getModify_date() {
        return modify_date;
    }

    public void setModify_date(Timestamp modify_date) {
        this.modify_date = modify_date;
    }

    public Timestamp getDelete_date() {
        return delete_date;
    }

    public void setDelete_date(Timestamp delete_date) {
        this.delete_date = delete_date;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Post getPost_id() {
        return post_id;
    }

    public void setPost_id(Post post_id) {
        this.post_id = post_id;
    }
}
