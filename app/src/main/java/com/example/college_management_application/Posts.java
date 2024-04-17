package com.example.college_management_application;

public class Posts {
    private int postId;
    private String postData;
    private String photoLink;

    // Empty constructor
    public Posts() {
    }

    // Constructor with postId, postData, and photoLink
    public Posts(int postId, String postData, String photoLink) {
        this.postId = postId;
        this.postData = postData;
        this.photoLink = photoLink;
    }

    // Getter and setter methods for postId, postData, and photoLink
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }
}

