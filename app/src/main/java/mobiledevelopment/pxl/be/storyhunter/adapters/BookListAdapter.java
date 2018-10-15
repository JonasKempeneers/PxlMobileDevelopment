package mobiledevelopment.pxl.be.storyhunter.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mobiledevelopment.pxl.be.storyhunter.BookDetailActivity;
import mobiledevelopment.pxl.be.storyhunter.BookListActivity;
import mobiledevelopment.pxl.be.storyhunter.R;
import mobiledevelopment.pxl.be.storyhunter.entities.Book;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListViewHolder> {

    private ArrayList<Book> dataList;
    private Context context;

    public BookListAdapter(ArrayList<Book> dataList, Context context) {

        this.dataList = dataList;
        this.context = context;
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
        holder.dateFound.setText(dataList.get(position).getDateFound());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class BookListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        TextView author;
        TextView isbn;
        TextView dateFound;
        private Toast mToast;

        public BookListViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.author = (TextView) itemView.findViewById(R.id.author);
            this.isbn = (TextView) itemView.findViewById(R.id.isbn);
            this.dateFound = (TextView) itemView.findViewById(R.id.dateFound);

            itemView.setOnClickListener(this);
        }

        public void onClick(View view){
            Intent openDetailIntent = new Intent(context, BookDetailActivity.class);

            openDetailIntent.putExtra("title" ,title.getText());
            openDetailIntent.putExtra("author" ,author.getText());
            openDetailIntent.putExtra("isbn" ,isbn.getText());
            openDetailIntent.putExtra("dateFound" ,dateFound.getText());

            context.startActivity(openDetailIntent);
        }
    }
}

