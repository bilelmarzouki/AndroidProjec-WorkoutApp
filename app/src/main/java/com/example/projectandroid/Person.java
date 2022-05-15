package com.example.projectandroid;

public class Person {

    private String name;
    private String mail;
    private String type;
    private String password;
    private String photo;


    public Person(String name, String mail, String type, String photo, String password) {

        this.name = name;
        this.mail = mail;
        this.type = type;
        this.photo = photo;
        this.password = password;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getType() {
        return type;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPassword() {
        return password;
    }
}
