package com.example.college_management_application;

public class PlacementMail {
    private int mailId;
    private String company;
    private String description;
    private String photoLink;
    private String fileLink;

    private String Form_link;

    int yop;

    // Empty constructor
    public PlacementMail() {
    }

    // Constructor with mailId, company, description, photoLink, and fileLink
    public PlacementMail(int mailId, String company, String description, String photoLink, String fileLink,String Form_link,int yop) {
        this.mailId = mailId;
        this.company = company;
        this.description = description;
        this.photoLink = photoLink;
        this.fileLink = fileLink;
        this.Form_link = Form_link;
        this.yop = yop;
    }

    // Getter and setter methods for mailId, company, description, photoLink, and fileLink
    public int getMailId() {
        return mailId;
    }

    public void setMailId(int mailId) {
        this.mailId = mailId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public String getForm_link() {
        return Form_link;
    }

    public void setForm_link(String form_link) {
        Form_link = form_link;
    }
    public int getYop() {
        return yop;
    }

    public void setYop(int yop) {
        this.yop = yop;
    }
}

