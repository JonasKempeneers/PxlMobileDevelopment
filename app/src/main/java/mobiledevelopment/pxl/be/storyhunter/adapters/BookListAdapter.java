package mobiledevelopment.pxl.be.storyhunter.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mobiledevelopment.pxl.be.storyhunter.R;
import mobiledevelopment.pxl.be.storyhunter.entities.Book;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListViewHolder> {

    private List<Book> booksList;

    public class BookListViewHolder extends RecyclerView.ViewHolder {
        public TextView title, author, isbn;

        public BookListViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            author = (TextView) view.findViewById(R.id.author);
            isbn = (TextView) view.findViewById(R.id.isbn);
        }
    }

    public BookListAdapter(List<Book> booksList) {
        this.booksList = booksList;
    }

    @Override
    @NonNull
    public BookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_book_list_list_item, parent, false);

        return new BookListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListViewHolder holder, int position) {
        Book book = booksList.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.isbn.setText(book.getIsbn());
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

}
