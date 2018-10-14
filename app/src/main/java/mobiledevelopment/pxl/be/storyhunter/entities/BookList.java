package mobiledevelopment.pxl.be.storyhunter.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookList {

    @SerializedName("bookList")
    private ArrayList<Book> bookList;

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }
}
