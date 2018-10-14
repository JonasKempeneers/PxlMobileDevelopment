package mobiledevelopment.pxl.be.storyhunter.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mobiledevelopment.pxl.be.storyhunter.R;
import mobiledevelopment.pxl.be.storyhunter.entities.Book;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListViewHolder> {

    private ArrayList<Book> dataList;

    public BookListAdapter(ArrayList<Book> dataList) {
        this.dataList = dataList;
    }

    @Override
    public BookListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_book_list_list_item, parent, false);
        return new BookListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookListViewHolder holder, int position) {
        holder.title.setText(dataList.get(position).getTitle());
        holder.author.setText(dataList.get(position).getAuthor());
        holder.isbn.setText(dataList.get(position).getIsbn());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class BookListViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView author;
        TextView isbn;

        public BookListViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.author = (TextView) itemView.findViewById(R.id.author);
            this.isbn = (TextView) itemView.findViewById(R.id.isbn);
        }
    }
}

