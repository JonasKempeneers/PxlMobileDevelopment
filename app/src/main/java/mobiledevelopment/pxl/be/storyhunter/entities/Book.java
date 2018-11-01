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
    @SerializedName("hint")
    private String hint;

    //Sqlite values
    public static final String FOUNDBOOKS_TABLE_NAME = "foundBooks";
    public static final String PLACEDBOOKS_TABLE_NAME = "placedBooks";

    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_LANGUAGE = "language";
    public static final String COLUMN_ISBN = "isbn";;
    public static final String COLUMN_DATEFOUND = "dateFound";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_HINT = "hint";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    public static final String CREATE_FOUNDBOOKS_TABLE =
            "CREATE TABLE " + FOUNDBOOKS_TABLE_NAME + "("
                    + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_AUTHOR + " TEXT,"
                    + COLUMN_LANGUAGE + " TEXT,"
                    + COLUMN_ISBN + " TEXT,"
                    + COLUMN_DATEFOUND + " TEXT,"
                    + COLUMN_LATITUDE + " DOUBLE,"
                    + COLUMN_LONGITUDE + " DOUBLE,"
                    + COLUMN_HINT + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public static final String CREATE_PLACEDBOOKS_TABLE =
            "CREATE TABLE " + PLACEDBOOKS_TABLE_NAME + "("
                    + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_AUTHOR + " TEXT,"
                    + COLUMN_LANGUAGE + " TEXT,"
                    + COLUMN_ISBN + " TEXT,"
                    + COLUMN_DATEFOUND + " TEXT,"
                    + COLUMN_LATITUDE + " DOUBLE,"
                    + COLUMN_LONGITUDE + " DOUBLE,"
                    + COLUMN_HINT + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

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

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
