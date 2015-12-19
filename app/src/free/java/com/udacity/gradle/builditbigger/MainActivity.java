package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.ads.AdRequest;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;

import dev.bltucker.androidjokes.JokeActivity;


public class MainActivity extends AppCompatActivity implements JokeRetriever.JokeRetrieverDelegate{

    private JokeRetriever jokeRetriever;

    private ProgressSpinnerFragment spinnerFragment;

    private InterstitialAd interstitialAd;

    private String latestJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerFragment = ProgressSpinnerFragment.newInstance();

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                JokeActivity.launch(MainActivity.this, MainActivity.this.latestJoke);
            }
        });

        requestInterstitialAd();

    }


    private void requestInterstitialAd(){
        com.google.android.gms.ads.AdRequest adRequest = new com.google.android.gms.ads.AdRequest.Builder()
                .addTestDevice(com.google.android.gms.ads.AdRequest.DEVICE_ID_EMULATOR)
                .build();

        interstitialAd.loadAd(adRequest);

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

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_activity_frame_layout, spinnerFragment)
                .commit();

        jokeRetriever = new JokeRetriever(this);
        jokeRetriever.execute();

    }


    @Override
    public void handleJoke(String joke) {

        this.jokeRetriever = null;
        this.latestJoke = joke;

        getSupportFragmentManager()
                .beginTransaction()
                .remove(spinnerFragment)
                .commit();

        if(interstitialAd.isLoaded()){
            interstitialAd.show();
        } else {
            JokeActivity.launch(this, this.latestJoke);
        }
    }
}
