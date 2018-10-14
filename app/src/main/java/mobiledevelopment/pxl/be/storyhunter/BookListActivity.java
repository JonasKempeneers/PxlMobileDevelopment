package mobiledevelopment.pxl.be.storyhunter;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import mobiledevelopment.pxl.be.storyhunter.adapters.BookListAdapter;
import mobiledevelopment.pxl.be.storyhunter.api.BooksApi;
import mobiledevelopment.pxl.be.storyhunter.api.RetroFitInstance;
import mobiledevelopment.pxl.be.storyhunter.entities.Book;
import mobiledevelopment.pxl.be.storyhunter.entities.BookList;
import mobiledevelopment.pxl.be.storyhunter.helpers.JSONArrayCursor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private BookListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        BooksApi service = RetroFitInstance.getRetrofitInstance().create(BooksApi.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<BookList> call = service.getBooks();

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<BookList>() {
            @Override
            public void onResponse(Call<BookList> call, Response<BookList> response) {
                generateBookList(response.body().getBookList());
            }

            @Override
            public void onFailure(Call<BookList> call, Throwable t) {
                Toast.makeText(BookListActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage());
            }
        });
    }

    private void generateBookList(ArrayList<Book> bookArrayList) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new BookListAdapter(bookArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BookListActivity.this);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(mAdapter);
    }

}
