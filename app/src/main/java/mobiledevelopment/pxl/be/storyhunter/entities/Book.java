package mobiledevelopment.pxl.be.storyhunter.entities;

import com.google.gson.annotations.SerializedName;


public class Book {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("author")
    private String author;
    @SerializedName("language")
    private String language;
    @SerializedName("isbn")
    private String isbn;
    @SerializedName("dateFound")
    private String dateFound;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;

    public Book(){}

    public Book(String title, String author, String language, String isbn) {
        this.title = title;
        this.author = author;
        this.language = language;
        this.isbn = isbn;
    }

    public Book(int id, String title, String author, String language, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.language = language;
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDateFound() {
        return dateFound;
    }

    public void setDateFound(String dateFound) {
        this.dateFound = dateFound;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
