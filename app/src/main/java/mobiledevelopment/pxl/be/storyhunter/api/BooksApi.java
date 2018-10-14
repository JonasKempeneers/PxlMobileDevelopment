package mobiledevelopment.pxl.be.storyhunter.api;

import java.util.List;

import mobiledevelopment.pxl.be.storyhunter.entities.BookList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BooksApi {

    @GET("bookList")
    public Call<BookList> getBooks();

    @GET("foundBooks")
    public Call<BookList> getFoundBooks();
}
