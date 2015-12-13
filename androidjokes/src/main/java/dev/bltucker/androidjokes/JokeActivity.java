package dev.bltucker.androidjokes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    private static final String JOKE_EXTRA = "joke";

    public static void launch(Context context, String joke){

        Intent launchIntent = new Intent(context, JokeActivity.class);
        launchIntent.putExtra(JOKE_EXTRA, joke);

        context.startActivity(launchIntent);
    }

    private TextView jokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        jokeTextView = (TextView) findViewById(R.id.joke_text_view);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(JOKE_EXTRA)) {
            jokeTextView.setText(intent.getStringExtra(JOKE_EXTRA));
        }

    }
}
