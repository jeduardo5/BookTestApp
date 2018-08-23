package appsbyjimmy.com.booktestapp;

import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;

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
            new AsyncGetImage(holder.mIconView).execute(books.get(position).imgURL);

//            holder.mIconView.setImageBitmap(books.get(position).img);

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
    private class AsyncGetImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imgView;

        public AsyncGetImage(ImageView imgView) {
            this.imgView = imgView;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }

        protected void onPostExecute(Bitmap result) {
            imgView.setImageBitmap(result);
        }
    }
    }