package mobiledevelopment.pxl.be.storyhunter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mobiledevelopment.pxl.be.storyhunter.adapters.BookListAdapter;
import mobiledevelopment.pxl.be.storyhunter.api.BooksApi;
import mobiledevelopment.pxl.be.storyhunter.entities.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookListActivity extends AppCompatActivity {
    private List<Book> bookList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private BookListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new BookListAdapter(bookList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        //booksTextview = findViewById(R.id.booksTextView);

        GetAllFoundBooks();
    }

    private void GetAllFoundBooks(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://my-json-server.typicode.com/JonasKempeneers/FakeApi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BooksApi booksApi = retrofit.create(BooksApi.class);

        Call<List<Book>> call = booksApi.getFoundBooks();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(!response.isSuccessful()){
                    return;
                }

                bookList = response.body();

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                //booksTextview.setText(t.getMessage());
            }
        });
    }
}
