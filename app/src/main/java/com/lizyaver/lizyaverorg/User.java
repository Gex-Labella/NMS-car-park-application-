package com.lizyaver.lizyaverorg;

public class User {

    private String fullnames, email, phonenumber, password;

    public User(String fullnames, String email, String phonenumber, String password) {
        this.fullnames = fullnames;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(){

    }


    public String getFullnames() {
        return fullnames;
    }

    public void setFullnames(String fullnames) {
        this.fullnames = fullnames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
