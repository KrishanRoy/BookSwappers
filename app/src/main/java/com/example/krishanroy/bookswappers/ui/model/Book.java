package com.example.krishanroy.bookswappers.ui.model;

public class Book {
    private String title;
    private String author;
    private String bookImage;
    private String timeStamp;
    private String uploaderCity;
    private String uploaderName;
    private String uploaderEmail;

    public Book(String title, String author, String bookImage,String uploaderCity, String uploaderName, String uploaderEmail) {
        this.title = title;
        this.author = author;
        this.bookImage = bookImage;
        this.uploaderCity = uploaderCity;
        this.uploaderName = uploaderName;
        this.uploaderEmail = uploaderEmail;
    }

    public Book(String title, String author, String bookImage, String timeStamp) {
        this.title = title;
        this.author = author;
        this.bookImage = bookImage;
        this.timeStamp = timeStamp;
    }

    public Book(String title, String author, String bookImage) {
        this.title = title;
        this.author = author;
        this.bookImage = bookImage;
    }

    public Book() {
    }

    public String getUploaderCity() {
        return uploaderCity;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public String getUploaderEmail() {
        return uploaderEmail;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookImage() {
        return bookImage;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
