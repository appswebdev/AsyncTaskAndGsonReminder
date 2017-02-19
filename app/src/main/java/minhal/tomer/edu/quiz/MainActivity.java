package minhal.tomer.edu.quiz;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ///Do In Background
        //Requires Internet Permission:
        //    <uses-permission android:name="android.permission.INTERNET"/>

        new AsyncTask<Void, Void, List<Movie>>() {
            List<Movie> movies;
            @Override
            protected void onPostExecute(List<Movie> movies) {
                //do work on Main UI Thread with movies.
            }
            @Override
            protected List<Movie> doInBackground(Void... voids) {
                try {
                    URL url = new URL("http://api.themoviedb.org/3/discover/movie?api_key=b3b1492d3e91e9f9403a2989f3031b0c&primary_release_year=2017&sort_by=popularity.desc");
                    URLConnection con = url.openConnection();
                    InputStream in = con.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    BufferedReader breader = new BufferedReader(reader);
                    String line = null;
                    StringBuilder builder = new StringBuilder();
                    while ((line = (breader.readLine())) != null) {
                        builder.append(line);
                    }
                    String json = builder.toString();
                    JSONObject result = new JSONObject(json);
                    JSONArray moviesArr = result.getJSONArray("results");
                    String newJson = moviesArr.toString();
                    Gson gson = new Gson();
                    Movie[] mArr = gson.fromJson(newJson, Movie[].class);
                    movies = Arrays.asList(mArr); //Convert an Array to a list
                    System.out.println(movies);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return movies;
            }
        }.execute();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
