package com.example.android.finalproject;

public class Movie {

    private int _id;//the id if for the my own use and not the real id- its beed a check key to see if the
    // movie already existed or i made a new one
    private String title;//title
    private String body;
    private String url;


    public  Movie(int id, String title , String body , String url){
        this._id = id;
        this.title= title;
        this.body = body;
        this.url = url;
    }

    public Movie() {

    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String subject) {
        this.title = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}