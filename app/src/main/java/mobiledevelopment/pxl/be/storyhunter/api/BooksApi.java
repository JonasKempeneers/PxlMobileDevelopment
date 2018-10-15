package mobiledevelopment.pxl.be.storyhunter.api;

import java.util.List;

import mobiledevelopment.pxl.be.storyhunter.entities.Book;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BooksApi {

    @GET("bookList")
    public Call<List<Book>> getBooks();

    @GET("foundBooks")
    public Call<List<Book>> getFoundBooks();

    @POST("foundBooks")
    public Call<Book> postBook(@Body Book book);
}
