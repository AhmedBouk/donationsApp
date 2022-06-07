package com.nfs.democours;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.nfs.democours.beans.Quote;

public class QuoteActivity extends AppCompatActivity {

    private Quote quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        quote = (Quote) getIntent().getExtras().get("quote");
        ((EditText)findViewById(R.id.quote_string_input)).setText(quote.getQuote());
        ((RatingBar) findViewById(R.id.quote_rating_bar)).setRating(quote.getNotation());
    }

    public void updateQuote(View view){
        quote.setQuote( ((EditText)findViewById(R.id.quote_string_input)).getText().toString());
        quote.setNotation( ((RatingBar) findViewById(R.id.quote_rating_bar)).getNumStars());
        getIntent().putExtra("quote", quote);
        setResult(RESULT_OK, getIntent());
        finish();
    }
    public void cancel(View view){
        setResult(RESULT_CANCELED, getIntent());
        finish();
    }
    public void delete(View view){
        getIntent().putExtra("index", quote.getId());
        setResult(RESULT_FIRST_USER, getIntent());
        finish();
    }
}