package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import dev.bltucker.builditbigger.backend.myApi.MyApi;

/**
 * Created by brett on 12/13/15.
 */
public final class JokeRetriever extends AsyncTask<Void, Void, String>{


    public static interface JokeRetrieverDelegate{
        void handleJoke(String joke);
    }

    private static String DEFAULT_JOKE = "A bug in the code is worth two in the documentation.";


    private JokeRetrieverDelegate delegate;

    public JokeRetriever(JokeRetrieverDelegate delegate){
        this.delegate = delegate;
    }


    public void removeDelegate(){
        this.delegate = null;
    }

    @Override
    protected String doInBackground(Void... params) {

        MyApi api = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                }).build();

        try{
            return api.tellJoke().execute().getData();
        } catch(Exception ex) {
            return DEFAULT_JOKE;
        }

    }


    @Override
    protected void onPostExecute(String s) {

        if(delegate != null){
            this.delegate.handleJoke(s);
        }

    }
}
