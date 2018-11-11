package mobiledevelopment.pxl.be.storyhunter.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mobiledevelopment.pxl.be.storyhunter.BookDetailActivity;
import mobiledevelopment.pxl.be.storyhunter.BookDetailFragment;
import mobiledevelopment.pxl.be.storyhunter.BookListActivity;
import mobiledevelopment.pxl.be.storyhunter.R;
import mobiledevelopment.pxl.be.storyhunter.entities.Book;

public class SimpleItemRecyclerViewAdapter
        extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final BookListActivity mParentActivity;
        private final List<Book> mValues;
        private final boolean mTwoPane;
       private final boolean mIsPlacedBook;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book item = (Book) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(BookDetailFragment.BOOK_ID, Integer.toString(item.getId()));
                    BookDetailFragment fragment = new BookDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.book_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, BookDetailActivity.class);
                    intent.putExtra(BookDetailFragment.BOOK_ID, Integer.toString(item.getId()));
                    intent.putExtra("isPlacedBook", mIsPlacedBook);
                    context.startActivity(intent);
                }
            }
        };

        public SimpleItemRecyclerViewAdapter(BookListActivity parent,
                                             List<Book> items,
                                             boolean twoPane,
                                             boolean isPlacedBook) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
            mIsPlacedBook = isPlacedBook;
        }
    };

    public SimpleItemRecyclerViewAdapter(BookListActivity parent,
                                         List<Book> items,
                                         boolean twoPane) {
        mValues = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
           holder.mIdView.setText(Integer.toString(mValues.get(position).getId()));
            holder.mContentView.setText(mValues.get(position).getTitle());

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // holder.mIdView.setText(Integer.toString(mValues.get(position).getId()));
        holder.mContentView.setText(mValues.get(position).getTitle());

        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

        class ViewHolder extends RecyclerView.ViewHolder {
           final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}
