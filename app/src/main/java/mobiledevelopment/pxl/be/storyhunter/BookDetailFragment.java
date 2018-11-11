package mobiledevelopment.pxl.be.storyhunter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mobiledevelopment.pxl.be.storyhunter.entities.Book;
import mobiledevelopment.pxl.be.storyhunter.helpers.DbHelper;


/**
 * A fragment representing a single Book detail screen.
 * This fragment is either contained in a {@link BookListActivity}
 * in two-pane mode (on tablets) or a {@link BookDetailActivity}
 * on handsets.
 */
public class BookDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String BOOK_ID = "item_id";

    private List<Book> mBooks;
    private Book mBook;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BookDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DbHelper db = new DbHelper(this.getContext());


        boolean isPlacedBook = getArguments().getBoolean("isPlacedBook");
        if(isPlacedBook){
            mBooks = db.getBookList(Book.PLACEDBOOKS_TABLE_NAME);
        } else {
            mBooks = db.getBookList(Book.FOUNDBOOKS_TABLE_NAME);
        }


        if (getArguments().containsKey(BOOK_ID)) {
            Bundle bundle = getArguments();
            for (Book book : mBooks) {
                if (Integer.toString(book.getId()).equals(bundle.get(BOOK_ID))) {
                    mBook = book;
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.book_detail, container, false);

        // Show the content as text in a TextView.
        if (mBook != null) {
            ((TextView) rootView.findViewById(R.id.book_detail)).setText(mBook.getTitle()+"\n"+mBook.getAuthor()+"\n"+mBook.getIsbn());
        }

        return rootView;
    }
}
