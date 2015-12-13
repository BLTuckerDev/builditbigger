package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import dev.bltucker.androidjokes.JokeActivity;


public class MainActivity extends AppCompatActivity implements JokeRetriever.JokeRetrieverDelegate{

    private JokeRetriever jokeRetriever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(jokeRetriever != null){
            this.jokeRetriever.removeDelegate();
        }
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

    public void onTellJokeClick(View view){
        //start the async task here.
        if(jokeRetriever != null){
            return;
        }

        jokeRetriever = new JokeRetriever(this);
        jokeRetriever.execute();

    }


    @Override
    public void handleJoke(String joke) {

        this.jokeRetriever = null;

        JokeActivity.launch(this, joke);
    }
}
