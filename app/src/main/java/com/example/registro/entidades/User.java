package com.example.registro.entidades;

public class User {
    private Integer document;
    private String user;
    private String name;
    private String lastName;
    private String password;

    public User(){

    }

    public User(Integer document, String user, String name, String lastName, String password) {
        this.document = document;
        this.user = user;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
    }

    public Integer getDocument() {
        return document;
    }

    public void setDocument(Integer document) {
        this.document = document;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    @Override
    public String toString() {
        return "User{" +
                "document=" + document +
                ", user='" + user + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
