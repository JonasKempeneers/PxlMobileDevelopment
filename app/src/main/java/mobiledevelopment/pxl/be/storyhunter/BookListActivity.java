package mobiledevelopment.pxl.be.storyhunter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import mobiledevelopment.pxl.be.storyhunter.adapters.SimpleItemRecyclerViewAdapter;
import mobiledevelopment.pxl.be.storyhunter.api.BooksApi;
import mobiledevelopment.pxl.be.storyhunter.api.RetroFitInstance;
import mobiledevelopment.pxl.be.storyhunter.entities.Book;
import mobiledevelopment.pxl.be.storyhunter.helpers.DbHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Books. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link BookDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class BookListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private BooksApi service;
    private DbHelper db;
    private RecyclerView mRecyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get the retrofit instance of the book class
        service = RetroFitInstance.getRetrofitInstance().create(BooksApi.class);
        db = new DbHelper(this);

        if (findViewById(R.id.book_detail_container) != null) {
            // The detail container view will be present only in the
            // landscape layouts.
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

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
                ArrayList<Book> bookList = (ArrayList<Book>)response.body();

                generateBookList(bookList);

                for (Book book: bookList){
                    db.addBookToTable(Book.FOUNDBOOKS_TABLE_NAME, book);
                    Log.i("INFO", book.toString() + " added to foundBooks table");
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(BookListActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage());

                ArrayList<Book> bookList = db.getBookList(Book.FOUNDBOOKS_TABLE_NAME);

                if(bookList != null){
                    generateBookList(bookList);
                }
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
                ArrayList<Book> bookList = (ArrayList<Book>)response.body();

                generateBookList(bookList);

                for (Book book: bookList){
                    db.addBookToTable(Book.PLACEDBOOKS_TABLE_NAME, book);
                    Log.i("INFO", book.toString() + " added to placedBooks table");
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(BookListActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage());

                ArrayList<Book> bookList = db.getBookList(Book.PLACEDBOOKS_TABLE_NAME);

                if(bookList != null){
                    generateBookList(bookList);
                } else {
                    Log.w("WARN", "Booklist is empty");
                }
            }
        });
    }

    private void generateBookList(ArrayList<Book> bookArrayList) {

        mRecyclerView = (RecyclerView) findViewById(R.id.book_list);
        assert mRecyclerView != null;
        mRecyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, bookArrayList, mTwoPane));

    }



}
