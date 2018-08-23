package appsbyjimmy.com.booktestapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v4.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import android.util.Log;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeduardo5 on 8/22/18.
 */

public class BooksFragment extends Fragment {
    private List<Book> books;
    private BookRecyclerViewAdapter adapter;
    private String jsonGet;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        books = new ArrayList<Book>();
        new AsyncGetBooks().execute();
        View v = inflater.inflate(R.layout.book_fragment_list, container, false);

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.list);

        adapter = new BookRecyclerViewAdapter(books);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return v;


    }

    public void update() {
        adapter.notifyDataSetChanged();
    }

    public void delete(int position) {
        books.remove(position);
        adapter.notifyItemRemoved(position);
    }

//    public void populate(){
//     new AsyncGetBooks().execute();
//    }

    private class AsyncGetBooks extends AsyncTask<Void, Void, String> {
        String jsonUrl = "http://de-coding-test.s3.amazonaws.com/books.json\n";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL(jsonUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((jsonGet = bufferedReader.readLine()) != null) {
                    stringBuffer.append(jsonGet + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuffer.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e("BooksFragment", result);
            JSONArray booksJsonArray;
            try {
                booksJsonArray = new JSONArray(result);

                for (int i = 0; i < booksJsonArray.length(); i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = booksJsonArray.getJSONObject(i);
                        String name = jsonObject.getString("title");
                        String author = jsonObject.getString("author");
                        String imgURL = jsonObject.getString("imageURL");
                        Book book = new Book(name, author, imgURL);


                        //add books to books list
                        books.add(book);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
