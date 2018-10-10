package mobiledevelopment.pxl.be.storyhunter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import mobiledevelopment.pxl.be.storyhunter.api.BooksApi;
import mobiledevelopment.pxl.be.storyhunter.entities.Book;
import mobiledevelopment.pxl.be.storyhunter.repositories.BookRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public Button colorButton;
    public TextView testTextView;
    private BookRepository bookRepository = new BookRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testTextView = findViewById(R.id.testTextView);

        GetAllBooks();
    }

    public void goToMenu(View view) {
        Intent intent = new Intent(MainActivity.this, BookListActivity.class);
        startActivity(intent);
    }

    public void navigateToMap(View view){
        Intent intent = new Intent (MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    public void changeColor(View view) {
        colorButton = findViewById(R.id.button);
        ColorDrawable buttonColor = (ColorDrawable) colorButton.getBackground();

        if(buttonColor.getColor() == Color.BLUE){
            colorButton.setBackgroundColor(Color.WHITE);
        } else {
            colorButton.setBackgroundColor(Color.BLUE);
        }
    }

    private void GetAllBooks(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://my-json-server.typicode.com/JonasKempeneers/FakeApi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BooksApi booksApi = retrofit.create(BooksApi.class);

        Call<List<Book>> call = booksApi.getBooks();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(!response.isSuccessful()){

                    testTextView.setText("Code: " + response.code());
                    return;
                }

                List<Book> bookList = response.body();

                for (Book book : bookList){
                    String content = "";
                    content += "Id: " + book.getId() + "\n";
                    content += "Author " + book.getAuthor() + "\n";
                    content += "Title " + book.getTitle() + "\n";

                    testTextView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                testTextView.setText(t.getMessage());
            }
        });
    }
}
