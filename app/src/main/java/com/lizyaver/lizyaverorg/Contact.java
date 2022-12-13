package com.lizyaver.lizyaverorg;

public class Contact {
    private String name;
    private String email;
    private String content_details;
    private String imageURL;


    public Contact(String name, String email, String content_details, String imageURL) {
        this.name = name;
        this.email = email;
        this.content_details = content_details;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent_details() {
        return content_details;
    }

    public void setContent_details(String content_details) {
        this.content_details = content_details;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", content_details='" + content_details + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
