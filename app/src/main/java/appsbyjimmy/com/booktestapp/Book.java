package appsbyjimmy.com.booktestapp;



import java.io.IOException;
import java.io.InputStream;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jeduardo5 on 8/22/18.
 */

public class Book {
    String title;
    String author;
    String imgURL;


    public Book(String title, String author, String imgURL){
        this.title = title;
        this.author = author;
        this.imgURL = imgURL;
    }

}
