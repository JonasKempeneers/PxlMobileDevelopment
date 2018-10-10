package mobiledevelopment.pxl.be.storyhunter.api;

import java.util.List;

import mobiledevelopment.pxl.be.storyhunter.entities.Book;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BooksApi {

    @GET("books")
    public Call<List<Book>> getBooks();

    @GET("foundBooks")
    public Call<List<Book>> getFoundBooks();
}
