package mobiledevelopment.pxl.be.storyhunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BookDetailActivity extends AppCompatActivity {

    TextView titleTextView;
    TextView authorTextView;
    TextView isbnTextView;
    TextView dateFoundTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        titleTextView = findViewById(R.id.title);
        authorTextView = findViewById(R.id.author);
        isbnTextView = findViewById(R.id.isbn);
        dateFoundTextView = findViewById(R.id.dateFound);

        Intent bookListIntent = getIntent();
        if(bookListIntent.hasExtra("title")){
            titleTextView.setText(bookListIntent.getStringExtra("title"));
        }
        if(bookListIntent.hasExtra("author")){
            authorTextView.setText(bookListIntent.getStringExtra("author"));
        }
        if(bookListIntent.hasExtra("isbn")){
            isbnTextView.setText(bookListIntent.getStringExtra("isbn"));
        }
        if(bookListIntent.hasExtra("dateFound")){
            dateFoundTextView.setText(bookListIntent.getStringExtra("dateFound"));
        }

    }
}
