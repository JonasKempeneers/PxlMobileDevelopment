package mobiledevelopment.pxl.be.storyhunter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private BookListAdapter mAdapter;
    private BooksApi service;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        service = RetroFitInstance.getRetrofitInstance().create(BooksApi.class);

        /*Call the method with parameter in the interface to get the book data*/

        Intent previousIntent = getIntent();
        if(previousIntent.getBooleanExtra("placedBooks", true)){
            getPlacedBooks();
        } else {
            getFoundBooks();
        }

    }

    private void getFoundBooks() {
        Call<List<Book>> call = service.getFoundBooks();

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                generateBookList((ArrayList<Book>)response.body()) ;
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(BookListActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage());
            }
        });
    }

    private void getPlacedBooks(){
        Call<List<Book>> call = service.getPlacedBooks();

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                generateBookList((ArrayList<Book>)response.body()) ;
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(BookListActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage());
            }
        });
    }

    private void generateBookList(ArrayList<Book> bookArrayList) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new BookListAdapter(bookArrayList, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BookListActivity.this);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(mAdapter);
    }

}
