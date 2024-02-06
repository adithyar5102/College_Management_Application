package com.example.college_management_application;

public class PlacementMail {
    private String mailId;
    private String company;
    private String description;
    private String photoLink;
    private String fileLink;

    private String Form_link;

    // Empty constructor
    public PlacementMail() {
    }

    // Constructor with mailId, company, description, photoLink, and fileLink
    public PlacementMail(String mailId, String company, String description, String photoLink, String fileLink,String Form_link) {
        this.mailId = mailId;
        this.company = company;
        this.description = description;
        this.photoLink = photoLink;
        this.fileLink = fileLink;
        this.Form_link = Form_link;
    }

    // Getter and setter methods for mailId, company, description, photoLink, and fileLink
    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
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
}

