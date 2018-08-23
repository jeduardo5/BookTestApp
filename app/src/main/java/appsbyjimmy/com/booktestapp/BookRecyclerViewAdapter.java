package appsbyjimmy.com.booktestapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;



import java.util.List;

/**
 * Created by jeduardo5 on 8/22/18.
 */

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.Holder> {
        private List<Book> books;

        public BookRecyclerViewAdapter(List<Book> options) {
            books = options;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_fragment_item, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(final Holder holder, int position) {
            holder.mItem = books.get(position);
            holder.mTitleView.setText(books.get(position).title);
            holder.mAuthorView.setText(books.get(position).author);
        }

        @Override
        public int getItemCount() {
            return books.size();
        }

        public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public final View mView;
            public final ImageView mIconView;
            public final TextView mTitleView;
            public final TextView mAuthorView;

            public Book mItem;

            public Holder(View view) {
                super(view);
                mView = view;
                mIconView = (ImageView) view.findViewById(R.id.icon);
                mTitleView = (TextView) view.findViewById(R.id.title);
                mAuthorView = (TextView) view.findViewById(R.id.author);
                mView.setOnClickListener(this);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTitleView.getText() + "'";
            }

            public void onClick(View v){

            }
        }
    }