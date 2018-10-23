package mobiledevelopment.pxl.be.storyhunter.api;

import java.util.List;

import mobiledevelopment.pxl.be.storyhunter.entities.Book;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BooksApi {

    @GET("bookList")
    public Call<List<Book>> getPlacedBooks();

    @GET("foundBooks")
    public Call<List<Book>> getFoundBooks();

    @GET("booksInLocationRadius")
    public Call<List<Book>> getBooksInLocationRadius();

    @POST("foundBooks")
    public Call<Book> postBook(@Body Book book);
}
