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

    public Book(){}

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
}
